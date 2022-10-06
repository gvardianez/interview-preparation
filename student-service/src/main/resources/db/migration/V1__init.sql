
create table if not exists students
(
    id       bigserial primary key,
    name     varchar(255) not null,
    age      int not null
);

insert into students (name, age)
values ('Вася', 21),
       ('Петя', 18),
       ('Маша', 19);
