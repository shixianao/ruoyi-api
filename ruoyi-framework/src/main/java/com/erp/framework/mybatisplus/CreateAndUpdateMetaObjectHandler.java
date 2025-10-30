package com.erp.framework.mybatisplus;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.erp.common.utils.SecurityUtils;
import com.erp.framework.security.util.AuthUtil;

import lombok.extern.slf4j.Slf4j;

import org.apache.ibatis.reflection.MetaObject;

import java.util.Date;

/**
 * MP注入处理器
 * @author Lion Li
 * @date 2021/4/25
 */
@Slf4j
public class CreateAndUpdateMetaObjectHandler implements MetaObjectHandler {

	@Override
	public void insertFill(MetaObject metaObject) {
		//根据属性名字设置要填充的值
		if (metaObject.hasGetter("createTime")) {
			if (metaObject.getValue("createTime") == null) {
				this.setFieldValByName("createTime", new Date(), metaObject);
			}
		}
		if (metaObject.hasGetter("createBy")) {
			if (metaObject.getValue("createBy") == null) {
				try {
					this.setFieldValByName("createBy", SecurityUtils.getUsername(), metaObject);
				} catch(Exception e) {
					log.error("SecurityUtils.getUsername", e);
				}
			}
		}
		if (metaObject.hasGetter("updateTime")) {
			if (metaObject.getValue("updateTime") == null) {
				this.setFieldValByName("updateTime", new Date(), metaObject);
			}
		}
		// 填充商户ID
		if (metaObject.hasGetter("merchantId")) {
			if (metaObject.getValue("merchantId") == null) {
				this.setFieldValByName("merchantId", AuthUtil.getMerchantIdOfCurrentUser(), metaObject);
			}
		}
	}

	@Override
	public void updateFill(MetaObject metaObject) {
		if (metaObject.hasGetter("updateBy")) {
			if (metaObject.getValue("updateBy") == null) {
				try {
					this.setFieldValByName("updateBy", SecurityUtils.getUsername(), metaObject);
				} catch(Exception e) {
					log.error("SecurityUtils.getUsername", e);
				}
			}
		}
		if (metaObject.hasGetter("updateTime")) {
			if (metaObject.getValue("updateTime") == null) {
				this.setFieldValByName("updateTime", new Date(), metaObject);
			}
		}
		// 填充商户ID
		if (metaObject.hasGetter("merchantId")) {
			if (metaObject.getValue("merchantId") == null) {
				this.setFieldValByName("merchantId", AuthUtil.getMerchantIdOfCurrentUser(), metaObject);
			}
		}
	}

}
