SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS `blog`;
CREATE TABLE `blog`  (
  `blog_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `publish_time` DATETIME,
  `update_time` DATETIME,
  `content` text CHARACTER SET utf8 COLLATE utf8_general_mysql500_ci NOT NULL,
  PRIMARY KEY (`blog_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 22 CHARACTER SET = utf8 COLLATE = utf8_general_mysql500_ci ROW_FORMAT = Dynamic;

INSERT INTO `blog` VALUES (12, 41, '2018-07-01 21:48:57', '2018-07-01 21:48:57', 'test blog 12 by user1');
INSERT INTO `blog` VALUES (13, 1, '2018-07-01 22:27:20', '2018-07-01 22:27:20', 'test blog 13 by root');
INSERT INTO `blog` VALUES (14, 1, '2018-07-01 22:27:32', '2018-07-01 22:27:32', 'test blog 14 by root');
INSERT INTO `blog` VALUES (15, 1, '2018-07-01 22:27:42', '2018-07-01 22:27:42', 'test blog 15 by root');
INSERT INTO `blog` VALUES (16, 41, '2018-07-01 22:28:02', '2018-07-01 22:28:02', 'test blog 16 by user1');
INSERT INTO `blog` VALUES (17, 41, '2018-07-01 22:28:13', '2018-07-01 22:28:13', 'test blog 17 by user1');
INSERT INTO `blog` VALUES (18, 42, '2018-07-01 22:28:36', '2018-07-01 22:28:36', 'test blog 18 by user2');
INSERT INTO `blog` VALUES (19, 43, '2018-07-01 22:29:24', '2018-07-01 22:29:24', 'test blog 19 by user3');
INSERT INTO `blog` VALUES (20, 44, '2018-07-01 22:30:38', '2018-07-01 22:30:38', 'test blog 20 by user4');
INSERT INTO `blog` VALUES (21, 43, '2018-07-01 22:31:12', '2018-07-01 22:31:12', 'test blog 21 by user3');

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_mysql500_ci NOT NULL UNIQUE,
  `avatar_id` int(11) NOT NULL DEFAULT 0,
  `md5_password` char(32) CHARACTER SET utf8 COLLATE utf8_general_mysql500_ci NOT NULL,
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 46 CHARACTER SET = utf8 COLLATE = utf8_general_mysql500_ci ROW_FORMAT = Dynamic;

INSERT INTO `user` VALUES (1, 'root', 1, '098f6bcd4621d373cade4e832627b4f6');
INSERT INTO `user` VALUES (41, 'user1', 4, '098f6bcd4621d373cade4e832627b4f6');
INSERT INTO `user` VALUES (42, 'user2', 2, '098f6bcd4621d373cade4e832627b4f6');
INSERT INTO `user` VALUES (43, 'user4', 3, '098f6bcd4621d373cade4e832627b4f6');
INSERT INTO `user` VALUES (44, 'user3', 4, '098f6bcd4621d373cade4e832627b4f6');
INSERT INTO `user` VALUES (45, 'user6', 2, '098f6bcd4621d373cade4e832627b4f6');

SET FOREIGN_KEY_CHECKS = 1;