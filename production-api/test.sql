-- Active: 1651407545552@@127.0.0.1@3306@db_shop_test
create table production (
    info_image varbinary(255), 
    introduce varchar(255), 
    name varchar(255), 
    sales integer, 
    score float(23), 
    system_type tinyint check (system_type between 0 and 2), 
    time bigint, 
    top_image varbinary(255), 
    type tinyint check (type between 0 and 2), 
    brand_value integer not null, 
    primary key (brand_value)) 
engine=InnoDB