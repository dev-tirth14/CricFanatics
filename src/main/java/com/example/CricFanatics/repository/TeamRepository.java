package com.example.CricFanatics.repository;

import com.example.CricFanatics.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TeamRepository extends JpaRepository<Team, Integer> {

    Optional<Team> findByName(String name);
}
