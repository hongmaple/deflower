/*
 Navicat Premium Data Transfer

 Source Server         : MySQL80
 Source Server Type    : MySQL
 Source Server Version : 80020
 Source Host           : localhost:3306
 Source Schema         : deflower

 Target Server Type    : MySQL
 Target Server Version : 80020
 File Encoding         : 65001

 Date: 16/03/2021 17:48:36
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tb_address
-- ----------------------------
DROP TABLE IF EXISTS `tb_address`;
CREATE TABLE `tb_address`  (
  `id` bigint(0) UNSIGNED NOT NULL AUTO_INCREMENT,
  `uid` bigint(0) NULL DEFAULT NULL COMMENT '用户id',
  `consignee` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '收件人',
  `consignee_phone` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '收件人手机号',
  `province` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '省',
  `city` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '市',
  `district` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '区',
  `detail` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '详细地址',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '地址' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tb_background_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_background_user`;
CREATE TABLE `tb_background_user`  (
  `id` bigint(0) UNSIGNED NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户昵称',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `phone` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机号',
  `avatar_image` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT 'http://localhost:9001/profile/upload/2021/03/10/dbced90a-9594-4aa7-b228-05a482c26937.png' COMMENT '头像',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_background_user
-- ----------------------------
INSERT INTO `tb_background_user` VALUES (1, 'maple', '123456', '18976618156', 'http://localhost:9001/profile/upload/2021/03/16/bd003530-157e-49c5-bb44-4341653fce58.jpeg', '2021-03-10 15:51:21');
INSERT INTO `tb_background_user` VALUES (2, 'maple2', '123456', '17976618156', 'http://localhost:9001/profile/upload/2021/03/10/dbced90a-9594-4aa7-b228-05a482c26937.png', '2021-03-10 16:06:24');
INSERT INTO `tb_background_user` VALUES (3, 'maple3', '123456', '16976618156', 'http://localhost:9001/profile/upload/2021/03/10/dbced90a-9594-4aa7-b228-05a482c26937.png', '2021-03-15 14:35:11');
INSERT INTO `tb_background_user` VALUES (4, 'maple4', '123456', '15679876356', 'http://localhost:9001/profile/upload/2021/03/10/dbced90a-9594-4aa7-b228-05a482c26937.png', '2021-03-15 14:45:52');
INSERT INTO `tb_background_user` VALUES (5, 'maple5', '123456', '16679876356', 'http://localhost:9001/profile/upload/2021/03/10/dbced90a-9594-4aa7-b228-05a482c26937.png', '2021-03-15 14:46:08');
INSERT INTO `tb_background_user` VALUES (6, 'maple6', '123456', '15879876356', 'http://localhost:9001/profile/upload/2021/03/10/dbced90a-9594-4aa7-b228-05a482c26937.png', '2021-03-15 14:46:24');

-- ----------------------------
-- Table structure for tb_cart
-- ----------------------------
DROP TABLE IF EXISTS `tb_cart`;
CREATE TABLE `tb_cart`  (
  `id` bigint(0) UNSIGNED NOT NULL AUTO_INCREMENT,
  `user_id` bigint(0) NULL DEFAULT NULL COMMENT '用户id',
  `sku_id` bigint(0) NULL DEFAULT NULL COMMENT '商品id',
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品标题',
  `image` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图片',
  `price` double NULL DEFAULT NULL COMMENT '价格 单位元',
  `num` int(0) NULL DEFAULT NULL COMMENT '数量',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '购物车' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tb_category
-- ----------------------------
DROP TABLE IF EXISTS `tb_category`;
CREATE TABLE `tb_category`  (
  `id` bigint(0) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '分类名',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '分类' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_category
-- ----------------------------
INSERT INTO `tb_category` VALUES (1, '鲜花');
INSERT INTO `tb_category` VALUES (2, '永生花');
INSERT INTO `tb_category` VALUES (3, '礼篮');
INSERT INTO `tb_category` VALUES (4, '绿植');

-- ----------------------------
-- Table structure for tb_flower
-- ----------------------------
DROP TABLE IF EXISTS `tb_flower`;
CREATE TABLE `tb_flower`  (
  `id` bigint(0) UNSIGNED NOT NULL AUTO_INCREMENT,
  `cid` bigint(0) NULL DEFAULT NULL COMMENT '分类id',
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '标题',
  `images` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图片',
  `price` double NULL DEFAULT NULL COMMENT '价格 单位元',
  `flower_language` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '花语',
  `appropriate_crowd` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '适宜人群',
  `is_deleted` tinyint(0) NULL DEFAULT 0 COMMENT '是否删除',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '花' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_flower
-- ----------------------------
INSERT INTO `tb_flower` VALUES (1, 2, '一路上有你 [泰国进口] 真空玫瑰花，红玫瑰系列', 'http://localhost:9001/profile/upload/2021/03/16/3ac4761a-619b-44d1-9889-dac25275185c.jpg', 368, '情感美丽永恒', '情侣', 0, '2021-03-16 14:25:27', '2021-03-16 15:23:11');
INSERT INTO `tb_flower` VALUES (2, 2, '你是唯一 [11枝新品 一心一意的爱] 卡罗拉红玫瑰11枝', 'http://localhost:9001/profile/upload/2021/03/16/2e691a23-9678-4bd4-bdbd-23bfeff3f5b8.jpg', 168, '情感美丽永恒', '情侣', 0, '2021-03-16 15:21:00', '2021-03-16 15:24:53');
INSERT INTO `tb_flower` VALUES (3, 4, '蝴蝶兰4株', 'http://localhost:9001/profile/upload/2021/03/16/aefb92ae-6dd4-4510-baa3-a09144f794be.jpg', 388, '4种被称为“卡特莱女王”的蝴蝶兰越来越受到花迷的青睐', '通用', 0, '2021-03-16 15:29:58', '2021-03-16 15:29:58');
INSERT INTO `tb_flower` VALUES (4, 4, '吉祥如意', 'http://localhost:9001/profile/upload/2021/03/16/d4832824-a70e-4704-8261-09b748868102.jpg', 218, '幸福美好', '通用', 0, '2021-03-16 15:32:22', '2021-03-16 15:32:22');
INSERT INTO `tb_flower` VALUES (5, 3, '一往情深', 'http://localhost:9001/profile/upload/2021/03/16/5601f330-1006-4bfb-a748-6212b95705b8.jpg', 328, '你的轻柔像阵微风，让我从容不迫，想让你知道，我对你始终一往情深。', '情侣，爱人', 0, '2021-03-16 15:36:00', '2021-03-16 15:36:00');
INSERT INTO `tb_flower` VALUES (6, 1, '恋恋情深 [经典款式 简约设计] 11枝香槟玫瑰，白色多头百合2枝', 'http://localhost:9001/profile/upload/2021/03/16/4ac113d3-3db0-47bc-8d41-ea72bb86fd50.jpg', 265, ' 喜欢像是一阵风，而爱你是细水长流', '情侣', 0, '2021-03-16 15:52:20', '2021-03-16 15:52:20');

-- ----------------------------
-- Table structure for tb_flower_favorite
-- ----------------------------
DROP TABLE IF EXISTS `tb_flower_favorite`;
CREATE TABLE `tb_flower_favorite`  (
  `id` bigint(0) UNSIGNED NOT NULL AUTO_INCREMENT,
  `uid` bigint(0) NULL DEFAULT NULL COMMENT '用户id',
  `favorite_id` bigint(0) NULL DEFAULT NULL COMMENT '花id',
  `create_time` bigint(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '收藏' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tb_order
-- ----------------------------
DROP TABLE IF EXISTS `tb_order`;
CREATE TABLE `tb_order`  (
  `orderId` bigint(0) NOT NULL COMMENT '订单id',
  `total_pay` bigint(0) NULL DEFAULT NULL COMMENT '总金额 单位分',
  `actual_pay` bigint(0) NULL DEFAULT NULL COMMENT '实付金额 单位分',
  `create_time` bigint(0) NULL DEFAULT NULL COMMENT '创建时间',
  `user_id` bigint(0) NULL DEFAULT NULL COMMENT '用户id',
  `buyer_nick` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '买家昵称',
  `receiver` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '买家全称',
  `receiver_mobile` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '收货人手机',
  `receiver_state` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '省份',
  `receiver_city` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '城市',
  `receiver_district` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '区/县 ',
  `receiver_address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '收货地址，如：xx路xx号',
  `status` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`orderId`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tb_order_detail
-- ----------------------------
DROP TABLE IF EXISTS `tb_order_detail`;
CREATE TABLE `tb_order_detail`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '订单详情id ',
  `order_id` bigint(0) NOT NULL COMMENT '订单id',
  `sku_id` bigint(0) NOT NULL COMMENT 'sku商品id',
  `num` int(0) NOT NULL COMMENT '购买数量',
  `title` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '商品标题',
  `own_spec` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '商品动态属性键值集',
  `price` double NOT NULL COMMENT '价格,单位：元',
  `image` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '商品图片',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `key_order_id`(`order_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '订单详情表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tb_order_status
-- ----------------------------
DROP TABLE IF EXISTS `tb_order_status`;
CREATE TABLE `tb_order_status`  (
  `order_id` bigint(0) NOT NULL COMMENT '订单id',
  `status` int(0) NULL DEFAULT NULL COMMENT '状态：1、未付款 2、已付款,未发货 3、已发货,未确认 4、交易成功 5、交易关闭',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '订单创建时间',
  `payment_time` datetime(0) NULL DEFAULT NULL COMMENT '付款时间',
  `consign_time` datetime(0) NULL DEFAULT NULL COMMENT '发货时间',
  `end_time` datetime(0) NULL DEFAULT NULL COMMENT '交易完成时间',
  `close_time` datetime(0) NULL DEFAULT NULL COMMENT '交易关闭时间',
  PRIMARY KEY (`order_id`) USING BTREE,
  INDEX `status`(`status`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '订单状态表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tb_says_commentary
-- ----------------------------
DROP TABLE IF EXISTS `tb_says_commentary`;
CREATE TABLE `tb_says_commentary`  (
  `id` bigint(0) UNSIGNED NOT NULL AUTO_INCREMENT,
  `flower_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '花名',
  `image` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图片',
  `narrate` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '解说',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '解说' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_says_commentary
-- ----------------------------
INSERT INTO `tb_says_commentary` VALUES (1, '莲花', 'http://localhost:9001/profile/upload/2021/03/16/e30f55bf-56eb-4593-aef3-c33a2b8b72eb.jpg', '莲之出淤泥而不染 濯清涟而不妖');
INSERT INTO `tb_says_commentary` VALUES (2, '菊花', 'http://localhost:9001/profile/upload/2021/03/16/253ce4fa-b96e-471c-b726-1e5b17d42129.jpg', '菊花的花语有很多，不同的品种和颜色代表的含义也不一样。其中黄色的菊花代表淡淡的爱，暗红色的菊花代表娇媚，白色的菊花代表贞洁和诚实。由于陶渊明的称颂，菊花也有花中隐士的称号。重阳采菊的习俗，也赋予它长寿的意思。');
INSERT INTO `tb_says_commentary` VALUES (7, '牡丹花', 'http://localhost:9001/profile/upload/2021/03/16/1893da5b-cc51-4563-b297-f76993807088.jpg', '牡丹花被称为花中之王，它的花语中有着高贵雍容、幸福吉祥、不畏强权、淡淡的爱等等。在表达爱意时，牡丹花会告诉你，只有在生活的点点滴滴里用心付出，才叫做真爱。');
INSERT INTO `tb_says_commentary` VALUES (8, '樱花', 'http://localhost:9001/profile/upload/2021/03/16/03823358-4d06-4efb-9b0f-1cf9bedf7c80.jpg', '樱花是爱情与希望的象征，代表着高雅，质朴纯洁的爱情。 ');

-- ----------------------------
-- Table structure for tb_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user`  (
  `id` bigint(0) UNSIGNED NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户昵称',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `phone` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机号',
  `avatar_image` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT 'http://localhost:9001/profile/upload/2021/03/10/dbced90a-9594-4aa7-b228-05a482c26937.png' COMMENT '头像',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_user
-- ----------------------------
INSERT INTO `tb_user` VALUES (1, 'maple', '123456', '19976618156', 'http://localhost:9001/profile/upload/2021/03/10/dbced90a-9594-4aa7-b228-05a482c26937.png', '2021-03-10 15:51:21');
INSERT INTO `tb_user` VALUES (2, 'maple', '123456', '13976618156', 'http://localhost:9001/profile/upload/2021/03/10/dbced90a-9594-4aa7-b228-05a482c26937.png', '2021-03-10 16:06:24');

SET FOREIGN_KEY_CHECKS = 1;
