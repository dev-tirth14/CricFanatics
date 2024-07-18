package com.example.CricFanatics.model;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
public class MatchStatId implements Serializable {
    private int team;
    private int match;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MatchStatId that = (MatchStatId) o;
        return team == that.team && match == that.match;
    }

    @Override
    public int hashCode() {
        return Objects.hash(team, match);
    }
}
