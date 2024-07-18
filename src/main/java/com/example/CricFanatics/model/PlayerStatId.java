package com.example.CricFanatics.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
public class PlayerStatId implements Serializable {
    private int player;
    private int match;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlayerStatId that = (PlayerStatId) o;
        return player == that.player && match == that.match;
    }

    @Override
    public int hashCode() {
        return Objects.hash(player, match);
    }
}
