create table car_washes (car_wash_id int4 generated by default as identity, location varchar(255) not null, primary key (car_wash_id));
create table confirmation_codes (confirmation_code_id int4 generated by default as identity, code varchar(255) not null, email varchar(255) not null, primary key (confirmation_code_id));
create table employees (employee_id int4 generated by default as identity, name varchar(255) not null, passport_data varchar(255) not null, car_wash_id int4 not null, primary key (employee_id));
create table order_service (order_id int4 not null, service_id int4 not null, primary key (order_id, service_id));
create table orders (order_id int4 generated by default as identity, date timestamp not null, grade float8, total_price int4 not null, car_wash_id int4 not null, employee_id int4 not null, user_id int4 not null, primary key (order_id));
create table reviews (review_id int4 generated by default as identity, review_text varchar(255) not null, car_wash_id int4 not null, user_id int4 not null, primary key (review_id));
create table services (service_id int4 generated by default as identity, name varchar(255) not null, price int4 not null, car_wash_id int4 not null, primary key (service_id));
create table users (user_id int4 generated by default as identity, email varchar(255) not null, name varchar(255) not null, password varchar(255) not null, primary key (user_id));
alter table if exists employees add constraint FK66qf3y81urtiknh6wuqms9l85 foreign key (car_wash_id) references car_washes;
alter table if exists order_service add constraint FKgc67yeomlx546t08eep9k3sc8 foreign key (service_id) references services;
alter table if exists order_service add constraint FKq63mqdm9abe2kw0hsa1qh4pdw foreign key (order_id) references orders;
alter table if exists orders add constraint FK3l6j0unv4rbx4b8tp5cd17xdj foreign key (car_wash_id) references car_washes;
alter table if exists orders add constraint FKfhl8bv0xn3sj33q2f3scf1bq6 foreign key (employee_id) references employees;
alter table if exists orders add constraint FK32ql8ubntj5uh44ph9659tiih foreign key (user_id) references users;
alter table if exists reviews add constraint FKji7r7ixpouf76tj23kkxmex00 foreign key (car_wash_id) references car_washes;
alter table if exists reviews add constraint FKcgy7qjc1r99dp117y9en6lxye foreign key (user_id) references users;
alter table if exists services add constraint FKclnkd4md84dbhiemunf44sk7e foreign key (car_wash_id) references car_washes;
