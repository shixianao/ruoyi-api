package com.erp.demo.vo;

import com.erp.common.annotation.Excel;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.util.Date;



/**
 * 测试单Route视图对象 test_demo
 *
 * @author erp
 * @date 2022-04-15
 */
@Data
@ApiModel("测试单Route视图对象")
public class TestDemoRouteVo {

	/** 主键 */
	@ApiModelProperty("主键")
	private Long id;

	/** 用户id */
	@Excel(name = "用户id")
	@ApiModelProperty("用户id")
	private Long userId;

	/** 名称 */
	@Excel(name = "名称")
	@ApiModelProperty("名称")
	private String name;

	/** 头像 */
	@Excel(name = "头像")
	@ApiModelProperty("头像")
	private String img;

	/** 状态 */
	@Excel(name = "状态")
	@ApiModelProperty("状态")
	private String status;

	/** 出生日期 */
	@Excel(name = "出生日期" , width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty("出生日期")
	private Date birthday;

	/** 性别 */
	@Excel(name = "性别")
	@ApiModelProperty("性别")
	private String sex;

	/** 爱好 */
	@Excel(name = "爱好")
	@ApiModelProperty("爱好")
	private String love;

	/** 附件 */
	@Excel(name = "附件")
	@ApiModelProperty("附件")
	private String attachFiles;

	/** 简介 */
	@Excel(name = "简介")
	@ApiModelProperty("简介")
	private String info;

	/** 备注 */
	@Excel(name = "备注")
	@ApiModelProperty("备注")
	private String remark;


}
