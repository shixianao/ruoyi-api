
ALTER table sys_role add COLUMN `merchant_id` bigint(20) DEFAULT NULL COMMENT '商户id' AFTER `role_id`;
ALTER table sys_user add COLUMN `merchant_id` bigint(20) DEFAULT NULL COMMENT '商户id' AFTER `user_id`;

INSERT INTO `sys_role` (`role_name`, `role_key`, `role_sort`, `data_scope`, `menu_check_strictly`, `dept_check_strictly`, `status`, `del_flag`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`)
VALUES ( '站点管理员', 'siteAdmin', 1, '1', 1, 1, '0', '0', 'devAdmin', '2022-01-16 16:39:06', '', NULL, '');

