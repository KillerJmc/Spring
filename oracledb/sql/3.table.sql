drop table Jmc.t_user

create table Jmc.t_user
(
    -- number是Oracle的通用数字类型，number(3)表示3位数；number(2.3)表示小数如58.123
    id       number primary key,
    -- varchar2是Oracle特有的数据类型（默认），所有的英文，汉字都占2个字节
    name     varchar2(20) not null,
    age      number(3) not null,
    password varchar2(20) not null
);

insert into t_user (id, name, age, password) values (1, 'Jmc', 18, '0189');
insert into t_user (id, name, age, password) values (2, 'Merry', 18, '2222');
insert into t_user (id, name, age, password) values (3, 'Jack', 30, '3333');
insert into t_user (id, name, age, password) values (4, 'Lucy', 16, '0000');
insert into t_user (id, name, age, password) values (5, 'Jerry', 30, '1111');

-- 注意：Oracle需要手动commit
commit;

select * from t_user;
