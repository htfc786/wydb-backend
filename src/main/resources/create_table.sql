-- 数据库初始化

-- 创建库
create database if not exists testdb;

-- 切换库
use testdb;

-- 用户表
create table if not exists `user`
(
    `id`       bigint       not null auto_increment comment 'id' primary key,
    `username` varchar(256) not null comment '用户名',
    `nickname` varchar(256) comment '昵称',
    `password` varchar(512) not null comment '密码-可以使用SHA256'
) comment '用户表' default charset = utf8mb4
                collate = utf8mb4_unicode_ci;

-- 文言文集合(文集)表 - wy_collection
create table if not exists `wy_collection`
(
    `id`          bigint       not null auto_increment comment 'id' primary key,
    `name`        varchar(255) not null comment '集合名称',
    `description` text comment '集合描述（可选）',
    `created_at`  datetime     not null default current_timestamp comment '文集创建时间'
) comment '文言文集合(文集)表' default charset = utf8mb4
                       collate = utf8mb4_unicode_ci;

-- 文言文文章表 - wy_article
create table if not exists `wy_article`
(
    `id`            bigint       not null auto_increment comment 'id' primary key,
    `collection_id` bigint       not null comment '所属集合ID，关联collection表',
    `name`          varchar(255) null comment '文言文文章名称',
    `writer`        varchar(255) null comment '文章作者',
    `dynasty`       varchar(255) null comment '文章被写成时的的朝代（年份）',
    `source`        varchar(255) null comment '文章选自的书籍',
    `main_idea`     text         null comment '文章主旨',
    `note`          text         null comment '备注信息',
    `created_at`    datetime     not null default CURRENT_TIMESTAMP comment '文章创建时间',
    foreign key (collection_id) references wy_collection (id), -- 关联
    index idx_collection_id (collection_id)
) comment '文言文文章表' default charset = utf8mb4
                   collate = utf8mb4_unicode_ci;

-- 文言文内容表 - wy_content
create table if not exists `wy_content`
(
    `id`            bigint   not null auto_increment comment 'id' primary key,
    `collection_id` bigint   not null comment '所属集合id，关联collection表',
    `article_id`    bigint   not null comment '所属文章id，关联article表',
    `paragraph`     int      not null default 1 comment '句子所在的段落（从1开始）',
    `order`         int      not null comment '句子排序，用于还原文章',
    `content`       text     not null comment '句子内容',
    `translations`  text     null comment '句子所对应的翻译',
    `created_at`    datetime not null default current_timestamp comment '创建时间',
    foreign key (collection_id) references wy_collection (id),
    foreign key (article_id) references wy_article (id),
    index idx_collection_id (collection_id),
    index idx_article_id (article_id)
) comment '文言文内容表' default charset = utf8mb4
                   collate = utf8mb4_unicode_ci;
