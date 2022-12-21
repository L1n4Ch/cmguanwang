ALTER TABLE cm_banner_config ADD client_type INT (1) DEFAULT 0 COMMENT '客户端类型: 0 PC端，1 手机端';

INSERT INTO `sys_dict_data` VALUES ('200', '1', '手机端', '1', 'cm_banner_client_type', null, null, 'N', '0', 'admin', '2021-08-25 22:28:53', '', null, '手机端');
INSERT INTO `sys_dict_data` VALUES ('201', '0', 'PC端', '0', 'cm_banner_client_type', null, null, 'Y', '0', 'admin', '2021-08-25 22:29:05', '', null, 'PC端');
INSERT INTO `sys_dict_type` VALUES ('200', '客户端类型', 'cm_banner_client_type', '0', 'admin', '2021-08-25 22:28:32', '', null, '客户端类型');
COMMIT;

ALTER TABLE cm_recruit ADD edu varchar(20) COMMENT '学历';


ALTER TABLE cm_donation_user ADD lang_mark varchar(20) NULL DEFAULT '' COMMENT '来源CN或EN' AFTER file_id;
ALTER TABLE cm_msg_manage ADD fy_msg_detail varchar(2000) NULL DEFAULT '' COMMENT '翻译后的留言内容' AFTER msg_detail;
ALTER TABLE cm_msg_manage ADD fy_msg_answer varchar(1000) NULL DEFAULT '' COMMENT '翻译后的回复内容' AFTER msg_answer;
ALTER TABLE cm_big_event ADD pub_lang varchar(20) NULL DEFAULT 'CN' COMMENT '发布语言CN或EN' AFTER be_id;
