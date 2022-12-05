drop sequence seq_test;

-- 序列，用于提供主键自增功能：Oracle没有（悲
create sequence seq_test;

-- 获取序列下一个值（从1开始）
select seq_test.nextval from dual;

-- 获取序列当前值
select seq_test.currval from dual;

