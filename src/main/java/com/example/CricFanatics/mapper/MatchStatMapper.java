package com.example.CricFanatics.mapper;

import com.example.CricFanatics.dto.MatchStatDTO;
import com.example.CricFanatics.model.MatchStat;


public class MatchStatMapper {
    public static MatchStatDTO toDTO(MatchStat matchStat) {
        MatchStatDTO matchStatDTO = new MatchStatDTO();
        matchStatDTO.setTeamId(matchStat.getTeam().getId());
        matchStatDTO.setMatchId(matchStat.getMatch().getId());
        matchStatDTO.setRuns(matchStat.getRuns());
        matchStatDTO.setWickets(matchStat.getWickets());
        matchStatDTO.setOvers(matchStat.getOvers());
        return matchStatDTO;
        }

    public static MatchStat toEntity(MatchStatDTO matchStatDTO) {
        return new MatchStat(null,null,matchStatDTO.getRuns(),matchStatDTO.getWickets(),matchStatDTO.getOvers());
    }
}
