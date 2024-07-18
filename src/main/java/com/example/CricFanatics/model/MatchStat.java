package com.example.CricFanatics.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "team_match_stat")
@IdClass(MatchStatId.class)
public class MatchStat {
    @Id
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "team_id", nullable = false)
    @JsonBackReference
    private Team team;

    @Id
    @ManyToOne(cascade = {CascadeType.REFRESH,CascadeType.REMOVE})
    @JoinColumn(name = "match_id", nullable = false)
    @JsonBackReference
    private Match match;

    @Column(name = "total_runs")
    private int runs;

    @Column(name = "total_wickets")
    private int wickets;

    @Column(name = "overs_played")
    private int overs;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MatchStat matchStat = (MatchStat) o;
        return Objects.equals(team, matchStat.team) && Objects.equals(match, matchStat.match);
    }

    @Override
    public int hashCode() {
        return Objects.hash(team, match);
    }
}
