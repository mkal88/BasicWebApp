drop table subscriber if exists;

create table subscriber (
    id integer generated by default as identity primary key,
    name varchar(80) not null,
    email varchar(256) not null
);
