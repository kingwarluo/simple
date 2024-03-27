-- 订单表
CREATE TABLE `tb_order` (
  `id` bigint NOT NULL,
  `user_id` BIGINT(20) NOT NULL DEFAULT '0' COMMENT '购买人id',
  `sku_id` varchar(50) DEFAULT NULL COMMENT '购买的sku_id',
  `amount` int DEFAULT '0' COMMENT '购买数量',
  `money` bigint(20) DEFAULT '0' COMMENT '购买金额(分)',
  `pay_time` datetime DEFAULT NULL COMMENT '支付时间',
  `pay_status` int DEFAULT '0' COMMENT '支付状态 0-待支付 1-已支付 2-支付失败',
  `del_flag` tinyint(4) DEFAULT '0' COMMENT '删除标志 0-存在 1-删除',
  `create_by` bigint(20) DEFAULT '0' COMMENT '创建人',
	`create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` bigint(20) DEFAULT '0' COMMENT '修改人',
	`update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
	key `idx_user_id`(`user_id`)
) ENGINE=InnoDB COMMENT '订单表';