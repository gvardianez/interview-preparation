create table if not exists students
(
    id       bigserial primary key,
    name     varchar(255) not null,
    mark     int not null
);

-- create table if not exists films
-- (
--     id       bigserial primary key,
--     title    varchar(255),
--     duration time
-- );
--
-- create table if not exists sessions
-- (
--     id       bigserial primary key,
--     film_id  bigint references films (id),
--     price    int       not null,
--     datetime timestamp not null
-- );
--
-- create table if not exists tickets
-- (
--     id         bigserial primary key,
--     number     int not null,
--     session_id bigint references sessions (id)
-- );

