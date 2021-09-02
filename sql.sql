
use pet;
DROP TABLE IF EXISTS `geek_second_chat_copy1`;
CREATE TABLE `geek_second_chat_copy1` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
 `position` varchar(2048) NOT NULL DEFAULT '' COMMENT '内容',
 `type` varchar(32) NOT NULL DEFAULT '' COMMENT '内容',
 `special_type` varchar(32) NOT NULL DEFAULT '' COMMENT '内容',
 `uid_tail` varchar(256) NOT NULL DEFAULT '' COMMENT '标题',
  `position_name` varchar(2048) NOT NULL DEFAULT '' COMMENT '内容',
  `content` varchar(256) NOT NULL DEFAULT '' COMMENT '主图',
 PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='文章信息';


DROP TABLE IF EXISTS `geek_second_chat`;
CREATE TABLE `geek_second_chat` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
 `position` varchar(2048) NOT NULL DEFAULT '' COMMENT '内容',
  `type` varchar(32) NOT NULL DEFAULT '' COMMENT '内容',
 `special_type` varchar(32) NOT NULL DEFAULT '' COMMENT '内容',
 `uid_tail` varchar(256) NOT NULL DEFAULT '' COMMENT '标题',
  `position_name` varchar(2048) NOT NULL DEFAULT '' COMMENT '内容',
  `content` varchar(256) NOT NULL DEFAULT '' COMMENT '主图',
 PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='文章信息';


DROP TABLE IF EXISTS `pet_info3`;
CREATE TABLE `pet_info3` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
 `position` varchar(2048) NOT NULL DEFAULT '' COMMENT '内容',
  `type` varchar(32) NOT NULL DEFAULT '' COMMENT '内容',
 `special_type` varchar(32) NOT NULL DEFAULT '' COMMENT '内容',
 `uid_tail` varchar(256) NOT NULL DEFAULT '' COMMENT '标题',
  `position_name` varchar(2048) NOT NULL DEFAULT '' COMMENT '内容',
  `content` varchar(256) NOT NULL DEFAULT '' COMMENT '主图',
 PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='文章信息';


SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for article_info
-- ----------------------------
DROP TABLE IF EXISTS `article_info`;
CREATE TABLE `article_info` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `title` varchar(256) NOT NULL DEFAULT '' COMMENT '标题',
  `body` text NOT NULL DEFAULT '' COMMENT '内容',
  `image` varchar(256) NOT NULL DEFAULT '' COMMENT '主图',
  `view_num` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '观看数',
  `praise_num` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '点赞数',
  `type` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT '类型',
  `add_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '添加时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `pet_id` int(10) unsigned NOT NULL COMMENT '宠物id',
  `author` varchar(32) NOT NULL DEFAULT '' COMMENT '作者',
  `source` varchar(32) NOT NULL DEFAULT '' COMMENT '来源',
  PRIMARY KEY (`id`),
  KEY `idx_name` (`title`(191)),
  KEY `idx_pet_id` (`pet_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='文章信息';

-- ----------------------------
-- Table structure for banner_info
-- ----------------------------
DROP TABLE IF EXISTS `banner_info`;
CREATE TABLE `banner_info` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `title` varchar(256) NOT NULL DEFAULT '' COMMENT '标题',
  `image` varchar(256) NOT NULL DEFAULT '' COMMENT '主图',
  `type` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT '类型 0 无 1文章 2品种 3动态',
  `level` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '优先级',
  `add_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '添加时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `status` int(11) NOT NULL COMMENT '0 下线 1上线',
  `object_id` int(11) unsigned NOT NULL COMMENT '关联的Id',
    `url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='轮播信息';

