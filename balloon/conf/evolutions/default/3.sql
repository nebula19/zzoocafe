# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups
alter table t_user  modify  social_account_id varchar(100) not null default '';



# --- !Downs
alter table t_user  modify  social_account_id bigint(20) default 0;

