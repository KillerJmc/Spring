-- 触发器：一段被自动调用的代码，常用于更新后，插入前做一些操作

-- 前置触发器
create trigger before_insert before insert on t_user
begin
    dbms_output.put_line('t_user.before_insert trigger!');
end;

-- 后置触发器
create trigger after_update after update on t_user
begin
    dbms_output.put_line('t_user.after_update trigger!');
end;

-- 触发前置触发器
insert into t_user values (6, 'What', 33, '666');

-- 触发后置触发器
update t_user set id = 7 where name = 'What';

delete from t_user where id = 7;
