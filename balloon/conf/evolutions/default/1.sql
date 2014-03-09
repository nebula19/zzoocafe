# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table t_planet (
  id                        bigint auto_increment not null,
  name                      varchar(100) not null,
  constraint uq_t_planet_name unique (name),
  constraint pk_t_planet primary key (id))
;

create table t_user (
  id                        bigint auto_increment not null,
  username                  varchar(100) not null default '',
  user_id                   bigint(20) default 0,
  thumbnail_url             varchar(250) default '',
  gold                      int(5) default 0,
  oil                       int(5) default 0,
  diamond                   int(5) default 0,
  score                     int(11) default 0,
  create_date               timestamp default CURRENT_TIMESTAMP,
  constraint uq_t_user_username unique (username),
  constraint uq_t_user_user_id unique (user_id),
  constraint pk_t_user primary key (id))
;

create table t_user_buff (
  user_uid                  bigint auto_increment not null,
  hero_power                float not null default 0.0,
  hero_shoot_speed          float default 0.0,
  hero_critical_ratio       float default 0.0,
  hero_critical_damage      float default 0.0,
  mob_red_create_ratio      float default 0.0,
  mob_red_hp                float default 0.0,
  mob_red_item_ratio        float default 0.0,
  mob_red_point             float default 0.0,
  mob_green_create_ratio    float default 0.0,
  mob_green_hp              float default 0.0,
  mob_green_item_ratio      float default 0.0,
  mob_green_point           float default 0.0,
  mob_blue_create_ratio     float default 0.0,
  mob_blue_hp               float default 0.0,
  mob_blue_item_ratio       float default 0.0,
  mob_blue_point            float default 0.0,
  mob_yellow_create_ratio   float default 0.0,
  mob_yellow_hp             float default 0.0,
  mob_yellow_item_ratio     float default 0.0,
  mob_yellow_point          float default 0.0,
  mob_black_create_ratio    float default 0.0,
  mob_black_hp              float default 0.0,
  mob_black_item_ratio      float default 0.0,
  mob_black_point           float default 0.0,
  pz_reload_speed           float default 0.0,
  item_shoot_velocity       float default 0.0,
  item_drop_acceleration    float default 0.0,
  game_play_time            float default 0.0,
  constraint pk_t_user_buff primary key (user_uid))
;

create table user_weapon (
  username                  varchar(100) not null default '',
  user_id                   bigint(20) default 0,
  thumbnail_url             varchar(250) default '',
  gold                      int(5) default 0,
  oil                       int(5) default 0,
  diamond                   int(5) default 0,
  score                     int(11) default 0,
  create_date               timestamp default CURRENT_TIMESTAMP,
  weapon_id                 bigint,
  damage                    float,
  shooting_rate             float,
  critical_rate             float,
  velocity                  float,
  position                  integer,
  constraint uq_t_user_username unique (username),
  constraint uq_t_user_user_id unique (user_id),
  constraint pk_user_weapon primary key (username, user_id, thumbnail_url, gold, oil, diamond, score, create_date))
;

create table t_weapon (
  id                        bigint auto_increment not null,
  name                      varchar(255),
  type                      varchar(255),
  grade                     varchar(255),
  image                     varchar(255),
  sound                     varchar(255),
  price                     integer,
  create_date               timestamp default CURRENT_TIMESTAMP,
  constraint pk_t_weapon primary key (id))
;

alter table user_weapon add constraint fk_user_weapon_t_user_1 foreign key (user_id) references t_user (id) on delete restrict on update restrict;
create index ix_user_weapon_t_user_1 on user_weapon (user_id);



# --- !Downs

SET FOREIGN_KEY_CHECKS=0;

drop table t_planet;

drop table t_user;

drop table t_user_buff;

drop table user_weapon;

drop table t_weapon;

SET FOREIGN_KEY_CHECKS=1;

