# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups
alter table t_user  modify	username  varchar(100)  default '',
							drop oil, 
							drop diamond,
							modify social_account_id varchar(100) not null default '',
							modify create_date timestamp default '1980-01-01 00:00:00';



# --- !Downs
alter table t_user  modify  username  varchar(100) not null default '', 
							modify social_account_id varchar(100)  default '';

