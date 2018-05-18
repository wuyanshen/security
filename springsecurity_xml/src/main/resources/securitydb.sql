/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : securitydb

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2018-05-17 02:20:14
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for permission
-- ----------------------------
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission` (
  `permissionid` int(10) NOT NULL COMMENT '权限ID',
  `permissionname` varchar(50) DEFAULT NULL COMMENT '权限名',
  `permissionflag` varchar(50) DEFAULT NULL COMMENT '权限标识',
  PRIMARY KEY (`permissionid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='权限表';

-- ----------------------------
-- Records of permission
-- ----------------------------
INSERT INTO `permission` VALUES ('1', '添加用户', '/user/add');
INSERT INTO `permission` VALUES ('2', '更新用户', '/user/update');
INSERT INTO `permission` VALUES ('3', '删除用户', '/user/delete');
INSERT INTO `permission` VALUES ('4', '查找用户', '/user/find');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `roleid` int(10) NOT NULL AUTO_INCREMENT COMMENT '角色ID',
  `rolename` varchar(50) DEFAULT NULL COMMENT '角色名',
  `roledesc` varchar(50) DEFAULT NULL COMMENT '角色说明',
  PRIMARY KEY (`roleid`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='角色表';

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('1', 'ROLE_USER', '普通用户');
INSERT INTO `role` VALUES ('2', 'ROLE_NOMAL', '一般用户');
INSERT INTO `role` VALUES ('3', 'ROLE_VIP', 'VIP用户');
INSERT INTO `role` VALUES ('4', 'ROLE_ADMIN', '系统管理员');

-- ----------------------------
-- Table structure for role_permission
-- ----------------------------
DROP TABLE IF EXISTS `role_permission`;
CREATE TABLE `role_permission` (
  `roleid` int(10) DEFAULT NULL COMMENT '角色ID',
  `permissionid` int(10) DEFAULT NULL COMMENT '权限ID',
  KEY `fk_permission` (`permissionid`),
  KEY `fk_role_2` (`roleid`),
  CONSTRAINT `fk_permission` FOREIGN KEY (`permissionid`) REFERENCES `permission` (`permissionid`),
  CONSTRAINT `fk_role_2` FOREIGN KEY (`roleid`) REFERENCES `role` (`roleid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色权限表';

-- ----------------------------
-- Records of role_permission
-- ----------------------------
INSERT INTO `role_permission` VALUES ('1', '1');
INSERT INTO `role_permission` VALUES ('2', '2');
INSERT INTO `role_permission` VALUES ('3', '3');
INSERT INTO `role_permission` VALUES ('4', '4');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `userid` int(10) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `username` varchar(50) DEFAULT NULL COMMENT '用户名',
  `realname` varchar(50) DEFAULT NULL COMMENT '真实姓名',
  `password` varchar(50) DEFAULT NULL COMMENT '密码',
  `createtdate` date DEFAULT NULL COMMENT '创建时间',
  `lastlogindate` date DEFAULT NULL COMMENT '最后登录时间',
  `isuse` int(5) DEFAULT NULL COMMENT '是否可用',
  `isexpired` int(5) DEFAULT NULL COMMENT '是否过期',
  `idlocked` int(5) DEFAULT NULL COMMENT '是否锁定',
  `certificateisexpired` int(5) DEFAULT NULL COMMENT '证书是否过期',
  PRIMARY KEY (`userid`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='用户表';

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'jack', '杰克', '123', '2018-05-17', '2018-05-17', '1', '1', '1', '1');
INSERT INTO `user` VALUES ('2', 'tom', '汤姆', '123', '2018-05-17', '2018-05-17', '1', '1', '1', '1');
INSERT INTO `user` VALUES ('3', 'jim', '吉姆', '123', '2018-05-17', '2018-05-17', '1', '1', '1', '1');

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `roleid` int(10) DEFAULT NULL COMMENT '角色ID',
  `userid` int(10) DEFAULT NULL COMMENT '用户ID',
  KEY `fk_role_1` (`roleid`),
  KEY `fk_user` (`userid`),
  CONSTRAINT `fk_role_1` FOREIGN KEY (`roleid`) REFERENCES `role` (`roleid`),
  CONSTRAINT `fk_user` FOREIGN KEY (`userid`) REFERENCES `user` (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户角色表';

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES ('1', '1');
INSERT INTO `user_role` VALUES ('2', '2');
INSERT INTO `user_role` VALUES ('3', '3');
