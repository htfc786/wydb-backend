-- 数据库初始化

-- 创建库
create database if not exists testdb;

-- 切换库
use testdb;

-- 用户表
create table if not exists `user` (
    `id` bigint not null auto_increment comment 'id' primary key,
    `username` varchar(256) not null comment '用户名',
    `nickname` varchar(256) comment '昵称',
    `password` varchar(512) not null comment '密码'
) comment '用户表' default charset = utf8mb4 collate = utf8mb4_unicode_ci;
