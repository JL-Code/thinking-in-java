CREATE DATABASE `springboot-db`;

CREATE TABLE `springboot-db`.`user`
(
    `id`       VARCHAR(36) NOT NULL,
    `username` VARCHAR(45) NULL,
    PRIMARY KEY (`id`)
);