package com.example.CricFanatics.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class MatchStatDTO {
    private int teamId;
    private int matchId;
    private int runs;
    private int wickets;
    private int overs;
}
