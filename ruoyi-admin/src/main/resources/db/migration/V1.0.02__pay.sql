

-- ----------------------------
-- Table structure for li_setting
-- ----------------------------
DROP TABLE IF EXISTS `li_setting`;
CREATE TABLE `li_setting`  (
  `id` varchar(255) NOT NULL COMMENT 'ID',
  `create_by` varchar(255) NULL DEFAULT NULL COMMENT '创建者',
  `create_time` datetime(6) NULL DEFAULT NULL COMMENT '创建时间',
  `delete_flag` bit(1) NULL DEFAULT NULL COMMENT '删除标志 true/false 删除/未删除',
  `update_by` varchar(255) NULL DEFAULT NULL COMMENT '更新者',
  `update_time` datetime(6) NULL DEFAULT NULL COMMENT '更新时间',
  `setting_value` mediumtext NULL COMMENT '配置值value',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of li_setting
-- ----------------------------
INSERT INTO `li_setting` VALUES 
('ALIPAY_PAYMENT', 'admin', '2021-02-27 07:20:11.914000', b'0', 'admin', '2021-09-15 11:57:38.683000', '{\"privateKey\":\"privateKey\",\"alipayPublicCertPath\":\"/home/crt/lili-alipay/alipayCertPublicKey_RSA2.crt\",\"certPath\":\"/home/crt/lili-alipay/appCertPublicKey_2021002107649773.crt\",\"appId\":\"appId\",\"rootCertPath\":\"/home/crt/lili-alipay/alipayRootCert.crt\"}'),
('WECHAT_CONNECT', 'admin', '2021-03-02 09:17:10.000000', b'0', 'admin', '2022-01-21 14:44:19.213000', '{\"wechatConnectSettingItems\":[{\"clientType\":\"PC\",\"appId\":\"\",\"appSecret\":\"\"},{\"clientType\":\"H5\",\"appId\":\"\",\"appSecret\":\"\"},{\"clientType\":\"WECHAT_MP\",\"appId\":\"\",\"appSecret\":\"\"}]}'),
('WECHAT_PAYMENT', 'admin', '2021-02-27 07:18:13.767000', b'0', 'admin', '2022-01-21 15:33:40.874000', '{\"mchId\":\"\",\"apiclient_key\":\"/data/deploy/cert/pay/apiclient_key.pem\",\"serialNumber\":\"\",\"apiKey3\":\"\",\"apiclient_cert_pem\":\"/data/deploy/cert/pay/apiclient_cert.pem\",\"apiclient_cert_p12\":\"/data/deploy/cert/pay/apiclient_cert.p12\",\"appId\":\"\"}');

