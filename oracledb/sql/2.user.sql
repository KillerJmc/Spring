drop user jmc;

-- Oracle中每个用户管理1个或多个表空间，也就是说有库才有用户！
-- 而在MySQL中则没有这么细化，没有默认库也能有用户
create user jmc
    identified by root
    default tablespace Jmc;

grant dba to jmc;
