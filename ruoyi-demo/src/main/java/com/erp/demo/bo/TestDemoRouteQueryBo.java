package com.erp.demo.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

import com.diboot.core.binding.query.BindQuery;
import com.diboot.core.binding.query.Comparison;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;


/**
 * 测试单Route分页查询对象 test_demo
 *
 * @author erp
 * @date 2022-04-15
 */
@Data
@ApiModel("测试单Route分页查询对象")
public class TestDemoRouteQueryBo {

	/** 分页大小 */
	@ApiModelProperty("分页大小")
	@BindQuery(ignore = true)
	private Integer pageSize;
	/** 当前页数 */
	@ApiModelProperty("当前页数")
	@BindQuery(ignore = true)
	private Integer pageNum;


	/** 名称 */
	@ApiModelProperty("名称")
	@BindQuery(comparison = Comparison.LIKE)
	private String name;
	/** 状态 */
	@ApiModelProperty("状态")
	@BindQuery(comparison = Comparison.EQ)
	private String status;
	/** 出生日期 */
	@ApiModelProperty("出生日期")
	@BindQuery(comparison = Comparison.BETWEEN, field = "birthday")
	private String[] birthdayRange;

}
