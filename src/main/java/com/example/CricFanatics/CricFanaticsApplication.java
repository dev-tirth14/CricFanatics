package com.example.CricFanatics;

import com.example.CricFanatics.model.Match;
import com.example.CricFanatics.model.MatchStat;
import com.example.CricFanatics.model.Team;
import com.example.CricFanatics.repository.MatchRepository;
import com.example.CricFanatics.repository.PlayerRepository;
import com.example.CricFanatics.repository.TeamRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;
import java.util.HashSet;

@SpringBootApplication
public class CricFanaticsApplication {

	public static void main(String[] args) {
		SpringApplication.run(CricFanaticsApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(TeamRepository teamRepo, MatchRepository matchRepo, PlayerRepository playerRepo) {
		return runner ->  {
//			Team rcb=new Team(1,"Royal Challengers Banglore",3,new HashSet<>(), new HashSet<>());
//			Team gt=new Team(2,"Gujarat Titans",2,new HashSet<>(), new HashSet<>());
			Team gt=teamRepo.findById(1).get();
			Team csk=teamRepo.findById(12).get();
//
//
//			Player vk=new Player(1,"Virat Kohli", PlayerRole.BATSMAN,50,null, new HashSet<>());
//			Player hp=new Player(2,"Hardik Pandya", PlayerRole.ALL_ROUNDER,50,null,new HashSet<>());
//			Player rk=new Player(3,"Rashid Khan", PlayerRole.ALL_ROUNDER,50,null, new HashSet<>());
//
////			vk.setTeam(rcb);
////			rcb.getSquad().add(vk);
//
//			hp.setTeam(gt);
//			gt.getSquad().add(hp);
//
//			rk.setTeam(gt);
//			gt.getSquad().add(rk);
//
//			teamRepo.save(gt);
//			teamRepo.save(rcb);
//
			Match m1=new Match(1, LocalDateTime.now(),"Barbados",2,new HashSet<>(), new HashSet<>());
//
			MatchStat m1T1=new MatchStat(csk,m1,170,4,20);
			MatchStat m1T2=new MatchStat(gt,m1,169,7,19);
//
			m1.getTeam_stats().add(m1T1);
			m1.getTeam_stats().add(m1T2);
			csk.getMatches().add(m1T1);
			gt.getMatches().add(m1T2);

			matchRepo.save(m1);

//			Match match=matchRepo.findById(1).get();
//			Set<Team> teams=match.getTeams().stream().map(s->s.getTeam()).collect(Collectors.toSet());
//
//			teams.forEach(team->System.out.println(team));
//			System.out.println(rcb.getSquad());
//			teamRepo.delete(gt);
//			Player vk=playerRepo.findById(1).get();
//			Player hp=playerRepo.findById(2).get();
//			Player rk=playerRepo.findById(3).get();
//
//			Match rcbVsGt=matchRepo.findById(1).get();

//			PlayerStat hpVsRcb=new PlayerStat(hp,rcbVsGt,30,0,0,0,0,0, BigDecimal.ZERO,0,0,0,0);
//			PlayerStat rkVsRcb=new PlayerStat(rk,rcbVsGt,4,0,0,0,0,0, BigDecimal.ZERO,0,0,0,0);
//			PlayerStat vkVsGT=new PlayerStat(vk,rcbVsGt,101,0,0,0,0,0, BigDecimal.ZERO,0,0,0,0);
//
//			rcbVsGt.getPlayer_stats().add(hpVsRcb);
//			hp.getStats().add(hpVsRcb);
//
//			rcbVsGt.getPlayer_stats().add(rkVsRcb);
//			rk.getStats().add(rkVsRcb);
//
//			rcbVsGt.getPlayer_stats().add(vkVsGT);
//			vk.getStats().add(vkVsGT);
//
//			matchRepo.save(rcbVsGt);
//			System.out.println(rcbVsGt.getPlayer_stats());
//teamRepo.delete(rcb);












		};
	}

}
