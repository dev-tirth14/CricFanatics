package com.example.CricFanatics.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Data
@Getter
@Setter
public class PlayerDTO {
    private int id;
    private String fullName;
    private String role;
    private int cost;
    private Set<PlayerStatDTO> playerStats;
}
