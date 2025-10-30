package com.erp.biz.common.constant;

/**
 * @ClassName HqMerchantConstant.java
 * @Description 商户常量类
 */
public class HqMerchantConstants {

	/**
	 * 商户账号状态 1:开户 0:未开户
	 */
	public static final String HQ_MERCHANT_ACCOUNT_STATUS_OPEN = "1";

	/**
	 * 商户账号状态 1:开户 0:未开户
	 */
	public static final String HQ_MERCHANT_ACCOUNT_STATUS_CLOSE = "0";


	/**
	 * 商户状态 1:启用 2:停用 3:已过期
	 */
	public static final String HQ_MERCHANT_STATUS_ENABLED = "1";

	/**
	 * 商户状态 1:启用 2:停用 3:已过期
	 */
	public static final String HQ_MERCHANT_STATUS_DISABLED = "2";

	/**
	 * 商户状态 1:启用 2:停用 3:已过期
	 */
	public static final String HQ_MERCHANT_STATUS_EXPIRED = "3";

	/**
	 * 商户管理员账号 admin_ 前缀
	 */
	public static final String HQ_MERCHANT_ADMIN_ACCOUNT_PREFIX = "admin_";

	/**
	 * 商户管理员账号 nickname admin
	 */
	public static final String HQ_MERCHANT_ADMIN_NICKNAME = "admin";


	/**
	 * 商户管理员账号 userType 01:总部端管理员
	 */
	public static final String HQ_MERCHANT_USER_TYPE_ADMIN = "02";
	
	/**
	 *  userType 00:普通用户
	*/
	public static final String HQ_MERCHANT_USER_TYPE_COMMON = "00";

	/**
	 * 商户管理员账号 userType 01:总部端管理员
	 */
	public static final String HQ_MERCHANT_USER_SEX_UNKNOWN = "2";

	/**
	 * 商户管理员 角色id
	 */
	public static final Long HQ_MERCHANT_ADMIN_ROLE_ID = 1L;
}
