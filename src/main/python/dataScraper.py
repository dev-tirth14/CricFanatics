import requests
from bs4 import BeautifulSoup
import requests
import json
from datetime import datetime
import pytz

def convert_date_format(original_date_str):
    original_date = datetime.strptime(original_date_str, '%m/%d/%YT%H:%M:%S%z')
    utc_date = original_date.astimezone(pytz.utc)
    new_date_str = utc_date.strftime('%Y-%m-%dT%H:%M:%S.%fZ')
    new_date_str = new_date_str[:-4] + 'Z'
    return new_date_str

def getTeams():
    teamsURL='https://www.icc-cricket.com/tournaments/t20cricketworldcup/teams'
    response=requests.get(teamsURL)
    soup = BeautifulSoup(response.content, "html.parser")
    
    allTeamHTML=soup.find("div",class_="TeamListEvent_team-list-event__yZXRO").contents[0]

    teamsData=[]

    for teamHTML in allTeamHTML.contents:
        teamName=teamHTML.find("div",class_="text-dark-title").text
        print("Fetching meta-data for team: "+teamName)
        teamURL=teamHTML.find("a")["href"]
        teamId=teamURL.split("/")[-2]
        odiRankText=teamHTML.find("div",class_="text-dark-title").next_sibling.text
        teamRank=None
        if "odi ranking" in odiRankText:
            teamRank=odiRankText.split(":")[1].strip()
        teamData={
            "id":teamId,
            "name":teamName,
            "rank":teamRank,
            "url":teamURL,
            "squad":[],
            "matches":[]
        }
        teamsData.append(teamData)
    return teamsData
 
def getSquad(teamData):
    print("Fetching squad-data for team: "+teamData["name"])
    teamURL = requests.get(teamData["url"])
    soup = BeautifulSoup(teamURL.content, "html.parser")
    squadHTML=soup.find(lambda tag: tag.text=="Squad").next_sibling.findAll("div",class_="swiper-slide")
    squad=[]
    for player in squadHTML:
        playerURL=player.find("a")["href"]
        urlSegments=playerURL.split("/")

        playerId=urlSegments[-2]
        playerName=" ".join(urlSegments[-1].split("-")).title()

        print("Fetching details for player: "+playerName)
        
        playerDetailResponse = requests.get("https://assets-icc.sportz.io/cricket/v1/player?client_id=tPZJbRgIub3Vua93%2FDWtyQ%3D%3D&feed_format=json&lang=en&player_id="+playerId)

        playerRole=None
        if playerDetailResponse.status_code == 200:
            playerDetails = playerDetailResponse.json()
            playerRole=playerDetails["data"]["profile"]["player_skill"]["role"].upper().replace(" ","_")
        else:
            print(f"Failed to fetch details for player {playerName}")
        playerData={
            "id":int(playerId),
            "fullName":playerName,
            "role":playerRole,
            "cost":0,
            "playerStats":[]
        }
        squad.append(playerData)
    return squad

def saveTeamToApp(teamData):
    print("Saving data for team: "+teamData["name"])
    teamJson={}
    teamJson["id"]=int(teamData["id"])
    teamJson["name"]=teamData["name"]
    teamJson["rank"]=int(teamData["rank"]) if teamData["rank"] is not None else None
    teamJson["name"]=teamData["name"]
    teamJson["squad"]=teamData["squad"]
    teamJson["matches"]=[]
    try:
        url = 'http://localhost:8080/api/teams'
        response = requests.post(url, json=teamJson)
        print(response)
        return True
    except:
        print("There was an ERROR saving team: "+teamData["name"])
        return False


def getAllMatchFixtures():
    print("FETCH all fixtures.")
    print("------------------------------")
    # THIS IS JUST A TESTING URL FOR FIXTURES BECAUSE THERE ARE CURRENTLY NO UPCOMING FIXTURES
    fixturesURL="https://web.archive.org/web/20240622142418/https://assets-icc.sportz.io/cricket/v1/schedule?client_id=tPZJbRgIub3Vua93%2FDWtyQ%3D%3D&feed_format=json&is_deleted=false&is_live=true&is_recent=false&is_upcoming=true&lang=en&pagination=false&series_ids=6122&timezone=0000&timezone=0000"
    return getAllMatches(fixturesURL)
    

def getAllCompletedMatches():
    print("FETCH all completed matches.")
    print("------------------------------")
    completedMatchesURL="https://assets-icc.sportz.io/cricket/v1/schedule?client_id=tPZJbRgIub3Vua93%2FDWtyQ%3D%3D&feed_format=json&is_deleted=false&is_live=false&is_recent=true&is_upcoming=false&lang=en&pagination=false&series_ids=6122&timezone=-0400&timezone=+0400"
    return getAllMatches(completedMatchesURL)

def getAllMatches(url):
    matchesResponse = requests.get(url)
    matches=[]
    if(matchesResponse.status_code==200):
        matchesJSON = matchesResponse.json()
        for matchData in matchesJSON["data"]["matches"]:
            if(matchData["teama_id"]=="" or matchData["teama_id"]==""):
                continue
            matchDTO={}
            matchDTO["id"]=int(matchData["match_id"])
            print("FETCHing data for match: "+str(matchDTO["id"]))
            matchDTO["dateTime"]=convert_date_format(matchData["start_date"])
            matchDTO["location"]=matchData["venue"]+", "+matchData["country"]
            matchDTO["matchNumber"]=int(matchData["match_ordinal"])
            matchDTO["teamStats"]=[]
            matchDTO["playerStats"]=[]
            for teamKey in ["teama_id","teamb_id"]:
                matchDTO["teamStats"].append({
                    "teamId":int(matchData[teamKey]),
                    "matchId":matchDTO["id"],
                    "runs":0,
                    "wickets":0,
                    "overs":0
                })
            matches.append(matchDTO)
    else:
        print(f"Failed to fetch fixture data.")
        return
    return matches

