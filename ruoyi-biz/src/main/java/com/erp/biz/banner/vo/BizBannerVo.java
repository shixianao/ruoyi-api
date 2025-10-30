package com.erp.biz.banner.vo;

import com.erp.common.annotation.Excel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.util.Date;



/**
 * 轮播Banner视图对象 biz_banner
 *
 * @author erp
 * @date 2022-06-24
 */
@Data
@ApiModel("轮播Banner视图对象")
public class BizBannerVo {

	/** ID */
	@ApiModelProperty("ID")
	private Long id;

	/** 名称 */
	@Excel(name = "名称")
	@ApiModelProperty("名称")
	private String name;

	/** 链接 */
	@Excel(name = "链接")
	@ApiModelProperty("链接")
	private String url;

	/** 类型（p图片 v视频） */
	@Excel(name = "类型" , readConverterExp = "p=图片,v=视频")
	@ApiModelProperty("类型（p图片 v视频）")
	private String type;

	/** 显示顺序 */
	@Excel(name = "显示顺序")
	@ApiModelProperty("显示顺序")
	private Long sort;

	/** 状态（0正常 1停用） */
	@Excel(name = "状态" , readConverterExp = "0=正常,1=停用")
	@ApiModelProperty("状态（0正常 1停用）")
	private String status;

	/** 创建者 */
	@Excel(name = "创建者")
	@ApiModelProperty("创建者")
	private String createBy;

	/** 创建时间 */
	@Excel(name = "创建时间" , width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty("创建时间")
	private Date createTime;

	/** 更新者 */
	@Excel(name = "更新者")
	@ApiModelProperty("更新者")
	private String updateBy;

	/** 更新时间 */
	@Excel(name = "更新时间" , width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty("更新时间")
	private Date updateTime;

	/** 备注 */
	@Excel(name = "备注")
	@ApiModelProperty("备注")
	private String remark;


}
