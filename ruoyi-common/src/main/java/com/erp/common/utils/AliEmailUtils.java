package com.erp.common.utils;

import com.erp.common.config.ErpConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dm.model.v20151123.SingleSendMailRequest;
import com.aliyuncs.dm.model.v20151123.SingleSendMailResponse;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;

public class AliEmailUtils {
	private static final Logger log = LoggerFactory.getLogger(AliEmailUtils.class);

	public static final String ACCOUNT_NAME = "admin@mail.xxxx.com";
	public static final String TAG_NAME = "xxxx";
	public static final String REPLY_ADDRESS = "xxxx@163.com";

	public static final String DEMO_TEMPLATE = "亲爱的xxxxx：<br />"
			+ "您有新的消息！<br />";

	public static void main(String[] args) {
		sendMail("xxxxx@qq.com", "测试邮件", DEMO_TEMPLATE);
	}

	@SuppressWarnings("deprecation")
	public static boolean sendMail(String toAddr, String subject, String htmlBody) {
		IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", ErpConfig.getAliAccessKey(),
			ErpConfig.getAliSecret());
		IAcsClient client = new DefaultAcsClient(profile);
		SingleSendMailRequest request = new SingleSendMailRequest();
		try {
			request.setAccountName(ACCOUNT_NAME);
			request.setFromAlias(ErpConfig.getAliSignName());
			request.setAddressType(1);
			request.setTagName(TAG_NAME);
			request.setToAddress(toAddr);
			request.setReplyToAddress(true);
			request.setReplyAddress(REPLY_ADDRESS);
			request.setSubject(subject);
			request.setHtmlBody(htmlBody);
			request.setMethod(MethodType.POST);
			SingleSendMailResponse httpResponse = client.getAcsResponse(request);
			System.out.println(JSON.toJSONString(httpResponse));
			return true;
		} catch (Exception e) {
			log.error("ali send email failed. ", e);
			return false;
		}
	}
}
