DROP SCHEMA IF EXISTS `cricFanatics`;

CREATE SCHEMA `cricFanatics`;

use `cricFanatics`;

SET FOREIGN_KEY_CHECKS = 0;



CREATE TABLE `player` (
  `id` int NOT NULL,
  `full_name` varchar(50) NOT NULL,
  `role` varchar(20) DEFAULT NULL,
  `cost` int DEFAULT NULL,
  `team_id` int NOT NULL,

  PRIMARY KEY (`id`),

  KEY `FK_team_idx` (`team_id`),
  CONSTRAINT `FK_team` FOREIGN KEY (`team_id`)
  REFERENCES `team` (`id`) ON DELETE CASCADE ON UPDATE CASCADE

) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `team` (
  `id` int NOT NULL,
  `name` varchar(50) NOT NULL,
  `rank` int DEFAULT NULL,

  PRIMARY KEY (`id`)

) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `cric_match` (
  `id` int NOT NULL,
  `date_time` datetime default NULL,
  `location` varchar(50) default NULL,
  `match_number` int default null,

  PRIMARY KEY (`id`)

) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `team_match_stat` (
  `match_id` int NOT NULL,
  `team_id` int NOT NULL,
  `total_runs` int DEFAULT NULL,
  `total_wickets` int DEFAULT NULL,
  `overs_played` int DEFAULT NULL,

  PRIMARY KEY (`match_id`,`team_id`),

  CONSTRAINT `FK_match_team_match_stat` FOREIGN KEY (`match_id`)
  REFERENCES `cric_match` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,

  CONSTRAINT `FK_team_team_match_stat` FOREIGN KEY (`team_id`)
  REFERENCES `team` (`id`) ON DELETE CASCADE ON UPDATE CASCADE

) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `player_match_stat` (
  `match_id` int NOT NULL,
  `player_id` int NOT NULL,
  `runs_scored` int DEFAULT NULL,
  `strike_rate` double DEFAULT NULL,
  `balls_played` int DEFAULT NULL,
  `sixes` int DEFAULT NULL,
  `fours` int DEFAULT NULL,
  `wickets` int DEFAULT NULL,
  `economy_rate` double DEFAULT NULL,
  `overs_bowled` int DEFAULT NULL,
  `runs_given` int DEFAULT NULL,
  `maiden_overs` int DEFAULT NULL,

  PRIMARY KEY (`match_id`,`player_id`),

  CONSTRAINT `FK_match_player_match_stat` FOREIGN KEY (`match_id`)
  REFERENCES `cric_match` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,

  CONSTRAINT `FK_player_player_match_stat` FOREIGN KEY (`player_id`)
  REFERENCES `player` (`id`) ON DELETE CASCADE ON UPDATE CASCADE

) ENGINE=InnoDB DEFAULT CHARSET=latin1;

SET FOREIGN_KEY_CHECKS = 1;
