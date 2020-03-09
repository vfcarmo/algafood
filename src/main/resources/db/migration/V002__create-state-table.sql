-- Creating table to store states
create table tab_state(
id bigint not null auto_increment,
name varchar(60) not null,
created_date datetime NULL,
updated_date datetime NULL,
primary key (id)
) engine=InnoDB default charset=utf8;

-- Inserting states into state table
insert into tab_state (name, created_date, updated_date) select distinct c.state_name, now(), now() from tab_city c;

-- Creating column to refer to state table
alter table tab_city add column state_id bigint not null;

-- Populating the new column, and created_date and updated_date columns
update tab_city c set c.state_id = ( select s.id from tab_state s where c.state_name = s.name ), created_date = now(), updated_date = now();

alter table tab_city drop column state_name;

-- Changing column name
alter table tab_city change city_name name varchar(80) not null;

-- Changing columns to be not null
alter table tab_city change created_date created_date datetime not null;
alter table tab_city change updated_date updated_date datetime not null;