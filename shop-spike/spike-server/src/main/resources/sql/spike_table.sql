DROP TABLE IF EXISTS `spike_product_info`;
CREATE TABLE `spike_product_info` (
  `id` bigint(11) NOT NULL COMMENT '主键',
  `product_code` varchar(100) NOT NULL COMMENT '商品编码',
  `product_name` varchar(200) DEFAULT NULL COMMENT '商品名称',
	`spike_price` decimal(8,2) DEFAULT NULL COMMENT '秒杀价',
  `del_line_price` decimal(8,2) DEFAULT NULL COMMENT '划线价',
  `status` tinyint(3) DEFAULT '0' COMMENT '状态(0.下架 1.上架 2.已售罄)',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `valid` tinyint(1) DEFAULT '1' COMMENT '是否有效',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='秒杀商品信息表';