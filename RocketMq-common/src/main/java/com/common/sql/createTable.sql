CREATE TABLE `t_rocketmq_user_info`
(
    `id`          int NOT NULL AUTO_INCREMENT COMMENT '主键',
    `user_name`   varchar(255) DEFAULT NULL COMMENT '名字',
    `nike_name`   varchar(255) DEFAULT NULL COMMENT '昵称',
    `address`     varchar(255) DEFAULT NULL COMMENT '地址',
    `phone`       varchar(255) DEFAULT NULL COMMENT '手机',
    `city`        varchar(255) DEFAULT NULL COMMENT '城市',
    `sex`         int          DEFAULT NULL COMMENT '性别',
    `create_time` datetime     DEFAULT NULL COMMENT '创建时间',
    `type`        int          DEFAULT NULL COMMENT '优惠卷类别[0:200,1:100,2:50,3:10]',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 13001
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;