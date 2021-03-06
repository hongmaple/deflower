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

 Date: 20/04/2021 10:46:15
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
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '地址' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_address
-- ----------------------------
INSERT INTO `tb_address` VALUES (1, 3, 'maple', '17986897562', '湖南省', '长沙市', '岳麓区', '科教新村709');
INSERT INTO `tb_address` VALUES (5, 2, 'sad', '15876899876', '河北省', '怀化市', '撒旦区', '阿萨的撒反对发598');
INSERT INTO `tb_address` VALUES (6, 1, '加拿大', '17895345324', '广东省', '广州市', '时代区', '撒大苏打809');

-- ----------------------------
-- Table structure for tb_background_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_background_user`;
CREATE TABLE `tb_background_user`  (
  `id` bigint(0) UNSIGNED NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户昵称',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `phone` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机号',
  `role` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色',
  `avatar_image` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '/profile/upload/2021/03/10/dbced90a-9594-4aa7-b228-05a482c26937.png' COMMENT '头像',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_background_user
-- ----------------------------
INSERT INTO `tb_background_user` VALUES (1, 'maple', '123456', '18976618156', 'admin', '/profile/upload/2021/03/18/985d6fb9-9841-43e4-8001-4167c4068591.png', '2021-03-10 15:51:21');
INSERT INTO `tb_background_user` VALUES (2, 'maple2', '123456', '17976618156', 'admin', '/profile/upload/2021/03/10/dbced90a-9594-4aa7-b228-05a482c26937.png', '2021-03-10 16:06:24');
INSERT INTO `tb_background_user` VALUES (3, 'maple3', '123456', '16976618156', 'admin', '/profile/upload/2021/03/10/dbced90a-9594-4aa7-b228-05a482c26937.png', '2021-03-15 14:35:11');
INSERT INTO `tb_background_user` VALUES (4, 'maple4', '123456', '15679876356', 'admin', '/profile/upload/2021/03/10/dbced90a-9594-4aa7-b228-05a482c26937.png', '2021-03-15 14:45:52');
INSERT INTO `tb_background_user` VALUES (5, 'maple5', '123456', '16679876356', 'admin', '/profile/upload/2021/03/10/dbced90a-9594-4aa7-b228-05a482c26937.png', '2021-03-15 14:46:08');
INSERT INTO `tb_background_user` VALUES (6, 'maple6', '123456', '15879876356', 'admin', '/profile/upload/2021/03/10/dbced90a-9594-4aa7-b228-05a482c26937.png', '2021-03-15 14:46:24');

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
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '购物车' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_cart
-- ----------------------------
INSERT INTO `tb_cart` VALUES (9, 3, 3, '蝴蝶兰4株', '/profile/upload/2021/03/16/aefb92ae-6dd4-4510-baa3-a09144f794be.jpg', 388, 1);

-- ----------------------------
-- Table structure for tb_category
-- ----------------------------
DROP TABLE IF EXISTS `tb_category`;
CREATE TABLE `tb_category`  (
  `id` bigint(0) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '分类名',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '分类' ROW_FORMAT = Dynamic;

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
  `images` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '花封面',
  `images_list` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图片列表',
  `price` double NULL DEFAULT NULL COMMENT '价格 单位元',
  `flower_language` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '花语',
  `appropriate_crowd` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '适宜人群',
  `is_deleted` tinyint(0) NULL DEFAULT 0 COMMENT '是否删除',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '花' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_flower
-- ----------------------------
INSERT INTO `tb_flower` VALUES (1, 2, '一路上有你 [泰国进口] 真空玫瑰花，红玫瑰系列', '/profile/upload/2021/03/16/3ac4761a-619b-44d1-9889-dac25275185c.jpg', '/profile/upload/2021/03/18/06cdf7be-b59f-4125-a159-e665cb2c66ba.jpg,/profile/upload/2021/03/18/0a3bb2c4-5d82-468f-a343-56834ffa060f.jpg,', 368, '情感美丽永恒', '情侣', 0, '2021-03-16 14:25:27', '2021-03-18 17:09:32');
INSERT INTO `tb_flower` VALUES (2, 2, '你是唯一 [11枝新品 一心一意的爱] 卡罗拉红玫瑰11枝', '/profile/upload/2021/03/16/2e691a23-9678-4bd4-bdbd-23bfeff3f5b8.jpg', '/profile/upload/2021/03/18/0e27ece2-49ec-407f-b7ca-3dc1b4ecfbf1.jpg,/profile/upload/2021/03/18/b4e22182-bb97-4379-b598-d1d9523363cd.jpg,/profile/upload/2021/03/18/5d377f93-df0a-4f6c-b0a7-b1edde1a5099.jpg,/profile/upload/2021/03/18/e8d7c402-4877-49a9-9c18-a012526b7b0e.jpg,', 168, '情感美丽永恒', '情侣', 0, '2021-03-16 15:21:00', '2021-03-18 17:12:10');
INSERT INTO `tb_flower` VALUES (3, 4, '蝴蝶兰4株', '/profile/upload/2021/03/16/aefb92ae-6dd4-4510-baa3-a09144f794be.jpg', '/profile/upload/2021/03/16/aefb92ae-6dd4-4510-baa3-a09144f794be.jpg', 388, '4种被称为“卡特莱女王”的蝴蝶兰越来越受到花迷的青睐', '通用', 0, '2021-03-16 15:29:58', '2021-03-16 15:29:58');
INSERT INTO `tb_flower` VALUES (4, 4, '吉祥如意', '/profile/upload/2021/03/16/d4832824-a70e-4704-8261-09b748868102.jpg', '/profile/upload/2021/03/16/d4832824-a70e-4704-8261-09b748868102.jpg', 218, '幸福美好', '通用', 0, '2021-03-16 15:32:22', '2021-03-16 15:32:22');
INSERT INTO `tb_flower` VALUES (5, 3, '一往情深', '/profile/upload/2021/03/16/5601f330-1006-4bfb-a748-6212b95705b8.jpg', '/profile/upload/2021/03/18/81fbf2b0-2bce-44f5-8daf-b9cef307aa28.jpg,/profile/upload/2021/03/18/82937213-4767-4746-b418-1ea8a90f7882.jpg,/profile/upload/2021/03/18/1fb65fb2-9f68-47e1-af09-0e6eba018a9e.jpg,/profile/upload/2021/03/18/0c742913-c9c9-4175-9a06-099216a32fda.jpg,', 328, '你的轻柔像阵微风，让我从容不迫，想让你知道，我对你始终一往情深。', '情侣，爱人', 0, '2021-03-16 15:36:00', '2021-03-18 17:10:54');
INSERT INTO `tb_flower` VALUES (6, 1, '恋恋情深 [经典款式 简约设计] 11枝香槟玫瑰，白色多头百合2枝', '/profile/upload/2021/03/16/4ac113d3-3db0-47bc-8d41-ea72bb86fd50.jpg', '/profile/upload/2021/03/18/70f8274e-4e12-4ebb-b263-c566116b9815.jpg,/profile/upload/2021/03/18/d8572165-e2d9-412d-9e2d-74823b78ed16.jpg,/profile/upload/2021/03/18/86728f42-0ac1-4c3f-a113-5ec62b183888.jpg,', 265, ' 喜欢像是一阵风，而爱你是细水长流', '情侣', 0, '2021-03-16 15:52:20', '2021-03-18 17:09:57');
INSERT INTO `tb_flower` VALUES (7, 1, '狗尾巴花', '/profile/upload/2021/03/18/d01aec61-8c8e-4a38-9a5b-be17ff784873.jpg', '/profile/upload/2021/03/18/bcb852dc-57c7-4ee4-abaf-4310d2f63e92.jpg,/profile/upload/2021/03/18/b6caa864-f12f-4a1d-8297-45699d71f91a.jpg,', 168, '偷偷的暗恋着你，就是对喜欢的人始终无法找到合适的机会表达自己的情感，就这样悄无声息的进行陪伴着那个人。这样坚忍的毅力很多人是无法做到的，所以也代表着一种默默奉献之情。', '通用', 0, '2021-03-18 10:12:37', '2021-03-18 16:42:05');
INSERT INTO `tb_flower` VALUES (8, 2, '留住好时光  [精选昆明A级花材] 粉绣球1枝，粉雪山玫瑰6枝', '/profile/upload/2021/03/18/5e45d3f3-4e3c-416c-96af-0c2f71e0da29.jpg', '/profile/upload/2021/03/18/58d61881-07bc-40ae-b9b1-8d205069e8fc.jpg,/profile/upload/2021/03/18/d40d357c-494a-4ae2-a870-d916c49ffaa8.jpg,/profile/upload/2021/03/18/c905aaef-c322-4217-8108-0b53a6136328.jpg,', 66.5, ' 让每点阳光，洒于你脸庞；令你的微笑，比花更盛放！', '长辈', 0, '2021-03-18 16:45:56', '2021-03-18 16:59:11');

-- ----------------------------
-- Table structure for tb_flower_favorite
-- ----------------------------
DROP TABLE IF EXISTS `tb_flower_favorite`;
CREATE TABLE `tb_flower_favorite`  (
  `id` bigint(0) UNSIGNED NOT NULL AUTO_INCREMENT,
  `uid` bigint(0) NULL DEFAULT NULL COMMENT '用户id',
  `favorite_id` bigint(0) NULL DEFAULT NULL COMMENT '花id',
  `type` int(0) NULL DEFAULT 0 COMMENT '0: 花，1：视频',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '收藏' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_flower_favorite
-- ----------------------------
INSERT INTO `tb_flower_favorite` VALUES (6, 1, 3, 0, '2021-04-16 13:22:06');
INSERT INTO `tb_flower_favorite` VALUES (7, 1, 5, 0, '2021-04-16 13:22:08');
INSERT INTO `tb_flower_favorite` VALUES (8, 1, 1, 0, '2021-04-16 13:22:10');
INSERT INTO `tb_flower_favorite` VALUES (12, 1, 8, 0, '2021-04-16 13:22:12');
INSERT INTO `tb_flower_favorite` VALUES (15, 1, 7, 0, '2021-04-16 13:22:13');
INSERT INTO `tb_flower_favorite` VALUES (16, 1, 6, 0, '2021-04-16 13:22:16');
INSERT INTO `tb_flower_favorite` VALUES (17, 1, 1, 1, '2021-04-16 13:22:18');

-- ----------------------------
-- Table structure for tb_order
-- ----------------------------
DROP TABLE IF EXISTS `tb_order`;
CREATE TABLE `tb_order`  (
  `order_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '订单id',
  `total_pay` bigint(0) NULL DEFAULT NULL COMMENT '总金额 单位分',
  `actual_pay` bigint(0) NULL DEFAULT NULL COMMENT '实付金额 单位分',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `user_id` bigint(0) NULL DEFAULT NULL COMMENT '用户id',
  `buyer_nick` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '买家昵称',
  `receiver` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '买家全称',
  `receiver_mobile` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '收货人手机',
  `receiver_state` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '省份',
  `receiver_city` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '城市',
  `receiver_district` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '区/县 ',
  `receiver_address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '收货地址，如：xx路xx号',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '留言',
  `status` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `is_deleted` tinyint(0) NULL DEFAULT 0,
  PRIMARY KEY (`order_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_order
