# 测试表
CREATE TABLE `t_rocketmq_user_info`
(
    `id`        int          DEFAULT NULL COMMENT '主键',
    `user_name` varchar(255) DEFAULT NULL COMMENT '名字',
    `nike_name` varchar(255) DEFAULT NULL COMMENT '昵称',
    `address`   varchar(255) DEFAULT NULL COMMENT '地址',
    `phone`     varchar(255) DEFAULT NULL COMMENT '手机',
    `city`      varchar(255) DEFAULT NULL COMMENT '城市',
    `sex`       int          DEFAULT NULL COMMENT '性别'
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;