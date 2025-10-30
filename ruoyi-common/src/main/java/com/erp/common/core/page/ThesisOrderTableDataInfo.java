package com.erp.common.core.page;

import com.erp.common.annotation.Excel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 表格分页数据对象
 *
 * @author Lion Li
 */

@Data
@NoArgsConstructor
@Accessors(chain = true)
@ApiModel("分页响应对象")
public class ThesisOrderTableDataInfo<T> extends TableDataInfo<T> implements Serializable {
	private static final long serialVersionUID = 1L;

	/** 总付费金额 */
	@Excel(name = "总付费金额")
	@ApiModelProperty("总付费金额")
	private BigDecimal totalAmount;

	/** 学校付费金额 */
	@Excel(name = "学校付费金额")
	@ApiModelProperty("学校付费金额")
	private BigDecimal siteAmount;

	/** 用户付费金额 */
	@Excel(name = "用户付费金额")
	@ApiModelProperty("用户付费金额")
	private BigDecimal userAmount;


}
