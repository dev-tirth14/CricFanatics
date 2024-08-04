package com.example.CricFanatics.mapper;

import com.example.CricFanatics.dto.FanaticsTeamDTO;
import com.example.CricFanatics.dto.TeamDTO;
import com.example.CricFanatics.dto.TournamentDTO;
import com.example.CricFanatics.model.Team;
import com.example.CricFanatics.model.Tournament;
import com.example.CricFanatics.model.TournamentStatus;

import java.util.Comparator;
import java.util.HashSet;
import java.util.stream.Collectors;

public class TournamentMapper {
    public static TournamentDTO toDTO(Tournament tournament) {
        TournamentDTO tournamentDTO=new TournamentDTO();

        tournamentDTO.setId(tournament.getId());
        tournamentDTO.setName(tournament.getName());
        tournamentDTO.setUniqueCode(tournament.getUniqueCode());
        tournamentDTO.setOrganizer(tournament.getOrganizer());
        tournamentDTO.setStatus(tournament.getStatus().toString());
        tournamentDTO.setCreatedDate(tournament.getCreatedDate());

        tournamentDTO.setMaxPlayersPerTeam(tournament.getMaxPlayersPerTeam());
        tournamentDTO.setRequiredBatters(tournament.getRequiredBatters());
        tournamentDTO.setRequiredBowlers(tournament.getRequiredBowlers());
        tournamentDTO.setRequiredAllRounders(tournament.getRequiredAllRounders());
        tournamentDTO.setRequiredWicketKeepers(tournament.getRequiredWicketKeepers());
        tournamentDTO.setPointsPerRun(tournament.getPointsPerRun());
        tournamentDTO.setPointsPerSix(tournament.getPointsPerSix());
        tournamentDTO.setPointsPerFour(tournament.getPointsPerFour());
        tournamentDTO.setPointsPerWicket(tournament.getPointsPerWicket());
        tournamentDTO.setPointsPerCatch(tournament.getPointsPerCatch());

        tournamentDTO.setPointsForStrikeRateOver100(tournament.getPointsForStrikeRateOver100());
        tournamentDTO.setPointsDeductedForStrikeRateUnder50(tournament.getPointsDeductedForStrikeRateUnder50());
        tournamentDTO.setPointsForEconomyRateUnder4(tournament.getPointsForEconomyRateUnder4());
        tournamentDTO.setPointsDeductedForEconomyRateOver9(tournament.getPointsDeductedForEconomyRateOver9());
        tournamentDTO.setPointsForMaidenOver(tournament.getPointsForMaidenOver());

        tournamentDTO.setTeams(tournament.getTeams().stream().map(team->FanaticsTeamMapper.toDTO(team)).sorted(Comparator.comparingInt(FanaticsTeamDTO::getPoints).reversed()).toList());

        return tournamentDTO;

    }

    public static Tournament toEntity(TournamentDTO tournamentDTO) {
        return new Tournament(tournamentDTO.getId(),tournamentDTO.getName(),tournamentDTO.getUniqueCode(),tournamentDTO.getOrganizer(), TournamentStatus.valueOf(tournamentDTO.getStatus()),tournamentDTO.getCreatedDate(),tournamentDTO.getMaxPlayersPerTeam(),tournamentDTO.getRequiredBatters(),tournamentDTO.getRequiredBowlers(),tournamentDTO.getRequiredAllRounders(),tournamentDTO.getRequiredWicketKeepers(),tournamentDTO.getPointsPerRun(),tournamentDTO.getPointsPerSix(),tournamentDTO.getPointsPerFour(),tournamentDTO.getPointsPerWicket(),tournamentDTO.getPointsPerCatch(),tournamentDTO.getPointsForStrikeRateOver100(),tournamentDTO.getPointsDeductedForStrikeRateUnder50(),tournamentDTO.getPointsForEconomyRateUnder4(),tournamentDTO.getPointsDeductedForEconomyRateOver9(),tournamentDTO.getPointsForMaidenOver(), new HashSet<>());
    }
}
