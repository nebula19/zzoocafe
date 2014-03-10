# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table t_user (
  id                        bigint auto_increment not null,
  username                  varchar(100) not null default '',
  social_account_id         bigint(20) default 0,
  thumbnail_url             varchar(250) default '',
  gold                      int(5) default 0,
  oil                       int(5) default 0,
  diamond                   int(5) default 0,
  score                     int(11) default 0,
  create_date               timestamp default CURRENT_TIMESTAMP,
  constraint uq_t_user_username unique (username),
  constraint uq_t_user_social_account_id unique (social_account_id),
  constraint pk_t_user primary key (id))
;

create table t_user_weapon (
  id                        bigint auto_increment not null,
  user_id                   bigint,
  weapon_id                 bigint,
  power                     float,
  shooting_rate             float,
  critical_rate             float,
  velocity                  float,
  position                  integer,
  constraint pk_t_user_weapon primary key (id))
;

create table t_weapon (
  id                        bigint auto_increment not null,
  name                      varchar(255),
  type                      varchar(255),
  rarity                    varchar(255),
  image                     varchar(255),
  sound                     varchar(255),
  price                     integer,
  power                     float,
  shooting_rate             float,
  critical_rate             float,
  velocity                  float,
  create_date               timestamp default CURRENT_TIMESTAMP,
  constraint pk_t_weapon primary key (id))
;

alter table t_user_weapon add constraint fk_t_user_weapon_user_1 foreign key (user_id) references t_user (id) on delete restrict on update restrict;
create index ix_t_user_weapon_user_1 on t_user_weapon (user_id);



# --- !Downs

SET FOREIGN_KEY_CHECKS=0;

drop table t_user;

drop table t_user_weapon;

drop table t_weapon;

SET FOREIGN_KEY_CHECKS=1;

