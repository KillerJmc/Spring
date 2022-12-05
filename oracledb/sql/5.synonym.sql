-- 同义词可以用来创建表，视图，序列的别名
create synonym Seq for seq_test;

select Seq.nextval from dual;

select Seq.currval from dual;
