package com.example.CricFanatics.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Data
@Getter
@Setter
public class TeamDTO {
    private int id;
    private String name;
    private int rank;
    private Set<PlayerDTO> squad;
    private Set<MatchDTO> matches;
}