-- ----------------------------
INSERT INTO `tb_order` VALUES ('1374292737267879936', 3844, 3844, '2021-03-23 17:31:36', 3, 'maple', 'maple', '17986897562', '湖南省', '长沙市', '岳麓区', '科教新村709', '', '2', 0);
INSERT INTO `tb_order` VALUES ('1374894696794546176', 378, 378, '2021-03-25 09:23:35', 3, 'maple', 'maple', '17986897562', '湖南省', '长沙市', '岳麓区', '科教新村709', '会不会v计划', '4', 0);
INSERT INTO `tb_order` VALUES ('1375371868126601216', 178, 178, '2021-03-26 16:59:41', 2, 'maple', 'sad', '15876899876', '河北省', '怀化市', '撒旦区', '阿萨的撒反对发598', '', '1', 0);
INSERT INTO `tb_order` VALUES ('1384322968577916928', 338, 338, '2021-04-20 09:48:10', 1, 'maple', '加拿大', '17895345324', '广东省', '广州市', '时代区', '撒大苏打809', '', '4', 0);

-- ----------------------------
-- Table structure for tb_order_detail
-- ----------------------------
DROP TABLE IF EXISTS `tb_order_detail`;
CREATE TABLE `tb_order_detail`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '订单详情id ',
  `order_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '订单id',
  `sku_id` bigint(0) NOT NULL COMMENT 'sku商品id',
  `num` int(0) NOT NULL COMMENT '购买数量',
  `title` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '商品标题',
  `own_spec` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '商品动态属性键值集',
  `price` double NOT NULL COMMENT '价格,单位：元',
  `image` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '商品图片',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `key_order_id`(`order_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 152 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '订单详情表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_order_detail
