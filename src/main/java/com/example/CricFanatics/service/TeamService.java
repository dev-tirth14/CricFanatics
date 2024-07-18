package com.example.CricFanatics.service;

import com.example.CricFanatics.dto.PlayerDTO;
import com.example.CricFanatics.dto.TeamDTO;
import com.example.CricFanatics.mapper.PlayerMapper;
import com.example.CricFanatics.mapper.TeamMapper;
import com.example.CricFanatics.model.Player;
import com.example.CricFanatics.model.Team;
import com.example.CricFanatics.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class TeamService {
    @Autowired
    private TeamRepository teamRepo;

    public Optional<Team> getTeam(int id){
        return teamRepo.findById(id);
    }
    public Set<Team> getAllTeams(){
        return Set.copyOf(teamRepo.findAll());
    }
    public Optional<Team> getTeam(String name){
        return teamRepo.findByName(name);
    }

    public Team createTeam(TeamDTO teamDTO){

        //deconstruct the teamDTO object and create team entity from it
        Team team= TeamMapper.toEntity(teamDTO);
        Set<Player> players=teamDTO.getSquad().stream()
                .map(playerDTO -> {
                    Player player = PlayerMapper.toEntity(playerDTO);
                    player.setTeam(team);
                    return player;
                })
                .collect(Collectors.toSet());

        team.setSquad(players);

        teamRepo.save(team);
        return team;
    }
    public Player addPlayerToTeam(Team team,PlayerDTO playerDTO){
        Player player=PlayerMapper.toEntity(playerDTO);
        player.setTeam(team);
        team.getSquad().add(player);
        teamRepo.save(team);
        return player;
    }
    public void deleteTeam(int id){
        teamRepo.deleteById(id);
    }

}
