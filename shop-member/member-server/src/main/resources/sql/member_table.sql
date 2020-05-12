
-- 会员信息表
DROP TABLE IF EXISTS `member_info`;
CREATE TABLE `member_info` (
  `id` bigint(11) NOT NULL COMMENT '主键',
  `nick_name` varchar(255) DEFAULT NULL COMMENT '昵称',
  `age` int(11) DEFAULT NULL COMMENT '年龄',
  `phone` varchar(13) DEFAULT NULL COMMENT '手机号',
  `password` varchar(255) DEFAULT NULL COMMENT '密码',
  `level` int(11) DEFAULT NULL COMMENT '等级',
  `integral` decimal(11,0) DEFAULT '0' COMMENT '积分',
  `address` varchar(255) DEFAULT NULL COMMENT '地址',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `valid` tinyint(1) DEFAULT '1' COMMENT '是否有效',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='会员信息表';