package com.erp.biz.banner.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.util.Date;
import javax.validation.constraints.*;


/**
 * 轮播Banner编辑对象 biz_banner
 *
 * @author erp
 * @date 2022-06-24
 */
@Data
@ApiModel("轮播Banner编辑对象")
public class BizBannerEditBo {


    /** ID */
    @ApiModelProperty("ID")
    private Long id;

    /** 名称 */
    @ApiModelProperty("名称")
    private String name;

    /** 链接 */
    @ApiModelProperty("链接")
    private String url;

    /** 类型（p图片 v视频） */
    @ApiModelProperty("类型（p图片 v视频）")
    private String type;

    /** 显示顺序 */
    @ApiModelProperty("显示顺序")
    private Long sort;

    /** 状态（0正常 1停用） */
    @ApiModelProperty("状态（0正常 1停用）")
    private String status;

    /** 备注 */
    @ApiModelProperty("备注")
    private String remark;
}
