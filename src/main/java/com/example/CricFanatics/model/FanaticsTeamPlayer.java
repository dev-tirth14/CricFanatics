package com.example.CricFanatics.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "fanatics_team_player")
public class FanaticsTeamPlayer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "points")
    private int points;

    @ManyToOne(cascade = {
            CascadeType.REFRESH,
    })
    @JoinColumn(name = "fanatics_team_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonBackReference
    private FanaticsTeam team;

    @ManyToOne(cascade = {
            CascadeType.REFRESH,
    })
    @JoinColumn(name = "player_id")
    @JsonBackReference
    private Player player;



}
