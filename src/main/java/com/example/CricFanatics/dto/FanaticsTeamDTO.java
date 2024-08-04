package com.example.CricFanatics.dto;

import com.example.CricFanatics.model.FanaticsTeamPlayer;
import com.example.CricFanatics.model.FanaticsTeamStatus;
import com.example.CricFanatics.model.Tournament;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FanaticsTeamDTO {
    private int id;
    private String name;
    private int owner;
    private String status;
    private LocalDateTime createdDate;
    private int points;

    private Set<FanaticsTeamPlayerDTO> squad;
}
