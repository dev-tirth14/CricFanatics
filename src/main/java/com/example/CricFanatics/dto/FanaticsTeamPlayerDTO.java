package com.example.CricFanatics.dto;

import com.example.CricFanatics.model.FanaticsTeam;
import com.example.CricFanatics.model.Player;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FanaticsTeamPlayerDTO {
    private int id;
    private int points;
    private int player_id;

    public FanaticsTeamPlayerDTO(int points, int player_id) {
        this.points = points;
        this.player_id = player_id;
    }
}
