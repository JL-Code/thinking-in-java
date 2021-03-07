CREATE TABLE IF NOT EXISTS cloud_user
(
    id              char(36) NOT NULL COMMENT '主键ID',
    name            varchar(50)  DEFAULT NULL COMMENT '真实姓名',
    nick_name       varchar(50)  DEFAULT NULL COMMENT '昵称',
    company         varchar(50)  DEFAULT NULL COMMENT '公司',
    avatar          varchar(255) DEFAULT NULL COMMENT '头像',
    create_time     datetime     DEFAULT NULL COMMENT '创建时间',
    update_time     datetime     DEFAULT NULL COMMENT '更新时间',
    inviter_id      char(36)     DEFAULT NULL COMMENT '经纪人ID',
    weixin_nickname varchar(50)  DEFAULT NULL COMMENT '微信昵称',
    is_deleted      tinyint      DEFAULT '0' COMMENT '是否删除',
    is_disabled     tinyint      DEFAULT NULL COMMENT '是否禁用',
    account_id      char(8)      DEFAULT NULL COMMENT '账号ID',
    username        char(11)     DEFAULT NULL COMMENT '账号（手机号）',
    password        char(72)     DEFAULT NULL COMMENT '密码',
    wechat_open_id  char(28)     DEFAULT NULL COMMENT '微信网站应用OpenId',
    union_id        varchar(36)  DEFAULT NULL COMMENT '微信号唯一标识',
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS cloud_invitation_user_agent
(
    id          char(36) NOT NULL COMMENT '主键ID',
    user_id     char(36) NOT NULL COMMENT '用户ID',
    agent_id    char(36) NOT NULL COMMENT '经纪人ID',
    create_time DATETIME NOT NULL COMMENT '创建时间',
    PRIMARY KEY (id)
);

