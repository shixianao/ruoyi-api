package com.erp.web.controller.system;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.erp.system.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.erp.common.constant.Constants;
import com.erp.common.core.domain.AjaxResult;
import com.erp.common.core.domain.entity.SysMenu;
import com.erp.common.core.domain.entity.SysUser;
import com.erp.common.core.domain.model.LoginBody;
import com.erp.common.core.domain.model.LoginUser;
import com.erp.common.utils.ServletUtils;
import com.erp.framework.web.service.SysLoginService;
import com.erp.framework.web.service.SysPermissionService;
import com.erp.framework.web.service.TokenService;
import com.erp.system.service.ISysMenuService;

/**
 * 登录验证
 *
 * @author erp
 */
@RestController
public class SysLoginController
{
    @Autowired
    private SysLoginService loginService;

    @Autowired
    private ISysMenuService menuService;

    @Autowired
    private SysPermissionService permissionService;

    @Autowired
    private TokenService tokenService;

	@Autowired
	private ISysUserService userService;

    /**
	 * 短信验证码登录
     *
     * @param user 登录信息
     * @return 结果
     */
    @PostMapping("/loginBySmsCode")
    public AjaxResult loginBySmsCode(@RequestBody SysUser user)
    {
		Map<String,Object> ajax = new HashMap<>();
		// 生成令牌
		String token = loginService.loginByPhone(user);
		ajax.put(Constants.TOKEN, token);
		return AjaxResult.success(ajax);
    }

	/**
	 * openId登录
	 *
	 * @param loginBody 登录信息
	 * @return 结果
	 */
	@PostMapping("/loginByOpenId")
	public AjaxResult loginByOpenId(@RequestBody LoginBody loginBody)
	{
		Map<String,Object> ajax = new HashMap<>();
		// 生成令牌
		String token = loginService.loginByOpenId(loginBody.getUsername());
		if (null == token) {
			ajax.put(Constants.TOKEN, "");
		} else {
			ajax.put(Constants.TOKEN, token);
		}
		return AjaxResult.success(ajax);
	}

	/**
	 * 登录方法
	 *
	 * @param loginBody 登录信息
	 * @return 结果
	 */
	@PostMapping("/login")
	public AjaxResult login(@RequestBody LoginBody loginBody)
	{
		Map<String,Object> ajax = new HashMap<>();
		// 生成令牌
		String token = loginService.login(loginBody.getUsername(), loginBody.getPassword(), loginBody.getCode(),
			loginBody.getUuid());
		ajax.put(Constants.TOKEN, token);
		return AjaxResult.success(ajax);
	}

    /**
     * 获取用户信息
     *
     * @return 用户信息
     */
    @GetMapping("getInfo")
    public AjaxResult getInfo()
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        SysUser user = loginUser.getUser();
		if (null != user.getUserName()) {
			user = userService.selectUserByUserName(user.getUserName());
		}
        // 角色集合
        Set<String> roles = permissionService.getRolePermission(user);
        // 权限集合
        Set<String> permissions = permissionService.getMenuPermission(user);
		Map<String,Object> ajax = new HashMap<>();
        ajax.put("user", user);
        ajax.put("roles", roles);
        ajax.put("permissions", permissions);
        if (user.getMerchantId() != null) {
//        	ITSiteService siteService = SpringUtils.getBean(ITSiteService.class);
//        	TSite site = siteService.getById(user.getMerchantId());
//        	if (null != site) {
//                ajax.put("site", site);
//        	}
        }
//		IAccAccountService accountService = SpringUtil.getBean(IAccAccountService.class);
//		List<AccAccount> accountList = accountService.lambdaQuery().eq(AccAccount::getUserId, user.getUserId()).list();
//		ajax.put("accounts", accountList);
//		IAccAccountDetailService accountDetailService = SpringUtil.getBean(IAccAccountDetailService.class);
//		List<AccAccountDetail> accountDetailList = accountDetailService.lambdaQuery().eq(AccAccountDetail::getUserId, user.getUserId()).eq(AccAccountDetail::getType, "JF").eq(AccAccountDetail::getDirection, "OUT").list();
//		ajax.put("accountJfOutDetails", accountDetailList);
		return AjaxResult.success(ajax);
    }

    /**
     * 获取路由信息
     *
     * @return 路由信息
     */
    @GetMapping("getRouters")
    public AjaxResult getRouters()
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        // 用户信息
        SysUser user = loginUser.getUser();
        List<SysMenu> menus = menuService.selectMenuTreeByUserId(user.getUserId());
        return AjaxResult.success(menuService.buildMenus(menus));
    }
}
