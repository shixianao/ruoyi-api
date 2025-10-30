package com.erp.common.enums;

import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

public enum ThesisOrderStatus {

	SITE_CHECK(10, "待学校审核")
	,SITE_REJECT(15, "学校审核驳回")
	,SITE_AGREE(20, "学校审核通过")
	,PLAT_APPLY(30, "待审核分配")
	,PLAT_REJECT(35, "主编审核驳回")
	,POLISHING(40, "批改中")
	,USER_CHECK(50, "用户确认")
	,DONE(100, "已完结")
	;

	public String getDescByCode(Integer code){
		for (ThesisOrderStatus value : values()) {
			if (Objects.equals(value.getCode(), code)) {
				return value.getDesc();
			}
		}
		return StringUtils.EMPTY;
	}

	private final Integer code;
	private final String desc;

	ThesisOrderStatus(Integer code, String desc)
	{
		this.code = code;
		this.desc = desc;
	}

	public Integer getCode()
	{
		return code;
	}

	public String getDesc()
	{
		return desc;
	}
}
