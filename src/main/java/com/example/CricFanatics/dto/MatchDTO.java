package com.example.CricFanatics.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MatchDTO {
    private int id;
    private LocalDateTime dateTime;
    private String location;
    private int matchNumber;
    private Set<MatchStatDTO> teamStats;
    private Set<PlayerStatDTO> playerStats;
}
