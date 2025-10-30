package com.erp.framework.security.util;

import com.erp.common.core.domain.entity.SysUser;
import com.erp.common.core.domain.model.LoginUser;
import com.erp.common.utils.SecurityUtils;
import com.erp.common.utils.ServletUtils;
import com.erp.common.utils.spring.SpringUtils;
import com.erp.framework.web.service.TokenService;

public class AuthUtil extends SecurityUtils {
	
	/**
	 * 获取当前用户
	 */
	public static SysUser getUser() {
		LoginUser loginUser = SpringUtils.getBean(TokenService.class).getLoginUser(ServletUtils.getRequest());
		if (null != loginUser) {
			return loginUser.getUser();
		}
		return null;
	}
	
	/**
	 * 获取当前用户的商户ID 
	 */
	public static Long getMerchantIdOfCurrentUser() {
		SysUser u = getUser();
		return u == null ? null : u.getMerchantId();
	}
	
	/**
	 * 获取当前用户的ID 
	 */
	public static Long getUserId() {
		SysUser u = getUser();
		return u == null ? null : u.getUserId();
	}
	
}
