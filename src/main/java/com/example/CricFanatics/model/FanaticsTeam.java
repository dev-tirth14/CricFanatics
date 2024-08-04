package com.example.CricFanatics.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
@Table(name = "fanatics_team")
public class FanaticsTeam {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "owner_id")
    private int owner;
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private FanaticsTeamStatus status;
    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @ManyToOne(cascade = {
            CascadeType.REFRESH,
    })
    @JoinColumn(name = "tournament_id")
    @EqualsAndHashCode.Exclude
    @JsonBackReference
    @ToString.Exclude
    private Tournament tournament;

    @OneToMany(mappedBy = "team",fetch = FetchType.LAZY, cascade = {
            CascadeType.MERGE,
            CascadeType.REMOVE,
            CascadeType.REFRESH
    })
    @EqualsAndHashCode.Exclude
    @JsonManagedReference
    private Set<FanaticsTeamPlayer> squad;


}
