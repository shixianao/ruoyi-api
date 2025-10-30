package com.erp.biz.common.constant;

/**
 * 采购订单业务类型
 */
public enum ClPurchaseOrderTypeEnum {
	PURCHASE_IN("1","CG", "采购入库", "3")
	, PURCHASE_RETURN("2","CT", "采购退货", "4")
	,PURCHASE_HIS_CHANGE("3","CGD", "销售改单", "5")
	;

	/** 类型标记 */
	private String type;

	/** 订单标记 */
	private String orderFlag;

	/** 描述 */
	private String description;

	/** 账单类型 */
	private String billType;

	public String getType() {
		return type;
	}

	public String getOrderFlag() {
		return orderFlag;
	}

	public String getDescription() {
		return description;
	}

	public String getBillType() {
		return billType;
	}

	ClPurchaseOrderTypeEnum(String type, String orderFlag, String description, String billType) {
		this.type = type;
		this.orderFlag = orderFlag;
		this.description = description;
		this.billType = billType;
	}

	public static String getOrderFlagByType(String type){
		for (ClPurchaseOrderTypeEnum value : values()) {
			if (value.getType().equals(type)) {
				return value.getOrderFlag();
			}
		}
		return null;
	}

	public static String getBillTypeByType(String type){
		for (ClPurchaseOrderTypeEnum value : values()) {
			if (value.getType().equals(type)) {
				return value.getBillType();
			}
		}
		return null;
	}
}
