package com.example.CricFanatics;

import com.example.CricFanatics.dto.FanaticsTeamDTO;
import com.example.CricFanatics.dto.FanaticsTeamPlayerDTO;
import com.example.CricFanatics.dto.TournamentDTO;
import com.example.CricFanatics.model.*;
import com.example.CricFanatics.repository.*;
import com.example.CricFanatics.service.TournamentService;
import org.hibernate.jdbc.Expectation;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@SpringBootApplication
public class CricFanaticsApplication {

	public static void main(String[] args) {
		SpringApplication.run(CricFanaticsApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(TeamRepository teamRepo, MatchRepository matchRepo, PlayerRepository playerRepo, TournamentRepository tournamentRepo, FanaticTeamRepository fanaticTeamRepo, TournamentService tournamentService) {
		return runner ->  {
//			Tournament tournament=new Tournament(
//					"WCT20 2024",
//					"tirth1415",
//					1,
//					TournamentStatus.UPCOMING,
//					LocalDateTime.now(),
//					6,
//					2,
//					2,
//					0,
//					0,
//					1,
//					2,
//					1,
//					25,
//					10,
//					0,
//					0,
//					0,
//					0,
//					0,
//					new HashSet<>()
//			);
//			TournamentDTO dto=new TournamentDTO(
//					"",
//					"",
//					1,
//					"UPCOMING",
//					LocalDateTime.now(),
//					6,
//					2,
//					2,
//					0,
//					0,
//					1,
//					2,
//					1,
//					25,
//					10,
//					0,
//					0,
//					0,
//					0,
//					0,
//					new HashSet<>()
//			);
//			Optional<Tournament> tournament=tournamentService.createTournament(dto);
			FanaticsTeamDTO fanaticsTeamDTO=new FanaticsTeamDTO();
			fanaticsTeamDTO.setName("Tirth's Strikers");
			fanaticsTeamDTO.setOwner(1);
			fanaticsTeamDTO.setStatus("PENDING");
			fanaticsTeamDTO.setCreatedDate(LocalDateTime.now());

			fanaticsTeamDTO.setSquad(
					new HashSet<>(
							List.of(
									new FanaticsTeamPlayerDTO(
											0,
											3852
									),
									new FanaticsTeamPlayerDTO(
											0,
											9844
									),
									new FanaticsTeamPlayerDTO(
											0,
											3850
									),
									new FanaticsTeamPlayerDTO(
											0,
											3993
									)
							)
					)
			);

			FanaticsTeamDTO fanaticsTeamDTO2=new FanaticsTeamDTO();
			fanaticsTeamDTO2.setName("Abhi's Apes");
			fanaticsTeamDTO2.setOwner(2);
			fanaticsTeamDTO2.setStatus("PENDING");
			fanaticsTeamDTO2.setCreatedDate(LocalDateTime.now());

			fanaticsTeamDTO2.setSquad(
					new HashSet<>(
							List.of(
									new FanaticsTeamPlayerDTO(
											0,
											63751
									),
									new FanaticsTeamPlayerDTO(
											0,
											65584
									),
									new FanaticsTeamPlayerDTO(
											0,
											5313
									),
									new FanaticsTeamPlayerDTO(
											0,
											26718

									)
							)
					)
			);

			FanaticsTeamDTO fanaticsTeamDTO3=new FanaticsTeamDTO();
			fanaticsTeamDTO3.setName("Team BJP");
			fanaticsTeamDTO3.setOwner(3);
			fanaticsTeamDTO3.setStatus("PENDING");
			fanaticsTeamDTO3.setCreatedDate(LocalDateTime.now());

			fanaticsTeamDTO3.setSquad(
					new HashSet<>(
							List.of(
									new FanaticsTeamPlayerDTO(
											0,
											4661
									),
									new FanaticsTeamPlayerDTO(
											0,
											28035
									),
									new FanaticsTeamPlayerDTO(
											0,
											63641
									),
									new FanaticsTeamPlayerDTO(
											0,
											3852
									)
							)
					)
			);
//			tournamentService.createTeam(2,fanaticsTeamDTO2);
//			tournamentService.createTeam(2,fanaticsTeamDTO3);
//
//			Optional<FanaticsTeam> fanaticsTeam=tournamentService.createTeam(
//					2,
//					fanaticsTeamDTO
//			);
//			if(fanaticsTeam.isPresent()){
//				System.out.print(fanaticsTeam.get().getId());
//			}else{
//				System.out.print("SOMETHING WENT WRONG");
//			}



		};

	}

}
