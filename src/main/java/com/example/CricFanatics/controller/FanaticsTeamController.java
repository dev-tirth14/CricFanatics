package com.example.CricFanatics.controller;

import com.example.CricFanatics.dto.FanaticsTeamDTO;
import com.example.CricFanatics.mapper.FanaticsTeamMapper;
import com.example.CricFanatics.service.FanaticsTeamService;
import com.example.CricFanatics.service.TournamentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/fanaticsTeams")
public class FanaticsTeamController {
    @Autowired
    private FanaticsTeamService fanaticsTeamService;

    @PutMapping(path = "/{fanaticsTeamId}/approveStatus")
    public ResponseEntity<FanaticsTeamDTO> approveFanaticsTeam(@PathVariable int fanaticsTeamId) {
        return fanaticsTeamService.approveFanaticsTeam(fanaticsTeamId)
                .map(fanaticsTeam -> new ResponseEntity<>(FanaticsTeamMapper.toDTO(fanaticsTeam), HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
