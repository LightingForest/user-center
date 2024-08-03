create table user
(
    id           bigint auto_increment comment '主键id'
        primary key,
    username     varchar(256)                       null comment '用户昵称',
    userAccount  varchar(256)                       null comment '账号',
    avatarUrl    varchar(1024)                      null comment '图像',
    gender       tinyint                            null comment '性别',
    userPassword varchar(512)                       not null comment '密码',
    email        varchar(512)                       null comment '邮箱',
    userStatus   int      default 0                 null comment ' 用户状态 0-正常',
    phone        varchar(128)                       null comment '电话',
    createTime   datetime default CURRENT_TIMESTAMP null comment '创建时间',
    updateTime   datetime default CURRENT_TIMESTAMP null comment '更新时间',
    isDelete     tinyint  default 0                 not null comment '是否删除',
    userRole     int      default 0                 not null comment '用户角色 0-普通用户 1-管理员'
)
    comment '用户表';
alter table user add column tags varchar(1024) null comment '标签列表';

create table tag
(
    id         bigint auto_increment comment '主键id'
        primary key,
    tagName    varchar(256)                       null comment '标签名',
    userId     bigint                             null comment '用户id',
    parentId   bigint                             null comment '父标签id',
    isParent   tinyint                            null comment '是否为父标签，0-不是；1-是',
    createTime datetime default CURRENT_TIMESTAMP null comment '创建时间',
    updateTime datetime default CURRENT_TIMESTAMP null comment '更新时间',
    isDelete   tinyint  default 0                 not null comment '是否删除'
)
    comment '标签';