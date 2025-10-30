package com.erp.biz.common.constant;

/**
 * 事件类型
 */
public enum EventTypeEnum {
	SCORE("0")
	,ADMIT("1")
	,STRATEGY("2")
	;

	private String type;

	public String getType() {
		return type;
	}

	EventTypeEnum(String type) {
		this.type = type;
	}
}
