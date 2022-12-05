-- PL/SQL (Procedure Lang/SQL) 是Oracle的过程处理语言
-- 用来编写sql高级脚本

-- 声明变量
declare
    -- 变量名 类型
    n number;
    name0 varchar2(20);
    age0 number(3);
    -- 从表中抓取列类型
    password0 t_user.password%type;
    -- 从表中抓取行类型
    line t_user%rowtype;
begin
    -- 变量赋值
    n := 1;
    -- 注意，需要在idea Services -> Oracle数据库实例 -> 6.pl_sql右键 -> Enable DBMS_OUTPUT开启输出功能！
    dbms_output.put_line('n = ' || n);

    -- 注入变量
    select name, age, password into name0, age0, password0 from t_user where id = 1;
    dbms_output.put_line(
        'id = 1的记录 -> name = ' || name0 ||
         '; age = ' || age0 ||
         '; password = ' || password0
    );

    -- 注入一整行
    select * into line from t_user where id = 2;
    dbms_output.put_line('id = 2的记录 -> name = ' || line.name || '; age = ' || line.age);

    -- 判断
    if line.age < 18 then
        dbms_output.put_line(line.name || ': 未成年');
    elsif line.age = 18 then
        dbms_output.put_line(line.name || ': 刚好成年');
    else
        dbms_output.put_line(line.name || ': 老了!');
    end if;

    -- 循环
    dbms_output.put('n = ');
    while n <= 10
    loop
        dbms_output.put(n || ', ');
        n := n + 1;
    end loop;

    dbms_output.put_line('');

    dbms_output.put('i = ');
    for i in 1..5
    loop
            dbms_output.put(i || ', ');
    end loop;
    dbms_output.put_line('');

    -- 引发没找到类型异常
    select * into line from t_user where id = 999;

    -- 引发找到行太多异常
    select * into line from t_user;
exception
    -- 和java异常一样,会中断程序
    when no_data_found then
        dbms_output.put_line('找不到该行!');
    when too_many_rows then
        dbms_output.put_line('结果行太多了!');
end;