def saveMatchToApp(matchDTO):
    print("SAVE match: "+str(matchDTO["id"]))
    try:
        url = 'http://localhost:8080/api/matches'
        response = requests.post(url, json=matchDTO)
        print(response)
        return True
    except:
        print("There was an ERROR saving match: "+matchDTO["id"])
        return False

def getMatchStats(matchDTO):
    matchId=matchDTO["id"]
    print("FETCH stats for match: "+ str(matchId))
    matchURL="https://assets-icc.sportz.io/cricket/v1/game/scorecard?client_id=tPZJbRgIub3Vua93%2FDWtyQ%3D%3D&feed_format=json&game_id="+str(matchId)+"&lang=en"
    match_response = requests.get(matchURL)
    playerStats={}
    if match_response.status_code == 200:
        match_json = match_response.json()
        if("Innings" not in match_json["data"].keys()):
            return matchDTO
        for stat in match_json["data"]["Innings"]:
            teamStatDTO=None
            for teamStat in matchDTO["teamStats"]:
                if(teamStat["teamId"]==int(stat["Battingteam"])):
                    teamStatDTO=teamStat
                    break
            teamStatDTO["runs"]=int(stat["Total"])
            teamStatDTO["wickets"]=int(stat["Wickets"])
            teamStatDTO["overs"]=float(stat["Overs"])


            for batsmanData in stat["Batsmen"]:
                if(batsmanData["Dismissal"]==""):
                    break
                batsmanId=int(batsmanData["Batsman"])
                if(batsmanId not in playerStats.keys()):
                    playerStats[batsmanId]={
                        "playerId":batsmanId,
                        "matchId":matchId
                    }
                print(batsmanData)
                batsmanDTO=playerStats[batsmanId]
                batsmanDTO["runsScored"]=int(batsmanData["Runs"])
                batsmanDTO["strikeRate"]=float(batsmanData["Strikerate"]) if batsmanData["Strikerate"] is not "" else None
                batsmanDTO["ballsPlayed"]=int(batsmanData["Balls"])
                batsmanDTO["sixes"]=int(batsmanData["Sixes"])
                batsmanDTO["fours"]=int(batsmanData["Fours"])
                if(batsmanData["Dismissal"]=="caught"):
                    catcherId=int(batsmanData["Fielder"])
                    if(catcherId not in playerStats.keys()):
                        playerStats[catcherId]={
                            "playerId":catcherId,
                            "matchId":matchId
                        }
                    catcherDTO=playerStats[catcherId]
                    if("wicketsCaught" not in catcherDTO.keys()):
                        catcherDTO["wicketsCaught"]=0
                    catcherDTO["wicketsCaught"]=catcherDTO["wicketsCaught"]+1
            
            
            for bowlerData in stat["Bowlers"]:
                bowlerId=int(bowlerData["Bowler"])
                if(bowlerId not in playerStats.keys()):
                    playerStats[bowlerId]={
                        "playerId":bowlerId,
                        "matchId":matchId
                    }
                bowlerDTO=playerStats[bowlerId]
                bowlerDTO["wickets"]=int(bowlerData["Wickets"])
                bowlerDTO["economyRate"]=float(bowlerData["Economyrate"])
                bowlerDTO["oversBowled"]=float(bowlerData["Overs"])
                bowlerDTO["runsGiven"]=int(bowlerData["Runs"])
                bowlerDTO["maidenOvers"]=int(bowlerData["Maidens"])
    else:
        print(f"Failed to fetch stats for match: {matchId}")
        return
    matchDTO["playerStats"]=list(playerStats.values())
    return matchDTO

def saveTeamStatToApp(teamStat):
    print("SAVING STAT: ")
    updateScoresURL = "http://localhost:8080/api/matches/"+str(teamStat["matchId"])+"/scores"
    try:
        response = requests.put(updateScoresURL, json=teamStat)
        print(response)
        return True
    except:
        print("ERROR saving team match stat.")
        return False

def savePlayerStatToApp(playerStat):
    print("SAVING player stat for "+str(playerStat["playerId"]) +" in match "+str(playerStat["matchId"]))
    dtoFields=["playerId","matchId","runsScored","strikeRate","ballsPlayed","sixes","fours","wickets","economyRate","oversBowled","runsGiven","maidenOvers","wicketsCaught"]
    statDTO={key: (playerStat[key] if key in playerStat else 0) for key in dtoFields}
    try:
        url = "http://localhost:8080/api/matches/"+str(playerStat["matchId"])+"/playerStats"
        response = requests.post(url, json=statDTO)
        print(response)
        return True
    except:
        print("ERROR saving player stat")
        return False

def scrapeData(routine):
    if(routine=="initial"):
        # Getting all teams
        teams=getTeams()
        for team in teams:
            team["squad"]=getSquad(team)
            saveTeamToApp(team)
    
    # Getting all fixtures
    fixtures=getAllMatchFixtures()
    for match in fixtures:
        saveMatchToApp(match)

    # Getting all completed matches
    completedMatches=getAllCompletedMatches()
    for match in completedMatches:
        saveMatchToApp(match)
        getMatchStats(match)
        for teamStat in match["teamStats"]:
            saveTeamStatToApp(teamStat)
        for playerStat in match["playerStats"]:
            savePlayerStatToApp(playerStat)

scrapeData("daily")