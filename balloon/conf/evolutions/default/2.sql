# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups
rename table t_weapon to t_weapon_bak;

CREATE TABLE `t_weapon` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `grade` smallint(6) DEFAULT '1',
  `image` varchar(255) DEFAULT NULL,
  `sound` varchar(255) DEFAULT NULL,
  `price` int(11) DEFAULT NULL,
  `power` float DEFAULT NULL,
  `shooting_rate` float DEFAULT NULL,
  `critical_rate` float DEFAULT NULL,
  `velocity` float DEFAULT NULL,
  `create_date` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `modify_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

alter table t_user 	 modify create_date timestamp not null default '0000-00-00 00:00:00',
                     add    modify_date timestamp not null DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP;

alter table t_user_weapon 	add		create_date timestamp not null default '0000-00-00 00:00:00',
                     		add		modify_date timestamp not null DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP;




INSERT INTO `t_weapon` (`id`, `type`, `grade`, `name`, `power`, `shooting_rate`, `critical_rate`, `velocity`, `image`, `sound`, `price`, `create_date`, `modify_date`)
VALUES 
(1,'WT0021','1',' 망고 블라스터','30','5','10','10',' weapon00_1',' sfx00_1',500,NULL,NULL),
(2,'WT0021','1',' 애플 블라스터','30','5','10','10',' weapon00_1',' sfx00_1',500,NULL,NULL),
(3,'WT0021','1',' 블루베리 블라스터','30','5','10','10',' weapon00_2',' sfx00_2',500,NULL,NULL),
(4,'WT0021','2',' 토마토 블라스터','30','5','10','10',' weapon00_3',' sfx00_3',2000,NULL,NULL),
(5,'WT0021','2',' 오렌지 블라스터','30','5','10','10',' weapon00_4',' sfx00_4',2000,NULL,NULL),
(6,'WT0021','3',' 코코넛 블라스터','30','5','10','10',' weapon00_5',' sfx00_5',15000,NULL,NULL),
(7,'WT0022','1',' 레일 볼','30','5','10','10',' weapon01_1',' sfx01_1',500,NULL,NULL),
(8,'WT0022','1',' 레일 트라이','30','5','10','10',' weapon01_2',' sfx01_2',500,NULL,NULL),
(9,'WT0022','1',' 레일 박스','30','5','10','10',' weapon01_3',' sfx01_3',500,NULL,NULL),
(10,'WT0022','2',' 레일 웨이브','30','5','10','10',' weapon01_4',' sfx01_4',2000,NULL,NULL),
(11,'WT0022','2',' 레일 하트','30','5','10','10',' weapon01_5',' sfx01_5',2000,NULL,NULL),
(12,'WT0022','3',' 레일 스타','30','5','10','10',' weapon01_6',' sfx01_6',15000,NULL,NULL),
(13,'WT0023','2',' 소닉 애로우','30','5','10','10',' weapon02_1',' sfx02_1',2000,NULL,NULL),
(14,'WT0023','2',' 자벨 런쳐','30','5','10','10',' weapon02_2',' sfx02_2',2000,NULL,NULL),
(15,'WT0023','3',' 펄스 수리검','30','5','10','10',' weapon02_3',' sfx02_3',15000,NULL,NULL),
(16,'WT0024','2',' 아토믹 박격탄','30','5','10','10',' weapon03_1',' sfx03_1',2000,NULL,NULL),
(17,'WT0024','2',' RM 박격탄','30','5','10','10',' weapon03_2',' sfx03_2',2000,NULL,NULL),
(18,'WT0024','3',' EMP 충격탄','30','5','10','10',' weapon03_3',' sfx03_3',15000,NULL,NULL),
(19,'WT0025','2',' 플라즈마 캐논','30','5','10','10',' weapon04_1',' sfx04_1',2000,NULL,NULL),
(20,'WT0025','2',' 이온 캐논','30','5','10','10',' weapon04_2',' sfx04_2',2000,NULL,NULL),
(21,'WT0025','3',' 슈퍼 노바','30','5','10','10',' weapon04_3',' sfx04_3',10000,NULL,NULL);


# --- !Downs

SET FOREIGN_KEY_CHECKS=0;

drop table if exists t_weapon;
rename table t_weapon_bak to t_weapon;


SET FOREIGN_KEY_CHECKS=1;

