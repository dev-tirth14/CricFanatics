package com.example.CricFanatics.dto;

import com.example.CricFanatics.model.TournamentStatus;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TournamentDTO {

    private int id;
    private String name;
    private String uniqueCode;
    private int organizer;
    private String status;
    private LocalDateTime createdDate;
    private Integer maxPlayersPerTeam;
    private Integer requiredBatters;
    private Integer requiredBowlers;
    private Integer requiredAllRounders;
    private Integer requiredWicketKeepers;
    private Integer pointsPerRun;
    private Integer pointsPerSix;
    private Integer pointsPerFour;
    private Integer pointsPerWicket;
    private Integer pointsPerCatch;
    private Integer pointsForStrikeRateOver100;
    private Integer pointsDeductedForStrikeRateUnder50;
    private Integer pointsForEconomyRateUnder4;
    private Integer pointsDeductedForEconomyRateOver9;
    private Integer pointsForMaidenOver;
    private List<FanaticsTeamDTO> teams;

    public TournamentDTO(String name, String uniqueCode, int organizer, String status, LocalDateTime createdDate, Integer maxPlayersPerTeam, Integer requiredBatters, Integer requiredBowlers, Integer requiredAllRounders, Integer requiredWicketKeepers, Integer pointsPerRun, Integer pointsPerSix, Integer pointsPerFour, Integer pointsPerWicket, Integer pointsPerCatch, Integer pointsForStrikeRateOver100, Integer pointsDeductedForStrikeRateUnder50, Integer pointsForEconomyRateUnder4, Integer pointsDeductedForEconomyRateOver9, Integer pointsForMaidenOver, List<FanaticsTeamDTO> teams) {
        this.name = name;
        this.uniqueCode = uniqueCode;
        this.organizer = organizer;
        this.status = status;
        this.createdDate = createdDate;
        this.maxPlayersPerTeam = maxPlayersPerTeam;
        this.requiredBatters = requiredBatters;
        this.requiredBowlers = requiredBowlers;
        this.requiredAllRounders = requiredAllRounders;
        this.requiredWicketKeepers = requiredWicketKeepers;
        this.pointsPerRun = pointsPerRun;
        this.pointsPerSix = pointsPerSix;
        this.pointsPerFour = pointsPerFour;
        this.pointsPerWicket = pointsPerWicket;
        this.pointsPerCatch = pointsPerCatch;
        this.pointsForStrikeRateOver100 = pointsForStrikeRateOver100;
        this.pointsDeductedForStrikeRateUnder50 = pointsDeductedForStrikeRateUnder50;
        this.pointsForEconomyRateUnder4 = pointsForEconomyRateUnder4;
        this.pointsDeductedForEconomyRateOver9 = pointsDeductedForEconomyRateOver9;
        this.pointsForMaidenOver = pointsForMaidenOver;
        this.teams = teams;
    }
}
