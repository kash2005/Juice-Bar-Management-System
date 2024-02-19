create database if not exists juiceBar;
use juiceBar;
create table employee
(
    eId        varchar(50) primary key,
    name       varchar(50),
    address    text,
    email      text,
    contact    varchar(50),
    jobRoll    varchar(50),
    onePerHour varchar(50)
);
create table user
(
    userId   varchar(50) primary key,
    userName varchar(50),
    password varchar(50),
    foreign key (userId) references employee (eId)
);
create table customer
(
    customerId varchar(50) primary key,
    name       varchar(50),
    address    text,
    email      text,
    contact    varchar(50)
);
create table orders
(
    orderId    varchar(50) primary key,
    date       date,
    customerId varchar(50),
    foreign key (customerId) references customer (customerId)
);
create table item
(
    itemId      varchar(50) primary key,
    description varchar(50),
    qty         int,
    price double
);
create table orderDetails
(
    orderId varchar(50),
    itemId  varchar(50),
    getQty  int,
    amount double,
    foreign key (orderId) references orders (orderId),
    foreign key (itemId) references item (itemId),
    primary key (orderId, itemId)
);
create table ingredient
(
    ingredientId varchar(50) primary key,
    description  varchar(50),
    price double,
    weight       varchar(50)
);
create table itemAndIngredientDetails
(
    ingredientId varchar(50),
    itemId       varchar(50),
    getQty       int,
    amount double,
    foreign key (ingredientId) references ingredient (ingredientId),
    foreign key (itemId) references item (itemId),
    primary key (ingredientId, itemId)
);
create table supplier
(
    supplierId varchar(50) primary key,
    name       varchar(50),
    contact    varchar(50),
    company    text
);
create table supplierOrderDetails
(
    supplierId   varchar(50),
    ingredientId varchar(50),
    getQty       int,
    amount double,
    foreign key (ingredientId) references ingredient (ingredientId),
    foreign key (supplierId) references supplier (supplierId),
    primary key (supplierId, ingredientId)
);
create table delivery
(
    deliveryId varchar(50) primary key,
    distance   varchar(50),
    price double,
    orderId    varchar(50),
    foreign key (orderId) references orders (orderId)
);
create table attendance
(
    attendanceId varchar(50) primary key,
    departTime   time,
    entryTime    time,
    eId          varchar(50),
    foreign key (eId) references employee (eId)
);
