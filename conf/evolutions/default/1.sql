# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table user_model (
  id                            integer auto_increment not null,
  username                      varchar(255),
  password                      varchar(255),
  constraint pk_user_model primary key (id)
);


# --- !Downs

drop table if exists user_model;

