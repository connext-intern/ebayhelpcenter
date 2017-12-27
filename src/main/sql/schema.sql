-- 数据库
CREATE DATABASE IF NOT EXISTS ebayhelpcenter DEFAULT CHARSET utf8 COLLATE utf8_general_ci;

USE ebayhelpcenter;

-- 一级菜单表
create table ebay_first_menus(
  first_id int not null auto_increment comment '一级菜单主键',
  first_serial int not null comment '一级菜单标题排序号',
  first_title varchar(20) not null comment '一级菜单标题',
  primary key(first_id)
);

-- 二级菜单表
create table ebay_second_menus(
  second_id int not null auto_increment primary key comment '二级菜单主键',
  second_serial int not null comment '二级菜单标题排序号',
  second_title varchar(20) not null comment '二级菜单标题',
  content text not null comment '二级菜单的文本内容',
  html text not null comment '二级菜单的html内容',
  second_first_id int not null comment '一级菜单的id',
  constraint FK_fid foreign key(second_first_id) references ebay_first_menus(first_id)
);
