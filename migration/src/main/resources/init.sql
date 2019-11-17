create database if not exists pizzeria;
use pizzeria;

create table if not exists extra_sections (
    id				int primary key auto_increment not null,
    name			varchar(255) not null
);

create table if not exists extras (
    id 				int primary key auto_increment not null,
    name			varchar(255) not null,
    weight      	int not null,
    price           double not null,
    section_id		int not null,
    foreign key (section_id) references extra_sections(id)
);

create table if not exists pizza_sections (
    id 				int primary key auto_increment not null,
    name			varchar(255) not null,
    price_s         double not null,
    price_m         double not null,
    price_l         double not null
);

create table if not exists pizzas (
    id 				int primary key auto_increment not null,
    name			varchar(255) not null,
    weight_s        int not null,
    weight_m        int not null,
    weight_l        int not null,
    section_id		int not null,
    foreign key (section_id) references pizza_sections(id)
);

create table if not exists toppings (
    id				int primary key auto_increment not null,
    name            varchar(255) not null
);

create table if not exists pizza_toppings (
    id 				int primary key auto_increment not null,
    pizza_id		int not null,
    topping_id	    int not null,
    foreign key (pizza_id) references pizzas(id),
    foreign key (topping_id) references toppings(id)
);

create table if not exists users (
    id              int primary key auto_increment not null,
    login           varchar(255) unique not null,
    password_hash   varchar(64) unique not null ,
    password_salt	varchar(16) not null,
    admin           boolean
);

create table if not exists orders (
    id              int primary key auto_increment not null,
    date_time       datetime not null,
    ready           boolean,
    user_id         int not null,
    foreign key (user_id) references users(id)
);

create table if not exists ordered_pizzas (
    id              int primary key auto_increment not null,
    size            int not null,
    count           int not null,
    pizza_id        int not null,
    order_id        int not null,
    foreign key (pizza_id) references pizzas(id),
    foreign key (order_id) references orders(id)
);

create table if not exists ordered_extras (
    id              int primary key auto_increment not null,
    count           int not null,
    extra_id        int not null,
    order_id        int not null,
    foreign key (extra_id) references extras(id),
    foreign key (order_id) references orders(id)
);

