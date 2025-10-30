package com.erp.biz.banner.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

import com.diboot.core.binding.query.BindQuery;
import com.diboot.core.binding.query.Comparison;



/**
 * 轮播Banner分页查询对象 biz_banner
 *
 * @author erp
 * @date 2022-06-24
 */
@Data
@ApiModel("轮播Banner分页查询对象")
public class BizBannerQueryBo {

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
	/** 类型（p图片 v视频） */
	@ApiModelProperty("类型（p图片 v视频）")
	@BindQuery(comparison = Comparison.EQ)
	private String type;
	/** 显示顺序 */
	@ApiModelProperty("显示顺序")
	@BindQuery(comparison = Comparison.EQ)
	private Long sort;
	/** 状态（0正常 1停用） */
	@ApiModelProperty("状态（0正常 1停用）")
	@BindQuery(comparison = Comparison.EQ)
	private String status;

}