-- ----------------------------
-- Table structure for comment_info
-- ----------------------------
DROP TABLE IF EXISTS `comment_info`;
CREATE TABLE `comment_info` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `dynamic_id` int(11) unsigned NOT NULL COMMENT '动态Id',
  `user_id` int(11) unsigned NOT NULL COMMENT '用户Id',
  `user_name` varchar(64) NOT NULL DEFAULT '' COMMENT '用户名称',
  `head_img` varchar(256) NOT NULL DEFAULT '' COMMENT '头像',
  `reply_name` varchar(64) NOT NULL DEFAULT '' COMMENT '回复',
  `content` varchar(1024) NOT NULL DEFAULT '' COMMENT '内容',
  `status` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT '0 未审核 1审核通过 2驳回',
  `praise_num` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '点赞数',
  `add_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '添加时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_dynamic_id` (`dynamic_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8mb4 COMMENT='评论信息';

-- ----------------------------
-- Table structure for dynamic_info
-- ----------------------------
DROP TABLE IF EXISTS `dynamic_info`;
CREATE TABLE `dynamic_info` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` int(11) unsigned NOT NULL COMMENT '用户Id',
  `user_name` varchar(64) NOT NULL DEFAULT '' COMMENT '用户名称',
  `head_img` varchar(256) NOT NULL DEFAULT '' COMMENT '头像',
  `title` varchar(128) NOT NULL DEFAULT '' COMMENT '标题',
  `content` varchar(1024) NOT NULL DEFAULT '' COMMENT '内容',
  `images` varchar(256) NOT NULL DEFAULT '' COMMENT '配图',
  `status` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT '0 未审核 1审核通过 2驳回',
  `praise_num` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '点赞数',
  `add_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '添加时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='动态信息';

-- ----------------------------
-- Table structure for feedback_info
-- ----------------------------
DROP TABLE IF EXISTS `feedback_info`;
CREATE TABLE `feedback_info` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `content` varchar(1024) NOT NULL DEFAULT '' COMMENT '内容',
  `user_id` int(11) unsigned NOT NULL COMMENT '用户Id',
  `add_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '添加时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='反馈信息';

-- ----------------------------
-- Table structure for ip_list
-- ----------------------------
DROP TABLE IF EXISTS `ip_list`;
CREATE TABLE `ip_list` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `ip` varchar(64) NOT NULL DEFAULT '' COMMENT 'ip',
  `reason` varchar(64) NOT NULL DEFAULT '' COMMENT '原因',
  `type` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT '0 黑名单 1白名单',
  `add_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '0黑名单 1白名单',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='ip黑白名单';

-- ----------------------------
-- Table structure for message_info
-- ----------------------------
DROP TABLE IF EXISTS `message_info`;
CREATE TABLE `message_info` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '用户id',
  `title` varchar(256) NOT NULL DEFAULT '' COMMENT '标题',
  `body` varchar(1024) NOT NULL DEFAULT '' COMMENT '内容j',
  `type` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT '类型 1 系统 2 动态 3 相册',
  `status` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT '状态 0:未读 1:已读',
  `deleted` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT '状态 0:未删除 1:已删除',
  `add_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '添加时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `object_id` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '关联Id',
  PRIMARY KEY (`id`),
  KEY `idx_userid_type` (`user_id`,`type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='消息通知';

-- ----------------------------
-- Table structure for pet_character
-- ----------------------------
DROP TABLE IF EXISTS `pet_character`;
CREATE TABLE `pet_character` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(64) NOT NULL DEFAULT '' COMMENT '名称',
  `type` tinyint(3) NOT NULL DEFAULT '0' COMMENT '状态 0-猫 1狗',
  `add_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='宠物性格';

-- ----------------------------
-- Table structure for pet_character_mapping
-- ----------------------------
DROP TABLE IF EXISTS `pet_character_mapping`;
CREATE TABLE `pet_character_mapping` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `character_id` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '性格dd',
  `pet_id` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '宠物id',
  `add_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_character_id` (`character_id`),
  KEY `idx_pet_id` (`pet_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='宠物性格关系表';

-- ----------------------------
-- Table structure for pet_info
-- ----------------------------
DROP TABLE IF EXISTS `pet_info`;
CREATE TABLE `pet_info` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(64) NOT NULL DEFAULT '' COMMENT '名称',
  `nation` varchar(64) NOT NULL DEFAULT '' COMMENT '产地',
  `image` varchar(256) NOT NULL DEFAULT '' COMMENT '主图',
  `life` varchar(32) NOT NULL DEFAULT '' COMMENT '寿命',
  `feature` varchar(256) NOT NULL DEFAULT '' COMMENT '特点',
  `introduce` varchar(2048) NOT NULL DEFAULT '' COMMENT '介绍',
  `care_knowledge` varchar(2048) NOT NULL DEFAULT '' COMMENT '介绍',
  `feed_points` varchar(2048) NOT NULL DEFAULT '' COMMENT '介绍',
  `character_feature` varchar(2048) NOT NULL DEFAULT '' COMMENT '介绍',
  `level` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '优先级',
  `type` tinyint(3) NOT NULL DEFAULT '0' COMMENT '状态 0-猫 1狗',
  `trait` varchar(128) NOT NULL DEFAULT '' COMMENT '特点（,分割）',
  `advantage` varchar(128) NOT NULL DEFAULT '' COMMENT '优点(,分割)',
  `defect` varchar(128) NOT NULL DEFAULT '' COMMENT '缺点(,分割)',
  `price` varchar(32) NOT NULL DEFAULT '' COMMENT '均价(100-2200元)',
  `score` int(11) NOT NULL DEFAULT '0' COMMENT '评分',
  `deleted` int(11) unsigned NOT NULL  DEFAULT '0' COMMENT '0未删除 1已删除',
  `add_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='宠物表';

-- ----------------------------
-- Table structure for pet_picture
-- ----------------------------
DROP TABLE IF EXISTS `pet_picture`;
CREATE TABLE `pet_picture` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `pet_id` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '宠物id',
  `title` varchar(64) NOT NULL DEFAULT '' COMMENT '标题',
  `image` varchar(256) NOT NULL DEFAULT '' COMMENT '图片',
  `level` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '优先级',
  `add_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_pet_id` (`pet_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='宠物图片表';

-- ----------------------------
-- Table structure for praise_info
-- ----------------------------
DROP TABLE IF EXISTS `praise_info`;
CREATE TABLE `praise_info` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `object_id` int(11) unsigned NOT NULL COMMENT '动态Id',
  `user_id` int(11) unsigned NOT NULL COMMENT '用户Id',
  `add_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '添加时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `type` tinyint(3) NOT NULL COMMENT '0动态 1文章',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_oid_uid_type` (`object_id`,`user_id`,`type`) USING BTREE
) ENGINE=InnoDB  DEFAULT CHARSET=utf8mb4 COMMENT='点赞信息';

-- ----------------------------
-- Table structure for user_info
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `account` varchar(64) NOT NULL DEFAULT '' COMMENT '登录账号',
  `name` varchar(64) NOT NULL DEFAULT '',
  `head_img` varchar(256) NOT NULL DEFAULT '',
  `sex` tinyint(3) NOT NULL COMMENT '0 女 1 男',
  `password` varchar(32) NOT NULL DEFAULT '',
  `status` tinyint(11) unsigned NOT NULL DEFAULT '0' COMMENT '0 待审核 1 有效 2冻结',
  `identity` tinyint(3) NOT NULL COMMENT '0 普通用户 1 管理员',
  `add_time` datetime NOT NULL,
  `update_time` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP,
  `wechat` varchar(32) NOT NULL DEFAULT '' COMMENT '微信',
  `introduce` varchar(1024) NOT NULL DEFAULT '' COMMENT '介绍',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_account` (`account`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户信息表';

SET FOREIGN_KEY_CHECKS = 1;
