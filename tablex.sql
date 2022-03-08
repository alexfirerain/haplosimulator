create table CUSTOMERS
(
    id  integer primary key auto_increment,
    name    varchar(255),
    surname varchar(255),
    age int check ( age >=0 ),
    phone_number varchar(63)
);

create table ORDERS
(
    id integer primary key auto_increment,
    date date,
    customer_id integer,
    product_name varchar(255),
    amount integer check ( amount >= 0 )
);