package com.erp.framework.web.service;

import cn.hutool.core.lang.Assert;
import com.erp.common.constant.Constants;
import com.erp.common.core.domain.entity.SysUser;
import com.erp.common.core.domain.model.LoginUser;
import com.erp.common.core.redis.RedisCache;
import com.erp.common.exception.CustomException;
import com.erp.common.exception.user.CaptchaException;
import com.erp.common.exception.user.CaptchaExpireException;
import com.erp.common.exception.user.UserPasswordNotMatchException;
import com.erp.common.utils.DateUtils;
import com.erp.common.utils.MessageUtils;
import com.erp.common.utils.SecurityUtils;
import com.erp.common.utils.ServletUtils;
import com.erp.framework.config.properties.CaptchaProperties;
import com.erp.system.service.ISysConfigService;
import com.erp.system.service.ISysUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;

/**
 * 登录校验方法
 *
 * @author erp
 */
@Component
@Slf4j
public class SysLoginService
{
	@Autowired
	private TokenService tokenService;

	@Resource
	private AuthenticationManager authenticationManager;

	@Autowired
	private RedisCache redisCache;

	@Autowired
	private CaptchaProperties captchaProperties;

	@Autowired
	private ISysUserService userService;

	@Autowired
	private ISysConfigService configService;

	@Autowired
	private AsyncService asyncService;


	/**
	 * 根据openId登录，如果没有此用户，则返回空
	 *
	 * @param openId
	 * @return
	 */
	public String loginByOpenId(String openId) {
		HttpServletRequest request = ServletUtils.getRequest();
		SysUser user = userService.selectUserByUserName(openId);
		if (null == user) {
			return null;
		} else {
			asyncService.recordLogininfor(user.getUserName(), Constants.LOGIN_SUCCESS, MessageUtils.message("user.login.success"), request);
		}
		LoginUser loginUser = new LoginUser(user, new HashSet<>());
		// 生成token
		return tokenService.createToken(loginUser);
	}

	/**
	 * 手机验证码登录，如果没有账号自动注册
	 *
	 * @param userLogin
	 * @return
	 */
	public String loginByPhone(SysUser userLogin)
	{
		String phone = userLogin.getPhonenumber();
		String code = userLogin.getDelFlag();
		Assert.notBlank(phone, "手机号不能为空");
		Assert.notBlank(code, "验证码不能为空");

		HttpServletRequest request = ServletUtils.getRequest();
		String verifyKey = Constants.REG_LOGIN_CODE_KEY + phone;
		String codeCached = redisCache.getCacheObject(verifyKey);
		if (codeCached == null) {
			throw new CaptchaExpireException();
		}
		if (!code.equalsIgnoreCase(codeCached)) {
			throw new CaptchaException();
		}
		SysUser user = userService.selectUserByPhone(phone);
		if (null == user) {
			user = userLogin;
			if (StringUtils.isBlank(user.getUserName())) {
				user.setUserName(user.getPhonenumber());
			}
			if (user.getFromUserId()  != null) {
				SysUser fromUser = userService.getById(user.getFromUserId());
				if (null == fromUser) {
					log.error("不存在这个用户, ID：" + user.getFromUserId());
				} else {
					user.setFromUserPath((fromUser.getFromUserPath() == null ? "" : fromUser.getFromUserPath()) + fromUser.getUserId() + ".");
				}
			}
			user.setCreateBy("devAdmin");
			user.setDelFlag(null);
			String password = configService.selectConfigByKey("sys.user.initPassword");
			user.setPassword(SecurityUtils.encryptPassword(password));
			userService.insertUser(user);
			user = userService.selectUserByPhone(phone);
		} else {
			asyncService.recordLogininfor(user.getUserName(), Constants.LOGIN_SUCCESS, MessageUtils.message("user.login.success"), request);
		}
		LoginUser loginUser = new LoginUser(user, new HashSet<>());
		redisCache.deleteObject(verifyKey);
		// 生成token
		return tokenService.createToken(loginUser);
	}

	/**
	 * 登录验证
	 *
	 * @param username 用户名
	 * @param password 密码
	 * @param code 验证码
	 * @param uuid 唯一标识
	 * @return 结果
	 */
	public String login(String username, String password, String code, String uuid)
	{
		HttpServletRequest request = ServletUtils.getRequest();
		if(captchaProperties.getEnabled()) {
			String verifyKey = Constants.CAPTCHA_CODE_KEY + uuid;
			String captcha = redisCache.getCacheObject(verifyKey);
			redisCache.deleteObject(verifyKey);
			if (captcha == null) {
				asyncService.recordLogininfor(username, Constants.LOGIN_FAIL, MessageUtils.message("user.jcaptcha.expire"), request);
				throw new CaptchaExpireException();
			}
			if (!code.equalsIgnoreCase(captcha)) {
				asyncService.recordLogininfor(username, Constants.LOGIN_FAIL, MessageUtils.message("user.jcaptcha.error"), request);
				throw new CaptchaException();
			}
		}
		// 用户验证
		Authentication authentication = null;
		try
		{
			// 该方法会去调用UserDetailsServiceImpl.loadUserByUsername
			authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		}
		catch (Exception e)
		{
			if (e instanceof BadCredentialsException)
			{
				asyncService.recordLogininfor(username, Constants.LOGIN_FAIL, MessageUtils.message("user.password.not.match"), request);
				throw new UserPasswordNotMatchException();
			}
			else
			{
				asyncService.recordLogininfor(username, Constants.LOGIN_FAIL, e.getMessage(), request);
				throw new CustomException(e.getMessage());
			}
		}
		asyncService.recordLogininfor(username, Constants.LOGIN_SUCCESS, MessageUtils.message("user.login.success"), request);
		LoginUser loginUser = (LoginUser) authentication.getPrincipal();
		recordLoginInfo(loginUser.getUser());
		// 生成token
		return tokenService.createToken(loginUser);
	}

	/**
	 * 记录登录信息
	 */
	public void recordLoginInfo(SysUser user)
	{
		user.setLoginIp(ServletUtils.getClientIP());
		user.setLoginDate(DateUtils.getNowDate());
		user.setUpdateBy(user.getUserName());
		userService.updateUserProfile(user);
	}
}
