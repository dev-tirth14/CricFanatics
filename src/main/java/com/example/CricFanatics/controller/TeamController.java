package com.example.CricFanatics.controller;

import com.example.CricFanatics.dto.PlayerDTO;
import com.example.CricFanatics.dto.TeamDTO;
import com.example.CricFanatics.mapper.PlayerMapper;
import com.example.CricFanatics.mapper.TeamMapper;
import com.example.CricFanatics.model.Player;
import com.example.CricFanatics.model.Team;
import com.example.CricFanatics.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/teams")
public class TeamController {
    @Autowired
    private TeamService teamService;

    @GetMapping("")
    public ResponseEntity<Set<TeamDTO>> getAllTeams() {
        return new ResponseEntity<>(teamService.getAllTeams().stream().map(team -> TeamMapper.toDTO(team)).collect(Collectors.toSet()), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TeamDTO> getTeamById(@PathVariable int id) {
        return teamService.getTeam(id)
                .map(team -> new ResponseEntity<>(TeamMapper.toDTO(team), HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/{id}/squad")
    public ResponseEntity<Set<PlayerDTO>> getSquadByTeamId(@PathVariable int id) {

        return teamService.getTeam(id)
                .map(team -> new ResponseEntity<>(team.getSquad().stream().map(player -> PlayerMapper.toDTO(player)).collect(Collectors.toSet()), HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping(path = "")
    public ResponseEntity<TeamDTO> createTeam(@RequestBody TeamDTO teamDTO) {
        Team team = teamService.createTeam(teamDTO);
        return new ResponseEntity<>(TeamMapper.toDTO(team), HttpStatus.OK);
    }

    @PostMapping(path = "/{id}/squad")
    public ResponseEntity<PlayerDTO> addPlayerToTeam(@PathVariable int id, @RequestBody PlayerDTO playerDTO) {
        Optional<Team> team = teamService.getTeam(id);
        if (team.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Player player = teamService.addPlayerToTeam(team.get(), playerDTO);
        return new ResponseEntity<>(PlayerMapper.toDTO(player), HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<String> deleteTeam(@PathVariable int id) {
        teamService.deleteTeam(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
