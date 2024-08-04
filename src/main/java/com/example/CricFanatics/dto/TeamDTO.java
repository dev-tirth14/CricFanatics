package com.example.CricFanatics.dto;

import lombok.*;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TeamDTO {
    private int id;
    private String name;
    private int rank;
    private Set<PlayerDTO> squad;
    private Set<MatchDTO> matches;

}
