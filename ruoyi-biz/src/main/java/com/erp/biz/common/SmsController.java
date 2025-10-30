package com.erp.biz.common;

import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.erp.common.constant.Constants;
import com.erp.common.core.domain.AjaxResult;
import com.erp.common.core.redis.RedisCache;

/**
 * 通用请求处理
 *
 * @author erp
 */
@RestController
public class SmsController
{
    private static final Logger log = LoggerFactory.getLogger(SmsController.class);

	@Autowired
	private RedisCache redisCache;

	/**
	 *
	 * @param mobile
	 */
    @GetMapping("/sms/registerCode")
    public AjaxResult<Void> registerCode(@RequestParam("mobile")String mobile){
		String codeStr = Long.toString(System.nanoTime());
		String verifyCode = "111111"; // TODO codeStr.substring(codeStr.length() - 6);
		log.info("Regist SMS code, phone: {}, code: {}", mobile, verifyCode);
		//发送短信
		try {
//			AliSmsUtils.sendRegistCodeSms(mobile, verifyCode);
		} catch (Exception e){
			return AjaxResult.error("验证码发送失败");
		}
		//放入redis
		redisCache.setCacheObject("REG:" + mobile, verifyCode, Constants.SMS_EXPIRATION, TimeUnit.MINUTES);
		return AjaxResult.success();
    }

}
