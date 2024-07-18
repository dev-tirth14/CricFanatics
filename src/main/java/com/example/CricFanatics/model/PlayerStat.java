package com.example.CricFanatics.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "player_match_stat")
@IdClass(PlayerStatId.class)
public class PlayerStat {
    @Id
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "player_id", nullable = false)
    @JsonBackReference
    private Player player;

    @Id
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "match_id", nullable = false)
    @JsonBackReference
    private Match match;

    @Column(name = "runs_scored")
    private int runsScored;

    @Column(name = "strike_rate")
    private double strikeRate;

    @Column(name = "balls_played")
    private int ballsPlayed;

    @Column(name = "sixes")
    private int sixes;

    @Column(name = "fours")
    private int fours;

    @Column(name = "wickets")
    private int wickets;

    @Column(name = "economy_rate")
    private BigDecimal economyRate;

    @Column(name = "overs_bowled")
    private int oversBowled;

    @Column(name = "runs_given")
    private int runsGiven;

    @Column(name = "maiden_overs")
    private int maidenOvers;

    @Column(name = "wickets_caught")
    private int wicketsCaught;



}
