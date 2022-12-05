drop tablespace Jmc including contents and datafiles;

-- Oracle中是表空间下存放多个对象(表，视图，同义词，序列等)
-- 也就是说Oracle的表空间和MySQL的数据库有点像
-- 区别在于Oracle每个表空间包含1个或多个dbf文件，必须手动指定
-- MySQL的数据库是一个文件夹，每个表对应一个ibd文件，无需指定
create tablespace Jmc
    datafile '/u01/app/oracle/oradata/XE/jmc.dbf'
    size 100m
    autoextend on
    next 10m;
