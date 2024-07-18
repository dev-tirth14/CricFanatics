package com.example.CricFanatics.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@Getter
@Setter
public class MatchDTO {
    private int id;
    private LocalDateTime dateTime;
    private String location;
    private int matchNumber;
    private Set<MatchStatDTO> teamStats;
    private Set<PlayerStatDTO> playerStats;
}
