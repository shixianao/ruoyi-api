package com.erp.framework.wx.controller;

import com.erp.common.core.domain.AjaxResult;
import com.erp.common.exception.CustomException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.bean.WxOAuth2UserInfo;
import me.chanjar.weixin.common.bean.oauth2.WxOAuth2AccessToken;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Edward
 */
@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/wx/core/{appid}")
public class WxCoreController {
	private final WxMpService wxService;

	@RequestMapping("/getWxUserInfo")
	public AjaxResult getWxUserInfo(@PathVariable String appid, @RequestParam String code) {
		if (!this.wxService.switchover(appid)) {
			throw new IllegalArgumentException(String.format("未找到对应appid=[%s]的配置，请核实！", appid));
		}
		try {
			WxOAuth2AccessToken accessToken = wxService.getOAuth2Service().getAccessToken(code);
			WxOAuth2UserInfo user = wxService.getOAuth2Service().getUserInfo(accessToken, null);
			return AjaxResult.success(user);
		} catch (WxErrorException e) {
			log.error("getWxUserInfo failed. ", e);
			throw new CustomException("微信授权失败");
		}
	}
}
