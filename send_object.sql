
SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for send_object
-- ----------------------------
DROP TABLE IF EXISTS `send_object`;
CREATE TABLE `send_object` (
  `id` bigint(20) NOT NULL,
  `user_id` varchar(32) NOT NULL COMMENT '微信ID',
  `template_id` varchar(43) NOT NULL COMMENT '模版ID',
  `app_id` varchar(18) NOT NULL COMMENT '测试号ID',
  `app_secret` varchar(32) NOT NULL COMMENT '测试号secret',
  `template_content` varchar(1000) NOT NULL COMMENT '模版内容',
  `href` varchar(100) DEFAULT NULL COMMENT '跳转地址',
  `amap_key` varchar(255) DEFAULT NULL COMMENT '高德API KEY',
  `poem_type` int(11) DEFAULT NULL COMMENT '诗歌类型',
  `create_time` date NOT NULL COMMENT '创建时间',
  `update_time` date NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `index_uId_tId_aId_aSecret` (`user_id`,`template_id`,`app_id`,`app_secret`) USING BTREE COMMENT '微信ID、模版ID、appID、appSecret索引'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

SET FOREIGN_KEY_CHECKS = 1;
