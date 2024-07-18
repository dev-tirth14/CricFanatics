package com.example.CricFanatics.service;

import com.example.CricFanatics.dto.MatchDTO;
import com.example.CricFanatics.dto.MatchStatDTO;
import com.example.CricFanatics.dto.PlayerStatDTO;
import com.example.CricFanatics.mapper.MatchMapper;
import com.example.CricFanatics.mapper.MatchStatMapper;
import com.example.CricFanatics.mapper.PlayerStatMapper;
import com.example.CricFanatics.model.*;
import com.example.CricFanatics.repository.MatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;

@Service
public class MatchService {

    @Autowired
    private MatchRepository matchRepo;

    @Autowired
    private TeamService teamService;

    @Autowired
    private PlayerService playerService;

    public Set<Match> getAllMatches(){
        return Set.copyOf(matchRepo.findAll());
    }
    public Optional<Match> getMatch(int id){
        return matchRepo.findById(id);
    }

    public Optional<Match> createMatch(MatchDTO matchDTO){
        Match match=MatchMapper.toEntity(matchDTO);

        try {
            matchDTO.getTeamStats().stream().forEach((matchStatDTO -> {
                Optional<Team> teamOp=teamService.getTeam(matchStatDTO.getTeamId());
                if(teamOp.isEmpty()){
                    throw new NoSuchElementException();
                }
                Team team=teamOp.get();
                MatchStat matchStat= MatchStatMapper.toEntity(matchStatDTO);

                matchStat.setMatch(match);
                match.getTeam_stats().add(matchStat);

                matchStat.setTeam(team);
                team.getMatches().add(matchStat);
            }));
        }catch (NoSuchElementException e){
            return Optional.empty();
        }

        matchRepo.save(match);
        return Optional.of(match);
    }
    public Optional<MatchStat> updateScore(MatchStatDTO matchStatDTO){
        Match match;
        Team team;
        try{
            match=getMatch(matchStatDTO.getMatchId()).orElseThrow(()->new NoSuchElementException());
            team=teamService.getTeam(matchStatDTO.getTeamId()).orElseThrow(()->new NoSuchElementException());
        }catch (NoSuchElementException e){
            return Optional.empty();
        }
        MatchStat matchStat=MatchStatMapper.toEntity(matchStatDTO);

        matchStat.setTeam(team);
        matchStat.setMatch(match);

        team.getMatches().removeIf(currentMatchStat -> currentMatchStat.equals(matchStat));
        team.getMatches().add(matchStat);

        match.getTeam_stats().removeIf(currentMatchStat -> currentMatchStat.equals(matchStat));
        match.getTeam_stats().add(matchStat);

        matchRepo.save(match);
        return Optional.of(matchStat);
    }

    public Optional<PlayerStat> createPlayerStat(PlayerStatDTO playerStatDTO){
        Match match;
        Player player;
        try{
            match=getMatch(playerStatDTO.getMatchId()).orElseThrow(()->new NoSuchElementException());
            player=playerService.getPlayer(playerStatDTO.getPlayerId()).orElseThrow(()->new NoSuchElementException());
        }catch (NoSuchElementException e){
            return Optional.empty();
        }

        PlayerStat playerStat= PlayerStatMapper.toEntity(playerStatDTO);

        playerStat.setMatch(match);
        playerStat.setPlayer(player);

        player.getStats().add(playerStat);
        match.getPlayer_stats().add(playerStat);

        matchRepo.save(match);
        return Optional.of(playerStat);


    }



}
