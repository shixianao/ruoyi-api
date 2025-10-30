package com.erp.common.core.page;

import com.erp.common.annotation.Excel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * 表格分页数据对象
 *
 * @author Lion Li
 */

@Data
@NoArgsConstructor
@Accessors(chain = true)
@ApiModel("分页响应对象")
public class BillOrderTableDataInfo<T> extends TableDataInfo<T> implements Serializable {
	private static final long serialVersionUID = 1L;

	/** 账单合计金额 */
	@Excel(name = "账单合计金额总计")
	@ApiModelProperty("账单合计金额总计")
	private BigDecimal tableTotalAmount;

	/** 应收金额 */
	@Excel(name = "应收金额总计")
	@ApiModelProperty("应收金额总计")
	private BigDecimal tableNeedAmount;

	/** 已收金额 */
	@Excel(name = "已收金额总计")
	@ApiModelProperty("已收金额总计")
	private BigDecimal tableReceivedAmount;


}
