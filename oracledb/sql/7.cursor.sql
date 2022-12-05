-- 游标：用来遍历表的行

declare
    -- 定义显式游标
    cursor lines is select * from t_user;
begin
    for line in lines
    loop
        dbms_output.put_line('name = ' || line.name);
    end loop;

    -- 其实sql会创建一个隐式游标，可以直接用
    for line in (select * from t_user)
    loop
        dbms_output.put_line('id = ' || line.ID);
    end loop;
end;
