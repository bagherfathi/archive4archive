--公交线路
CREATE TABLE `bus` (
  `id` int(11) NOT NULL auto_increment,
  `user_id` int(11) NOT NULL default '0',
  `name` varchar(120) NOT NULL default '',
  `content` text,
  `time_create` datetime NOT NULL default '2005-12-21 03:38:00',
  `time_modified` datetime NOT NULL default '2005-12-21 03:38:00',
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=gbk;

--公交站点
CREATE TABLE `bus_station` (
  `id` int(11) NOT NULL auto_increment,
  `user_id` int(11) NOT NULL default '0',
  `bus_id` int(11) NOT NULL default '0',
  `name` varchar(20) NOT NULL default '',
  `content` varchar(120) NOT NULL default '',
  `place_x` DOUBLE,
  `place_y` DOUBLE,
  `place_z` DOUBLE,
  `pitch` DOUBLE,
  `rotate` DOUBLE,
  `label_id` varchar(120) NOT NULL default '',
  `label_name` varchar(120) NOT NULL default '',
  `label_icon` varchar(120) NOT NULL default '',
  `label_memo` varchar(120) NOT NULL default '',
  `label_font` varchar(120) NOT NULL default '',
  `lod` DOUBLE,
  `color` int,
  `bkColor` int,
  `driveUp` DOUBLE,
  `remark` varchar(120) NOT NULL default '',

  `time_create` datetime NOT NULL default '2005-12-21 03:38:00',
  `time_modified` datetime NOT NULL default '2005-12-21 03:38:00',
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=gbk;

--建筑信息
CREATE TABLE `building` (
  `id` int(11) NOT NULL auto_increment,
  `user_id` int(11) NOT NULL default '0',	--发布人id
  `subject` varchar(120) NOT NULL default '',--建筑名称
  `content` text,							--描述
  `type` int NOT NULL default '0',			--建筑类型类型(与系统资源表关联)
  `place_x` DOUBLE,							--坐标
  `place_y` DOUBLE,
  `place_z` DOUBLE,
  `pitch` DOUBLE,
  `rotate` DOUBLE,
  `label_id` varchar(120) NOT NULL default '',
  `label_name` varchar(120) NOT NULL default '',
  `label_icon` varchar(120) NOT NULL default '',
  `label_memo` varchar(120) NOT NULL default '',
  `label_font` varchar(120) NOT NULL default '',
  `lod` DOUBLE,
  `color` int,
  `bkColor` int,
  `driveUp` DOUBLE,
  `remark` varchar(120) NOT NULL default '',
  
  `time_create` datetime NOT NULL default '2005-12-21 03:38:00',
  `time_modified` datetime NOT NULL default '2005-12-21 03:38:00',
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=gbk;

--公告
CREATE TABLE `call_board` (
  `id` int(11) NOT NULL auto_increment,
  `user_id` int(11) NOT NULL default '0',
  `subject` varchar(127) NOT NULL default '',
  `content` text,
  `time_create` datetime NOT NULL default '2005-12-21 03:38:00',
  `time_modified` datetime NOT NULL default '2005-12-21 03:38:00',
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=gbk;

--客户反馈
CREATE TABLE `feed_back` (
  `id` int(11) NOT NULL auto_increment,
  `user_id` int(11) NOT NULL default '0',		--回复人id
  `subject` varchar(120) NOT NULL default '',	--反馈标题
  `content` text,								--反馈内容
  `reply` text,									--回复内容
  `status` int(11) NOT NULL default '0',		--回复状态 0：未回复，1：已回复
  `time_create` datetime NOT NULL default '2005-12-21 03:38:00',
  `time_modified` datetime NOT NULL default '2005-12-21 03:38:00',
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=gbk;

--特殊路径
CREATE TABLE `special_path` (
  `id` int(11) NOT NULL auto_increment,
  `user_id` int(11) NOT NULL default '0',		--发布人id
  `file_name` varchar(120) NOT NULL default '',	--名称
  `file_path` varchar(120),						--url
  `content` text,								--描述
  `picture` varchar(120),						--图片
  `time_create` datetime NOT NULL default '2005-12-21 03:38:00',
  `time_modified` datetime NOT NULL default '2005-12-21 03:38:00',
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=gbk;

--视点
CREATE TABLE `view_point` (
  `id` int(11) NOT NULL auto_increment,
  `user_id` int(11) NOT NULL default '0',
  `name` varchar(120) NOT NULL default '',
  `content` varchar(120) NOT NULL default '',
  `place_x` DOUBLE,
  `place_y` DOUBLE,
  `place_z` DOUBLE,
  `pitch` DOUBLE,
  `rotate` DOUBLE,
  `time_create` datetime NOT NULL default '2005-12-21 03:38:00',
  `time_modified` datetime NOT NULL default '2005-12-21 03:38:00',
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=gbk;

--标注
CREATE TABLE `mark` (
  `id` int(11) NOT NULL auto_increment,
  `user_id` int(11) NOT NULL default '0',
  `name` varchar(120) NOT NULL default '',
  `icon` varchar(120) NOT NULL default '',
  `font` varchar(120) NOT NULL default '',
  `place_x` DOUBLE,
  `place_y` DOUBLE,
  `place_z` DOUBLE,
  `lod` DOUBLE,
  `color` LONG,
  `bkColor` LONG,
  `driveUp` DOUBLE,
  `remark` varchar(120) NOT NULL default '',
  
  `time_create` datetime NOT NULL default '2005-12-21 03:38:00',
  `time_modified` datetime NOT NULL default '2005-12-21 03:38:00',
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=gbk;

--操作日志
CREATE TABLE `log` (
  `id` int(11) NOT NULL auto_increment,
  `user_id` int(11) NOT NULL default '0',	--操作用户id
  `subject` varchar(50) NOT NULL default '',--操作功能点名称
  `time_create` datetime NOT NULL default '2005-12-21 03:38:00',
  `time_modified` datetime NOT NULL default '2005-12-21 03:38:00',
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=gbk;

--用户
CREATE TABLE `member` (
  `id` int(11) NOT NULL auto_increment,
  `login_name` varchar(32) default NULL,
  `password` varchar(32) default NULL,
  `state` int(11) default '0',
  `is_admin` char(1) default 'n',
  `time_create` int(11) default NULL,
  `time_modified` int(11) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=gbk;

--系统参数
CREATE TABLE `resources` (
  `id` int(11) NOT NULL auto_increment,
  `created_date` int(11) default NULL,
  `last_updated_date` int(11) default NULL,
  `type` varchar(32) NOT NULL default '',
  `value` varchar(128) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM  CHARSET=gbk;
