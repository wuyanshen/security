/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : securitydb

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2018-05-10 02:51:04
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL,
  `nameZH` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('1', 'ROLE_USER', '普通用户');
INSERT INTO `role` VALUES ('2', 'ROLE_ADMIN', '管理员');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(10) DEFAULT NULL,
  `password` varchar(10) DEFAULT NULL,
  `age` varchar(3) DEFAULT NULL,
  `phone` varchar(13) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'aaa', 'aaa', '22', '13511483365');
INSERT INTO `user` VALUES ('2', 'bbb', 'bbb', '23', '13678883333');
INSERT INTO `user` VALUES ('3', 'ccc', 'ccc', '24', '13787238782');
INSERT INTO `user` VALUES ('4', 'wuyanshen', '123456', '22', '12345678910');
INSERT INTO `user` VALUES ('5', '小明1', '123456', '22', '18922222222');
INSERT INTO `user` VALUES ('6', '小明2', '123456', '22', '18922222222');
INSERT INTO `user` VALUES ('7', '小明3', '123456', '22', '18922222222');
INSERT INTO `user` VALUES ('8', 'ddd', '123456', '22', '18922222222');
INSERT INTO `user` VALUES ('9', 'vvvvvvv', '123456', '22', '18922222222');

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `roleid` bigint(20) DEFAULT NULL,
  `userid` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `user_pk` (`userid`),
  KEY `role_pk` (`roleid`),
  CONSTRAINT `role_pk` FOREIGN KEY (`roleid`) REFERENCES `role` (`id`),
  CONSTRAINT `user_pk` FOREIGN KEY (`userid`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES ('1', '1', '1');
INSERT INTO `user_role` VALUES ('2', '2', '2');
INSERT INTO `user_role` VALUES ('3', '1', '3');
