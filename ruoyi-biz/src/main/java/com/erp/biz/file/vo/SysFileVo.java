package com.erp.biz.file.vo;

import com.erp.common.annotation.Excel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.util.Date;



/**
 * 文件管理视图对象 sys_file
 *
 * @author erp
 * @date 2023-11-22
 */
@Data
@ApiModel("文件管理视图对象")
public class SysFileVo {

	/** ID */
	@ApiModelProperty("ID")
	private Long id;

	/** 存储文件名 */
	@Excel(name = "存储文件名")
	@ApiModelProperty("存储文件名")
	private String fileKey;

	/** 大小 */
	@Excel(name = "大小")
	@ApiModelProperty("大小")
	private Long fileSize;

	/** 文件类型 */
	@Excel(name = "文件类型")
	@ApiModelProperty("文件类型")
	private String fileType;

	/** 文件名 */
	@Excel(name = "文件名")
	@ApiModelProperty("文件名")
	private String name;

	/** 原文件名 */
	@Excel(name = "原文件名")
	@ApiModelProperty("原文件名")
	private String originalName;

	/** 路径 */
	@Excel(name = "路径")
	@ApiModelProperty("路径")
	private String url;

	/** 创建者 */
	@Excel(name = "创建者")
	@ApiModelProperty("创建者")
	private String createBy;

	/** 创建时间 */
	@Excel(name = "创建时间" , width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty("创建时间")
	private Date createTime;

	/** 备注 */
	@Excel(name = "备注")
	@ApiModelProperty("备注")
	private String remark;


}
