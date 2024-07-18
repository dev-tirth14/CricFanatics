package com.example.CricFanatics.mapper;

import com.example.CricFanatics.dto.MatchDTO;
import com.example.CricFanatics.model.Match;

import java.util.HashSet;
import java.util.stream.Collectors;

public class MatchMapper {
    public static MatchDTO toDTO(Match match) {
        MatchDTO matchDTO = new MatchDTO();
        matchDTO.setId(match.getId());
        matchDTO.setDateTime(match.getDateTime());
        matchDTO.setLocation(match.getLocation());
        matchDTO.setMatchNumber(match.getMatchNumber());
        matchDTO.setTeamStats(match.getTeam_stats().stream()
                .map(MatchStatMapper::toDTO)
                .collect(Collectors.toSet()));
        matchDTO.setPlayerStats(match.getPlayer_stats().stream()
                .map(PlayerStatMapper::toDTO)
                .collect(Collectors.toSet()));

        return matchDTO;
    }

    public static Match toEntity(MatchDTO matchDTO) {
        return new Match(matchDTO.getId(),matchDTO.getDateTime(),matchDTO.getLocation(),matchDTO.getMatchNumber(), new HashSet<>(),new HashSet<>());
    }
}
