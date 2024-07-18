package com.example.CricFanatics.controller;

import com.example.CricFanatics.dto.MatchDTO;
import com.example.CricFanatics.dto.MatchStatDTO;
import com.example.CricFanatics.dto.PlayerStatDTO;
import com.example.CricFanatics.mapper.MatchMapper;
import com.example.CricFanatics.mapper.MatchStatMapper;
import com.example.CricFanatics.mapper.PlayerStatMapper;
import com.example.CricFanatics.service.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/matches")
public class MatchController {
    @Autowired
    private MatchService matchService;

    @GetMapping("")
    public ResponseEntity<Set<MatchDTO>> getAllMatches() {
        return new ResponseEntity<>(matchService.getAllMatches().stream().map(match -> MatchMapper.toDTO(match)).collect(Collectors.toSet()), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MatchDTO> getMatchById(@PathVariable int id) {
        return matchService.getMatch(id)
                .map(match -> new ResponseEntity<>(MatchMapper.toDTO(match), HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping(path = "")
    public ResponseEntity<MatchDTO> createMatch(@RequestBody MatchDTO matchDTO) {
        return matchService.createMatch(matchDTO)
                .map(match -> new ResponseEntity<>(MatchMapper.toDTO(match), HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping(path = "/{id}/scores")
    public ResponseEntity<MatchStatDTO> updateMatchScore(@PathVariable int id,@RequestBody MatchStatDTO matchStatDTO){
        if(id!=matchStatDTO.getMatchId()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return matchService.updateScore(matchStatDTO)
                .map(
                        matchStat -> new ResponseEntity<>(MatchStatMapper.toDTO(matchStat), HttpStatus.OK)
                ).orElse(
                        new ResponseEntity<>(HttpStatus.NOT_FOUND)
                );
    }

    @PostMapping(path = "/{id}/playerStats")
    public ResponseEntity<PlayerStatDTO> createPlayerStat(@PathVariable int id, @RequestBody PlayerStatDTO playerStatDTO){
        if(id!=playerStatDTO.getMatchId()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return matchService.createPlayerStat(playerStatDTO).map(
                playerStat -> new ResponseEntity<>(PlayerStatMapper.toDTO(playerStat), HttpStatus.OK)
        ).orElse(
                new ResponseEntity<>(HttpStatus.NOT_FOUND)
        );
    }


}
