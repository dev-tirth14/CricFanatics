package com.example.CricFanatics.repository;

import com.example.CricFanatics.model.FanaticsTeam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FanaticTeamRepository extends JpaRepository<FanaticsTeam, Integer> {
}
