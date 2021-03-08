CREATE TABLE IF NOT EXISTS `cloud_invitation_user_agent`
(
    `id`          varchar(36) COLLATE utf8mb4_general_ci NOT NULL,
    `user_id`     varchar(45) COLLATE utf8mb4_general_ci DEFAULT NULL,
    `agent_id`    varchar(45) COLLATE utf8mb4_general_ci DEFAULT NULL,
    `create_time` datetime                               DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;

-- cloud_user
-- '1', '蒋勇', '蒋勇', NULL, NULL, NULL, NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL, NULL
-- '2', '杨三', '杨三', NULL, NULL, NULL, NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL, NULL

select *
from cloud_user;

-- cloud_invitation_user_agent
-- '1', '1', '75488aad-1692-4f66-a2df-4ae5f5716ae8', '2021-02-28 21:25:53'
-- '2', '2', '75488aad-1692-4f66-a2df-4ae5f5716ae8', '2021-02-28 21:25:57'

select *
from cloud_invitation_user_agent;

-- Cause: java.sql.SQLException: Illegal mix of
-- collations (utf8mb4_general_ci,IMPLICIT) and (utf8mb4_unicode_ci,IMPLICIT) for operation '='
-- 查看字符集
SHOW VARIABLES WHERE variable_name
                         LIKE 'character\_set\_%' OR Variable_name LIKE 'collation%';

select t1.nick_name, t1.id, t1.name, t2.agent_id, t2.create_time
from cloud_user t1
         right join cloud_invitation_user_agent t2 on t1.id = t2.user_id
where t2.agent_id = '2';


-- SELECT COUNT(1) FROM cloud_user t1 
-- RIGHT JOIN cloud_invitation_user_agent t2 ON t1.id = t2.user_id WHERE t2.agent_id = ?

-- MySQL 分页：https://www.mysqltutorial.org/mysql-limit.aspx
-- SELECT 
--     select_list
-- FROM
--     table_name
-- LIMIT [offset,] row_count;
-- Code language: SQL (Structured Query Language) (sql)
-- In this syntax:

-- The offset specifies the offset of the first row to return. The offset of the first row is 0, not 1.
-- The row_count specifies the maximum number of rows to return.

select t1.nick_name, t1.id, t1.name, t2.agent_id, t2.create_time
from cloud_user t1
         right join cloud_invitation_user_agent t2
                    on t1.id = t2.user_id
where t2.agent_id = '013f00be-923d-4a73-8896-43ac0b87d618'
LIMIT 0,2

