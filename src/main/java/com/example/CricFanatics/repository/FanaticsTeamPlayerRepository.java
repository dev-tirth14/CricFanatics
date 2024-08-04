package com.example.CricFanatics.repository;

import com.example.CricFanatics.model.FanaticsTeamPlayer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FanaticsTeamPlayerRepository extends JpaRepository<FanaticsTeamPlayer, Integer> {
}
