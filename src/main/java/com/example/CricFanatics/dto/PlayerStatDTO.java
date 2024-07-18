package com.example.CricFanatics.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class PlayerStatDTO {
    private int playerId;
    private int matchId;
    private int runsScored;
    private double strikeRate;
    private int ballsPlayed;
    private int sixes;
    private int fours;
    private int wickets;
    private double economyRate;
    private int oversBowled;
    private int runsGiven;
    private int maidenOvers;
    private int wicketsCaught;
}
