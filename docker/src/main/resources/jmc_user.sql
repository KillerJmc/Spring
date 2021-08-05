create table user
(
    id       int auto_increment
        primary key,
    name     char(50) not null,
    age      int      not null,
    password char(50) not null,
    constraint user_name_uindex
        unique (name)
);

INSERT INTO jmc.user (id, name, age, password) VALUES (1, 'Jmc', 18, '0189');
INSERT INTO jmc.user (id, name, age, password) VALUES (2, 'Merry', 18, '2222');
INSERT INTO jmc.user (id, name, age, password) VALUES (3, 'Jack', 30, '3333');
INSERT INTO jmc.user (id, name, age, password) VALUES (4, 'Lucy', 16, '0000');
INSERT INTO jmc.user (id, name, age, password) VALUES (5, 'Jerry', 30, '1111');