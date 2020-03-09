create table tab_city (id bigint not null auto_increment, created_date datetime(6), updated_date datetime(6), name varchar(255) not null, state_id bigint not null, primary key (id)) engine=InnoDB
create table tab_cookery (id bigint not null auto_increment, created_date datetime(6), updated_date datetime(6), name varchar(255) not null, primary key (id)) engine=InnoDB
create table tab_group (id bigint not null auto_increment, created_date datetime(6), updated_date datetime(6), name varchar(255) not null, primary key (id)) engine=InnoDB
create table tab_group_permission (group_id bigint not null, permission_id bigint not null, primary key (group_id, permission_id)) engine=InnoDB
create table tab_order (id bigint not null auto_increment, created_date datetime(6), updated_date datetime(6), cancellation_date datetime(6), code varchar(255) not null, confirmation_date datetime(6), address_cep varchar(255), address_complement varchar(255), address_district varchar(255), address_number varchar(255), address_street varchar(255), delivery_date datetime(6), shipping_fee decimal(19,2) not null, status integer, sub_total decimal(19,2) not null, total_value decimal(19,2) not null, customer_id bigint not null, address_city_id bigint not null, payment_method_id bigint not null, restaurant_id bigint not null, primary key (id)) engine=InnoDB
create table tab_payment_method (id bigint not null auto_increment, created_date datetime(6), updated_date datetime(6), name varchar(255) not null, primary key (id)) engine=InnoDB
create table tab_permission (id bigint not null auto_increment, created_date datetime(6), updated_date datetime(6), description varchar(255) not null, name varchar(255) not null, primary key (id)) engine=InnoDB
create table tab_product (id bigint not null auto_increment, created_date datetime(6), updated_date datetime(6), active bit not null, description varchar(255) not null, name varchar(255) not null, value decimal(19,2) not null, restaurant_id bigint not null, primary key (id)) engine=InnoDB
create table tab_product_item (id bigint not null auto_increment, created_date datetime(6), updated_date datetime(6), observation varchar(255), quantity integer not null, total_value decimal(19,2) not null, unit_value decimal(19,2) not null, order_id bigint not null, product_id bigint not null, primary key (id)) engine=InnoDB
create table tab_product_photo (id bigint not null auto_increment, created_date datetime(6), updated_date datetime(6), content_type varchar(255) not null, description varchar(255) not null, length bigint not null, name varchar(255) not null, product_id bigint not null, primary key (id)) engine=InnoDB
create table tab_restaurant (id bigint not null auto_increment, created_date datetime(6), updated_date datetime(6), active bit not null, address_cep varchar(255), address_complement varchar(255), address_district varchar(255), address_number varchar(255), address_street varchar(255), name varchar(255) not null, open bit not null, shipping_fee decimal(19,2) not null, address_city_id bigint not null, cookery_id bigint not null, primary key (id)) engine=InnoDB
create table tab_restaurant_payment_method (restaurant_id bigint not null, payment_method_id bigint not null, primary key (restaurant_id, payment_method_id)) engine=InnoDB
create table tab_restaurant_user (restaurant_id bigint not null, user_id bigint not null, primary key (restaurant_id, user_id)) engine=InnoDB
create table tab_state (id bigint not null auto_increment, created_date datetime(6), updated_date datetime(6), name varchar(255) not null, primary key (id)) engine=InnoDB
create table tab_user (id bigint not null auto_increment, created_date datetime(6), updated_date datetime(6), email varchar(255) not null, name varchar(255) not null, password varchar(255) not null, primary key (id)) engine=InnoDB
create table tab_user_group (user_id bigint not null, group_id bigint not null) engine=InnoDB
alter table tab_city add constraint fk_state foreign key (state_id) references tab_state (id)
alter table tab_group_permission add constraint fk_permission_group foreign key (permission_id) references tab_permission (id)
alter table tab_group_permission add constraint fk_group_permission foreign key (group_id) references tab_group (id)
alter table tab_order add constraint fk_customer foreign key (customer_id) references tab_user (id)
alter table tab_order add constraint FK5wvnusqb21o32vqaepv87at1n foreign key (address_city_id) references tab_city (id)
alter table tab_order add constraint fk_payment_method foreign key (payment_method_id) references tab_payment_method (id)
alter table tab_order add constraint fk_restaurant foreign key (restaurant_id) references tab_restaurant (id)
alter table tab_product add constraint fk_restaurant foreign key (restaurant_id) references tab_restaurant (id)
alter table tab_product_item add constraint fk_order_product_item foreign key (order_id) references tab_order (id)
alter table tab_product_item add constraint fk_product_product_item foreign key (product_id) references tab_product (id)
alter table tab_product_photo add constraint fk_product foreign key (product_id) references tab_product (id)
alter table tab_restaurant add constraint FKmjga3rvuox3hrgv5cwrutbkn7 foreign key (address_city_id) references tab_city (id)
alter table tab_restaurant add constraint fk_cookery foreign key (cookery_id) references tab_cookery (id)
alter table tab_restaurant_payment_method add constraint fk_payment_method_restaurant foreign key (payment_method_id) references tab_payment_method (id)
alter table tab_restaurant_payment_method add constraint fk_restaurant_payment_method foreign key (restaurant_id) references tab_restaurant (id)
alter table tab_restaurant_user add constraint fk_responsible_restaurant foreign key (user_id) references tab_user (id)
alter table tab_restaurant_user add constraint fk_restaurant_responsible foreign key (restaurant_id) references tab_restaurant (id)
alter table tab_user_group add constraint fk_group_user foreign key (group_id) references tab_group (id)
alter table tab_user_group add constraint fk_user_group foreign key (user_id) references tab_user (id)
create table tab_city (id bigint not null auto_increment, created_date datetime(6), updated_date datetime(6), name varchar(255) not null, state_id bigint not null, primary key (id)) engine=InnoDB
create table tab_cookery (id bigint not null auto_increment, created_date datetime(6), updated_date datetime(6), name varchar(255) not null, primary key (id)) engine=InnoDB
create table tab_group (id bigint not null auto_increment, created_date datetime(6), updated_date datetime(6), name varchar(255) not null, primary key (id)) engine=InnoDB
create table tab_group_permission (group_id bigint not null, permission_id bigint not null, primary key (group_id, permission_id)) engine=InnoDB
create table tab_order (id bigint not null auto_increment, created_date datetime(6), updated_date datetime(6), cancellation_date datetime(6), code varchar(255) not null, confirmation_date datetime(6), address_cep varchar(255), address_complement varchar(255), address_district varchar(255), address_number varchar(255), address_street varchar(255), delivery_date datetime(6), shipping_fee decimal(19,2) not null, status integer, sub_total decimal(19,2) not null, total_value decimal(19,2) not null, customer_id bigint not null, address_city_id bigint not null, payment_method_id bigint not null, restaurant_id bigint not null, primary key (id)) engine=InnoDB
create table tab_payment_method (id bigint not null auto_increment, created_date datetime(6), updated_date datetime(6), name varchar(255) not null, primary key (id)) engine=InnoDB
create table tab_permission (id bigint not null auto_increment, created_date datetime(6), updated_date datetime(6), description varchar(255) not null, name varchar(255) not null, primary key (id)) engine=InnoDB
create table tab_product (id bigint not null auto_increment, created_date datetime(6), updated_date datetime(6), active bit not null, description varchar(255) not null, name varchar(255) not null, value decimal(19,2) not null, restaurant_id bigint not null, primary key (id)) engine=InnoDB
create table tab_product_item (id bigint not null auto_increment, created_date datetime(6), updated_date datetime(6), observation varchar(255), quantity integer not null, total_value decimal(19,2) not null, unit_value decimal(19,2) not null, order_id bigint not null, product_id bigint not null, primary key (id)) engine=InnoDB
create table tab_product_photo (id bigint not null auto_increment, created_date datetime(6), updated_date datetime(6), content_type varchar(255) not null, description varchar(255) not null, length bigint not null, name varchar(255) not null, product_id bigint not null, primary key (id)) engine=InnoDB
create table tab_restaurant (id bigint not null auto_increment, created_date datetime(6), updated_date datetime(6), active bit not null, address_cep varchar(255), address_complement varchar(255), address_district varchar(255), address_number varchar(255), address_street varchar(255), name varchar(255) not null, open bit not null, shipping_fee decimal(19,2) not null, address_city_id bigint not null, cookery_id bigint not null, primary key (id)) engine=InnoDB
create table tab_restaurant_payment_method (restaurant_id bigint not null, payment_method_id bigint not null, primary key (restaurant_id, payment_method_id)) engine=InnoDB
create table tab_restaurant_user (restaurant_id bigint not null, user_id bigint not null, primary key (restaurant_id, user_id)) engine=InnoDB
create table tab_state (id bigint not null auto_increment, created_date datetime(6), updated_date datetime(6), name varchar(255) not null, primary key (id)) engine=InnoDB
create table tab_user (id bigint not null auto_increment, created_date datetime(6), updated_date datetime(6), email varchar(255) not null, name varchar(255) not null, password varchar(255) not null, primary key (id)) engine=InnoDB
create table tab_user_group (user_id bigint not null, group_id bigint not null) engine=InnoDB
alter table tab_city add constraint fk_state foreign key (state_id) references tab_state (id)
alter table tab_group_permission add constraint fk_permission_group foreign key (permission_id) references tab_permission (id)
alter table tab_group_permission add constraint fk_group_permission foreign key (group_id) references tab_group (id)
alter table tab_order add constraint fk_customer foreign key (customer_id) references tab_user (id)
alter table tab_order add constraint FK5wvnusqb21o32vqaepv87at1n foreign key (address_city_id) references tab_city (id)
alter table tab_order add constraint fk_payment_method foreign key (payment_method_id) references tab_payment_method (id)
alter table tab_order add constraint fk_restaurant foreign key (restaurant_id) references tab_restaurant (id)
alter table tab_product add constraint fk_restaurant foreign key (restaurant_id) references tab_restaurant (id)
alter table tab_product_item add constraint fk_order_product_item foreign key (order_id) references tab_order (id)
alter table tab_product_item add constraint fk_product_product_item foreign key (product_id) references tab_product (id)
alter table tab_product_photo add constraint fk_product foreign key (product_id) references tab_product (id)
alter table tab_restaurant add constraint FKmjga3rvuox3hrgv5cwrutbkn7 foreign key (address_city_id) references tab_city (id)
alter table tab_restaurant add constraint fk_cookery foreign key (cookery_id) references tab_cookery (id)
alter table tab_restaurant_payment_method add constraint fk_payment_method_restaurant foreign key (payment_method_id) references tab_payment_method (id)
alter table tab_restaurant_payment_method add constraint fk_restaurant_payment_method foreign key (restaurant_id) references tab_restaurant (id)
alter table tab_restaurant_user add constraint fk_responsible_restaurant foreign key (user_id) references tab_user (id)
alter table tab_restaurant_user add constraint fk_restaurant_responsible foreign key (restaurant_id) references tab_restaurant (id)
alter table tab_user_group add constraint fk_group_user foreign key (group_id) references tab_group (id)
alter table tab_user_group add constraint fk_user_group foreign key (user_id) references tab_user (id)
create table tab_city (id bigint not null auto_increment, created_date datetime(6), updated_date datetime(6), name varchar(255) not null, state_id bigint not null, primary key (id)) engine=InnoDB
create table tab_cookery (id bigint not null auto_increment, created_date datetime(6), updated_date datetime(6), name varchar(255) not null, primary key (id)) engine=InnoDB
create table tab_group (id bigint not null auto_increment, created_date datetime(6), updated_date datetime(6), name varchar(255) not null, primary key (id)) engine=InnoDB
create table tab_group_permission (group_id bigint not null, permission_id bigint not null, primary key (group_id, permission_id)) engine=InnoDB
create table tab_order (id bigint not null auto_increment, created_date datetime(6), updated_date datetime(6), cancellation_date datetime(6), code varchar(255) not null, confirmation_date datetime(6), address_cep varchar(255), address_complement varchar(255), address_district varchar(255), address_number varchar(255), address_street varchar(255), delivery_date datetime(6), shipping_fee decimal(19,2) not null, status integer, sub_total decimal(19,2) not null, total_value decimal(19,2) not null, customer_id bigint not null, address_city_id bigint not null, payment_method_id bigint not null, restaurant_id bigint not null, primary key (id)) engine=InnoDB
create table tab_payment_method (id bigint not null auto_increment, created_date datetime(6), updated_date datetime(6), name varchar(255) not null, primary key (id)) engine=InnoDB
create table tab_permission (id bigint not null auto_increment, created_date datetime(6), updated_date datetime(6), description varchar(255) not null, name varchar(255) not null, primary key (id)) engine=InnoDB
create table tab_product (id bigint not null auto_increment, created_date datetime(6), updated_date datetime(6), active bit not null, description varchar(255) not null, name varchar(255) not null, value decimal(19,2) not null, restaurant_id bigint not null, primary key (id)) engine=InnoDB
create table tab_product_item (id bigint not null auto_increment, created_date datetime(6), updated_date datetime(6), observation varchar(255), quantity integer not null, total_value decimal(19,2) not null, unit_value decimal(19,2) not null, order_id bigint not null, product_id bigint not null, primary key (id)) engine=InnoDB
create table tab_product_photo (id bigint not null auto_increment, created_date datetime(6), updated_date datetime(6), content_type varchar(255) not null, description varchar(255) not null, length bigint not null, name varchar(255) not null, product_id bigint not null, primary key (id)) engine=InnoDB
create table tab_restaurant (id bigint not null auto_increment, created_date datetime(6), updated_date datetime(6), active bit not null, address_cep varchar(255), address_complement varchar(255), address_district varchar(255), address_number varchar(255), address_street varchar(255), name varchar(255) not null, open bit not null, shipping_fee decimal(19,2) not null, address_city_id bigint not null, cookery_id bigint not null, primary key (id)) engine=InnoDB
create table tab_restaurant_payment_method (restaurant_id bigint not null, payment_method_id bigint not null, primary key (restaurant_id, payment_method_id)) engine=InnoDB
create table tab_restaurant_user (restaurant_id bigint not null, user_id bigint not null, primary key (restaurant_id, user_id)) engine=InnoDB
create table tab_state (id bigint not null auto_increment, created_date datetime(6), updated_date datetime(6), name varchar(255) not null, primary key (id)) engine=InnoDB
create table tab_user (id bigint not null auto_increment, created_date datetime(6), updated_date datetime(6), email varchar(255) not null, name varchar(255) not null, password varchar(255) not null, primary key (id)) engine=InnoDB
create table tab_user_group (user_id bigint not null, group_id bigint not null) engine=InnoDB
alter table tab_city add constraint fk_state foreign key (state_id) references tab_state (id)
alter table tab_group_permission add constraint fk_permission_group foreign key (permission_id) references tab_permission (id)
alter table tab_group_permission add constraint fk_group_permission foreign key (group_id) references tab_group (id)
alter table tab_order add constraint fk_customer foreign key (customer_id) references tab_user (id)
alter table tab_order add constraint FK5wvnusqb21o32vqaepv87at1n foreign key (address_city_id) references tab_city (id)
alter table tab_order add constraint fk_payment_method foreign key (payment_method_id) references tab_payment_method (id)
alter table tab_order add constraint fk_restaurant foreign key (restaurant_id) references tab_restaurant (id)
alter table tab_product add constraint fk_restaurant foreign key (restaurant_id) references tab_restaurant (id)
alter table tab_product_item add constraint fk_order_product_item foreign key (order_id) references tab_order (id)
alter table tab_product_item add constraint fk_product_product_item foreign key (product_id) references tab_product (id)
alter table tab_product_photo add constraint fk_product foreign key (product_id) references tab_product (id)
alter table tab_restaurant add constraint FKmjga3rvuox3hrgv5cwrutbkn7 foreign key (address_city_id) references tab_city (id)
alter table tab_restaurant add constraint fk_cookery foreign key (cookery_id) references tab_cookery (id)
alter table tab_restaurant_payment_method add constraint fk_payment_method_restaurant foreign key (payment_method_id) references tab_payment_method (id)
alter table tab_restaurant_payment_method add constraint fk_restaurant_payment_method foreign key (restaurant_id) references tab_restaurant (id)
alter table tab_restaurant_user add constraint fk_responsible_restaurant foreign key (user_id) references tab_user (id)
alter table tab_restaurant_user add constraint fk_restaurant_responsible foreign key (restaurant_id) references tab_restaurant (id)
alter table tab_user_group add constraint fk_group_user foreign key (group_id) references tab_group (id)
alter table tab_user_group add constraint fk_user_group foreign key (user_id) references tab_user (id)
create table tab_city (id bigint not null auto_increment, created_date datetime(6), updated_date datetime(6), name varchar(255) not null, state_id bigint not null, primary key (id)) engine=InnoDB
create table tab_cookery (id bigint not null auto_increment, created_date datetime(6), updated_date datetime(6), name varchar(255) not null, primary key (id)) engine=InnoDB
create table tab_group (id bigint not null auto_increment, created_date datetime(6), updated_date datetime(6), name varchar(255) not null, primary key (id)) engine=InnoDB
create table tab_group_permission (group_id bigint not null, permission_id bigint not null, primary key (group_id, permission_id)) engine=InnoDB
create table tab_order (id bigint not null auto_increment, created_date datetime(6), updated_date datetime(6), cancellation_date datetime(6), code varchar(255) not null, confirmation_date datetime(6), address_cep varchar(255), address_complement varchar(255), address_district varchar(255), address_number varchar(255), address_street varchar(255), delivery_date datetime(6), shipping_fee decimal(19,2) not null, status integer, sub_total decimal(19,2) not null, total_value decimal(19,2) not null, customer_id bigint not null, address_city_id bigint not null, payment_method_id bigint not null, restaurant_id bigint not null, primary key (id)) engine=InnoDB
create table tab_payment_method (id bigint not null auto_increment, created_date datetime(6), updated_date datetime(6), name varchar(255) not null, primary key (id)) engine=InnoDB
create table tab_permission (id bigint not null auto_increment, created_date datetime(6), updated_date datetime(6), description varchar(255) not null, name varchar(255) not null, primary key (id)) engine=InnoDB
create table tab_product (id bigint not null auto_increment, created_date datetime(6), updated_date datetime(6), active bit not null, description varchar(255) not null, name varchar(255) not null, value decimal(19,2) not null, restaurant_id bigint not null, primary key (id)) engine=InnoDB
create table tab_product_item (id bigint not null auto_increment, created_date datetime(6), updated_date datetime(6), observation varchar(255), quantity integer not null, total_value decimal(19,2) not null, unit_value decimal(19,2) not null, order_id bigint not null, product_id bigint not null, primary key (id)) engine=InnoDB
create table tab_product_photo (id bigint not null auto_increment, created_date datetime(6), updated_date datetime(6), content_type varchar(255) not null, description varchar(255) not null, length bigint not null, name varchar(255) not null, product_id bigint not null, primary key (id)) engine=InnoDB
create table tab_restaurant (id bigint not null auto_increment, created_date datetime(6), updated_date datetime(6), active bit not null, address_cep varchar(255), address_complement varchar(255), address_district varchar(255), address_number varchar(255), address_street varchar(255), name varchar(255) not null, open bit not null, shipping_fee decimal(19,2) not null, address_city_id bigint not null, cookery_id bigint not null, primary key (id)) engine=InnoDB
create table tab_restaurant_payment_method (restaurant_id bigint not null, payment_method_id bigint not null, primary key (restaurant_id, payment_method_id)) engine=InnoDB
create table tab_restaurant_user (restaurant_id bigint not null, user_id bigint not null, primary key (restaurant_id, user_id)) engine=InnoDB
create table tab_state (id bigint not null auto_increment, created_date datetime(6), updated_date datetime(6), name varchar(255) not null, primary key (id)) engine=InnoDB
create table tab_user (id bigint not null auto_increment, created_date datetime(6), updated_date datetime(6), email varchar(255) not null, name varchar(255) not null, password varchar(255) not null, primary key (id)) engine=InnoDB
create table tab_user_group (user_id bigint not null, group_id bigint not null) engine=InnoDB
alter table tab_city add constraint fk_state foreign key (state_id) references tab_state (id)
alter table tab_group_permission add constraint fk_permission_group foreign key (permission_id) references tab_permission (id)
alter table tab_group_permission add constraint fk_group_permission foreign key (group_id) references tab_group (id)
alter table tab_order add constraint fk_customer foreign key (customer_id) references tab_user (id)
alter table tab_order add constraint FK5wvnusqb21o32vqaepv87at1n foreign key (address_city_id) references tab_city (id)
alter table tab_order add constraint fk_payment_method foreign key (payment_method_id) references tab_payment_method (id)
alter table tab_order add constraint fk_restaurant foreign key (restaurant_id) references tab_restaurant (id)
alter table tab_product add constraint fk_restaurant foreign key (restaurant_id) references tab_restaurant (id)
alter table tab_product_item add constraint fk_order_product_item foreign key (order_id) references tab_order (id)
alter table tab_product_item add constraint fk_product_product_item foreign key (product_id) references tab_product (id)
alter table tab_product_photo add constraint fk_product foreign key (product_id) references tab_product (id)
alter table tab_restaurant add constraint FKmjga3rvuox3hrgv5cwrutbkn7 foreign key (address_city_id) references tab_city (id)
alter table tab_restaurant add constraint fk_cookery foreign key (cookery_id) references tab_cookery (id)
alter table tab_restaurant_payment_method add constraint fk_payment_method_restaurant foreign key (payment_method_id) references tab_payment_method (id)
alter table tab_restaurant_payment_method add constraint fk_restaurant_payment_method foreign key (restaurant_id) references tab_restaurant (id)
alter table tab_restaurant_user add constraint fk_responsible_restaurant foreign key (user_id) references tab_user (id)
alter table tab_restaurant_user add constraint fk_restaurant_responsible foreign key (restaurant_id) references tab_restaurant (id)
alter table tab_user_group add constraint fk_group_user foreign key (group_id) references tab_group (id)
alter table tab_user_group add constraint fk_user_group foreign key (user_id) references tab_user (id)
create table tab_city (id bigint not null auto_increment, created_date datetime(6), updated_date datetime(6), name varchar(255) not null, state_id bigint not null, primary key (id)) engine=InnoDB
create table tab_cookery (id bigint not null auto_increment, created_date datetime(6), updated_date datetime(6), name varchar(255) not null, primary key (id)) engine=InnoDB
create table tab_group (id bigint not null auto_increment, created_date datetime(6), updated_date datetime(6), name varchar(255) not null, primary key (id)) engine=InnoDB
create table tab_group_permission (group_id bigint not null, permission_id bigint not null, primary key (group_id, permission_id)) engine=InnoDB
create table tab_order (id bigint not null auto_increment, created_date datetime(6), updated_date datetime(6), cancellation_date datetime(6), code varchar(255) not null, confirmation_date datetime(6), address_cep varchar(255), address_complement varchar(255), address_district varchar(255), address_number varchar(255), address_street varchar(255), delivery_date datetime(6), shipping_fee decimal(19,2) not null, status integer, sub_total decimal(19,2) not null, total_value decimal(19,2) not null, customer_id bigint not null, address_city_id bigint not null, payment_method_id bigint not null, restaurant_id bigint not null, primary key (id)) engine=InnoDB
create table tab_payment_method (id bigint not null auto_increment, created_date datetime(6), updated_date datetime(6), name varchar(255) not null, primary key (id)) engine=InnoDB
create table tab_permission (id bigint not null auto_increment, created_date datetime(6), updated_date datetime(6), description varchar(255) not null, name varchar(255) not null, primary key (id)) engine=InnoDB
create table tab_product (id bigint not null auto_increment, created_date datetime(6), updated_date datetime(6), active bit not null, description varchar(255) not null, name varchar(255) not null, value decimal(19,2) not null, restaurant_id bigint not null, primary key (id)) engine=InnoDB
create table tab_product_item (id bigint not null auto_increment, created_date datetime(6), updated_date datetime(6), observation varchar(255), quantity integer not null, total_value decimal(19,2) not null, unit_value decimal(19,2) not null, order_id bigint not null, product_id bigint not null, primary key (id)) engine=InnoDB
create table tab_product_photo (id bigint not null auto_increment, created_date datetime(6), updated_date datetime(6), content_type varchar(255) not null, description varchar(255) not null, length bigint not null, name varchar(255) not null, product_id bigint not null, primary key (id)) engine=InnoDB
create table tab_restaurant (id bigint not null auto_increment, created_date datetime(6), updated_date datetime(6), active bit not null, address_cep varchar(255), address_complement varchar(255), address_district varchar(255), address_number varchar(255), address_street varchar(255), name varchar(255) not null, open bit not null, shipping_fee decimal(19,2) not null, address_city_id bigint not null, cookery_id bigint not null, primary key (id)) engine=InnoDB
create table tab_restaurant_payment_method (restaurant_id bigint not null, payment_method_id bigint not null, primary key (restaurant_id, payment_method_id)) engine=InnoDB
create table tab_restaurant_user (restaurant_id bigint not null, user_id bigint not null, primary key (restaurant_id, user_id)) engine=InnoDB
create table tab_state (id bigint not null auto_increment, created_date datetime(6), updated_date datetime(6), name varchar(255) not null, primary key (id)) engine=InnoDB
create table tab_user (id bigint not null auto_increment, created_date datetime(6), updated_date datetime(6), email varchar(255) not null, name varchar(255) not null, password varchar(255) not null, primary key (id)) engine=InnoDB
create table tab_user_group (user_id bigint not null, group_id bigint not null) engine=InnoDB
alter table tab_city add constraint fk_state foreign key (state_id) references tab_state (id)
alter table tab_group_permission add constraint fk_permission_group foreign key (permission_id) references tab_permission (id)
alter table tab_group_permission add constraint fk_group_permission foreign key (group_id) references tab_group (id)
alter table tab_order add constraint fk_customer foreign key (customer_id) references tab_user (id)
alter table tab_order add constraint FK5wvnusqb21o32vqaepv87at1n foreign key (address_city_id) references tab_city (id)
alter table tab_order add constraint fk_payment_method foreign key (payment_method_id) references tab_payment_method (id)
alter table tab_order add constraint fk_restaurant foreign key (restaurant_id) references tab_restaurant (id)
alter table tab_product add constraint fk_restaurant foreign key (restaurant_id) references tab_restaurant (id)
alter table tab_product_item add constraint fk_order_product_item foreign key (order_id) references tab_order (id)
alter table tab_product_item add constraint fk_product_product_item foreign key (product_id) references tab_product (id)
alter table tab_product_photo add constraint fk_product foreign key (product_id) references tab_product (id)
alter table tab_restaurant add constraint FKmjga3rvuox3hrgv5cwrutbkn7 foreign key (address_city_id) references tab_city (id)
alter table tab_restaurant add constraint fk_cookery foreign key (cookery_id) references tab_cookery (id)
alter table tab_restaurant_payment_method add constraint fk_payment_method_restaurant foreign key (payment_method_id) references tab_payment_method (id)
alter table tab_restaurant_payment_method add constraint fk_restaurant_payment_method foreign key (restaurant_id) references tab_restaurant (id)
alter table tab_restaurant_user add constraint fk_responsible_restaurant foreign key (user_id) references tab_user (id)
alter table tab_restaurant_user add constraint fk_restaurant_responsible foreign key (restaurant_id) references tab_restaurant (id)
alter table tab_user_group add constraint fk_group_user foreign key (group_id) references tab_group (id)
alter table tab_user_group add constraint fk_user_group foreign key (user_id) references tab_user (id)
create table tab_city (id bigint not null auto_increment, created_date datetime(6), updated_date datetime(6), name varchar(255) not null, state_id bigint not null, primary key (id)) engine=InnoDB
create table tab_cookery (id bigint not null auto_increment, created_date datetime(6), updated_date datetime(6), name varchar(255) not null, primary key (id)) engine=InnoDB
create table tab_group (id bigint not null auto_increment, created_date datetime(6), updated_date datetime(6), name varchar(255) not null, primary key (id)) engine=InnoDB
create table tab_group_permission (group_id bigint not null, permission_id bigint not null, primary key (group_id, permission_id)) engine=InnoDB
create table tab_order (id bigint not null auto_increment, created_date datetime(6), updated_date datetime(6), cancellation_date datetime(6), code varchar(255) not null, confirmation_date datetime(6), address_cep varchar(255), address_complement varchar(255), address_district varchar(255), address_number varchar(255), address_street varchar(255), delivery_date datetime(6), shipping_fee decimal(19,2) not null, status integer, sub_total decimal(19,2) not null, total_value decimal(19,2) not null, customer_id bigint not null, address_city_id bigint not null, payment_method_id bigint not null, restaurant_id bigint not null, primary key (id)) engine=InnoDB
create table tab_payment_method (id bigint not null auto_increment, created_date datetime(6), updated_date datetime(6), name varchar(255) not null, primary key (id)) engine=InnoDB
create table tab_permission (id bigint not null auto_increment, created_date datetime(6), updated_date datetime(6), description varchar(255) not null, name varchar(255) not null, primary key (id)) engine=InnoDB
create table tab_product (id bigint not null auto_increment, created_date datetime(6), updated_date datetime(6), active bit not null, description varchar(255) not null, name varchar(255) not null, value decimal(19,2) not null, restaurant_id bigint not null, primary key (id)) engine=InnoDB
create table tab_product_item (id bigint not null auto_increment, created_date datetime(6), updated_date datetime(6), observation varchar(255), quantity integer not null, total_value decimal(19,2) not null, unit_value decimal(19,2) not null, order_id bigint not null, product_id bigint not null, primary key (id)) engine=InnoDB
create table tab_product_photo (id bigint not null auto_increment, created_date datetime(6), updated_date datetime(6), content_type varchar(255) not null, description varchar(255) not null, length bigint not null, name varchar(255) not null, product_id bigint not null, primary key (id)) engine=InnoDB
create table tab_restaurant (id bigint not null auto_increment, created_date datetime(6), updated_date datetime(6), active bit not null, address_cep varchar(255), address_complement varchar(255), address_district varchar(255), address_number varchar(255), address_street varchar(255), name varchar(255) not null, open bit not null, shipping_fee decimal(19,2) not null, address_city_id bigint not null, cookery_id bigint not null, primary key (id)) engine=InnoDB
create table tab_restaurant_payment_method (restaurant_id bigint not null, payment_method_id bigint not null, primary key (restaurant_id, payment_method_id)) engine=InnoDB
create table tab_restaurant_user (restaurant_id bigint not null, user_id bigint not null, primary key (restaurant_id, user_id)) engine=InnoDB
create table tab_state (id bigint not null auto_increment, created_date datetime(6), updated_date datetime(6), name varchar(255) not null, primary key (id)) engine=InnoDB
create table tab_user (id bigint not null auto_increment, created_date datetime(6), updated_date datetime(6), email varchar(255) not null, name varchar(255) not null, password varchar(255) not null, primary key (id)) engine=InnoDB
create table tab_user_group (user_id bigint not null, group_id bigint not null) engine=InnoDB
alter table tab_city add constraint fk_state foreign key (state_id) references tab_state (id)
alter table tab_group_permission add constraint fk_permission_group foreign key (permission_id) references tab_permission (id)
alter table tab_group_permission add constraint fk_group_permission foreign key (group_id) references tab_group (id)
alter table tab_order add constraint fk_customer foreign key (customer_id) references tab_user (id)
alter table tab_order add constraint FK5wvnusqb21o32vqaepv87at1n foreign key (address_city_id) references tab_city (id)
alter table tab_order add constraint fk_payment_method foreign key (payment_method_id) references tab_payment_method (id)
alter table tab_order add constraint fk_restaurant foreign key (restaurant_id) references tab_restaurant (id)
alter table tab_product add constraint fk_restaurant foreign key (restaurant_id) references tab_restaurant (id)
alter table tab_product_item add constraint fk_order_product_item foreign key (order_id) references tab_order (id)
alter table tab_product_item add constraint fk_product_product_item foreign key (product_id) references tab_product (id)
alter table tab_product_photo add constraint fk_product foreign key (product_id) references tab_product (id)
alter table tab_restaurant add constraint FKmjga3rvuox3hrgv5cwrutbkn7 foreign key (address_city_id) references tab_city (id)
alter table tab_restaurant add constraint fk_cookery foreign key (cookery_id) references tab_cookery (id)
alter table tab_restaurant_payment_method add constraint fk_payment_method_restaurant foreign key (payment_method_id) references tab_payment_method (id)
alter table tab_restaurant_payment_method add constraint fk_restaurant_payment_method foreign key (restaurant_id) references tab_restaurant (id)
alter table tab_restaurant_user add constraint fk_responsible_restaurant foreign key (user_id) references tab_user (id)
alter table tab_restaurant_user add constraint fk_restaurant_responsible foreign key (restaurant_id) references tab_restaurant (id)
alter table tab_user_group add constraint fk_group_user foreign key (group_id) references tab_group (id)
alter table tab_user_group add constraint fk_user_group foreign key (user_id) references tab_user (id)
create table tab_city (id bigint not null auto_increment, created_date datetime(6), updated_date datetime(6), name varchar(255) not null, state_id bigint not null, primary key (id)) engine=InnoDB
create table tab_cookery (id bigint not null auto_increment, created_date datetime(6), updated_date datetime(6), name varchar(255) not null, primary key (id)) engine=InnoDB
create table tab_group (id bigint not null auto_increment, created_date datetime(6), updated_date datetime(6), name varchar(255) not null, primary key (id)) engine=InnoDB
create table tab_group_permission (group_id bigint not null, permission_id bigint not null, primary key (group_id, permission_id)) engine=InnoDB
create table tab_order (id bigint not null auto_increment, created_date datetime(6), updated_date datetime(6), cancellation_date datetime(6), code varchar(255) not null, confirmation_date datetime(6), address_cep varchar(255), address_complement varchar(255), address_district varchar(255), address_number varchar(255), address_street varchar(255), delivery_date datetime(6), shipping_fee decimal(19,2) not null, status integer, sub_total decimal(19,2) not null, total_value decimal(19,2) not null, customer_id bigint not null, address_city_id bigint not null, payment_method_id bigint not null, restaurant_id bigint not null, primary key (id)) engine=InnoDB
create table tab_payment_method (id bigint not null auto_increment, created_date datetime(6), updated_date datetime(6), name varchar(255) not null, primary key (id)) engine=InnoDB
create table tab_permission (id bigint not null auto_increment, created_date datetime(6), updated_date datetime(6), description varchar(255) not null, name varchar(255) not null, primary key (id)) engine=InnoDB
create table tab_product (id bigint not null auto_increment, created_date datetime(6), updated_date datetime(6), active bit not null, description varchar(255) not null, name varchar(255) not null, value decimal(19,2) not null, restaurant_id bigint not null, primary key (id)) engine=InnoDB
create table tab_product_item (id bigint not null auto_increment, created_date datetime(6), updated_date datetime(6), observation varchar(255), quantity integer not null, total_value decimal(19,2) not null, unit_value decimal(19,2) not null, order_id bigint not null, product_id bigint not null, primary key (id)) engine=InnoDB
create table tab_product_photo (id bigint not null auto_increment, created_date datetime(6), updated_date datetime(6), content_type varchar(255) not null, description varchar(255) not null, length bigint not null, name varchar(255) not null, product_id bigint not null, primary key (id)) engine=InnoDB
create table tab_restaurant (id bigint not null auto_increment, created_date datetime(6), updated_date datetime(6), active bit not null, address_cep varchar(255), address_complement varchar(255), address_district varchar(255), address_number varchar(255), address_street varchar(255), name varchar(255) not null, open bit not null, shipping_fee decimal(19,2) not null, address_city_id bigint not null, cookery_id bigint not null, primary key (id)) engine=InnoDB
create table tab_restaurant_payment_method (restaurant_id bigint not null, payment_method_id bigint not null, primary key (restaurant_id, payment_method_id)) engine=InnoDB
create table tab_restaurant_user (restaurant_id bigint not null, user_id bigint not null, primary key (restaurant_id, user_id)) engine=InnoDB
create table tab_state (id bigint not null auto_increment, created_date datetime(6), updated_date datetime(6), name varchar(255) not null, primary key (id)) engine=InnoDB
create table tab_user (id bigint not null auto_increment, created_date datetime(6), updated_date datetime(6), email varchar(255) not null, name varchar(255) not null, password varchar(255) not null, primary key (id)) engine=InnoDB
create table tab_user_group (user_id bigint not null, group_id bigint not null) engine=InnoDB
alter table tab_city add constraint fk_state foreign key (state_id) references tab_state (id)
alter table tab_group_permission add constraint fk_permission_group foreign key (permission_id) references tab_permission (id)
alter table tab_group_permission add constraint fk_group_permission foreign key (group_id) references tab_group (id)
alter table tab_order add constraint fk_customer foreign key (customer_id) references tab_user (id)
alter table tab_order add constraint FK5wvnusqb21o32vqaepv87at1n foreign key (address_city_id) references tab_city (id)
alter table tab_order add constraint fk_payment_method foreign key (payment_method_id) references tab_payment_method (id)
alter table tab_order add constraint fk_restaurant foreign key (restaurant_id) references tab_restaurant (id)
alter table tab_product add constraint fk_restaurant foreign key (restaurant_id) references tab_restaurant (id)
alter table tab_product_item add constraint fk_order_product_item foreign key (order_id) references tab_order (id)
alter table tab_product_item add constraint fk_product_product_item foreign key (product_id) references tab_product (id)
alter table tab_product_photo add constraint fk_product foreign key (product_id) references tab_product (id)
alter table tab_restaurant add constraint FKmjga3rvuox3hrgv5cwrutbkn7 foreign key (address_city_id) references tab_city (id)
alter table tab_restaurant add constraint fk_cookery foreign key (cookery_id) references tab_cookery (id)
alter table tab_restaurant_payment_method add constraint fk_payment_method_restaurant foreign key (payment_method_id) references tab_payment_method (id)
alter table tab_restaurant_payment_method add constraint fk_restaurant_payment_method foreign key (restaurant_id) references tab_restaurant (id)
alter table tab_restaurant_user add constraint fk_responsible_restaurant foreign key (user_id) references tab_user (id)
alter table tab_restaurant_user add constraint fk_restaurant_responsible foreign key (restaurant_id) references tab_restaurant (id)
alter table tab_user_group add constraint fk_group_user foreign key (group_id) references tab_group (id)
alter table tab_user_group add constraint fk_user_group foreign key (user_id) references tab_user (id)
create table tab_city (id bigint not null auto_increment, created_date datetime(6), updated_date datetime(6), name varchar(255) not null, state_id bigint not null, primary key (id)) engine=InnoDB
create table tab_cookery (id bigint not null auto_increment, created_date datetime(6), updated_date datetime(6), name varchar(255) not null, primary key (id)) engine=InnoDB
create table tab_group (id bigint not null auto_increment, created_date datetime(6), updated_date datetime(6), name varchar(255) not null, primary key (id)) engine=InnoDB
create table tab_group_permission (group_id bigint not null, permission_id bigint not null, primary key (group_id, permission_id)) engine=InnoDB
create table tab_order (id bigint not null auto_increment, created_date datetime(6), updated_date datetime(6), cancellation_date datetime(6), code varchar(255) not null, confirmation_date datetime(6), address_cep varchar(255), address_complement varchar(255), address_district varchar(255), address_number varchar(255), address_street varchar(255), delivery_date datetime(6), shipping_fee decimal(19,2) not null, status integer, sub_total decimal(19,2) not null, total_value decimal(19,2) not null, customer_id bigint not null, address_city_id bigint not null, payment_method_id bigint not null, restaurant_id bigint not null, primary key (id)) engine=InnoDB
create table tab_payment_method (id bigint not null auto_increment, created_date datetime(6), updated_date datetime(6), name varchar(255) not null, primary key (id)) engine=InnoDB
create table tab_permission (id bigint not null auto_increment, created_date datetime(6), updated_date datetime(6), description varchar(255) not null, name varchar(255) not null, primary key (id)) engine=InnoDB
create table tab_product (id bigint not null auto_increment, created_date datetime(6), updated_date datetime(6), active bit not null, description varchar(255) not null, name varchar(255) not null, value decimal(19,2) not null, restaurant_id bigint not null, primary key (id)) engine=InnoDB
create table tab_product_item (id bigint not null auto_increment, created_date datetime(6), updated_date datetime(6), observation varchar(255), quantity integer not null, total_value decimal(19,2) not null, unit_value decimal(19,2) not null, order_id bigint not null, product_id bigint not null, primary key (id)) engine=InnoDB
create table tab_product_photo (id bigint not null auto_increment, created_date datetime(6), updated_date datetime(6), content_type varchar(255) not null, description varchar(255) not null, length bigint not null, name varchar(255) not null, product_id bigint not null, primary key (id)) engine=InnoDB
create table tab_restaurant (id bigint not null auto_increment, created_date datetime(6), updated_date datetime(6), active bit not null, address_cep varchar(255), address_complement varchar(255), address_district varchar(255), address_number varchar(255), address_street varchar(255), name varchar(255) not null, open bit not null, shipping_fee decimal(19,2) not null, address_city_id bigint not null, cookery_id bigint not null, primary key (id)) engine=InnoDB
create table tab_restaurant_payment_method (restaurant_id bigint not null, payment_method_id bigint not null, primary key (restaurant_id, payment_method_id)) engine=InnoDB
create table tab_restaurant_user (restaurant_id bigint not null, user_id bigint not null, primary key (restaurant_id, user_id)) engine=InnoDB
create table tab_state (id bigint not null auto_increment, created_date datetime(6), updated_date datetime(6), name varchar(255) not null, primary key (id)) engine=InnoDB
create table tab_user (id bigint not null auto_increment, created_date datetime(6), updated_date datetime(6), email varchar(255) not null, name varchar(255) not null, password varchar(255) not null, primary key (id)) engine=InnoDB
create table tab_user_group (user_id bigint not null, group_id bigint not null) engine=InnoDB
alter table tab_city add constraint fk_state foreign key (state_id) references tab_state (id)
alter table tab_group_permission add constraint fk_permission_group foreign key (permission_id) references tab_permission (id)
alter table tab_group_permission add constraint fk_group_permission foreign key (group_id) references tab_group (id)
alter table tab_order add constraint fk_customer foreign key (customer_id) references tab_user (id)
alter table tab_order add constraint FK5wvnusqb21o32vqaepv87at1n foreign key (address_city_id) references tab_city (id)
alter table tab_order add constraint fk_payment_method foreign key (payment_method_id) references tab_payment_method (id)
alter table tab_order add constraint fk_restaurant foreign key (restaurant_id) references tab_restaurant (id)
alter table tab_product add constraint fk_restaurant foreign key (restaurant_id) references tab_restaurant (id)
alter table tab_product_item add constraint fk_order_product_item foreign key (order_id) references tab_order (id)
alter table tab_product_item add constraint fk_product_product_item foreign key (product_id) references tab_product (id)
alter table tab_product_photo add constraint fk_product foreign key (product_id) references tab_product (id)
alter table tab_restaurant add constraint FKmjga3rvuox3hrgv5cwrutbkn7 foreign key (address_city_id) references tab_city (id)
alter table tab_restaurant add constraint fk_cookery foreign key (cookery_id) references tab_cookery (id)
alter table tab_restaurant_payment_method add constraint fk_payment_method_restaurant foreign key (payment_method_id) references tab_payment_method (id)
alter table tab_restaurant_payment_method add constraint fk_restaurant_payment_method foreign key (restaurant_id) references tab_restaurant (id)
alter table tab_restaurant_user add constraint fk_responsible_restaurant foreign key (user_id) references tab_user (id)
alter table tab_restaurant_user add constraint fk_restaurant_responsible foreign key (restaurant_id) references tab_restaurant (id)
alter table tab_user_group add constraint fk_group_user foreign key (group_id) references tab_group (id)
alter table tab_user_group add constraint fk_user_group foreign key (user_id) references tab_user (id)
create table tab_city (id bigint not null auto_increment, created_date datetime(6), updated_date datetime(6), name varchar(255) not null, state_id bigint not null, primary key (id)) engine=InnoDB
create table tab_cookery (id bigint not null auto_increment, created_date datetime(6), updated_date datetime(6), name varchar(255) not null, primary key (id)) engine=InnoDB
create table tab_group (id bigint not null auto_increment, created_date datetime(6), updated_date datetime(6), name varchar(255) not null, primary key (id)) engine=InnoDB
create table tab_group_permission (group_id bigint not null, permission_id bigint not null, primary key (group_id, permission_id)) engine=InnoDB
create table tab_order (id bigint not null auto_increment, created_date datetime(6), updated_date datetime(6), cancellation_date datetime(6), code varchar(255) not null, confirmation_date datetime(6), address_cep varchar(255), address_complement varchar(255), address_district varchar(255), address_number varchar(255), address_street varchar(255), delivery_date datetime(6), shipping_fee decimal(19,2) not null, status integer, sub_total decimal(19,2) not null, total_value decimal(19,2) not null, customer_id bigint not null, address_city_id bigint not null, payment_method_id bigint not null, restaurant_id bigint not null, primary key (id)) engine=InnoDB
create table tab_payment_method (id bigint not null auto_increment, created_date datetime(6), updated_date datetime(6), name varchar(255) not null, primary key (id)) engine=InnoDB
create table tab_permission (id bigint not null auto_increment, created_date datetime(6), updated_date datetime(6), description varchar(255) not null, name varchar(255) not null, primary key (id)) engine=InnoDB
create table tab_product (id bigint not null auto_increment, created_date datetime(6), updated_date datetime(6), active bit not null, description varchar(255) not null, name varchar(255) not null, value decimal(19,2) not null, restaurant_id bigint not null, primary key (id)) engine=InnoDB
create table tab_product_item (id bigint not null auto_increment, created_date datetime(6), updated_date datetime(6), observation varchar(255), quantity integer not null, total_value decimal(19,2) not null, unit_value decimal(19,2) not null, order_id bigint not null, product_id bigint not null, primary key (id)) engine=InnoDB
create table tab_product_photo (id bigint not null auto_increment, created_date datetime(6), updated_date datetime(6), content_type varchar(255) not null, description varchar(255) not null, length bigint not null, name varchar(255) not null, product_id bigint not null, primary key (id)) engine=InnoDB
create table tab_restaurant (id bigint not null auto_increment, created_date datetime(6), updated_date datetime(6), active bit not null, address_cep varchar(255), address_complement varchar(255), address_district varchar(255), address_number varchar(255), address_street varchar(255), name varchar(255) not null, open bit not null, shipping_fee decimal(19,2) not null, address_city_id bigint not null, cookery_id bigint not null, primary key (id)) engine=InnoDB
create table tab_restaurant_payment_method (restaurant_id bigint not null, payment_method_id bigint not null, primary key (restaurant_id, payment_method_id)) engine=InnoDB
create table tab_restaurant_user (restaurant_id bigint not null, user_id bigint not null, primary key (restaurant_id, user_id)) engine=InnoDB
create table tab_state (id bigint not null auto_increment, created_date datetime(6), updated_date datetime(6), name varchar(255) not null, primary key (id)) engine=InnoDB
create table tab_user (id bigint not null auto_increment, created_date datetime(6), updated_date datetime(6), email varchar(255) not null, name varchar(255) not null, password varchar(255) not null, primary key (id)) engine=InnoDB
create table tab_user_group (user_id bigint not null, group_id bigint not null) engine=InnoDB
alter table tab_city add constraint fk_state foreign key (state_id) references tab_state (id)
alter table tab_group_permission add constraint fk_permission_group foreign key (permission_id) references tab_permission (id)
alter table tab_group_permission add constraint fk_group_permission foreign key (group_id) references tab_group (id)
alter table tab_order add constraint fk_customer foreign key (customer_id) references tab_user (id)
alter table tab_order add constraint FK5wvnusqb21o32vqaepv87at1n foreign key (address_city_id) references tab_city (id)
alter table tab_order add constraint fk_payment_method foreign key (payment_method_id) references tab_payment_method (id)
alter table tab_order add constraint fk_restaurant foreign key (restaurant_id) references tab_restaurant (id)
alter table tab_product add constraint fk_restaurant foreign key (restaurant_id) references tab_restaurant (id)
alter table tab_product_item add constraint fk_order_product_item foreign key (order_id) references tab_order (id)
alter table tab_product_item add constraint fk_product_product_item foreign key (product_id) references tab_product (id)
alter table tab_product_photo add constraint fk_product foreign key (product_id) references tab_product (id)
alter table tab_restaurant add constraint FKmjga3rvuox3hrgv5cwrutbkn7 foreign key (address_city_id) references tab_city (id)
alter table tab_restaurant add constraint fk_cookery foreign key (cookery_id) references tab_cookery (id)
alter table tab_restaurant_payment_method add constraint fk_payment_method_restaurant foreign key (payment_method_id) references tab_payment_method (id)
alter table tab_restaurant_payment_method add constraint fk_restaurant_payment_method foreign key (restaurant_id) references tab_restaurant (id)
alter table tab_restaurant_user add constraint fk_responsible_restaurant foreign key (user_id) references tab_user (id)
alter table tab_restaurant_user add constraint fk_restaurant_responsible foreign key (restaurant_id) references tab_restaurant (id)
alter table tab_user_group add constraint fk_group_user foreign key (group_id) references tab_group (id)
alter table tab_user_group add constraint fk_user_group foreign key (user_id) references tab_user (id)
create table tab_city (id bigint not null auto_increment, created_date datetime(6), updated_date datetime(6), name varchar(255) not null, state_id bigint not null, primary key (id)) engine=InnoDB
create table tab_cookery (id bigint not null auto_increment, created_date datetime(6), updated_date datetime(6), name varchar(255) not null, primary key (id)) engine=InnoDB
create table tab_group (id bigint not null auto_increment, created_date datetime(6), updated_date datetime(6), name varchar(255) not null, primary key (id)) engine=InnoDB
create table tab_group_permission (group_id bigint not null, permission_id bigint not null, primary key (group_id, permission_id)) engine=InnoDB
create table tab_order (id bigint not null auto_increment, created_date datetime(6), updated_date datetime(6), cancellation_date datetime(6), code varchar(255) not null, confirmation_date datetime(6), address_cep varchar(255), address_complement varchar(255), address_district varchar(255), address_number varchar(255), address_street varchar(255), delivery_date datetime(6), shipping_fee decimal(19,2) not null, status integer, sub_total decimal(19,2) not null, total_value decimal(19,2) not null, customer_id bigint not null, address_city_id bigint not null, payment_method_id bigint not null, restaurant_id bigint not null, primary key (id)) engine=InnoDB
create table tab_payment_method (id bigint not null auto_increment, created_date datetime(6), updated_date datetime(6), name varchar(255) not null, primary key (id)) engine=InnoDB
create table tab_permission (id bigint not null auto_increment, created_date datetime(6), updated_date datetime(6), description varchar(255) not null, name varchar(255) not null, primary key (id)) engine=InnoDB
create table tab_product (id bigint not null auto_increment, created_date datetime(6), updated_date datetime(6), active bit not null, description varchar(255) not null, name varchar(255) not null, value decimal(19,2) not null, restaurant_id bigint not null, primary key (id)) engine=InnoDB
create table tab_product_item (id bigint not null auto_increment, created_date datetime(6), updated_date datetime(6), observation varchar(255), quantity integer not null, total_value decimal(19,2) not null, unit_value decimal(19,2) not null, order_id bigint not null, product_id bigint not null, primary key (id)) engine=InnoDB
create table tab_product_photo (id bigint not null auto_increment, created_date datetime(6), updated_date datetime(6), content_type varchar(255) not null, description varchar(255) not null, length bigint not null, name varchar(255) not null, product_id bigint not null, primary key (id)) engine=InnoDB
create table tab_restaurant (id bigint not null auto_increment, created_date datetime(6), updated_date datetime(6), active bit not null, address_cep varchar(255), address_complement varchar(255), address_district varchar(255), address_number varchar(255), address_street varchar(255), name varchar(255) not null, open bit not null, shipping_fee decimal(19,2) not null, address_city_id bigint not null, cookery_id bigint not null, primary key (id)) engine=InnoDB
create table tab_restaurant_payment_method (restaurant_id bigint not null, payment_method_id bigint not null, primary key (restaurant_id, payment_method_id)) engine=InnoDB
create table tab_restaurant_user (restaurant_id bigint not null, user_id bigint not null, primary key (restaurant_id, user_id)) engine=InnoDB
create table tab_state (id bigint not null auto_increment, created_date datetime(6), updated_date datetime(6), name varchar(255) not null, primary key (id)) engine=InnoDB
create table tab_user (id bigint not null auto_increment, created_date datetime(6), updated_date datetime(6), email varchar(255) not null, name varchar(255) not null, password varchar(255) not null, primary key (id)) engine=InnoDB
create table tab_user_group (user_id bigint not null, group_id bigint not null, primary key (user_id, group_id)) engine=InnoDB
alter table tab_city add constraint fk_state foreign key (state_id) references tab_state (id)
alter table tab_group_permission add constraint fk_permission_group foreign key (permission_id) references tab_permission (id)
alter table tab_group_permission add constraint fk_group_permission foreign key (group_id) references tab_group (id)
alter table tab_order add constraint fk_customer foreign key (customer_id) references tab_user (id)
alter table tab_order add constraint FK5wvnusqb21o32vqaepv87at1n foreign key (address_city_id) references tab_city (id)
alter table tab_order add constraint fk_payment_method foreign key (payment_method_id) references tab_payment_method (id)
alter table tab_order add constraint fk_restaurant foreign key (restaurant_id) references tab_restaurant (id)
alter table tab_product add constraint fk_restaurant foreign key (restaurant_id) references tab_restaurant (id)
alter table tab_product_item add constraint fk_order_product_item foreign key (order_id) references tab_order (id)
alter table tab_product_item add constraint fk_product_product_item foreign key (product_id) references tab_product (id)
alter table tab_product_photo add constraint fk_product foreign key (product_id) references tab_product (id)
alter table tab_restaurant add constraint FKmjga3rvuox3hrgv5cwrutbkn7 foreign key (address_city_id) references tab_city (id)
alter table tab_restaurant add constraint fk_cookery foreign key (cookery_id) references tab_cookery (id)
alter table tab_restaurant_payment_method add constraint fk_payment_method_restaurant foreign key (payment_method_id) references tab_payment_method (id)
alter table tab_restaurant_payment_method add constraint fk_restaurant_payment_method foreign key (restaurant_id) references tab_restaurant (id)
alter table tab_restaurant_user add constraint fk_responsible_restaurant foreign key (user_id) references tab_user (id)
alter table tab_restaurant_user add constraint fk_restaurant_responsible foreign key (restaurant_id) references tab_restaurant (id)
alter table tab_user_group add constraint fk_group_user foreign key (group_id) references tab_group (id)
alter table tab_user_group add constraint fk_user_group foreign key (user_id) references tab_user (id)
create table tab_city (id bigint not null auto_increment, created_date datetime(6), updated_date datetime(6), name varchar(255) not null, state_id bigint not null, primary key (id)) engine=InnoDB
create table tab_cookery (id bigint not null auto_increment, created_date datetime(6), updated_date datetime(6), name varchar(255) not null, primary key (id)) engine=InnoDB
create table tab_group (id bigint not null auto_increment, created_date datetime(6), updated_date datetime(6), name varchar(255) not null, primary key (id)) engine=InnoDB
create table tab_group_permission (group_id bigint not null, permission_id bigint not null, primary key (group_id, permission_id)) engine=InnoDB
create table tab_order (id bigint not null auto_increment, created_date datetime(6), updated_date datetime(6), cancellation_date datetime(6), code varchar(255) not null, confirmation_date datetime(6), address_cep varchar(255), address_complement varchar(255), address_district varchar(255), address_number varchar(255), address_street varchar(255), delivery_date datetime(6), shipping_fee decimal(19,2) not null, status integer, sub_total decimal(19,2) not null, total_value decimal(19,2) not null, customer_id bigint not null, address_city_id bigint not null, payment_method_id bigint not null, restaurant_id bigint not null, primary key (id)) engine=InnoDB
create table tab_payment_method (id bigint not null auto_increment, created_date datetime(6), updated_date datetime(6), name varchar(255) not null, primary key (id)) engine=InnoDB
create table tab_permission (id bigint not null auto_increment, created_date datetime(6), updated_date datetime(6), description varchar(255) not null, name varchar(255) not null, primary key (id)) engine=InnoDB
create table tab_product (id bigint not null auto_increment, created_date datetime(6), updated_date datetime(6), active bit not null, description varchar(255) not null, name varchar(255) not null, value decimal(19,2) not null, restaurant_id bigint not null, primary key (id)) engine=InnoDB
create table tab_product_item (id bigint not null auto_increment, created_date datetime(6), updated_date datetime(6), observation varchar(255), quantity integer not null, total_value decimal(19,2) not null, unit_value decimal(19,2) not null, order_id bigint not null, product_id bigint not null, primary key (id)) engine=InnoDB
create table tab_product_photo (id bigint not null auto_increment, created_date datetime(6), updated_date datetime(6), content_type varchar(255) not null, description varchar(255) not null, length bigint not null, name varchar(255) not null, product_id bigint not null, primary key (id)) engine=InnoDB
create table tab_restaurant (id bigint not null auto_increment, created_date datetime(6), updated_date datetime(6), active bit not null, address_cep varchar(255), address_complement varchar(255), address_district varchar(255), address_number varchar(255), address_street varchar(255), name varchar(255) not null, open bit not null, shipping_fee decimal(19,2) not null, address_city_id bigint not null, cookery_id bigint not null, primary key (id)) engine=InnoDB
create table tab_restaurant_payment_method (restaurant_id bigint not null, payment_method_id bigint not null, primary key (restaurant_id, payment_method_id)) engine=InnoDB
create table tab_restaurant_user (restaurant_id bigint not null, user_id bigint not null, primary key (restaurant_id, user_id)) engine=InnoDB
create table tab_state (id bigint not null auto_increment, created_date datetime(6), updated_date datetime(6), name varchar(255) not null, primary key (id)) engine=InnoDB
create table tab_user (id bigint not null auto_increment, created_date datetime(6), updated_date datetime(6), email varchar(255) not null, name varchar(255) not null, password varchar(255) not null, primary key (id)) engine=InnoDB
create table tab_user_group (user_id bigint not null, group_id bigint not null, primary key (user_id, group_id)) engine=InnoDB
alter table tab_city add constraint fk_state foreign key (state_id) references tab_state (id)
alter table tab_group_permission add constraint fk_permission_group foreign key (permission_id) references tab_permission (id)
alter table tab_group_permission add constraint fk_group_permission foreign key (group_id) references tab_group (id)
alter table tab_order add constraint fk_customer foreign key (customer_id) references tab_user (id)
alter table tab_order add constraint FK5wvnusqb21o32vqaepv87at1n foreign key (address_city_id) references tab_city (id)
alter table tab_order add constraint fk_payment_method foreign key (payment_method_id) references tab_payment_method (id)
alter table tab_order add constraint fk_restaurant foreign key (restaurant_id) references tab_restaurant (id)
alter table tab_product add constraint fk_restaurant foreign key (restaurant_id) references tab_restaurant (id)
alter table tab_product_item add constraint fk_order_product_item foreign key (order_id) references tab_order (id)
alter table tab_product_item add constraint fk_product_product_item foreign key (product_id) references tab_product (id)
alter table tab_product_photo add constraint fk_product foreign key (product_id) references tab_product (id)
alter table tab_restaurant add constraint FKmjga3rvuox3hrgv5cwrutbkn7 foreign key (address_city_id) references tab_city (id)
alter table tab_restaurant add constraint fk_cookery foreign key (cookery_id) references tab_cookery (id)
alter table tab_restaurant_payment_method add constraint fk_payment_method_restaurant foreign key (payment_method_id) references tab_payment_method (id)
alter table tab_restaurant_payment_method add constraint fk_restaurant_payment_method foreign key (restaurant_id) references tab_restaurant (id)
alter table tab_restaurant_user add constraint fk_responsible_restaurant foreign key (user_id) references tab_user (id)
alter table tab_restaurant_user add constraint fk_restaurant_responsible foreign key (restaurant_id) references tab_restaurant (id)
alter table tab_user_group add constraint fk_group_user foreign key (group_id) references tab_group (id)
alter table tab_user_group add constraint fk_user_group foreign key (user_id) references tab_user (id)
create table tab_city (id bigint not null auto_increment, created_date datetime(6), updated_date datetime(6), name varchar(255) not null, state_id bigint not null, primary key (id)) engine=InnoDB
create table tab_cookery (id bigint not null auto_increment, created_date datetime(6), updated_date datetime(6), name varchar(255) not null, primary key (id)) engine=InnoDB
create table tab_group (id bigint not null auto_increment, created_date datetime(6), updated_date datetime(6), name varchar(255) not null, primary key (id)) engine=InnoDB
create table tab_group_permission (group_id bigint not null, permission_id bigint not null, primary key (group_id, permission_id)) engine=InnoDB
create table tab_order (id bigint not null auto_increment, created_date datetime(6), updated_date datetime(6), cancellation_date datetime(6), code varchar(255) not null, confirmation_date datetime(6), address_cep varchar(255), address_complement varchar(255), address_district varchar(255), address_number varchar(255), address_street varchar(255), delivery_date datetime(6), shipping_fee decimal(19,2) not null, status integer, sub_total decimal(19,2) not null, total_value decimal(19,2) not null, customer_id bigint not null, address_city_id bigint not null, payment_method_id bigint not null, restaurant_id bigint not null, primary key (id)) engine=InnoDB
create table tab_payment_method (id bigint not null auto_increment, created_date datetime(6), updated_date datetime(6), name varchar(255) not null, primary key (id)) engine=InnoDB
create table tab_permission (id bigint not null auto_increment, created_date datetime(6), updated_date datetime(6), description varchar(255) not null, name varchar(255) not null, primary key (id)) engine=InnoDB
create table tab_product (id bigint not null auto_increment, created_date datetime(6), updated_date datetime(6), active bit not null, description varchar(255) not null, name varchar(255) not null, value decimal(19,2) not null, restaurant_id bigint not null, primary key (id)) engine=InnoDB
create table tab_product_item (id bigint not null auto_increment, created_date datetime(6), updated_date datetime(6), observation varchar(255), quantity integer not null, total_value decimal(19,2) not null, unit_value decimal(19,2) not null, order_id bigint not null, product_id bigint not null, primary key (id)) engine=InnoDB
create table tab_product_photo (id bigint not null auto_increment, created_date datetime(6), updated_date datetime(6), content_type varchar(255) not null, description varchar(255) not null, length bigint not null, name varchar(255) not null, product_id bigint not null, primary key (id)) engine=InnoDB
create table tab_restaurant (id bigint not null auto_increment, created_date datetime(6), updated_date datetime(6), active bit not null, address_cep varchar(255), address_complement varchar(255), address_district varchar(255), address_number varchar(255), address_street varchar(255), name varchar(255) not null, open bit not null, shipping_fee decimal(19,2) not null, address_city_id bigint not null, cookery_id bigint not null, primary key (id)) engine=InnoDB
create table tab_restaurant_payment_method (restaurant_id bigint not null, payment_method_id bigint not null, primary key (restaurant_id, payment_method_id)) engine=InnoDB
create table tab_restaurant_user (restaurant_id bigint not null, user_id bigint not null, primary key (restaurant_id, user_id)) engine=InnoDB
create table tab_state (id bigint not null auto_increment, created_date datetime(6), updated_date datetime(6), name varchar(255) not null, primary key (id)) engine=InnoDB
create table tab_user (id bigint not null auto_increment, created_date datetime(6), updated_date datetime(6), email varchar(255) not null, name varchar(255) not null, password varchar(255) not null, primary key (id)) engine=InnoDB
create table tab_user_group (user_id bigint not null, group_id bigint not null, primary key (user_id, group_id)) engine=InnoDB
alter table tab_city add constraint fk_state foreign key (state_id) references tab_state (id)
alter table tab_group_permission add constraint fk_permission_group foreign key (permission_id) references tab_permission (id)
alter table tab_group_permission add constraint fk_group_permission foreign key (group_id) references tab_group (id)
alter table tab_order add constraint fk_customer foreign key (customer_id) references tab_user (id)
alter table tab_order add constraint FK5wvnusqb21o32vqaepv87at1n foreign key (address_city_id) references tab_city (id)
alter table tab_order add constraint fk_payment_method foreign key (payment_method_id) references tab_payment_method (id)
alter table tab_order add constraint fk_restaurant foreign key (restaurant_id) references tab_restaurant (id)
alter table tab_product add constraint fk_restaurant foreign key (restaurant_id) references tab_restaurant (id)
alter table tab_product_item add constraint fk_order_product_item foreign key (order_id) references tab_order (id)
alter table tab_product_item add constraint fk_product_product_item foreign key (product_id) references tab_product (id)
alter table tab_product_photo add constraint fk_product foreign key (product_id) references tab_product (id)
alter table tab_restaurant add constraint FKmjga3rvuox3hrgv5cwrutbkn7 foreign key (address_city_id) references tab_city (id)
alter table tab_restaurant add constraint fk_cookery foreign key (cookery_id) references tab_cookery (id)
alter table tab_restaurant_payment_method add constraint fk_payment_method_restaurant foreign key (payment_method_id) references tab_payment_method (id)
alter table tab_restaurant_payment_method add constraint fk_restaurant_payment_method foreign key (restaurant_id) references tab_restaurant (id)
alter table tab_restaurant_user add constraint fk_responsible_restaurant foreign key (user_id) references tab_user (id)
alter table tab_restaurant_user add constraint fk_restaurant_responsible foreign key (restaurant_id) references tab_restaurant (id)
alter table tab_user_group add constraint fk_group_user foreign key (group_id) references tab_group (id)
alter table tab_user_group add constraint fk_user_group foreign key (user_id) references tab_user (id)
create table tab_city (id bigint not null auto_increment, created_date datetime(6), updated_date datetime(6), name varchar(255) not null, state_id bigint not null, primary key (id)) engine=InnoDB
create table tab_cookery (id bigint not null auto_increment, created_date datetime(6), updated_date datetime(6), name varchar(255) not null, primary key (id)) engine=InnoDB
create table tab_group (id bigint not null auto_increment, created_date datetime(6), updated_date datetime(6), name varchar(255) not null, primary key (id)) engine=InnoDB
create table tab_group_permission (group_id bigint not null, permission_id bigint not null, primary key (group_id, permission_id)) engine=InnoDB
create table tab_order (id bigint not null auto_increment, created_date datetime(6), updated_date datetime(6), cancellation_date datetime(6), code varchar(255) not null, confirmation_date datetime(6), address_cep varchar(255), address_complement varchar(255), address_district varchar(255), address_number varchar(255), address_street varchar(255), delivery_date datetime(6), shipping_fee decimal(19,2) not null, status varchar(255), sub_total decimal(19,2) not null, total_value decimal(19,2) not null, customer_id bigint not null, address_city_id bigint not null, payment_method_id bigint not null, restaurant_id bigint not null, primary key (id)) engine=InnoDB
create table tab_order_item (id bigint not null auto_increment, created_date datetime(6), updated_date datetime(6), observation varchar(255), quantity integer not null, total_value decimal(19,2) not null, unit_value decimal(19,2) not null, order_id bigint not null, product_id bigint not null, primary key (id)) engine=InnoDB
create table tab_payment_method (id bigint not null auto_increment, created_date datetime(6), updated_date datetime(6), name varchar(255) not null, primary key (id)) engine=InnoDB
create table tab_permission (id bigint not null auto_increment, created_date datetime(6), updated_date datetime(6), description varchar(255) not null, name varchar(255) not null, primary key (id)) engine=InnoDB
create table tab_product (id bigint not null auto_increment, created_date datetime(6), updated_date datetime(6), active bit not null, description varchar(255) not null, name varchar(255) not null, value decimal(19,2) not null, restaurant_id bigint not null, primary key (id)) engine=InnoDB
create table tab_product_photo (id bigint not null auto_increment, created_date datetime(6), updated_date datetime(6), content_type varchar(255) not null, description varchar(255) not null, length bigint not null, name varchar(255) not null, product_id bigint not null, primary key (id)) engine=InnoDB
create table tab_restaurant (id bigint not null auto_increment, created_date datetime(6), updated_date datetime(6), active bit not null, address_cep varchar(255), address_complement varchar(255), address_district varchar(255), address_number varchar(255), address_street varchar(255), name varchar(255) not null, open bit not null, shipping_fee decimal(19,2) not null, address_city_id bigint not null, cookery_id bigint not null, primary key (id)) engine=InnoDB
create table tab_restaurant_payment_method (restaurant_id bigint not null, payment_method_id bigint not null, primary key (restaurant_id, payment_method_id)) engine=InnoDB
create table tab_restaurant_user (restaurant_id bigint not null, user_id bigint not null, primary key (restaurant_id, user_id)) engine=InnoDB
create table tab_state (id bigint not null auto_increment, created_date datetime(6), updated_date datetime(6), name varchar(255) not null, primary key (id)) engine=InnoDB
create table tab_user (id bigint not null auto_increment, created_date datetime(6), updated_date datetime(6), email varchar(255) not null, name varchar(255) not null, password varchar(255) not null, primary key (id)) engine=InnoDB
create table tab_user_group (user_id bigint not null, group_id bigint not null, primary key (user_id, group_id)) engine=InnoDB
alter table tab_city add constraint fk_state foreign key (state_id) references tab_state (id)
alter table tab_group_permission add constraint fk_permission_group foreign key (permission_id) references tab_permission (id)
alter table tab_group_permission add constraint fk_group_permission foreign key (group_id) references tab_group (id)
alter table tab_order add constraint fk_customer foreign key (customer_id) references tab_user (id)
alter table tab_order add constraint FK5wvnusqb21o32vqaepv87at1n foreign key (address_city_id) references tab_city (id)
alter table tab_order add constraint fk_payment_method foreign key (payment_method_id) references tab_payment_method (id)
alter table tab_order add constraint fk_restaurant foreign key (restaurant_id) references tab_restaurant (id)
alter table tab_order_item add constraint fk_order_order_item foreign key (order_id) references tab_order (id)
alter table tab_order_item add constraint fk_product_order_item foreign key (product_id) references tab_product (id)
alter table tab_product add constraint fk_restaurant foreign key (restaurant_id) references tab_restaurant (id)
alter table tab_product_photo add constraint fk_product foreign key (product_id) references tab_product (id)
alter table tab_restaurant add constraint FKmjga3rvuox3hrgv5cwrutbkn7 foreign key (address_city_id) references tab_city (id)
alter table tab_restaurant add constraint fk_cookery foreign key (cookery_id) references tab_cookery (id)
alter table tab_restaurant_payment_method add constraint fk_payment_method_restaurant foreign key (payment_method_id) references tab_payment_method (id)
alter table tab_restaurant_payment_method add constraint fk_restaurant_payment_method foreign key (restaurant_id) references tab_restaurant (id)
alter table tab_restaurant_user add constraint fk_responsible_restaurant foreign key (user_id) references tab_user (id)
alter table tab_restaurant_user add constraint fk_restaurant_responsible foreign key (restaurant_id) references tab_restaurant (id)
alter table tab_user_group add constraint fk_group_user foreign key (group_id) references tab_group (id)
alter table tab_user_group add constraint fk_user_group foreign key (user_id) references tab_user (id)
create table tab_city (id bigint not null auto_increment, created_date datetime(6), updated_date datetime(6), name varchar(255) not null, state_id bigint not null, primary key (id)) engine=InnoDB
create table tab_cookery (id bigint not null auto_increment, created_date datetime(6), updated_date datetime(6), name varchar(255) not null, primary key (id)) engine=InnoDB
create table tab_group (id bigint not null auto_increment, created_date datetime(6), updated_date datetime(6), name varchar(255) not null, primary key (id)) engine=InnoDB
create table tab_group_permission (group_id bigint not null, permission_id bigint not null, primary key (group_id, permission_id)) engine=InnoDB
create table tab_order (id bigint not null auto_increment, created_date datetime(6), updated_date datetime(6), cancellation_date datetime(6), code varchar(255) not null, confirmation_date datetime(6), address_cep varchar(255), address_complement varchar(255), address_district varchar(255), address_number varchar(255), address_street varchar(255), delivery_date datetime(6), shipping_fee decimal(19,2) not null, status varchar(255), sub_total decimal(19,2) not null, total_value decimal(19,2) not null, customer_id bigint not null, address_city_id bigint not null, payment_method_id bigint not null, restaurant_id bigint not null, primary key (id)) engine=InnoDB
create table tab_order_item (id bigint not null auto_increment, created_date datetime(6), updated_date datetime(6), observation varchar(255), quantity integer not null, total_value decimal(19,2) not null, unit_value decimal(19,2) not null, order_id bigint not null, product_id bigint not null, primary key (id)) engine=InnoDB
create table tab_payment_method (id bigint not null auto_increment, created_date datetime(6), updated_date datetime(6), name varchar(255) not null, primary key (id)) engine=InnoDB
create table tab_permission (id bigint not null auto_increment, created_date datetime(6), updated_date datetime(6), description varchar(255) not null, name varchar(255) not null, primary key (id)) engine=InnoDB
create table tab_product (id bigint not null auto_increment, created_date datetime(6), updated_date datetime(6), active bit not null, description varchar(255) not null, name varchar(255) not null, value decimal(19,2) not null, restaurant_id bigint not null, primary key (id)) engine=InnoDB
create table tab_product_photo (id bigint not null auto_increment, created_date datetime(6), updated_date datetime(6), content_type varchar(255) not null, description varchar(255) not null, length bigint not null, name varchar(255) not null, product_id bigint not null, primary key (id)) engine=InnoDB
create table tab_restaurant (id bigint not null auto_increment, created_date datetime(6), updated_date datetime(6), active bit not null, address_cep varchar(255), address_complement varchar(255), address_district varchar(255), address_number varchar(255), address_street varchar(255), name varchar(255) not null, open bit not null, shipping_fee decimal(19,2) not null, address_city_id bigint not null, cookery_id bigint not null, primary key (id)) engine=InnoDB
create table tab_restaurant_payment_method (restaurant_id bigint not null, payment_method_id bigint not null, primary key (restaurant_id, payment_method_id)) engine=InnoDB
create table tab_restaurant_user (restaurant_id bigint not null, user_id bigint not null, primary key (restaurant_id, user_id)) engine=InnoDB
create table tab_state (id bigint not null auto_increment, created_date datetime(6), updated_date datetime(6), name varchar(255) not null, primary key (id)) engine=InnoDB
create table tab_user (id bigint not null auto_increment, created_date datetime(6), updated_date datetime(6), email varchar(255) not null, name varchar(255) not null, password varchar(255) not null, primary key (id)) engine=InnoDB
create table tab_user_group (user_id bigint not null, group_id bigint not null, primary key (user_id, group_id)) engine=InnoDB
alter table tab_city add constraint fk_state foreign key (state_id) references tab_state (id)
alter table tab_group_permission add constraint fk_permission_group foreign key (permission_id) references tab_permission (id)
alter table tab_group_permission add constraint fk_group_permission foreign key (group_id) references tab_group (id)
alter table tab_order add constraint fk_customer foreign key (customer_id) references tab_user (id)
alter table tab_order add constraint FK5wvnusqb21o32vqaepv87at1n foreign key (address_city_id) references tab_city (id)
alter table tab_order add constraint fk_payment_method foreign key (payment_method_id) references tab_payment_method (id)
alter table tab_order add constraint fk_restaurant foreign key (restaurant_id) references tab_restaurant (id)
alter table tab_order_item add constraint fk_order_order_item foreign key (order_id) references tab_order (id)
alter table tab_order_item add constraint fk_product_order_item foreign key (product_id) references tab_product (id)
alter table tab_product add constraint fk_restaurant foreign key (restaurant_id) references tab_restaurant (id)
alter table tab_product_photo add constraint fk_product foreign key (product_id) references tab_product (id)
alter table tab_restaurant add constraint FKmjga3rvuox3hrgv5cwrutbkn7 foreign key (address_city_id) references tab_city (id)
alter table tab_restaurant add constraint fk_cookery foreign key (cookery_id) references tab_cookery (id)
alter table tab_restaurant_payment_method add constraint fk_payment_method_restaurant foreign key (payment_method_id) references tab_payment_method (id)
alter table tab_restaurant_payment_method add constraint fk_restaurant_payment_method foreign key (restaurant_id) references tab_restaurant (id)
alter table tab_restaurant_user add constraint fk_responsible_restaurant foreign key (user_id) references tab_user (id)
alter table tab_restaurant_user add constraint fk_restaurant_responsible foreign key (restaurant_id) references tab_restaurant (id)
alter table tab_user_group add constraint fk_group_user foreign key (group_id) references tab_group (id)
alter table tab_user_group add constraint fk_user_group foreign key (user_id) references tab_user (id)
create table tab_city (id bigint not null auto_increment, created_date datetime(6), updated_date datetime(6), name varchar(255) not null, state_id bigint not null, primary key (id)) engine=InnoDB
create table tab_cookery (id bigint not null auto_increment, created_date datetime(6), updated_date datetime(6), name varchar(255) not null, primary key (id)) engine=InnoDB
create table tab_group (id bigint not null auto_increment, created_date datetime(6), updated_date datetime(6), name varchar(255) not null, primary key (id)) engine=InnoDB
create table tab_group_permission (group_id bigint not null, permission_id bigint not null, primary key (group_id, permission_id)) engine=InnoDB
create table tab_order (id bigint not null auto_increment, created_date datetime(6), updated_date datetime(6), cancellation_date datetime(6), code varchar(255) not null, confirmation_date datetime(6), address_cep varchar(255), address_complement varchar(255), address_district varchar(255), address_number varchar(255), address_street varchar(255), delivery_date datetime(6), shipping_fee decimal(19,2) not null, status varchar(255), sub_total decimal(19,2) not null, total_value decimal(19,2) not null, customer_id bigint not null, address_city_id bigint not null, payment_method_id bigint not null, restaurant_id bigint not null, primary key (id)) engine=InnoDB
create table tab_order_item (id bigint not null auto_increment, created_date datetime(6), updated_date datetime(6), observation varchar(255), quantity integer not null, total_value decimal(19,2) not null, unit_value decimal(19,2) not null, order_id bigint not null, product_id bigint not null, primary key (id)) engine=InnoDB
create table tab_payment_method (id bigint not null auto_increment, created_date datetime(6), updated_date datetime(6), name varchar(255) not null, primary key (id)) engine=InnoDB
create table tab_permission (id bigint not null auto_increment, created_date datetime(6), updated_date datetime(6), description varchar(255) not null, name varchar(255) not null, primary key (id)) engine=InnoDB
create table tab_product (id bigint not null auto_increment, created_date datetime(6), updated_date datetime(6), active bit not null, description varchar(255) not null, name varchar(255) not null, value decimal(19,2) not null, restaurant_id bigint not null, primary key (id)) engine=InnoDB
create table tab_product_photo (id bigint not null auto_increment, created_date datetime(6), updated_date datetime(6), content_type varchar(255) not null, description varchar(255) not null, length bigint not null, name varchar(255) not null, product_id bigint not null, primary key (id)) engine=InnoDB
create table tab_restaurant (id bigint not null auto_increment, created_date datetime(6), updated_date datetime(6), active bit not null, address_cep varchar(255), address_complement varchar(255), address_district varchar(255), address_number varchar(255), address_street varchar(255), name varchar(255) not null, open bit not null, shipping_fee decimal(19,2) not null, address_city_id bigint not null, cookery_id bigint not null, primary key (id)) engine=InnoDB
create table tab_restaurant_payment_method (restaurant_id bigint not null, payment_method_id bigint not null, primary key (restaurant_id, payment_method_id)) engine=InnoDB
create table tab_restaurant_user (restaurant_id bigint not null, user_id bigint not null, primary key (restaurant_id, user_id)) engine=InnoDB
create table tab_state (id bigint not null auto_increment, created_date datetime(6), updated_date datetime(6), name varchar(255) not null, primary key (id)) engine=InnoDB
create table tab_user (id bigint not null auto_increment, created_date datetime(6), updated_date datetime(6), email varchar(255) not null, name varchar(255) not null, password varchar(255) not null, primary key (id)) engine=InnoDB
create table tab_user_group (user_id bigint not null, group_id bigint not null, primary key (user_id, group_id)) engine=InnoDB
alter table tab_city add constraint fk_state foreign key (state_id) references tab_state (id)
alter table tab_group_permission add constraint fk_permission_group foreign key (permission_id) references tab_permission (id)
alter table tab_group_permission add constraint fk_group_permission foreign key (group_id) references tab_group (id)
alter table tab_order add constraint fk_customer foreign key (customer_id) references tab_user (id)
alter table tab_order add constraint FK5wvnusqb21o32vqaepv87at1n foreign key (address_city_id) references tab_city (id)
alter table tab_order add constraint fk_payment_method foreign key (payment_method_id) references tab_payment_method (id)
alter table tab_order add constraint fk_restaurant foreign key (restaurant_id) references tab_restaurant (id)
alter table tab_order_item add constraint fk_order_order_item foreign key (order_id) references tab_order (id)
alter table tab_order_item add constraint fk_product_order_item foreign key (product_id) references tab_product (id)
alter table tab_product add constraint fk_restaurant foreign key (restaurant_id) references tab_restaurant (id)
alter table tab_product_photo add constraint fk_product foreign key (product_id) references tab_product (id)
alter table tab_restaurant add constraint FKmjga3rvuox3hrgv5cwrutbkn7 foreign key (address_city_id) references tab_city (id)
alter table tab_restaurant add constraint fk_cookery foreign key (cookery_id) references tab_cookery (id)
alter table tab_restaurant_payment_method add constraint fk_payment_method_restaurant foreign key (payment_method_id) references tab_payment_method (id)
alter table tab_restaurant_payment_method add constraint fk_restaurant_payment_method foreign key (restaurant_id) references tab_restaurant (id)
alter table tab_restaurant_user add constraint fk_responsible_restaurant foreign key (user_id) references tab_user (id)
alter table tab_restaurant_user add constraint fk_restaurant_responsible foreign key (restaurant_id) references tab_restaurant (id)
alter table tab_user_group add constraint fk_group_user foreign key (group_id) references tab_group (id)
alter table tab_user_group add constraint fk_user_group foreign key (user_id) references tab_user (id)
create table tab_city (id bigint not null auto_increment, created_date datetime(6), updated_date datetime(6), name varchar(255) not null, state_id bigint not null, primary key (id)) engine=InnoDB
create table tab_cookery (id bigint not null auto_increment, created_date datetime(6), updated_date datetime(6), name varchar(255) not null, primary key (id)) engine=InnoDB
create table tab_group (id bigint not null auto_increment, created_date datetime(6), updated_date datetime(6), name varchar(255) not null, primary key (id)) engine=InnoDB
create table tab_group_permission (group_id bigint not null, permission_id bigint not null, primary key (group_id, permission_id)) engine=InnoDB
create table tab_order (id bigint not null auto_increment, created_date datetime(6), updated_date datetime(6), cancellation_date datetime(6), code varchar(255) not null, confirmation_date datetime(6), address_cep varchar(255), address_complement varchar(255), address_district varchar(255), address_number varchar(255), address_street varchar(255), delivery_date datetime(6), shipping_fee decimal(19,2) not null, status varchar(255), sub_total decimal(19,2) not null, total_value decimal(19,2) not null, customer_id bigint not null, address_city_id bigint not null, payment_method_id bigint not null, restaurant_id bigint not null, primary key (id)) engine=InnoDB
create table tab_order_item (id bigint not null auto_increment, created_date datetime(6), updated_date datetime(6), observation varchar(255), quantity integer not null, total_value decimal(19,2) not null, unit_value decimal(19,2) not null, order_id bigint not null, product_id bigint not null, primary key (id)) engine=InnoDB
create table tab_payment_method (id bigint not null auto_increment, created_date datetime(6), updated_date datetime(6), name varchar(255) not null, primary key (id)) engine=InnoDB
create table tab_permission (id bigint not null auto_increment, created_date datetime(6), updated_date datetime(6), description varchar(255) not null, name varchar(255) not null, primary key (id)) engine=InnoDB
create table tab_product (id bigint not null auto_increment, created_date datetime(6), updated_date datetime(6), active bit not null, description varchar(255) not null, name varchar(255) not null, value decimal(19,2) not null, restaurant_id bigint not null, primary key (id)) engine=InnoDB
create table tab_product_photo (id bigint not null auto_increment, created_date datetime(6), updated_date datetime(6), content_type varchar(255) not null, description varchar(255) not null, length bigint not null, name varchar(255) not null, product_id bigint not null, primary key (id)) engine=InnoDB
create table tab_restaurant (id bigint not null auto_increment, created_date datetime(6), updated_date datetime(6), active bit not null, address_cep varchar(255), address_complement varchar(255), address_district varchar(255), address_number varchar(255), address_street varchar(255), name varchar(255) not null, open bit not null, shipping_fee decimal(19,2) not null, address_city_id bigint not null, cookery_id bigint not null, primary key (id)) engine=InnoDB
create table tab_restaurant_payment_method (restaurant_id bigint not null, payment_method_id bigint not null, primary key (restaurant_id, payment_method_id)) engine=InnoDB
create table tab_restaurant_user (restaurant_id bigint not null, user_id bigint not null, primary key (restaurant_id, user_id)) engine=InnoDB
create table tab_state (id bigint not null auto_increment, created_date datetime(6), updated_date datetime(6), name varchar(255) not null, primary key (id)) engine=InnoDB
create table tab_user (id bigint not null auto_increment, created_date datetime(6), updated_date datetime(6), email varchar(255) not null, name varchar(255) not null, password varchar(255) not null, primary key (id)) engine=InnoDB
create table tab_user_group (user_id bigint not null, group_id bigint not null, primary key (user_id, group_id)) engine=InnoDB
alter table tab_city add constraint fk_state foreign key (state_id) references tab_state (id)
alter table tab_group_permission add constraint fk_permission_group foreign key (permission_id) references tab_permission (id)
alter table tab_group_permission add constraint fk_group_permission foreign key (group_id) references tab_group (id)
alter table tab_order add constraint fk_customer foreign key (customer_id) references tab_user (id)
alter table tab_order add constraint FK5wvnusqb21o32vqaepv87at1n foreign key (address_city_id) references tab_city (id)
alter table tab_order add constraint fk_payment_method foreign key (payment_method_id) references tab_payment_method (id)
alter table tab_order add constraint fk_restaurant foreign key (restaurant_id) references tab_restaurant (id)
alter table tab_order_item add constraint fk_order_order_item foreign key (order_id) references tab_order (id)
alter table tab_order_item add constraint fk_product_order_item foreign key (product_id) references tab_product (id)
alter table tab_product add constraint fk_restaurant foreign key (restaurant_id) references tab_restaurant (id)
alter table tab_product_photo add constraint fk_product foreign key (product_id) references tab_product (id)
alter table tab_restaurant add constraint FKmjga3rvuox3hrgv5cwrutbkn7 foreign key (address_city_id) references tab_city (id)
alter table tab_restaurant add constraint fk_cookery foreign key (cookery_id) references tab_cookery (id)
alter table tab_restaurant_payment_method add constraint fk_payment_method_restaurant foreign key (payment_method_id) references tab_payment_method (id)
alter table tab_restaurant_payment_method add constraint fk_restaurant_payment_method foreign key (restaurant_id) references tab_restaurant (id)
alter table tab_restaurant_user add constraint fk_responsible_restaurant foreign key (user_id) references tab_user (id)
alter table tab_restaurant_user add constraint fk_restaurant_responsible foreign key (restaurant_id) references tab_restaurant (id)
alter table tab_user_group add constraint fk_group_user foreign key (group_id) references tab_group (id)
alter table tab_user_group add constraint fk_user_group foreign key (user_id) references tab_user (id)
create table tab_city (id bigint not null auto_increment, created_date datetime(6), updated_date datetime(6), name varchar(255) not null, state_id bigint not null, primary key (id)) engine=InnoDB
create table tab_cookery (id bigint not null auto_increment, created_date datetime(6), updated_date datetime(6), name varchar(255) not null, primary key (id)) engine=InnoDB
create table tab_group (id bigint not null auto_increment, created_date datetime(6), updated_date datetime(6), name varchar(255) not null, primary key (id)) engine=InnoDB
create table tab_group_permission (group_id bigint not null, permission_id bigint not null, primary key (group_id, permission_id)) engine=InnoDB
create table tab_order (id bigint not null auto_increment, created_date datetime(6), updated_date datetime(6), cancellation_date datetime(6), code varchar(255) not null, confirmation_date datetime(6), address_cep varchar(255), address_complement varchar(255), address_district varchar(255), address_number varchar(255), address_street varchar(255), delivery_date datetime(6), shipping_fee decimal(19,2) not null, status varchar(255), sub_total decimal(19,2) not null, total_value decimal(19,2) not null, customer_id bigint not null, address_city_id bigint not null, payment_method_id bigint not null, restaurant_id bigint not null, primary key (id)) engine=InnoDB
create table tab_order_item (id bigint not null auto_increment, created_date datetime(6), updated_date datetime(6), observation varchar(255), quantity integer not null, total_value decimal(19,2) not null, unit_value decimal(19,2) not null, order_id bigint not null, product_id bigint not null, primary key (id)) engine=InnoDB
create table tab_payment_method (id bigint not null auto_increment, created_date datetime(6), updated_date datetime(6), name varchar(255) not null, primary key (id)) engine=InnoDB
create table tab_permission (id bigint not null auto_increment, created_date datetime(6), updated_date datetime(6), description varchar(255) not null, name varchar(255) not null, primary key (id)) engine=InnoDB
create table tab_product (id bigint not null auto_increment, created_date datetime(6), updated_date datetime(6), active bit not null, description varchar(255) not null, name varchar(255) not null, value decimal(19,2) not null, restaurant_id bigint not null, primary key (id)) engine=InnoDB
create table tab_product_photo (id bigint not null auto_increment, created_date datetime(6), updated_date datetime(6), content_type varchar(255) not null, description varchar(255) not null, length bigint not null, name varchar(255) not null, product_id bigint not null, primary key (id)) engine=InnoDB
create table tab_restaurant (id bigint not null auto_increment, created_date datetime(6), updated_date datetime(6), active bit not null, address_cep varchar(255), address_complement varchar(255), address_district varchar(255), address_number varchar(255), address_street varchar(255), name varchar(255) not null, open bit not null, shipping_fee decimal(19,2) not null, address_city_id bigint not null, cookery_id bigint not null, primary key (id)) engine=InnoDB
create table tab_restaurant_payment_method (restaurant_id bigint not null, payment_method_id bigint not null, primary key (restaurant_id, payment_method_id)) engine=InnoDB
create table tab_restaurant_user (restaurant_id bigint not null, user_id bigint not null, primary key (restaurant_id, user_id)) engine=InnoDB
create table tab_state (id bigint not null auto_increment, created_date datetime(6), updated_date datetime(6), name varchar(255) not null, primary key (id)) engine=InnoDB
create table tab_user (id bigint not null auto_increment, created_date datetime(6), updated_date datetime(6), email varchar(255) not null, name varchar(255) not null, password varchar(255) not null, primary key (id)) engine=InnoDB
create table tab_user_group (user_id bigint not null, group_id bigint not null, primary key (user_id, group_id)) engine=InnoDB
alter table tab_city add constraint fk_state foreign key (state_id) references tab_state (id)
alter table tab_group_permission add constraint fk_permission_group foreign key (permission_id) references tab_permission (id)
alter table tab_group_permission add constraint fk_group_permission foreign key (group_id) references tab_group (id)
alter table tab_order add constraint fk_customer foreign key (customer_id) references tab_user (id)
alter table tab_order add constraint FK5wvnusqb21o32vqaepv87at1n foreign key (address_city_id) references tab_city (id)
alter table tab_order add constraint fk_payment_method foreign key (payment_method_id) references tab_payment_method (id)
alter table tab_order add constraint fk_restaurant foreign key (restaurant_id) references tab_restaurant (id)
alter table tab_order_item add constraint fk_order_order_item foreign key (order_id) references tab_order (id)
alter table tab_order_item add constraint fk_product_order_item foreign key (product_id) references tab_product (id)
alter table tab_product add constraint fk_restaurant foreign key (restaurant_id) references tab_restaurant (id)
alter table tab_product_photo add constraint fk_product foreign key (product_id) references tab_product (id)
alter table tab_restaurant add constraint FKmjga3rvuox3hrgv5cwrutbkn7 foreign key (address_city_id) references tab_city (id)
alter table tab_restaurant add constraint fk_cookery foreign key (cookery_id) references tab_cookery (id)
alter table tab_restaurant_payment_method add constraint fk_payment_method_restaurant foreign key (payment_method_id) references tab_payment_method (id)
alter table tab_restaurant_payment_method add constraint fk_restaurant_payment_method foreign key (restaurant_id) references tab_restaurant (id)
alter table tab_restaurant_user add constraint fk_responsible_restaurant foreign key (user_id) references tab_user (id)
alter table tab_restaurant_user add constraint fk_restaurant_responsible foreign key (restaurant_id) references tab_restaurant (id)
alter table tab_user_group add constraint fk_group_user foreign key (group_id) references tab_group (id)
alter table tab_user_group add constraint fk_user_group foreign key (user_id) references tab_user (id)
