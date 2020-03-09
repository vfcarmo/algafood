set foreign_key_checks = 0;

delete from tab_cookery;
delete from tab_city;
delete from tab_state;
delete from tab_group;
delete from tab_permission;
delete from tab_group_permission;
delete from tab_payment_method;
delete from tab_user;
delete from tab_restaurant;
delete from tab_product;
delete from tab_product_photo;
delete from tab_order;
delete from tab_order_item;
delete from tab_restaurant_payment_method;
delete from tab_restaurant_user;
delete from tab_user_group;

set foreign_key_checks = 1;

alter table tab_cookery auto_increment = 1;
alter table tab_city auto_increment = 1;
alter table tab_state auto_increment = 1;
alter table tab_group auto_increment = 1;
alter table tab_permission auto_increment = 1;
alter table tab_payment_method auto_increment = 1;
alter table tab_user auto_increment = 1;
alter table tab_restaurant auto_increment = 1;
alter table tab_product auto_increment = 1;
alter table tab_product_photo auto_increment = 1;
alter table tab_order auto_increment = 1;
alter table tab_order_item auto_increment = 1;

insert into tab_cookery (name, created_date, updated_date) values ("Tailandesa", utc_timestamp, utc_timestamp);
insert into tab_cookery (name, created_date, updated_date) values ("Indiana", utc_timestamp, utc_timestamp);

insert into tab_state (name, created_date, updated_date) values ("Leinster", utc_timestamp, utc_timestamp);

insert into tab_city (name, state_id, created_date, updated_date) values ("Dublin", 1, utc_timestamp, utc_timestamp);
insert into tab_city (name, state_id, created_date, updated_date) values ("Malahide", 1, utc_timestamp, utc_timestamp);
insert into tab_city (name, state_id, created_date, updated_date) values ("Bray", 1, utc_timestamp, utc_timestamp);

insert into tab_restaurant (name, shipping_fee, cookery_id, address_cep, address_street, address_number, address_district, address_city_id, created_date, updated_date, open, active) values ("Thai Gourmet", 10, 1, "D02 DDE5", "Circular Road", "5", "Blabla", 1, utc_timestamp, utc_timestamp, 1, 1);
insert into tab_restaurant (name, shipping_fee, cookery_id, address_cep, address_street, address_number, address_district, address_city_id, created_date, updated_date, open, active) values ("Thai Delivery", 9.5, 1, "D12 DDE4", "Naas Road", "100", "Inchicore", 1, utc_timestamp, utc_timestamp, 1, 1);
insert into tab_restaurant (name, shipping_fee, cookery_id, address_cep, address_street, address_number, address_district, address_city_id, created_date, updated_date, open, active) values ("Tuk Tuk Comida Indiana", 15, 2, "M02 MME5", "Malahide Castle Street", "32", "Malahide Castle", 2, utc_timestamp, utc_timestamp, 1, 1);

insert into tab_payment_method (name, created_date, updated_date) values ("Credit Card", utc_timestamp, utc_timestamp);
insert into tab_payment_method (name, created_date, updated_date) values ("Debit Card", utc_timestamp, utc_timestamp);
insert into tab_payment_method (name, created_date, updated_date) values ("Cash", utc_timestamp, utc_timestamp);

insert into tab_restaurant_payment_method (restaurant_id, payment_method_id) values (1, 1), (1, 2), (1, 3), (2, 1), (2, 2), (3, 3)