package com.erp.biz.common.constant;

/**
 * 收款/付款订单状态
 */
public enum ClOrderBillStatusEnum {
	WAIT_PAY("10","待收款/付款")
	,PART_PAY("20","部分收款/付款")
	,COMPLETE_PAY("30","全部收款/付款")
	;

	private String status;

	private String description;

	public String getStatus() {
		return status;
	}

	public String getDescription() {
		return description;
	}

	ClOrderBillStatusEnum(String status, String description) {
		this.status = status;
		this.description = description;
	}

	public static String getDescriptionByStatus(String status){
		for (ClOrderBillStatusEnum value : values()) {
			if (value.getStatus().equals(status)) {
				return value.getDescription();
			}
		}
		return null;
	}
}
