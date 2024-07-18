package com.example.CricFanatics.mapper;

import com.example.CricFanatics.dto.PlayerDTO;
import com.example.CricFanatics.model.Player;
import com.example.CricFanatics.model.PlayerRole;

import java.util.HashSet;
import java.util.stream.Collectors;

public class PlayerMapper {
    public static PlayerDTO toDTO(Player player) {
        PlayerDTO playerDTO=new PlayerDTO();
        playerDTO.setId(player.getId());
        playerDTO.setFullName(player.getFullName());
        playerDTO.setCost(player.getCost());
        playerDTO.setRole(playerDTO.getRole());
        playerDTO.setPlayerStats(player.getStats().stream().map(playerStat -> PlayerStatMapper.toDTO(playerStat)).collect(Collectors.toSet()));
        return playerDTO;
    }

    public static Player toEntity(PlayerDTO playerDTO) {
        return new Player(playerDTO.getId(), playerDTO.getFullName(), PlayerRole.valueOf(playerDTO.getRole()),playerDTO.getCost(),null,new HashSet<>());
    }
}
