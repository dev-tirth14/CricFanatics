package com.example.CricFanatics.mapper;

import com.example.CricFanatics.dto.FanaticsTeamPlayerDTO;
import com.example.CricFanatics.dto.TournamentDTO;
import com.example.CricFanatics.model.FanaticsTeamPlayer;
import com.example.CricFanatics.model.Tournament;

public class FanaticsTeamPlayerMapper {
    public static FanaticsTeamPlayerDTO toDTO(FanaticsTeamPlayer fanaticsTeamPlayer) {
        FanaticsTeamPlayerDTO fanaticsTeamPlayerDTO=new FanaticsTeamPlayerDTO();
        fanaticsTeamPlayerDTO.setId(fanaticsTeamPlayer.getId());
        fanaticsTeamPlayerDTO.setPlayer_id(fanaticsTeamPlayer.getPlayer().getId());
        fanaticsTeamPlayerDTO.setPoints(fanaticsTeamPlayer.getPoints());
        return fanaticsTeamPlayerDTO;
    }

    public static FanaticsTeamPlayer toEntity(FanaticsTeamPlayerDTO fanaticsTeamPlayerDTO) {
        return new FanaticsTeamPlayer(fanaticsTeamPlayerDTO.getId(), fanaticsTeamPlayerDTO.getPoints(), null,null);
    }
}
