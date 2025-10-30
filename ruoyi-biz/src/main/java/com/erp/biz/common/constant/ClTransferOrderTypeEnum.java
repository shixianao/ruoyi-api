package com.erp.biz.common.constant;

/**
 * 采购订单业务类型
 */
public enum ClTransferOrderTypeEnum {
	TRANSFER("1","DB", "调拨")
	;

	/** 类型标记 */
	private String type;

	/** 订单标记 */
	private String orderFlag;

	/** 描述 */
	private String description;

	public String getType() {
		return type;
	}

	public String getOrderFlag() {
		return orderFlag;
	}

	public String getDescription() {
		return description;
	}

	ClTransferOrderTypeEnum(String type, String orderFlag, String description) {
		this.type = type;
		this.orderFlag = orderFlag;
		this.description = description;
	}

	public static String getOrderFlagByType(String type){
		for (ClTransferOrderTypeEnum value : values()) {
			if (value.getType().equals(type)) {
				return value.getOrderFlag();
			}
		}
		return null;
	}
}
