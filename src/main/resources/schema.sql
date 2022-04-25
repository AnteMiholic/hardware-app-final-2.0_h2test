create table if not exists mydb.hardware
(
    id     int auto_increment
        primary key,
    name   varchar(30) not null,
    code   varchar(15) not null,
    price  decimal(2)  not null,
    type   varchar(15) not null,
    amount int         not null
);