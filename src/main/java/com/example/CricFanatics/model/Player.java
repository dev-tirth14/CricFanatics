package com.example.CricFanatics.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "player")
public class Player {
    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private PlayerRole role;

    @Column(name = "cost")
    private int cost;

    @ManyToOne(cascade = {
            CascadeType.REFRESH,
    })
    @JoinColumn(name = "team_id")
    @EqualsAndHashCode.Exclude
    @JsonBackReference
    private Team team;

    @OneToMany(mappedBy = "player",fetch = FetchType.LAZY, cascade = {
            CascadeType.REMOVE,
            CascadeType.REFRESH
    })
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JsonManagedReference
    private Set<PlayerStat> stats;




}
