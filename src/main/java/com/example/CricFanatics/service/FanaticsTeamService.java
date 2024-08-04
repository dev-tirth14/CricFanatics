package com.example.CricFanatics.service;

import com.example.CricFanatics.dto.FanaticsTeamDTO;
import com.example.CricFanatics.dto.PlayerStatDTO;
import com.example.CricFanatics.dto.TournamentDTO;
import com.example.CricFanatics.mapper.FanaticsTeamMapper;
import com.example.CricFanatics.mapper.FanaticsTeamPlayerMapper;
import com.example.CricFanatics.model.*;
import com.example.CricFanatics.repository.FanaticTeamRepository;
import com.example.CricFanatics.repository.FanaticsTeamPlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class FanaticsTeamService {
    @Autowired
    private FanaticTeamRepository fanaticTeamRepo;

    @Autowired
    private PlayerService playerService;

    @Autowired
    private FanaticsTeamPlayerRepository fanaticsTeamPlayerRepo;



    public Optional<FanaticsTeam> getFanaticsTeam(int id){
        return fanaticTeamRepo.findById(id);
    }
    public Set<FanaticsTeam> getAllFanaticsTeam(){
        return Set.copyOf(fanaticTeamRepo.findAll());
    }

    public Optional<FanaticsTeam> createFanaticsTeam(FanaticsTeamDTO fanaticsTeamDTO){
        FanaticsTeam fanaticsTeam= FanaticsTeamMapper.toEntity(fanaticsTeamDTO);
        try{
            fanaticsTeamDTO
                    .getSquad()
                    .stream()
                    .forEach(fanaticsTeamPlayerDTO ->{
                        Player player=playerService
                                .getPlayer(fanaticsTeamPlayerDTO.getPlayer_id())
                                .orElseThrow(()->new NoSuchElementException());
                        FanaticsTeamPlayer fanaticsTeamPlayer= FanaticsTeamPlayerMapper.toEntity(fanaticsTeamPlayerDTO);
                        fanaticsTeamPlayer.setPlayer(player);

                        fanaticsTeamPlayer.setTeam(fanaticsTeam);
                        fanaticsTeam.getSquad().add(fanaticsTeamPlayer);
                    });
        }catch (NoSuchElementException e){
            return Optional.empty();
        }
        return Optional.of(fanaticsTeam);
    }
    public Optional<FanaticsTeam> approveFanaticsTeam(int fanaticsTeamId){
        Optional<FanaticsTeam> fanaticsTeam=getFanaticsTeam(fanaticsTeamId);
        if(fanaticsTeam.isEmpty()){
            return Optional.empty();
        }
        FanaticsTeam fanaticsTeamEntity=fanaticsTeam.get();
        if(fanaticsTeamEntity.getStatus().equals(FanaticsTeamStatus.APPROVED)){
            return Optional.empty();
        }
        fanaticsTeamEntity.setStatus(FanaticsTeamStatus.APPROVED);
        updateFanaticsTeamScore(fanaticsTeamEntity);
        fanaticTeamRepo.save(fanaticsTeamEntity);
        return Optional.of(fanaticsTeamEntity);
    }

    private void updateFanaticsTeamScore(FanaticsTeam fanaticsTeam){
        Tournament tournament=fanaticsTeam.getTournament();
        fanaticsTeam.getSquad().forEach(fanaticsTeamPlayer -> {
                    int playerScore=fanaticsTeamPlayer.getPlayer().getStats().stream()
                            .mapToInt(playerStat->{
                                int score=
                                        (playerStat.getRunsScored()*tournament.getPointsPerRun())+
                                                (playerStat.getFours()*tournament.getPointsPerFour())+
                                                (playerStat.getSixes()*tournament.getPointsPerSix())+
                                                (playerStat.getWickets()*tournament.getPointsPerWicket())+
                                                (playerStat.getMaidenOvers()*tournament.getPointsForMaidenOver())+
                                                (playerStat.getWicketsCaught()*tournament.getPointsPerCatch())+
                                                (playerStat.getStrikeRate()>100.0?tournament.getPointsForStrikeRateOver100():0)-
                                                (playerStat.getStrikeRate()<50.0?tournament.getPointsDeductedForStrikeRateUnder50():0)+
                                                (playerStat.getEconomyRate().doubleValue()<=4?tournament.getPointsForEconomyRateUnder4():0)-
                                                (playerStat.getEconomyRate().doubleValue()>=9?tournament.getPointsDeductedForEconomyRateOver9():0);
                                return score;
                            })
                            .sum();
                    fanaticsTeamPlayer.setPoints(playerScore);
                }
            );
        fanaticTeamRepo.save(fanaticsTeam);
    }

    public void updateFanaticsPlayerScore(PlayerStat playerStat){
        Set<FanaticsTeamPlayer> fanaticsPlayersToUpdate=fanaticsTeamPlayerRepo.findAll().stream().filter(fanaticsTeamPlayer -> (fanaticsTeamPlayer.getPlayer().getId()==playerStat.getPlayer().getId() && fanaticsTeamPlayer.getTeam().getStatus()==FanaticsTeamStatus.APPROVED)).collect(Collectors.toSet());
        System.out.println("NUMBER OF PLAYERS POINTS TO UPDATE: "+fanaticsPlayersToUpdate.size());
        fanaticsPlayersToUpdate.forEach(fanaticsTeamPlayer -> {
            Tournament tournament=fanaticsTeamPlayer.getTeam().getTournament();
            fanaticsTeamPlayer.setPoints(fanaticsTeamPlayer.getPoints()+
                    (playerStat.getRunsScored()*tournament.getPointsPerRun())+
                    (playerStat.getFours()*tournament.getPointsPerFour())+
                    (playerStat.getSixes()*tournament.getPointsPerSix())+
                    (playerStat.getWickets()*tournament.getPointsPerWicket())+
                    (playerStat.getMaidenOvers()*tournament.getPointsForMaidenOver())+
                    (playerStat.getWicketsCaught()*tournament.getPointsPerCatch())+
                    (playerStat.getStrikeRate()>100.0?tournament.getPointsForStrikeRateOver100():0)-
                    (playerStat.getStrikeRate()<50.0?tournament.getPointsDeductedForStrikeRateUnder50():0)+
                    (playerStat.getEconomyRate().doubleValue()<=4?tournament.getPointsForEconomyRateUnder4():0)-
                    (playerStat.getEconomyRate().doubleValue()>=9?tournament.getPointsDeductedForEconomyRateOver9():0));
            fanaticsTeamPlayerRepo.save(fanaticsTeamPlayer);
        });
    }


}
