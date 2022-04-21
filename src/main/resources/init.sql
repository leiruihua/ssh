-- 建表
create table demo_user(
  USERNO        number(8),
  USERNAME      VARCHAR2(20) not null,
  USERSEX       VARCHAR2(1),
  USERAGE       number(3) not null,
  USERPHONE     VARCHAR2(11),
  USERQQ        VARCHAR2(20)
);
-- 建主键
alter table test.demo_user add constraint pk_userno primary key (USERNO);
-- 建序列
create sequence seq_demo_user increment by 1 start with 1 maxvalue 999999999;
-- 查询数据
select * from demo_user;