-- ----------------------------
INSERT INTO `tb_order_detail` VALUES (146, '1374292737267879936', 5, 10, '一往情深', '', 328, '/profile/upload/2021/03/16/5601f330-1006-4bfb-a748-6212b95705b8.jpg');
INSERT INTO `tb_order_detail` VALUES (147, '1374292737267879936', 2, 2, '你是唯一 [11枝新品 一心一意的爱] 卡罗拉红玫瑰11枝', '', 168, '/profile/upload/2021/03/16/2e691a23-9678-4bd4-bdbd-23bfeff3f5b8.jpg');
INSERT INTO `tb_order_detail` VALUES (148, '1374292737267879936', 4, 1, '吉祥如意', '', 218, '/profile/upload/2021/03/16/d4832824-a70e-4704-8261-09b748868102.jpg');
INSERT INTO `tb_order_detail` VALUES (149, '1374299887356727296', 3, 1, '蝴蝶兰4株', '', 388, '/profile/upload/2021/03/16/aefb92ae-6dd4-4510-baa3-a09144f794be.jpg');
INSERT INTO `tb_order_detail` VALUES (150, '1374894696794546176', 1, 1, '一路上有你 [泰国进口] 真空玫瑰花，红玫瑰系列', '', 368, '/profile/upload/2021/03/16/3ac4761a-619b-44d1-9889-dac25275185c.jpg');
INSERT INTO `tb_order_detail` VALUES (151, '1375371868126601216', 2, 1, '你是唯一 [11枝新品 一心一意的爱] 卡罗拉红玫瑰11枝', '', 168, '/profile/upload/2021/03/16/2e691a23-9678-4bd4-bdbd-23bfeff3f5b8.jpg');
INSERT INTO `tb_order_detail` VALUES (152, '1384322968577916928', 5, 1, '一往情深', '', 328, '/profile/upload/2021/03/16/5601f330-1006-4bfb-a748-6212b95705b8.jpg');

