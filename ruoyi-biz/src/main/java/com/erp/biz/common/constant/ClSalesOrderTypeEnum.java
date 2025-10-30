package com.erp.biz.common.constant;

/**
 * 销售订单业务类型
 */
public enum ClSalesOrderTypeEnum {
	SALES_OUT("1","XS", "销售出库", "1")
	,SALES_RETURN("2","XT", "销售退货", "2")
	,SALES_HIS_CHANGE("3","XGD", "销售改单", "3")
	,E_SALES_OUT("4","DSXS", "电商销售出库", "5")
	,E_SALES_RETURN("5","DSXT", "电商销售退货", "6")
	;

	/** 类型标记 */
	private String type;

	/** 订单标记 */
	private String orderFlag;

	/** 描述 */
	private String description;

	/** 收款单类型 */
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

	ClSalesOrderTypeEnum(String type, String orderFlag, String description, String billType) {
		this.type = type;
		this.orderFlag = orderFlag;
		this.description = description;
		this.billType = billType;
	}

	public static String getOrderFlagByType(String type){
		for (ClSalesOrderTypeEnum value : values()) {
			if (value.getType().equals(type)) {
				return value.getOrderFlag();
			}
		}
		return null;
	}

	public static String getBillTypeByType(String type){
		for (ClSalesOrderTypeEnum value : values()) {
			if (value.getType().equals(type)) {
				return value.getBillType();
			}
		}
		return null;
	}
}
