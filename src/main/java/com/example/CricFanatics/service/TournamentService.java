package com.example.CricFanatics.service;

import com.example.CricFanatics.dto.FanaticsTeamDTO;
import com.example.CricFanatics.dto.TournamentDTO;
import com.example.CricFanatics.mapper.FanaticsTeamMapper;
import com.example.CricFanatics.mapper.FanaticsTeamPlayerMapper;
import com.example.CricFanatics.mapper.TournamentMapper;
import com.example.CricFanatics.model.*;
import com.example.CricFanatics.repository.TournamentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class TournamentService {
    @Autowired
    private TournamentRepository tournamentRepo;

    @Autowired
    private FanaticsTeamService fanaticsTeamService;

    @Autowired
    private PlayerService playerService;

    public Optional<Tournament> getTournament(int id){
        return tournamentRepo.findById(id);
    }
    public Set<Tournament> getAllTournaments(){
        return Set.copyOf(tournamentRepo.findAll());
    }

    public Optional<Tournament> createTournament(TournamentDTO tournamentDTO){
        Tournament tournament= TournamentMapper.toEntity(tournamentDTO);
        try{
            tournamentRepo.save(tournament);
        }catch (DataIntegrityViolationException e){
            return Optional.empty();
        }
        return Optional.of(tournament);
    }

    public Optional<FanaticsTeam> createTeam(int tournament_id,FanaticsTeamDTO fanaticsTeamDTO){
        Tournament tournament;
        FanaticsTeam fanaticsTeam;
        try{
            tournament=getTournament(tournament_id).orElseThrow(()->new NoSuchElementException());
            fanaticsTeam=fanaticsTeamService.createFanaticsTeam(fanaticsTeamDTO).orElseThrow(()->new NoSuchElementException());
        }catch (NoSuchElementException e){
            return Optional.empty();
        }

        if(!isTeamValid(tournament,fanaticsTeam)) return Optional.empty();
       

        fanaticsTeam.setTournament(tournament);
        tournament.getTeams().add(fanaticsTeam);
        try{
            tournamentRepo.save(tournament);
        }catch (DataIntegrityViolationException e){
            return Optional.empty();
        }

        return Optional.of(fanaticsTeam);
    }

    private  boolean isTeamValid(Tournament tournament, FanaticsTeam fanaticsTeam){
        int numPlayers=fanaticsTeam.getSquad().size();
        if(numPlayers!=tournament.getMaxPlayersPerTeam()) return false;
        int numBatters=(int) fanaticsTeam.getSquad().stream().filter(fanaticsTeamPlayer -> fanaticsTeamPlayer.getPlayer().getRole()==PlayerRole.BATTER).count();
        if(numBatters<tournament.getRequiredBatters()) return false;
        int numBowlers=(int) fanaticsTeam.getSquad().stream().filter(fanaticsTeamPlayer -> fanaticsTeamPlayer.getPlayer().getRole()==PlayerRole.BOWLER).count();
        if(numBowlers<tournament.getRequiredBowlers()) return false;
        int numWicketKeepers=(int) fanaticsTeam.getSquad().stream().filter(fanaticsTeamPlayer -> fanaticsTeamPlayer.getPlayer().getRole()==PlayerRole.WICKET_KEEPER).count();
        if(numWicketKeepers<tournament.getRequiredWicketKeepers()) return false;
        int numAllRounders=(int) fanaticsTeam.getSquad().stream().filter(fanaticsTeamPlayer -> fanaticsTeamPlayer.getPlayer().getRole()==PlayerRole.ALL_ROUNDER).count();
        if(numAllRounders<tournament.getRequiredAllRounders()) return false;
        return true;
    }
}
