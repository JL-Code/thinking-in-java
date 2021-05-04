CREATE DATABASE `springboot-db`;

CREATE TABLE `springboot-db`.`user`
(
    `id`       VARCHAR(36) NOT NULL,
    `username` VARCHAR(45) NULL,
    PRIMARY KEY (`id`)
);

-- 账户表
CREATE TABLE `springboot-db`.`account`
(
    `id`           INT            NOT NULL,
    `account_name` VARCHAR(45)    NULL,
    `balance`      DECIMAL(18, 6) NULL,
    PRIMARY KEY (`id`)
);
-- 测试数据
INSERT INTO `springboot-db`.`account` (`id`, `account_name`, `balance`) VALUES ('1', 'zhangsan', '10000');
INSERT INTO `springboot-db`.`account` (`id`, `account_name`, `balance`) VALUES ('2', 'lisi', '0');