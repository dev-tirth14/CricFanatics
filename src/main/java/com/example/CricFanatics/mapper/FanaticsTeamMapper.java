package com.example.CricFanatics.mapper;

import com.example.CricFanatics.dto.FanaticsTeamDTO;
import com.example.CricFanatics.dto.TournamentDTO;
import com.example.CricFanatics.model.FanaticsTeam;
import com.example.CricFanatics.model.FanaticsTeamPlayer;
import com.example.CricFanatics.model.FanaticsTeamStatus;
import com.example.CricFanatics.model.Tournament;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class FanaticsTeamMapper {
    public static FanaticsTeamDTO toDTO(FanaticsTeam fanaticsTeam) {
        FanaticsTeamDTO fanaticsTeamDTO=new FanaticsTeamDTO();
        fanaticsTeamDTO.setId(fanaticsTeam.getId());
        fanaticsTeamDTO.setName(fanaticsTeam.getName());
        fanaticsTeamDTO.setOwner(fanaticsTeam.getOwner());
        fanaticsTeamDTO.setStatus(fanaticsTeam.getStatus().toString());
        fanaticsTeamDTO.setCreatedDate(fanaticsTeam.getCreatedDate());
        fanaticsTeamDTO.setSquad(fanaticsTeam.getSquad().stream().map(player->FanaticsTeamPlayerMapper.toDTO(player)).collect(Collectors.toSet()));
        fanaticsTeamDTO.setPoints(fanaticsTeam.getSquad().stream().mapToInt(FanaticsTeamPlayer::getPoints).sum());
        return fanaticsTeamDTO;
    }

    public static FanaticsTeam toEntity(FanaticsTeamDTO fanaticsTeamDTO) {
        return new FanaticsTeam(fanaticsTeamDTO.getId(), fanaticsTeamDTO.getName(), fanaticsTeamDTO.getOwner(), FanaticsTeamStatus.valueOf(fanaticsTeamDTO.getStatus()),fanaticsTeamDTO.getCreatedDate(),null, new HashSet<>());
    }
}
