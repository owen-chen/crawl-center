drop table if exists stock;
create table stock (id bigint not null auto_increment, name varchar(255), code varchar(255), symbol varchar(255), addTime bigint, primary key (id)) type=MyISAM;
