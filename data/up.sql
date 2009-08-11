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

--信息门类管理
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

--字典类目
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








