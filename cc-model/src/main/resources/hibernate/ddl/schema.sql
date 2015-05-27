drop table if exists history_trade;
drop table if exists stock;
create table history_trade (id bigint not null auto_increment, symbol varchar(255), currentTime bigint, current float, quoteTrend float, dealAmount integer, dealFigure bigint, feature integer, primary key (id)) type=MyISAM;
create table stock (id bigint not null auto_increment, name varchar(255), code varchar(255), symbol varchar(255), addTime bigint, primary key (id)) type=MyISAM;
