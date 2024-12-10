create table CUSTOMERS
(
    id  integer auto_increment primary key,
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
    amount integer check ( amount >= 0 ),
    foreign key (customer_id) references CUSTOMERS (id)
);

insert into CUSTOMERS (name, surname, age, phone_number)
             VALUES ('alexey', 'pahov', 18, '+798432'),
                    ('Alexey', 'bahov', 19, '+758432'),
                    ('aleXey', 'fahov', 218, '+498432'),
                    ('kurt', 'zahov', 18, '+728432');

insert into ORDERS (date, customer_id, product_name, amount)
            VALUES (now(),                 1, 'tah', 2),
                   ('2007-08-26 17:13:25', 2, 'rae', 15),
                   ('2012-11-15 13:10:00', 3, 'anh', 8),
                   ('2013-05-17 22:14:05', 2, 'tth', 7),
                   (now(),                 4, 'gai', 6);

select c.name, o.product_name
from ORDERS o join CUSTOMERS c
                   on o.customer_id = c.id
WHERE c.name = 'alexey';