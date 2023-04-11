alter table comments 
   drop constraint FK6uv0qku8gsu6x1r2jkrtqwjtn;

alter table comments 
   drop constraint FK8omq0tc18jd43bu5tjh6jvraq;

alter table order_lines 
   drop constraint FK1smc0s578t2oih21yn9hw6usr;

alter table order_lines 
   drop constraint FK5v1oeejtgtf2n3toppm3tkuhh;

alter table orders 
   drop constraint FK32ql8ubntj5uh44ph9659tiih;

alter table products 
   drop constraint FKtng6hvelpjyy7el0f5eq93nq4;
 
drop table categories;
drop table comments;
drop table order_lines;
drop table orders;
drop table products;
drop table users;

create table categories (
   category_id bigint not null,
    description varchar(255) not null,
    highlighted boolean,
    icon varchar(255) not null,
    name varchar(255) not null,
    primary key (category_id)
);

create table comments (
   comment_id bigint generated by default as identity,
    rating integer not null,
    text varchar(255) not null,
    timestamp bigint not null,
    product_id bigint not null,
    user_id bigint not null,
    primary key (comment_id)
);

create table order_lines (
   order_line_id bigint generated by default as identity,
    price integer,
    order_id bigint not null,
    product_id bigint not null,
    primary key (order_line_id)
);

create table orders (
   order_id bigint generated by default as identity,
    address varchar(255) not null,
    name varchar(255) not null,
    price integer not null,
    state varchar(255) not null,
    timestamp bigint not null,
    user_id bigint not null,
    primary key (order_id)
);

create table products (
   product_id bigint not null,
    description varchar(255) not null,
    icon varchar(255) not null,
    name varchar(255) not null,
    price integer not null,
    sales integer not null,
    total_comments integer not null,
    total_score integer not null,
    category bigint not null,
    primary key (product_id)
);

create table users (
   user_id bigint generated by default as identity,
    address varchar(255) not null,
    card varchar(255),
    cvv integer,
    expiration_month integer,
    expiration_year integer,
    email varchar(255) not null,
    image varchar(255),
    name varchar(255) not null,
    password varchar(255) not null,
    reset_password_token varchar(255),
    primary key (user_id)
);

alter table categories 
   add constraint UK_t8o6pivur7nn124jehx7cygw5 unique (name);

alter table products 
   add constraint UK_o61fmio5yukmmiqgnxf8pnavn unique (name);

alter table users 
   add constraint UK_6dotkott2kjsp8vw4d0m25fb7 unique (email);

alter table comments 
   add constraint FK6uv0qku8gsu6x1r2jkrtqwjtn 
   foreign key (product_id) 
   references products;

alter table comments 
   add constraint FK8omq0tc18jd43bu5tjh6jvraq 
   foreign key (user_id) 
   references users;

alter table order_lines 
   add constraint FK1smc0s578t2oih21yn9hw6usr 
   foreign key (order_id) 
   references orders;

alter table order_lines 
   add constraint FK5v1oeejtgtf2n3toppm3tkuhh 
   foreign key (product_id) 
   references products;

alter table orders 
   add constraint FK32ql8ubntj5uh44ph9659tiih 
   foreign key (user_id) 
   references users;

alter table products 
   add constraint FKtng6hvelpjyy7el0f5eq93nq4 
   foreign key (category) 
   references categories;