-- ----------------------------
-- Table structure for tb_order_status
-- ----------------------------
DROP TABLE IF EXISTS `tb_order_status`;
CREATE TABLE `tb_order_status`  (
  `order_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '订单id',
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
-- Records of tb_order_status
-- ----------------------------
INSERT INTO `tb_order_status` VALUES ('1374292737267879936', 3, '2021-03-23 17:31:36', '2021-03-24 14:27:16', '2021-03-25 14:55:22', NULL, NULL);
INSERT INTO `tb_order_status` VALUES ('1374299887356727296', 5, '2021-03-23 18:00:01', '2021-03-24 15:07:44', NULL, NULL, NULL);
INSERT INTO `tb_order_status` VALUES ('1374894696794546176', 4, '2021-03-25 09:23:35', '2021-03-25 10:01:15', '2021-03-25 14:56:09', '2021-03-26 16:21:46', '2021-03-25 09:54:04');
INSERT INTO `tb_order_status` VALUES ('1375371868126601216', 1, '2021-03-26 16:59:41', NULL, NULL, NULL, NULL);
INSERT INTO `tb_order_status` VALUES ('1384322968577916928', 4, '2021-04-20 09:48:10', '2021-04-20 09:48:26', '2021-04-20 09:53:05', '2021-04-20 09:53:15', NULL);

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
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '解说' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_says_commentary
-- ----------------------------
INSERT INTO `tb_says_commentary` VALUES (1, '莲花', '/profile/upload/2021/03/16/e30f55bf-56eb-4593-aef3-c33a2b8b72eb.jpg', '莲之出淤泥而不染 濯清涟而不妖');
INSERT INTO `tb_says_commentary` VALUES (2, '菊花', '/profile/upload/2021/03/16/253ce4fa-b96e-471c-b726-1e5b17d42129.jpg', '菊花的花语有很多，不同的品种和颜色代表的含义也不一样。其中黄色的菊花代表淡淡的爱，暗红色的菊花代表娇媚，白色的菊花代表贞洁和诚实。由于陶渊明的称颂，菊花也有花中隐士的称号。重阳采菊的习俗，也赋予它长寿的意思。');
INSERT INTO `tb_says_commentary` VALUES (7, '牡丹花', '/profile/upload/2021/03/16/1893da5b-cc51-4563-b297-f76993807088.jpg', '牡丹花被称为花中之王，它的花语中有着高贵雍容、幸福吉祥、不畏强权、淡淡的爱等等。在表达爱意时，牡丹花会告诉你，只有在生活的点点滴滴里用心付出，才叫做真爱。');
INSERT INTO `tb_says_commentary` VALUES (8, '樱花', '/profile/upload/2021/03/16/03823358-4d06-4efb-9b0f-1cf9bedf7c80.jpg', '樱花是爱情与希望的象征，代表着高雅，质朴纯洁的爱情。 ');
INSERT INTO `tb_says_commentary` VALUES (9, '狗尾巴花', '/profile/upload/2021/03/18/e8c38375-3b9c-49a0-9530-d4486d546b0f.jpg', '偷偷的暗恋着你，就是对喜欢的人始终无法找到合适的机会表达自己的情感，就这样悄无声息的进行陪伴着那个人。这样坚忍的毅力很多人是无法做到的，所以也代表着一种默默奉献之情。\n');

-- ----------------------------
-- Table structure for tb_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user`  (
  `id` bigint(0) UNSIGNED NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户昵称',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `phone` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机号',
  `role` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色',
  `avatar_image` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '/profile/upload/2021/03/10/dbced90a-9594-4aa7-b228-05a482c26937.png' COMMENT '头像',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `age` int(0) NULL DEFAULT NULL COMMENT '年龄',
  `sex` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '性别',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_user
-- ----------------------------
INSERT INTO `tb_user` VALUES (1, 'maple', '123456', '19976618156', 'student', '/profile/upload/2021/03/10/dbced90a-9594-4aa7-b228-05a482c26937.png', '2021-03-10 15:51:21', 18, '女');
INSERT INTO `tb_user` VALUES (2, 'maple', '123456', '13976618156', 'student', '/profile/upload/2021/03/10/dbced90a-9594-4aa7-b228-05a482c26937.png', '2021-03-10 16:06:24', 19, '女');
INSERT INTO `tb_user` VALUES (3, 'maple16', '123456', '16976618156', 'student', '/profile/upload/2021/04/01/f0865468-d19a-4eae-b6ec-18985d14be25.png', '2021-03-18 18:34:11', 20, '男');
INSERT INTO `tb_user` VALUES (4, 'maple2', '123456', '16975718156', 'student', '/profile/upload/2021/03/10/dbced90a-9594-4aa7-b228-05a482c26937.png', '2021-04-19 16:02:08', 30, '女');
INSERT INTO `tb_user` VALUES (5, 'sadgf', '1221434', '15678877899', 'student', '/profile/upload/2021/03/10/dbced90a-9594-4aa7-b228-05a482c26937.png', '2021-04-19 16:02:49', 50, '男');
INSERT INTO `tb_user` VALUES (6, 'dsfdsg', '123213', '15856456454', 'student', '/profile/upload/2021/03/10/dbced90a-9594-4aa7-b228-05a482c26937.png', '2021-04-19 16:03:17', 60, '男');
INSERT INTO `tb_user` VALUES (7, 'asdsad', '112423', '15967867657', 'student', '/profile/upload/2021/03/10/dbced90a-9594-4aa7-b228-05a482c26937.png', '2021-04-19 16:04:03', 35, '女');

-- ----------------------------
-- Table structure for tb_video_teaching
-- ----------------------------
DROP TABLE IF EXISTS `tb_video_teaching`;
CREATE TABLE `tb_video_teaching`  (
  `id` bigint(0) UNSIGNED NOT NULL AUTO_INCREMENT,
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '标题',
  `pic` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '封面',
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '视频地址',
  `price` double(100, 2) NULL DEFAULT NULL COMMENT '价格',
  `is_charge` tinyint(0) NULL DEFAULT NULL COMMENT '是否收费',
  `status` int(0) NULL DEFAULT 0 COMMENT '状态',
  `creator_id` bigint(0) NULL DEFAULT NULL COMMENT '创建者',
  `creator_type` int(0) NULL DEFAULT 0 COMMENT '创建者类型(0:后台，1：C端）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_video_teaching
-- ----------------------------
INSERT INTO `tb_video_teaching` VALUES (1, '玫瑰花嫁接', '/profile/upload/2021/03/25/30ce9638-2a02-4c0e-9d31-674f26ae1a44.png', '/profile/upload/2021/03/25/1b8ff8ed-b25f-4f3e-a0cc-a0af7b39e0ec.mp4', 10.00, 1, 1, 1, 0);
INSERT INTO `tb_video_teaching` VALUES (2, '银领人才网', '/profile/upload/2021/04/16/bd1256cd-b5e3-4389-b7f1-8a99eaaa4d4e.png', '/profile/upload/2021/04/16/3a73adff-59b9-482d-8c3a-9a4292f91316.mp4', 100.00, 1, 0, 1, 1);
INSERT INTO `tb_video_teaching` VALUES (3, '即使对方', '/profile/upload/2021/04/16/4936c36f-3e21-4a2a-86e9-a26c9bea7e34.png', '/profile/upload/2021/04/16/56856371-6982-403e-970c-bc44c244e75d.mp4', 100.00, 1, 0, 1, 1);
INSERT INTO `tb_video_teaching` VALUES (4, 'rer', '/profile/upload/2021/04/16/eb4eb97f-3978-4217-80dd-7c664cba6459.png', '/profile/upload/2021/04/16/09de10cb-bb7d-4aa2-98f5-28cbb61a3b54.mp4', 0.00, 0, 0, 1, 1);

SET FOREIGN_KEY_CHECKS = 1;
