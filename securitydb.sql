/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : securitydb

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2018-05-29 22:40:36
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission` (
  `permissionid` int(10) NOT NULL COMMENT '权限ID',
  `permissionname` varchar(50) DEFAULT NULL COMMENT '权限名',
  `permissionflag` varchar(50) DEFAULT NULL COMMENT '权限标识',
  PRIMARY KEY (`permissionid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='权限表';

-- ----------------------------
-- Records of sys_permission
-- ----------------------------
INSERT INTO `sys_permission` VALUES ('1', '添加用户', '/user/add');
INSERT INTO `sys_permission` VALUES ('2', '更新用户', '/user/update');
INSERT INTO `sys_permission` VALUES ('3', '删除用户', '/user/delete');
INSERT INTO `sys_permission` VALUES ('4', '查找用户', '/user/find');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `roleid` int(10) NOT NULL AUTO_INCREMENT COMMENT '角色ID',
  `rolename` varchar(50) DEFAULT NULL COMMENT '角色名',
  `roledesc` varchar(50) DEFAULT NULL COMMENT '角色说明',
  PRIMARY KEY (`roleid`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='角色表';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', 'ROLE_USER', '普通用户');
INSERT INTO `sys_role` VALUES ('2', 'ROLE_NOMAL', '一般用户');
INSERT INTO `sys_role` VALUES ('3', 'ROLE_VIP', 'VIP用户');
INSERT INTO `sys_role` VALUES ('4', 'ROLE_ADMIN', '系统管理员');

-- ----------------------------
-- Table structure for sys_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_permission`;
CREATE TABLE `sys_role_permission` (
  `roleid` int(10) DEFAULT NULL COMMENT '角色ID',
  `permissionid` int(10) DEFAULT NULL COMMENT '权限ID',
  KEY `fk_permission` (`permissionid`),
  KEY `fk_role_2` (`roleid`),
  CONSTRAINT `fk_permission` FOREIGN KEY (`permissionid`) REFERENCES `sys_permission` (`permissionid`),
  CONSTRAINT `fk_role_2` FOREIGN KEY (`roleid`) REFERENCES `sys_role` (`roleid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色权限表';

-- ----------------------------
-- Records of sys_role_permission
-- ----------------------------
INSERT INTO `sys_role_permission` VALUES ('1', '1');
INSERT INTO `sys_role_permission` VALUES ('2', '2');
INSERT INTO `sys_role_permission` VALUES ('3', '3');
INSERT INTO `sys_role_permission` VALUES ('4', '4');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `userid` int(10) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `username` varchar(50) DEFAULT NULL COMMENT '用户名',
  `realname` varchar(50) DEFAULT NULL COMMENT '真实姓名',
  `password` varchar(100) DEFAULT NULL COMMENT '密码',
  `createtdate` date DEFAULT NULL COMMENT '创建时间',
  `lastlogindate` date DEFAULT NULL COMMENT '最后登录时间',
  `isuse` int(5) DEFAULT NULL COMMENT '是否可用',
  `isexpired` int(5) DEFAULT NULL COMMENT '是否过期',
  `idlocked` int(5) DEFAULT NULL COMMENT '是否锁定',
  `certificateisexpired` int(5) DEFAULT NULL COMMENT '证书是否过期',
  PRIMARY KEY (`userid`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='用户表';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', 'jack', '杰克', '$2a$10$FwoNmRDHMerlbaCiQ2rDZeFaH3n.YQ0UDoEfVWcBMD8tpWlxbywo.', '2018-05-17', '2018-05-17', '1', '1', '1', '1');
INSERT INTO `sys_user` VALUES ('2', 'tom', '汤姆', '$2a$10$FwoNmRDHMerlbaCiQ2rDZeFaH3n.YQ0UDoEfVWcBMD8tpWlxbywo.', '2018-05-17', '2018-05-17', '1', '1', '1', '1');
INSERT INTO `sys_user` VALUES ('3', 'jim', '吉姆', '$2a$10$FwoNmRDHMerlbaCiQ2rDZeFaH3n.YQ0UDoEfVWcBMD8tpWlxbywo.', '2018-05-17', '2018-05-17', '1', '1', '1', '1');

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `roleid` int(10) DEFAULT NULL COMMENT '角色ID',
  `userid` int(10) DEFAULT NULL COMMENT '用户ID',
  KEY `fk_role_1` (`roleid`),
  KEY `fk_user` (`userid`),
  CONSTRAINT `fk_role_1` FOREIGN KEY (`roleid`) REFERENCES `sys_role` (`roleid`),
  CONSTRAINT `fk_user` FOREIGN KEY (`userid`) REFERENCES `sys_user` (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户角色表';

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('1', '1');
INSERT INTO `sys_user_role` VALUES ('2', '2');
INSERT INTO `sys_user_role` VALUES ('3', '3');
