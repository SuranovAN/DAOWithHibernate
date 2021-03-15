create schema if not exists usersdb;

create table if not exists usersdb.users(
    login     varchar not null primary key,
    password  varchar not null,
    authorities varchar
);