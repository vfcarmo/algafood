create table tab_group (
    id bigint not null auto_increment,
    name varchar(60) not null,
    created_date datetime not null,
    updated_date datetime not null,
    primary key (id)
) engine=InnoDB default charset=utf8;

create table tab_permission (
    id bigint not null auto_increment,
    name varchar(100) not null,
    description varchar(60) not null,
    created_date datetime not null,
    updated_date datetime not null,
    primary key (id)
) engine=InnoDB default charset=utf8;

create table tab_payment_method (
    id bigint not null auto_increment,
    name varchar(60) not null,
    created_date datetime not null,
    updated_date datetime not null,
    primary key (id)
) engine=InnoDB default charset=utf8;

create table tab_user (
    id bigint not null auto_increment,
    name varchar(100) not null,
    email varchar(100) not null,
    password varchar(20) not null,
    created_date datetime not null,
    updated_date datetime not null,
    primary key (id)
) engine=InnoDB default charset=utf8;

create table tab_restaurant (
    id bigint not null auto_increment,
    name varchar(60) not null,
    shipping_fee decimal(10,2) not null,
    cookery_id bigint not null,
    open bit not null,
    active bit not null,
    address_cep varchar(9),
    address_street varchar(80),
    address_number varchar(20),
    address_complement varchar(60),
    address_district varchar(60),
    address_city_id bigint not null,
    created_date datetime not null,
    updated_date datetime not null,
    primary key (id)
) engine=InnoDB default charset=utf8;

create table tab_product (
    id bigint not null auto_increment,
    name varchar(80) not null,
    description varchar(100) not null,
    value decimal(10,2) not null,
    active bit not null,
    restaurant_id bigint not null,
    created_date datetime not null,
    updated_date datetime not null,
    primary key (id)
) engine=InnoDB default charset=utf8;

create table tab_order_item (
    id bigint not null auto_increment,
    quantity integer not null,
    unit_value decimal(10,2) not null,
    total_value decimal(10,2) not null,
    observation varchar(150),
    product_id bigint not null,
    order_id bigint not null,
    created_date datetime not null,
    updated_date datetime not null,
    primary key (id)
) engine=InnoDB default charset=utf8;

create table tab_product_photo (
    id bigint not null auto_increment,
    name varchar(60) not null,
    description varchar(100) not null,
    content_type varchar(30) not null,
    length bigint not null,
    product_id bigint not null,
    created_date datetime not null,
    updated_date datetime not null,
    primary key (id)
) engine=InnoDB default charset=utf8;

create table tab_order (
    id bigint not null auto_increment,
    code varchar(30) not null,
    restaurant_id bigint not null,
    customer_id bigint not null,
    sub_total decimal(10,2) not null,
    shipping_fee decimal(10,2) not null,
    total_value decimal(10,2) not null,
    payment_method_id bigint not null,
    status varchar(10) not null,
    delivery_date datetime not null,
    confirmation_date datetime null,
    cancellation_date datetime null,
    address_cep varchar(9),
    address_street varchar(80),
    address_number varchar(20),
    address_complement varchar(60),
    address_district varchar(60),
    address_city_id bigint not null,
    created_date datetime not null,
    updated_date datetime not null,
    primary key (id)
) engine=InnoDB default charset=utf8;

create table tab_restaurant_payment_method (
    restaurant_id bigint not null,
    payment_method_id bigint not null,
    primary key (restaurant_id, payment_method_id)
) engine=InnoDB default charset=utf8;

create table tab_restaurant_user (
    restaurant_id bigint not null,
    user_id bigint not null,
    primary key (restaurant_id, user_id)
) engine=InnoDB default charset=utf8;

create table tab_group_permission (
    group_id bigint not null,
    permission_id bigint not null,
    primary key (group_id, permission_id)
) engine=InnoDB default charset=utf8;

create table tab_user_group (
    user_id bigint not null,
    group_id bigint not null,
    primary key (user_id, group_id)
) engine=InnoDB default charset=utf8;

alter table tab_city add constraint fk_state foreign key (state_id) references tab_state (id);
alter table tab_order add constraint fk_customer foreign key (customer_id) references tab_user (id);
alter table tab_order add constraint fk_order_address_city_id foreign key (address_city_id) references tab_city (id);
alter table tab_order add constraint fk_payment_method foreign key (payment_method_id) references tab_payment_method (id);
alter table tab_order add constraint fk_restaurant foreign key (restaurant_id) references tab_restaurant (id);
alter table tab_order_item add constraint fk_order_order_item foreign key (order_id) references tab_order (id);
alter table tab_order_item add constraint fk_product_order_item foreign key (product_id) references tab_product (id);
alter table tab_product_photo add constraint fk_product foreign key (product_id) references tab_product (id);
alter table tab_restaurant add constraint fk_restaurant_address_city_id foreign key (address_city_id) references tab_city (id);
alter table tab_restaurant add constraint fk_cookery foreign key (cookery_id) references tab_cookery (id);
alter table tab_restaurant_payment_method add constraint fk_payment_method_restaurant foreign key (payment_method_id) references tab_payment_method (id);
alter table tab_restaurant_payment_method add constraint fk_restaurant_payment_method foreign key (restaurant_id) references tab_restaurant (id);
alter table tab_restaurant_user add constraint fk_responsible_restaurant foreign key (user_id) references tab_user (id);
alter table tab_restaurant_user add constraint fk_restaurant_responsible foreign key (restaurant_id) references tab_restaurant (id);
alter table tab_group_permission add constraint fk_permission_group foreign key (permission_id) references tab_permission (id);
alter table tab_group_permission add constraint fk_group_permission foreign key (group_id) references tab_group (id);
alter table tab_user_group add constraint fk_group_user foreign key (group_id) references tab_group (id);
alter table tab_user_group add constraint fk_user_group foreign key (user_id) references tab_user (id);