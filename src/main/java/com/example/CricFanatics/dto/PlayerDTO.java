package com.example.CricFanatics.dto;

import lombok.*;

import java.util.Set;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PlayerDTO {
    private int id;
    private String fullName;
    private String role;
    private int cost;
    private Set<PlayerStatDTO> playerStats;
}
