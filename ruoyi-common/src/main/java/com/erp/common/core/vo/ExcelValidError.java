package com.erp.common.core.vo;

import org.apache.commons.lang3.StringUtils;

import com.erp.common.annotation.Excel;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ExcelValidError {

	@Excel(name = "错误信息")
	@ApiModelProperty("导入失败的错误信息，此字段只在后端使用")
	private String errorMsg;
	
	public void addErrorMsg(String msg) {
		if (StringUtils.isEmpty(errorMsg)) {
			this.errorMsg = msg;
		} else {
			this.errorMsg = this.errorMsg + "," + msg;
		}
	}
}
