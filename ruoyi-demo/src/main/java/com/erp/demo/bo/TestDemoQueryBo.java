package com.erp.demo.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.erp.common.core.domain.BaseEntity;

/**
 * 测试单分页查询对象 test_demo
 *
 * @author erp
 * @date 2022-03-19
 */

@Data
@ApiModel("测试单分页查询对象")
public class TestDemoQueryBo {

	/** 分页大小 */
	@ApiModelProperty("分页大小")
	private Integer pageSize;
	/** 当前页数 */
	@ApiModelProperty("当前页数")
	private Integer pageNum;


	/** 名称 */
	@ApiModelProperty("名称")
	private String name;
	/** 头像 */
	@ApiModelProperty("头像")
	private String img;
	/** 状态（0正常 1停用） */
	@ApiModelProperty("状态（0正常 1停用）")
	private String status;
	/** 出生日期 */
	@ApiModelProperty("出生日期")
	private Date birthday;
	/** 性别 */
	@ApiModelProperty("性别")
	private String sex;

}
