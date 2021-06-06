CREATE DATABASE IF NOT EXISTS `springboot-db`;

CREATE TABLE IF NOT EXISTS case_order
(
    id              varchar(36) not null
        primary key,
    order_sn        varchar(45) null,
    status          int         null,
    use_integration int         null,
    member_id       varchar(36) null
);

CREATE TABLE IF NOT EXISTS case_order_item
(
    id               varchar(36) not null
        primary key,
    order_id         varchar(36) null,
    product_id       varchar(36) null,
    product_quantity int         null
);

CREATE TABLE IF NOT EXISTS case_order_log
(
    id         varchar(36)  not null
        primary key,
    operator   varchar(45)  null,
    created_at datetime     null,
    content    varchar(500) null
);

CREATE TABLE IF NOT EXISTS case_stock
(
    id         varchar(36) not null
        primary key,
    product_id varchar(36) null,
    stock      int         null,
    lock_stock int         null
);



CREATE TABLE IF NOT EXISTS user
(
    `id`       VARCHAR(36) NOT NULL,
    `username` VARCHAR(45) NULL,
    PRIMARY KEY (`id`)
);

-- 账户表
CREATE TABLE IF NOT EXISTS account
(
    `id`           INT            NOT NULL,
    `account_name` VARCHAR(45)    NULL,
    `balance`      DECIMAL(18, 6) NULL,
    PRIMARY KEY (`id`)
);
-- 测试数据
INSERT INTO account (`id`, `account_name`, `balance`)
VALUES ('1', 'zhangsan', '10000');
INSERT INTO account (`id`, `account_name`, `balance`)
VALUES ('2', 'lisi', '0');