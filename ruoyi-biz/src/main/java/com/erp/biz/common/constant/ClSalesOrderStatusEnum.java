package com.erp.biz.common.constant;

/**
 * 销售订单状态
 */
public enum ClSalesOrderStatusEnum {
	DRAFT("10","草稿")
	,WAIT_APPROVE("20","待审核")
	,REJECT_APPROVE("30","审核驳回")
	,COMPLETE("40","审核通过")
	,HIS_CHANGE("50","改单")
	;

	private String status;

	private String description;

	public String getStatus() {
		return status;
	}

	public String getDescription() {
		return description;
	}

	ClSalesOrderStatusEnum(String status, String description) {
		this.status = status;
		this.description = description;
	}

	public static String getDescriptionByStatus(String status){
		for (ClSalesOrderStatusEnum value : values()) {
			if (value.getStatus().equals(status)) {
				return value.getDescription();
			}
		}
		return null;
	}
}
