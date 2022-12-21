alter table cm_menu add language_type char(1) DEFAULT '0' COMMENT '语言（0中文 1英文）';
INSERT INTO `sys_dict_type` VALUES ('115', '语言类型', 'language_type', '0', 'admin', '2021-12-28 12:25:42', '', null, '语言类型');
INSERT INTO `sys_dict_data` VALUES ('202', '1', '中文', '0', 'language_type', '', 'default', 'Y', '0', 'admin', '2021-12-28 12:26:13', 'admin', '2021-12-28 12:26:57', '');
INSERT INTO `sys_dict_data` VALUES ('203', '2', '英文', '1', 'language_type', '', '', 'Y', '0', 'admin', '2021-12-28 12:26:35', 'admin', '2021-12-28 12:27:03', '');