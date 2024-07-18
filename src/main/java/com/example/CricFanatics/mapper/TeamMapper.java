package com.example.CricFanatics.mapper;

import com.example.CricFanatics.dto.TeamDTO;
import com.example.CricFanatics.model.Team;

import java.util.HashSet;
import java.util.stream.Collectors;

public class TeamMapper {
    public static TeamDTO toDTO(Team team) {
        TeamDTO teamDTO=new TeamDTO();
        teamDTO.setId(team.getId());
        teamDTO.setName(team.getName());
        teamDTO.setRank(team.getRank());
        teamDTO.setSquad(team.getSquad().stream().map(player -> PlayerMapper.toDTO(player)).collect(Collectors.toSet()));
        teamDTO.setMatches(team.getMatches().stream().map(matchStat -> MatchMapper.toDTO(matchStat.getMatch())).collect(Collectors.toSet()));
        return teamDTO;

    }

    public static Team toEntity(TeamDTO teamDTO) {
        return new Team(teamDTO.getId(),teamDTO.getName(),teamDTO.getRank(),new HashSet<>(), new HashSet<>());
    }
}
