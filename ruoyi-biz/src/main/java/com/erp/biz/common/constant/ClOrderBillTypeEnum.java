package com.erp.biz.common.constant;

/**
 * 收付款业务类型
 */
public enum ClOrderBillTypeEnum {
	SALES_OUT("1", "销售出库")
	,SALES_RETURN("2", "销售退货")
	,PURCHASE_IN("3", "采购入库")
	,PURCHASE_RETURN("4", "采购退货")
	,E_SALES_OUT("5", "销售出库")
	,E_SALES_RETURN("6", "销售退货")
	;

	/** 收款单类型 */
	private String billType;

	/** 描述 */
	private String description;


	public String getDescription() {
		return description;
	}

	public String getBillType() {
		return billType;
	}

	ClOrderBillTypeEnum(String billType, String description) {
		this.billType = billType;
		this.description = description;
	}

}
