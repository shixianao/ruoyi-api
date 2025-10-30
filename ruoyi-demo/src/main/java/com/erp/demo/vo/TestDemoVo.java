package com.erp.demo.vo;

import com.erp.common.annotation.Excel;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.util.Date;



/**
 * 测试单视图对象 test_demo
 *
 * @author erp
 * @date 2022-03-19
 */
@Data
@ApiModel("测试单视图对象")
public class TestDemoVo {

	private static final long serialVersionUID = 1L;

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

	/** 状态（0正常 1停用） */
	@Excel(name = "状态" , readConverterExp = "0=正常,1=停用")
	@ApiModelProperty("状态（0正常 1停用）")
	private String status;

	/** 出生日期 */
	@Excel(name = "出生日期" , width = 30, dateFormat = "yyyy-MM-dd")
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


	public boolean isEmpty() {
		if (StringUtils.isNotEmpty(this.name)) {
			return false;
		}
		if (null != this.birthday) {
			return false;
		}
		if (StringUtils.isNotEmpty(this.status)) {
			return false;
		}
		if (StringUtils.isNotEmpty(this.remark)) {
			return false;
		}
		return true;
	}


}
