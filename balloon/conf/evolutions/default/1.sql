# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table user (
  id                        bigint auto_increment not null,
  username                  varchar(100) not null default '',
  user_id                   bigint(20) not null default 0,
  thumbnail_url             varchar(250) default '',
  gold                      int(5) default 0,
  oil                       int(5) default 0,
  diamond                   int(5) default 0,
  score                     int(11) default 0,
  constraint pk_user primary key (id))
;

create table user_buffs (
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
  constraint pk_user_buffs primary key (user_uid))
;




# --- !Downs

SET FOREIGN_KEY_CHECKS=0;

drop table user;

drop table user_buffs;

SET FOREIGN_KEY_CHECKS=1;

