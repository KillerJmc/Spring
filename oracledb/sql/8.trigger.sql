-- 触发器：一段被自动调用的代码，常用于更新后，插入前做一些操作

-- 前置触发器
create or replace trigger before_insert before insert on t_user
for each row
begin
    dbms_output.put_line('t_user.before_insert trigger!');
    -- 通过:new获取新插入的行
    dbms_output.put_line('insert name -> ' || :new.name);
end;

-- 后置触发器
create or replace trigger after_update after update on t_user
for each row
begin
    dbms_output.put_line('t_user.after_update trigger!');
    -- 通过:old获取旧行
    dbms_output.put_line('old id -> ' || :old.id);
    -- 通过:new获取新更新的行
    dbms_output.put_line('new id -> ' || :new.id);
end;

-- 触发前置触发器
insert into t_user values (6, 'What', 33, '666');

-- 触发后置触发器
update t_user set id = 7 where name = 'What';

delete from t_user where id = 7;
