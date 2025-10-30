package com.erp.biz.common.constant;

/**
 * 调拨订单状态
 */
public enum ClTransferOrderStatusEnum {
	DRAFT("10","草稿")
	,WAIT_APPROVE("20","待审核")
	,REJECT_APPROVE("30","审核驳回")
	,COMPLETE("40","审核通过")
	;

	private String status;

	private String description;

	public String getStatus() {
		return status;
	}

	public String getDescription() {
		return description;
	}

	ClTransferOrderStatusEnum(String status, String description) {
		this.status = status;
		this.description = description;
	}

	public static String getDescriptionByStatus(String status){
		for (ClTransferOrderStatusEnum value : values()) {
			if (value.getStatus().equals(status)) {
				return value.getDescription();
			}
		}
		return null;
	}
}
