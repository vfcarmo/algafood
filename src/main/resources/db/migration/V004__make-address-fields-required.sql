-- Defining a default value to required fields in restaurant table
update tab_restaurant set address_cep = "Enter" where address_cep is null;
update tab_restaurant set address_street = "Enter value" where address_street is null;
update tab_restaurant set address_number = "Enter value" where address_number is null;
update tab_restaurant set address_district = "Enter value" where address_district is null;

-- Defining a default value to required fields in order table
update tab_order set address_cep = "Enter" where address_cep is null;
update tab_order set address_street = "Enter value" where address_street is null;
update tab_order set address_number = "Enter value" where address_number is null;
update tab_order set address_district = "Enter value" where address_district is null;

-- Changing address fields to be required in restaurant table.
alter table tab_restaurant change address_cep address_cep varchar(9) not null;
alter table tab_restaurant change address_street address_street varchar(80) not null;
alter table tab_restaurant change address_number address_number varchar(20) not null;
alter table tab_restaurant change address_district address_district varchar(60) not null;

-- Changing address fields to be required in order table.
alter table tab_order change address_cep address_cep varchar(9) not null;
alter table tab_order change address_street address_street varchar(80) not null;
alter table tab_order change address_number address_number varchar(20) not null;
alter table tab_order change address_district address_district varchar(60) not null;