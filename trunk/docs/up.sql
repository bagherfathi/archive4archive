CREATE TABLE `accessory` (
  `id` int(11) NOT NULL auto_increment,
  `file_id` int(11) default 0,
  `old_name`  varchar(120) NOT NULL default '',
  `new_name`  varchar(120) NOT NULL default '',
  `time_create` datetime NOT NULL default '2009-8-8 3:38:00',
  `time_modified` datetime NOT NULL default '2009-8-8 3:38:00',
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=gbk;



CREATE TABLE `file_pigeonhole` (
  `id` int(11) NOT NULL auto_increment,
  `info_sort_id` int(11) default 0,
  `info_column`  varchar(32) NOT NULL default '',
  `info_column_to`  varchar(32) NOT NULL default '',
  `time_create` datetime NOT NULL default '2009-8-8 3:38:00',
  `time_modified` datetime NOT NULL default '2009-8-8 3:38:00',
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=gbk;


CREATE TABLE `resources` (
  `id` int(11) NOT NULL auto_increment,
  `type` varchar(32) NOT NULL default '',
  `value` varchar(128) default NULL,
  `time_create` datetime NOT NULL default '2009-8-8 3:38:00',
  `time_modified` datetime NOT NULL default '2009-8-8 3:38:00',
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=10553 DEFAULT CHARSET=gbk;



CREATE TABLE `member` (
  `id` int(11) NOT NULL auto_increment,
  `login_name` varchar(32) NOT NULL default '',
  `password` varchar(32) NOT NULL default '',
  `state` int(11) NOT NULL default '0',
  `is_admin` char(1) default 'y',
  `time_create` datetime NOT NULL default '2009-8-8 3:38:00',
  `time_modified` datetime NOT NULL default '2005-8-8 3:38:00',
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=gbk;

--��Ϣ�������
CREATE TABLE `info_sort` (
  `id` int(11) NOT NULL auto_increment,
  `name` varchar(32) NOT NULL default '',
  `seq`  varchar(32) NOT NULL default '',
  `taxis` int(11) default 0,
  `type` int(11) default 0,
  `parent_id` int(11) default 0,
  `time_create` datetime NOT NULL default '2009-8-8 3:38:00',
  `time_modified` datetime NOT NULL default '2009-8-8 3:38:00',
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=gbk;

--�ֵ���Ŀ
CREATE TABLE `dictionary_sort` (
  `id` int(11) NOT NULL auto_increment,
  `name` varchar(32) NOT NULL default '',
  `seq`  varchar(32) NOT NULL default '',
  `default_value` int(11) default 0,
  `if_delete` int(11) default 0,
  `parent_id` int(11) default 0,
  `memo` varchar(255) NOT NULL default '',
  `time_create` datetime NOT NULL default '2009-8-8 3:38:00',
  `time_modified` datetime NOT NULL default '2009-8-8 3:38:00',
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=gbk;

CREATE TABLE `quanzong` (
  `id` int(11) NOT NULL auto_increment,
  `name` varchar(32) default '',
  `seq`  varchar(32) default '',
  `type` int(11) default 0,
  `time_create` datetime NOT NULL default '2009-8-8 3:38:00',
  `time_modified` datetime NOT NULL default '2009-8-8 3:38:00',
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=gbk;

--��ݱ�
CREATE TABLE `structure` (
  `id` int(11) NOT NULL auto_increment,
  `en_name` varchar(32) default '',
  `if_update` int(11) default 0,
  `info_sort_id` int(11) default 0,
  `zn_name` varchar(32) default '',
  `type` int(11) default 0,
  `length` int(11) default 0,
  `decimal_digits` int(11) default 0,
  `input_fashion` int(11) default 0,
  `dictionary_name` varchar(32) default '',
  `is_null` int(11) default 0,
  `is_overlap` int(11) default 0,
  `is_index` int(11) default 0,
  `is_query` int(11) default 0,
  `is_type_query` int(11) default 0,
  `is_purview` int(11) default 0,
  `is_self_motion` int(11) default 0,
  `is_extend` int(11) default 0,
  `is_balance` int(11) default 0,
  `is_serial_number` int(11) default 0,
  
  `time_create` datetime NOT NULL default '2009-8-8 3:38:00',
  `time_modified` datetime NOT NULL default '2009-8-8 3:38:00',
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=gbk;

alter table structure add column `is_delete` int default '0';
alter table structure add column `taxis` int default '0';


CREATE TABLE `file` (
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
		  `time_create` datetime NOT NULL default '2009-8-8 3:38:00',
		  `time_modified` datetime NOT NULL default '2009-8-8 3:38:00',
		  PRIMARY KEY  (`id`)
		) ENGINE=MyISAM DEFAULT CHARSET=gbk;




