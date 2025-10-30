package com.erp.common.utils;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.erp.common.config.ErpConfig;
import com.erp.common.exception.CustomException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AliSmsUtils {

//	public static final String ACCESS_KEY_ID = "";
//	public static final String SECRET = "";
//	public static final String SIGN_NAME = "";

	public static void main(String[] args) {
		sendBusinessSms("xxx", "SMS_xxx", String.format("{\"code\":\"%s\"}"));
	}

	/**
	 * 发送注册短信验证码
	 *
	 * @param phone
	 * @param code
	 */
	public static void sendRegistCodeSms(String phone, String code) {
		log.info("sendRegistCodeSms {} -> {}", phone, code);
		try {
			CommonResponse response = getClient().getCommonResponse(
					buildRequestBody(phone, "SMS_232908251", String.format("{\"code\":\"%s\"}", code)));
			log.info(response.getData());
		} catch (Exception e) {
			log.error("", e);
			throw new CustomException("发送失败");
		}
	}

	/**
	 * 发送忘记密码短信验证码
	 *
	 * @param phone
	 * @param code
	 */
	public static void sendForgetPwdCodeSms(String phone, String code) {
		log.info("sendForgetPwdCodeSms {} -> {}", phone, code);
		try {
			CommonResponse response = getClient().getCommonResponse(
					buildRequestBody(phone, "SMS_232913149", String.format("{\"code\":\"%s\"}", code)));
			log.info(response.getData());
		} catch (Exception e) {
			log.error("", e);
			throw new CustomException("发送失败");
		}
	}

	/**
	 * 发送短信
	 *
	 * @param phone
	 * @param templateCode
	 * @param templateContent
	 */
	public static void sendBusinessSms(String phone, String templateCode, String templateContent) {
		log.info("sendBusinessSms {} - {} -> {}", phone, templateCode, templateContent);
		try {
			CommonResponse response = getClient()
					.getCommonResponse(buildRequestBody(phone, templateCode, templateContent));
			log.info(response.getData());
		} catch (Exception e) {
			log.error("", e);
			throw new CustomException("发送失败");
		}
	}

	private static IAcsClient getClient() {
		DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", ErpConfig.getAliAccessKey(), ErpConfig.getAliSecret());
		return new DefaultAcsClient(profile);
	}

	private static CommonRequest buildRequestBody(String mobile, String templateCode, String templateContent) {
		CommonRequest request = new CommonRequest();
		request.setSysMethod(MethodType.POST);
		request.setSysDomain("dysmsapi.aliyuncs.com");
		request.setSysVersion("2017-05-25");
		request.setSysAction("SendSms");
		request.putQueryParameter("PhoneNumbers", mobile);
		request.putQueryParameter("SignName", ErpConfig.getAliSignName());
		request.putQueryParameter("TemplateCode", templateCode);
		request.putQueryParameter("TemplateParam", templateContent);
		return request;
	}

}
