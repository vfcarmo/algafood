-- Creates cookery table
CREATE TABLE tab_cookery (
  id bigint NOT NULL AUTO_INCREMENT,
  name varchar(60) NOT NULL,
  created_date datetime NOT NULL,
  updated_date datetime NOT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

create table tab_city (
 id bigint not null auto_increment,
 city_name varchar(60) not null,
 state_name varchar(50) not null,
 created_date datetime NOT NULL,
 updated_date datetime NOT NULL,
 primary key (id)
) engine=InnoDB DEFAULT CHARSET=utf8;