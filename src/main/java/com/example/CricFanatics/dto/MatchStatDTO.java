package com.example.CricFanatics.dto;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MatchStatDTO {
    private int teamId;
    private int matchId;
    private int runs;
    private int wickets;
    private double overs;
}
