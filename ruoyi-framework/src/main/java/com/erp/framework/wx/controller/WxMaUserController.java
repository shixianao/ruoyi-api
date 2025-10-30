package com.erp.framework.wx.controller;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import cn.binarywang.wx.miniapp.bean.WxMaPhoneNumberInfo;
import cn.binarywang.wx.miniapp.bean.WxMaUserInfo;
import com.erp.common.core.domain.AjaxResult;
import com.erp.common.exception.CustomException;
import com.erp.framework.config.WxMaConfiguration;
import io.jsonwebtoken.lang.Assert;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 微信小程序用户接口
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
@Slf4j
@RestController
@RequestMapping("/wx/user/{appid}")
public class WxMaUserController {

	/**
	 * 登陆接口
	 */
	@GetMapping("/login")
	public AjaxResult login(@PathVariable String appid, String code) {
		Assert.hasLength(code, "code不能为空");
		final WxMaService wxService = WxMaConfiguration.getMaService(appid);
		try {
			WxMaJscode2SessionResult session = wxService.getUserService().getSessionInfo(code);
			log.info(session.getSessionKey());
			log.info(session.getOpenid());
			//TODO 可以增加自己的逻辑，关联业务相关数据
			return AjaxResult.success(session);
		} catch (WxErrorException e) {
			log.error(e.getMessage(), e);
			throw new CustomException("小程序登录失败");
		}
	}

	/**
	 * <pre>
	 * 获取用户信息接口
	 * </pre>
	 */
	@GetMapping("/info")
	public AjaxResult info(@PathVariable String appid, String sessionKey,
						   String signature, String rawData, String encryptedData, String iv) {
		final WxMaService wxService = WxMaConfiguration.getMaService(appid);

		// 用户信息校验
		if (!wxService.getUserService().checkUserInfo(sessionKey, rawData, signature)) {
			throw new CustomException("user check failed");
		}

		// 解密用户信息
		WxMaUserInfo userInfo = wxService.getUserService().getUserInfo(sessionKey, encryptedData, iv);

		return AjaxResult.success(userInfo);
	}

	/**
	 * <pre>
	 * 获取用户绑定手机号信息
	 * </pre>
	 */
	@GetMapping("/phone")
	public AjaxResult phone(@PathVariable String appid, String sessionKey, String signature,
							String rawData, String encryptedData, String iv) {
		final WxMaService wxService = WxMaConfiguration.getMaService(appid);

		// 用户信息校验
		if (!wxService.getUserService().checkUserInfo(sessionKey, rawData, signature)) {
			new CustomException("user check failed");
		}

		// 解密
		WxMaPhoneNumberInfo phoneNoInfo = wxService.getUserService().getPhoneNoInfo(sessionKey, encryptedData, iv);

		return AjaxResult.success(phoneNoInfo);
	}

}
