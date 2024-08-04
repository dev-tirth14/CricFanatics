package com.example.CricFanatics.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tournament")
public class Tournament {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;
    @Column(name = "code")
    private String uniqueCode;

    @Column(name = "created_by")
    private int organizer;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private TournamentStatus status;
    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @Column(name = "max_players_per_team")
    private Integer maxPlayersPerTeam;
    @Column(name = "required_batters")
    private Integer requiredBatters;
    @Column(name = "required_bowlers")
    private Integer requiredBowlers;
    @Column(name = "required_all_rounders")
    private Integer requiredAllRounders;
    @Column(name = "required_wicket_keepers")
    private Integer requiredWicketKeepers;

    @Column(name = "runs_scored_points")
    private Integer pointsPerRun=1;
    @Column(name = "sixes_points")
    private Integer pointsPerSix=2;
    @Column(name = "fours_points")
    private Integer pointsPerFour=1;
    @Column(name = "wickets_points")
    private Integer pointsPerWicket=25;
    @Column(name = "wickets_caught_points")
    private Integer pointsPerCatch=10;
    @Column(name = "strike_rate_over_100_points")
    private Integer pointsForStrikeRateOver100=2;
    @Column(name = "strike_rate_under_50_points_deducted")
    private Integer pointsDeductedForStrikeRateUnder50=2;
    @Column(name = "economy_rate_under_4_points")
    private Integer pointsForEconomyRateUnder4=10;
    @Column(name = "economy_rate_over_9_points_deducted")
    private Integer pointsDeductedForEconomyRateOver9=10;
    @Column(name = "maiden_overs_points")
    private Integer pointsForMaidenOver=4;
    @OneToMany(mappedBy = "tournament",fetch = FetchType.LAZY, cascade = {
            CascadeType.MERGE,
            CascadeType.REMOVE,
            CascadeType.REFRESH
    })
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JsonManagedReference
    private Set<FanaticsTeam> teams;

    public Tournament(String name, String uniqueCode, int organizer, TournamentStatus status, LocalDateTime createdDate, Integer maxPlayersPerTeam, Integer requiredBatters, Integer requiredBowlers, Integer requiredAllRounders, Integer requiredWicketKeepers, Integer pointsPerRun, Integer pointsPerSix, Integer pointsPerFour, Integer pointsPerWicket, Integer pointsPerCatch, Integer pointsForStrikeRateOver100, Integer pointsDeductedForStrikeRateUnder50, Integer pointsForEconomyRateUnder4, Integer pointsDeductedForEconomyRateOver9, Integer pointsForMaidenOver, Set<FanaticsTeam> teams) {
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