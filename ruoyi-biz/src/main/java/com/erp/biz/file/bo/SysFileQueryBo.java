package com.erp.biz.file.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

import com.diboot.core.binding.query.BindQuery;
import com.diboot.core.binding.query.Comparison;



/**
 * 文件管理分页查询对象 sys_file
 *
 * @author erp
 * @date 2023-11-22
 */
@Data
@ApiModel("文件管理分页查询对象")
public class SysFileQueryBo {

	/** 分页大小 */
	@ApiModelProperty("分页大小")
	@BindQuery(ignore = true)
	private Integer pageSize;
	/** 当前页数 */
	@ApiModelProperty("当前页数")
	@BindQuery(ignore = true)
	private Integer pageNum;


	/** 存储文件名 */
	@ApiModelProperty("存储文件名")
	@BindQuery(comparison = Comparison.EQ)
	private String fileKey;
	/** 文件类型 */
	@ApiModelProperty("文件类型")
	@BindQuery(comparison = Comparison.EQ)
	private String fileType;
	/** 文件名 */
	@ApiModelProperty("文件名")
	@BindQuery(comparison = Comparison.LIKE)
	private String name;
	/** 原文件名 */
	@ApiModelProperty("原文件名")
	@BindQuery(comparison = Comparison.LIKE)
	private String originalName;

}
