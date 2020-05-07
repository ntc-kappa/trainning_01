/*
Navicat MySQL Data Transfer

Source Server         : localhost_mariadb
Source Server Version : 50567
Source Host           : localhost:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50567
File Encoding         : 65001

Date: 2020-04-25 18:02:13
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `category`
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `description` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_acatplu22q5d1andql2jbvjy7` (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of category
-- ----------------------------

-- ----------------------------
-- Table structure for `department`
-- ----------------------------
DROP TABLE IF EXISTS `department`;
CREATE TABLE `department` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `location` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of department
-- ----------------------------

-- ----------------------------
-- Table structure for `device`
-- ----------------------------
DROP TABLE IF EXISTS `device`;
CREATE TABLE `device` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `create_by` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `description` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `modify_by` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `modify_time` datetime DEFAULT NULL,
  `name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `price` double DEFAULT NULL,
  `id_category` int(11) NOT NULL,
  `id_user` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_sf41cw48kowh1b6ysrdjob3ch` (`code`),
  KEY `FK_nrmtjyq8mbssc9meh2kts0vcr` (`id_category`),
  KEY `FK_skjnwkwuhks8k57ooofqnwhp` (`id_user`),
  CONSTRAINT `FK_skjnwkwuhks8k57ooofqnwhp` FOREIGN KEY (`id_user`) REFERENCES `user` (`id`),
  CONSTRAINT `FK_nrmtjyq8mbssc9meh2kts0vcr` FOREIGN KEY (`id_category`) REFERENCES `category` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of device
-- ----------------------------

-- ----------------------------
-- Table structure for `position`
-- ----------------------------
DROP TABLE IF EXISTS `position`;
CREATE TABLE `position` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of position
-- ----------------------------

-- ----------------------------
-- Table structure for `project`
-- ----------------------------
DROP TABLE IF EXISTS `project`;
CREATE TABLE `project` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `checkin` date DEFAULT NULL,
  `checkout` date DEFAULT NULL,
  `create_by` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `description` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `modify_by` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `modify_time` datetime DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `title` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `id_type_project` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_iiohcc2ax9i53cdtla5m2ldng` (`id_type_project`),
  CONSTRAINT `FK_iiohcc2ax9i53cdtla5m2ldng` FOREIGN KEY (`id_type_project`) REFERENCES `type_project` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of project
-- ----------------------------
INSERT INTO `project` VALUES ('1', '2020-04-09', '2020-04-10', 'Do Thanh Quang', '2020-04-16 14:30:05', 'Ko có', null, null, '0', 'Dự án 1', '2');
INSERT INTO `project` VALUES ('2', '2020-11-01', '2020-12-01', 'Sesssion get', '2020-04-25 11:38:12', '', 'Chưa biết', '2020-04-25 11:38:39', '26', 'Dự án 2', '1');

-- ----------------------------
-- Table structure for `project_position`
-- ----------------------------
DROP TABLE IF EXISTS `project_position`;
CREATE TABLE `project_position` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of project_position
-- ----------------------------

-- ----------------------------
-- Table structure for `role`
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `create_by` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `description` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `modify_by` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `modify_time` datetime DEFAULT NULL,
  `name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_c36say97xydpmgigg38qv5l2p` (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of role
-- ----------------------------

-- ----------------------------
-- Table structure for `type_project`
-- ----------------------------
DROP TABLE IF EXISTS `type_project`;
CREATE TABLE `type_project` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `description` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_fihpthe9c2gw0pd306agu6c1` (`code`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of type_project
-- ----------------------------
INSERT INTO `type_project` VALUES ('1', 'BUG', 'Kiểu dự án là sửa lỗi', 'Bug');
INSERT INTO `type_project` VALUES ('2', 'TASK', 'Kiểu dự án là task', 'Task');
INSERT INTO `type_project` VALUES ('3', 'Q&A', 'Kiểu hỏi đáp', 'Q&A');

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `address` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `appro_by` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `avatar` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `create_by` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `email` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `full_name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `gender` bit(1) DEFAULT NULL,
  `is_active` bit(1) DEFAULT NULL,
  `modify_by` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `modify_time` datetime DEFAULT NULL,
  `password` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `phone_number` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `user_name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_ob8kqyqqgmefl0aco34akdtpe` (`email`),
  UNIQUE KEY `UK_lqjrcobrh9jc8wpcar64q1bfh` (`user_name`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', null, null, null, null, null, null, null, null, null, null, null, '123456', null, 'quangdtptit');

-- ----------------------------
-- Table structure for `user_department`
-- ----------------------------
DROP TABLE IF EXISTS `user_department`;
CREATE TABLE `user_department` (
  `id_department` int(11) NOT NULL,
  `id_user` int(11) NOT NULL,
  PRIMARY KEY (`id_department`,`id_user`),
  KEY `FK_379dyo5a7vt1mcfi6wsiu462h` (`id_user`),
  CONSTRAINT `FK_f02u629bdfu8frp1efg9i5giq` FOREIGN KEY (`id_department`) REFERENCES `department` (`id`),
  CONSTRAINT `FK_379dyo5a7vt1mcfi6wsiu462h` FOREIGN KEY (`id_user`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of user_department
-- ----------------------------

-- ----------------------------
-- Table structure for `user_position`
-- ----------------------------
DROP TABLE IF EXISTS `user_position`;
CREATE TABLE `user_position` (
  `id_position` int(11) NOT NULL,
  `id_user` int(11) NOT NULL,
  PRIMARY KEY (`id_position`,`id_user`),
  KEY `FK_gc8bij53atngfmb4c02pxbhow` (`id_user`),
  CONSTRAINT `FK_dpqk6faagm6uy5y80wc52c0v0` FOREIGN KEY (`id_position`) REFERENCES `position` (`id`),
  CONSTRAINT `FK_gc8bij53atngfmb4c02pxbhow` FOREIGN KEY (`id_user`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of user_position
-- ----------------------------

-- ----------------------------
-- Table structure for `user_project_position`
-- ----------------------------
DROP TABLE IF EXISTS `user_project_position`;
CREATE TABLE `user_project_position` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_project` int(11) DEFAULT NULL,
  `id_project_position` int(11) DEFAULT NULL,
  `id_user` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_ijr23olooc1olss0msy5df8m5` (`id_project`),
  KEY `FK_sjbipp321s3uh7e5sae6o2j3b` (`id_project_position`),
  KEY `FK_k1m1dk0xx19xuysju25hktboq` (`id_user`),
  CONSTRAINT `FK_k1m1dk0xx19xuysju25hktboq` FOREIGN KEY (`id_user`) REFERENCES `user` (`id`),
  CONSTRAINT `FK_ijr23olooc1olss0msy5df8m5` FOREIGN KEY (`id_project`) REFERENCES `project` (`id`),
  CONSTRAINT `FK_sjbipp321s3uh7e5sae6o2j3b` FOREIGN KEY (`id_project_position`) REFERENCES `project_position` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of user_project_position
-- ----------------------------

-- ----------------------------
-- Table structure for `user_role`
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `id_role` int(11) NOT NULL,
  `id_user` int(11) NOT NULL,
  PRIMARY KEY (`id_role`,`id_user`),
  KEY `FK_qibq3rh7mo4f8372yxkn549j7` (`id_user`),
  CONSTRAINT `FK_56olsq329osn3lxem8ftn9q9h` FOREIGN KEY (`id_role`) REFERENCES `role` (`id`),
  CONSTRAINT `FK_qibq3rh7mo4f8372yxkn549j7` FOREIGN KEY (`id_user`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of user_role
-- ----------------------------
