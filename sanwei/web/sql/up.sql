--������·
CREATE TABLE `bus` (
  `id` int(11) NOT NULL auto_increment,
  `user_id` int(11) NOT NULL default '0',
  `name` varchar(120) NOT NULL default '',
  `content` text,
  `time_create` datetime NOT NULL default '2005-12-21 03:38:00',
  `time_modified` datetime NOT NULL default '2005-12-21 03:38:00',
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=gbk;

--����վ��
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

--������Ϣ
CREATE TABLE `building` (
  `id` int(11) NOT NULL auto_increment,
  `user_id` int(11) NOT NULL default '0',	--������id
  `subject` varchar(120) NOT NULL default '',--��������
  `content` text,							--����
  `type` int NOT NULL default '0',			--������������(��ϵͳ��Դ�����)
  `place_x` DOUBLE,							--����
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

--����
CREATE TABLE `call_board` (
  `id` int(11) NOT NULL auto_increment,
  `user_id` int(11) NOT NULL default '0',
  `subject` varchar(127) NOT NULL default '',
  `content` text,
  `time_create` datetime NOT NULL default '2005-12-21 03:38:00',
  `time_modified` datetime NOT NULL default '2005-12-21 03:38:00',
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=gbk;

--�ͻ�����
CREATE TABLE `feed_back` (
  `id` int(11) NOT NULL auto_increment,
  `user_id` int(11) NOT NULL default '0',		--�ظ���id
  `subject` varchar(120) NOT NULL default '',	--��������
  `content` text,								--��������
  `reply` text,									--�ظ�����
  `status` int(11) NOT NULL default '0',		--�ظ�״̬ 0��δ�ظ���1���ѻظ�
  `time_create` datetime NOT NULL default '2005-12-21 03:38:00',
  `time_modified` datetime NOT NULL default '2005-12-21 03:38:00',
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=gbk;

--����·��
CREATE TABLE `special_path` (
  `id` int(11) NOT NULL auto_increment,
  `user_id` int(11) NOT NULL default '0',		--������id
  `file_name` varchar(120) NOT NULL default '',	--����
  `file_path` varchar(120),						--url
  `content` text,								--����
  `picture` varchar(120),						--ͼƬ
  `time_create` datetime NOT NULL default '2005-12-21 03:38:00',
  `time_modified` datetime NOT NULL default '2005-12-21 03:38:00',
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=gbk;

--�ӵ�
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

--��ע
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

--������־
CREATE TABLE `log` (
  `id` int(11) NOT NULL auto_increment,
  `user_id` int(11) NOT NULL default '0',	--�����û�id
  `subject` varchar(50) NOT NULL default '',--�������ܵ�����
  `time_create` datetime NOT NULL default '2005-12-21 03:38:00',
  `time_modified` datetime NOT NULL default '2005-12-21 03:38:00',
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=gbk;

--�û�
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

--ϵͳ����
CREATE TABLE `resources` (
  `id` int(11) NOT NULL auto_increment,
  `created_date` int(11) default NULL,
  `last_updated_date` int(11) default NULL,
  `type` varchar(32) NOT NULL default '',
  `value` varchar(128) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM  CHARSET=gbk;
