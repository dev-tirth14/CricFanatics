package com.example.CricFanatics.service;

import com.example.CricFanatics.model.Player;
import com.example.CricFanatics.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class PlayerService {

    @Autowired
    private PlayerRepository playerRepo;

    public Optional<Player> getPlayer(int id){
        return playerRepo.findById(id);
    }
    public Set<Player> getAllPlayers(){
        return Set.copyOf(playerRepo.findAll());
    }


}
