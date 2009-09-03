/*
MySQL Data Transfer
Source Host: localhost
Source Database: dangan
Target Host: localhost
Target Database: dangan
Date: 2009-8-16 16:27:35
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for dictionary_sort
-- ----------------------------
CREATE TABLE `dictionary_sort` (
  `id` int(11) NOT NULL auto_increment,
  `name` varchar(32) NOT NULL default '',
  `seq` varchar(32) NOT NULL default '',
  `taxis` int(11) default '0',
  `default_value` int(11) default '0',
  `if_delete` int(11) default '0',
  `parent_id` int(11) default '0',
  `memo` varchar(255) NOT NULL default '',
  `time_create` datetime NOT NULL default '2009-08-08 03:38:00',
  `time_modified` datetime NOT NULL default '2009-08-08 03:38:00',
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=15 DEFAULT CHARSET=gbk;

-- ----------------------------
-- Table structure for files
-- ----------------------------
CREATE TABLE `files` (
  `id` int(11) NOT NULL auto_increment,
  `A1` varchar(12) default '',
  `A2` varchar(12) default '',
  `A3` varchar(12) default '',
  `A4` varchar(12) default '',
  `A5` varchar(12) default '',
  `A6` varchar(12) default '',
  `A7` varchar(12) default '',
  `A8` varchar(12) default '',
  `A9` varchar(12) default '',
  `A10` varchar(12) default '',
  `A11` varchar(12) default '',
  `A12` varchar(12) default '',
  `A13` varchar(12) default '',
  `A14` varchar(12) default '',
  `A15` varchar(12) default '',
  `A16` varchar(12) default '',
  `A17` varchar(12) default '',
  `A18` varchar(12) default '',
  `A19` varchar(12) default '',
  `A20` varchar(12) default '',
  `A21` varchar(12) default '',
  `A22` varchar(12) default '',
  `A23` varchar(12) default '',
  `A24` varchar(12) default '',
  `A25` varchar(12) default '',
  `A26` varchar(12) default '',
  `A27` varchar(12) default '',
  `A28` varchar(12) default '',
  `A29` varchar(12) default '',
  `A30` varchar(12) default '',
  `A31` varchar(12) default '',
  `A32` varchar(12) default '',
  `A33` varchar(12) default '',
  `A34` varchar(12) default '',
  `A35` varchar(12) default '',
  `A36` varchar(12) default '',
  `A37` varchar(12) default '',
  `A38` varchar(12) default '',
  `A39` varchar(12) default '',
  `A40` varchar(12) default '',
  `A41` varchar(12) default '',
  `A42` varchar(12) default '',
  `A43` varchar(12) default '',
  `A44` varchar(12) default '',
  `A45` varchar(12) default '',
  `A46` varchar(12) default '',
  `A47` varchar(12) default '',
  `A48` varchar(12) default '',
  `A49` varchar(12) default '',
  `A50` varchar(12) default '',
  `time_create` datetime NOT NULL default '2009-08-08 03:38:00',
  `time_modified` datetime NOT NULL default '2009-08-08 03:38:00',
  `file` varchar(255) default NULL,
  `info_sort_id` int(11) default '0',
  `title` varchar(255) default '',
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=10 DEFAULT CHARSET=gbk;

-- ----------------------------
-- Table structure for info_sort
-- ----------------------------
CREATE TABLE `info_sort` (
  `id` int(11) NOT NULL auto_increment,
  `name` varchar(32) NOT NULL default '',
  `seq` varchar(32) NOT NULL default '',
  `taxis` int(11) default '0',
  `type` int(11) default '0',
  `parent_id` int(11) default '0',
  `time_create` datetime NOT NULL default '2009-08-08 03:38:00',
  `time_modified` datetime NOT NULL default '2009-08-08 03:38:00',
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=25 DEFAULT CHARSET=gbk;

-- ----------------------------
-- Table structure for member
-- ----------------------------
CREATE TABLE `member` (
  `id` int(11) NOT NULL auto_increment,
  `login_name` varchar(32) NOT NULL default '',
  `password` varchar(32) NOT NULL default '',
  `state` int(11) NOT NULL default '0',
  `is_admin` char(1) default 'y',
  `time_create` datetime NOT NULL default '2009-08-08 03:38:00',
  `time_modified` datetime NOT NULL default '2005-08-08 03:38:00',
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=gbk;

-- ----------------------------
-- Table structure for quanzong
-- ----------------------------
CREATE TABLE `quanzong` (
  `id` int(11) NOT NULL auto_increment,
  `name` varchar(32) default '',
  `seq` varchar(32) default '',
  `type` int(11) default '0',
  `time_create` datetime NOT NULL default '2009-08-08 03:38:00',
  `time_modified` datetime NOT NULL default '2009-08-08 03:38:00',
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=13 DEFAULT CHARSET=gbk;

-- ----------------------------
-- Table structure for resources
-- ----------------------------
CREATE TABLE `resources` (
  `id` int(11) NOT NULL auto_increment,
  `type` varchar(32) NOT NULL default '',
  `value` varchar(128) default NULL,
  `time_create` datetime NOT NULL default '2009-08-08 03:38:00',
  `time_modified` datetime NOT NULL default '2009-08-08 03:38:00',
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=gbk;

-- ----------------------------
-- Table structure for structure
-- ----------------------------
CREATE TABLE `structure` (
  `id` int(11) unsigned zerofill NOT NULL auto_increment,
  `serial_number` varchar(32) default NULL,
  `if_update` int(11) default '0',
  `info_sort_id` int(11) default '0',
  `zn_name` varchar(32) default '',
  `type` int(11) default '0',
  `length` int(11) default '0',
  `decimal_digits` int(11) default '0',
  `input_fashion` int(11) default '0',
  `dictionary_name` int(11) NOT NULL,
  `is_null` int(11) default '0',
  `is_overlap` int(11) default '0',
  `is_index` int(11) default '0',
  `is_query` int(11) default '0',
  `is_type_query` int(11) default '0',
  `is_purview` int(11) default '0',
  `is_self_motion` int(11) default '0',
  `is_extend` int(11) default '0',
  `is_balance` int(11) default '0',
  `is_serial_number` int(11) default '0',
  `time_create` datetime NOT NULL default '2009-08-08 03:38:00',
  `time_modified` datetime NOT NULL default '2009-08-08 03:38:00',
  `is_delete` int(11) default '0',
  `taxis` int(11) default '0',
  `is_list` int(11) default '0',
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=46 DEFAULT CHARSET=gbk;

-- ----------------------------
-- Records 
-- ----------------------------
INSERT INTO `dictionary_sort` VALUES ('8', '性别', '', '0', '0', '0', '0', '', '2009-08-15 21:32:25', '2009-08-15 21:32:25');
INSERT INTO `dictionary_sort` VALUES ('9', '男', '', '0', '0', '0', '8', '', '2009-08-15 21:32:33', '2009-08-15 21:32:33');
INSERT INTO `dictionary_sort` VALUES ('10', '女', '', '0', '0', '0', '8', '', '2009-08-15 21:32:40', '2009-08-15 21:32:40');
INSERT INTO `dictionary_sort` VALUES ('11', '紧急程度', '', '0', '0', '0', '0', '', '2009-08-15 21:43:55', '2009-08-15 21:43:55');
INSERT INTO `dictionary_sort` VALUES ('12', '急件', '', '0', '0', '0', '11', '', '2009-08-15 21:44:35', '2009-08-15 21:44:35');
INSERT INTO `dictionary_sort` VALUES ('13', '特级', '', '0', '0', '0', '11', '', '2009-08-15 21:44:39', '2009-08-15 21:44:39');
INSERT INTO `dictionary_sort` VALUES ('14', '平件', '', '0', '0', '0', '11', '', '2009-08-15 21:44:59', '2009-08-15 21:44:59');
INSERT INTO `files` VALUES ('9', '1213', '', '', '222222222222', '222222222222', '', '', '', '', '222222222222', '1', '1', '1', '', '1', '', '', '', '', '', '', '', '', '', '', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2009-08-08 03:38:00', '2009-08-08 03:38:00', null, '0', null);
INSERT INTO `info_sort` VALUES ('2', '文件', '01', '0', '0', '0', '2009-08-09 17:28:09', '2009-08-09 21:47:18');
INSERT INTO `info_sort` VALUES ('8', '发文', '0102', '0', '2', '2', '2009-08-09 21:08:35', '2009-08-09 21:08:35');
INSERT INTO `info_sort` VALUES ('4', '档案', '02', '0', '0', '0', '2009-08-09 17:31:56', '2009-08-09 20:59:59');
INSERT INTO `info_sort` VALUES ('7', '来文', '0101', '0', '2', '2', '2009-08-09 21:03:02', '2009-08-09 21:04:25');
INSERT INTO `info_sort` VALUES ('9', '内部文件', '0103', '0', '2', '2', '2009-08-09 21:14:40', '2009-08-09 21:50:06');
INSERT INTO `info_sort` VALUES ('23', '简化方法管理', '020102', '0', '2', '20', '2009-08-09 21:53:32', '2009-08-09 21:53:32');
INSERT INTO `info_sort` VALUES ('19', '人事档案', '0201', '0', '2', '4', '2009-08-09 21:51:07', '2009-08-09 21:51:07');
INSERT INTO `info_sort` VALUES ('20', '文书档案', '0202', '0', '1', '4', '2009-08-09 21:51:33', '2009-08-09 21:51:33');
INSERT INTO `info_sort` VALUES ('22', '传统方法管理', '020101', '0', '2', '20', '2009-08-09 21:53:15', '2009-08-09 21:53:15');
INSERT INTO `info_sort` VALUES ('24', '111', '1111', '0', '1', '20', '2009-08-12 18:09:32', '2009-08-12 18:09:32');
INSERT INTO `member` VALUES ('1', 'admin', 'pass', '0', 'y', '2009-08-08 03:38:00', '2005-08-08 03:38:00');
INSERT INTO `member` VALUES ('2', '1111', 'hello123', '0', null, '2009-08-09 17:09:30', '2009-08-09 17:11:45');
INSERT INTO `structure` VALUES ('00000000021', 'a1', '0', '8', '登记号', '2', '28', '0', '2', '0', '1', '1', '0', '1', '0', '0', '1', '0', '0', '0', '2009-08-15 14:21:22', '2009-08-16 16:02:07', '0', '1', '1');
INSERT INTO `structure` VALUES ('00000000022', 'a2', '0', '8', '登记日期', '3', '0', '0', '3', '0', '1', '1', '0', '0', '0', '0', '0', '0', '0', '0', '2009-08-15 14:22:34', '2009-08-15 15:11:04', '0', '4', '0');
INSERT INTO `structure` VALUES ('00000000023', 'a3', '0', '8', '字号', '0', '100', '0', '0', '0', '1', '1', '0', '0', '0', '0', '0', '1', '0', '0', '2009-08-15 14:23:22', '2009-08-16 16:02:24', '0', '5', '1');
INSERT INTO `structure` VALUES ('00000000024', 'a4', '0', '8', '责任人', '0', '200', '0', '0', '0', '1', '1', '0', '1', '0', '0', '0', '1', '0', '0', '2009-08-15 14:24:52', '2009-08-16 16:02:48', '0', '6', '1');
INSERT INTO `structure` VALUES ('00000000025', 'a5', '0', '8', '题名', '0', '255', '0', '0', '0', '0', '1', '0', '1', '0', '0', '0', '0', '0', '0', '2009-08-15 14:26:22', '2009-08-16 16:07:30', '0', '7', '1');
INSERT INTO `structure` VALUES ('00000000026', 'a6', '0', '8', '归档情况', '0', '6', '0', '1', '0', '0', '1', '0', '1', '0', '0', '0', '1', '0', '1', '2009-08-15 14:28:24', '2009-08-16 16:07:59', '0', '8', '1');
INSERT INTO `structure` VALUES ('00000000027', 'a7', '0', '8', '文件份数', '1', '0', '0', '0', '0', '1', '1', '0', '0', '0', '0', '0', '0', '0', '0', '2009-08-15 14:29:18', '2009-08-15 14:29:18', '0', '9', '0');
INSERT INTO `structure` VALUES ('00000000028', 'a8', '0', '8', '密级', '0', '4', '0', '1', '0', '1', '1', '0', '0', '0', '1', '0', '1', '0', '1', '2009-08-15 14:30:34', '2009-08-16 16:03:09', '0', '10', '1');
INSERT INTO `structure` VALUES ('00000000029', 'a9', '0', '8', '主题词', '0', '100', '0', '0', '0', '1', '1', '0', '0', '0', '0', '0', '0', '0', '0', '2009-08-15 14:31:04', '2009-08-16 16:02:55', '0', '11', '1');
INSERT INTO `structure` VALUES ('00000000030', 'a10', '0', '8', '备注', '0', '255', '0', '0', '0', '1', '1', '0', '0', '0', '0', '0', '0', '0', '0', '2009-08-15 14:32:16', '2009-08-15 14:32:16', '0', '12', '0');
INSERT INTO `structure` VALUES ('00000000031', 'a11', '0', '8', '来文机关', '0', '100', '0', '0', '0', '1', '1', '0', '0', '0', '0', '0', '1', '0', '0', '2009-08-15 14:32:44', '2009-08-15 14:32:44', '0', '13', '0');
INSERT INTO `structure` VALUES ('00000000032', 'a12', '0', '8', '签收人', '0', '20', '0', '0', '0', '1', '1', '0', '0', '0', '0', '0', '0', '0', '1', '2009-08-15 14:33:12', '2009-08-15 14:33:12', '0', '14', '0');
INSERT INTO `structure` VALUES ('00000000033', 'a13', '0', '8', '页数', '1', '0', '0', '0', '0', '1', '1', '0', '0', '0', '0', '0', '0', '0', '0', '2009-08-15 14:33:42', '2009-08-16 16:08:06', '0', '15', '1');
INSERT INTO `structure` VALUES ('00000000034', 'a14', '0', '8', '成文日期', '4', '50', '0', '0', '0', '1', '1', '0', '0', '0', '0', '0', '0', '0', '0', '2009-08-15 14:34:18', '2009-08-15 15:06:49', '0', '3', '0');
INSERT INTO `structure` VALUES ('00000000035', 'a15', '0', '8', '分发情况', '0', '250', '0', '0', '0', '1', '1', '0', '0', '0', '0', '0', '0', '0', '0', '2009-08-15 14:34:49', '2009-08-15 14:34:49', '0', '16', '0');
INSERT INTO `structure` VALUES ('00000000036', 'a16', '0', '8', '传阅情况', '0', '250', '0', '0', '0', '1', '1', '0', '0', '0', '0', '0', '0', '0', '0', '2009-08-15 14:34:56', '2009-08-15 14:35:21', '0', '17', '0');
INSERT INTO `structure` VALUES ('00000000037', 'a17', '0', '8', '处理情况', '0', '250', '0', '0', '0', '1', '1', '0', '0', '0', '0', '0', '0', '0', '0', '2009-08-15 14:35:46', '2009-08-15 14:35:46', '0', '18', '0');
INSERT INTO `structure` VALUES ('00000000038', 'a18', '0', '8', '存放位置', '0', '100', '0', '0', '0', '1', '1', '0', '0', '0', '0', '0', '0', '0', '0', '2009-08-15 14:36:10', '2009-08-15 14:36:10', '0', '19', '0');
INSERT INTO `structure` VALUES ('00000000039', 'a19', '0', '8', '全宗号', '0', '20', '0', '0', '0', '1', '1', '0', '1', '0', '0', '0', '1', '0', '1', '2009-08-15 14:37:12', '2009-08-15 14:37:35', '0', '20', '0');
INSERT INTO `structure` VALUES ('00000000040', 'a20', '0', '8', '实体分类号', '0', '16', '0', '0', '0', '1', '1', '0', '0', '0', '0', '0', '0', '0', '1', '2009-08-15 14:38:12', '2009-08-15 14:38:12', '0', '21', '0');
INSERT INTO `structure` VALUES ('00000000041', 'a21', '0', '8', '保管期限', '0', '10', '0', '1', '0', '1', '1', '0', '1', '0', '0', '0', '1', '0', '1', '2009-08-15 14:39:05', '2009-08-15 14:39:05', '0', '22', '0');
INSERT INTO `structure` VALUES ('00000000042', 'a22', '0', '8', '年度', '1', '0', '0', '1', '0', '1', '0', '0', '0', '0', '0', '0', '1', '0', '1', '2009-08-15 14:39:32', '2009-08-15 15:06:33', '0', '2', '0');
INSERT INTO `structure` VALUES ('00000000043', 'a23', '0', '8', '紧急程度', '0', '50', '0', '1', '11', '1', '1', '0', '1', '0', '0', '0', '1', '0', '1', '2009-08-15 14:40:08', '2009-08-15 21:46:24', '0', '23', '0');
INSERT INTO `structure` VALUES ('00000000044', 'a24', '0', '8', '类别', '0', '50', '0', '0', '0', '1', '1', '0', '0', '0', '0', '0', '1', '0', '1', '2009-08-15 14:40:36', '2009-08-15 14:40:36', '0', '24', '0');
INSERT INTO `structure` VALUES ('00000000045', 'a25', '0', '8', '主办单位', '0', '50', '0', '0', '0', '1', '1', '0', '0', '0', '0', '0', '1', '0', '0', '2009-08-15 14:41:04', '2009-08-15 14:41:04', '0', '25', '0');
