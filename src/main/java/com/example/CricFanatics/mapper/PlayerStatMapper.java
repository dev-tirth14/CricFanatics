package com.example.CricFanatics.mapper;

import com.example.CricFanatics.dto.PlayerStatDTO;
import com.example.CricFanatics.model.PlayerStat;

import java.math.BigDecimal;

public class PlayerStatMapper {
    public static PlayerStatDTO toDTO(PlayerStat playerStat) {
        PlayerStatDTO playerStatDTO=new PlayerStatDTO();

        playerStatDTO.setPlayerId(playerStat.getPlayer().getId());
        playerStatDTO.setMatchId(playerStat.getMatch().getId());

        playerStatDTO.setRunsScored(playerStat.getRunsScored());
        playerStatDTO.setStrikeRate(playerStat.getStrikeRate());
        playerStatDTO.setBallsPlayed(playerStat.getBallsPlayed());
        playerStatDTO.setSixes(playerStat.getSixes());
        playerStatDTO.setFours(playerStat.getFours());
        playerStatDTO.setWickets(playerStat.getWickets());
        playerStatDTO.setEconomyRate(playerStat.getEconomyRate().doubleValue());
        playerStatDTO.setOversBowled(playerStat.getOversBowled());
        playerStatDTO.setRunsGiven(playerStat.getRunsGiven());
        playerStatDTO.setMaidenOvers(playerStat.getMaidenOvers());
        playerStatDTO.setWicketsCaught(playerStat.getWicketsCaught());


        return playerStatDTO;
    }

    public static PlayerStat toEntity(PlayerStatDTO playerStatDTO) {
        return new PlayerStat(null,null,playerStatDTO.getRunsScored(),playerStatDTO.getStrikeRate(),playerStatDTO.getBallsPlayed(),playerStatDTO.getSixes(),playerStatDTO.getFours(),playerStatDTO.getWickets(), BigDecimal.valueOf(playerStatDTO.getEconomyRate()),playerStatDTO.getOversBowled(),playerStatDTO.getRunsGiven(),playerStatDTO.getMaidenOvers(),playerStatDTO.getWicketsCaught());
    }
}
