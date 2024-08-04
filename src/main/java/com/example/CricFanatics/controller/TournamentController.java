package com.example.CricFanatics.controller;

import com.example.CricFanatics.dto.FanaticsTeamDTO;
import com.example.CricFanatics.dto.TeamDTO;
import com.example.CricFanatics.dto.TournamentDTO;
import com.example.CricFanatics.mapper.FanaticsTeamMapper;
import com.example.CricFanatics.mapper.PlayerMapper;
import com.example.CricFanatics.mapper.TeamMapper;
import com.example.CricFanatics.mapper.TournamentMapper;
import com.example.CricFanatics.model.FanaticsTeam;
import com.example.CricFanatics.model.Team;
import com.example.CricFanatics.model.Tournament;
import com.example.CricFanatics.service.TournamentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/tournaments")
public class TournamentController {

    @Autowired
    private TournamentService tournamentService;

    @GetMapping("")
    public ResponseEntity<Set<TournamentDTO>> getAllTournaments() {
        return new ResponseEntity<>(tournamentService.getAllTournaments().stream().map(tournament -> TournamentMapper.toDTO(tournament)).collect(Collectors.toSet()), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TournamentDTO> getTournamentById(@PathVariable int id) {
        return tournamentService.getTournament(id)
                .map(tournament -> new ResponseEntity<>(TournamentMapper.toDTO(tournament), HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping(path = "")
    public ResponseEntity<TournamentDTO> createTournament(@RequestBody TournamentDTO tournamentDTO) {
        return tournamentService.createTournament(tournamentDTO)
                .map(tournament -> new ResponseEntity<>(TournamentMapper.toDTO(tournament), HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping(path = "/{id}/teams")
    public ResponseEntity<FanaticsTeamDTO> createFanaticsTeam(@PathVariable int tournamentId,@RequestBody FanaticsTeamDTO fanaticsTeamDTO) {
        return tournamentService.createTeam(tournamentId,fanaticsTeamDTO)
                .map(fanaticsTeam -> new ResponseEntity<>(FanaticsTeamMapper.toDTO(fanaticsTeam), HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


}
