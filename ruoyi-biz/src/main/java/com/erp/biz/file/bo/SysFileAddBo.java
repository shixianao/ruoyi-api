package com.erp.biz.file.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.util.Date;
import javax.validation.constraints.*;



/**
 * 文件管理添加对象 sys_file
 *
 * @author erp
 * @date 2023-11-22
 */
@Data
@ApiModel("文件管理添加对象")
public class SysFileAddBo {


    /** 存储文件名 */
    @ApiModelProperty("存储文件名")
    private String fileKey;

    /** 大小 */
    @ApiModelProperty("大小")
    private Long fileSize;

    /** 文件类型 */
    @ApiModelProperty("文件类型")
    private String fileType;

    /** 文件名 */
    @ApiModelProperty("文件名")
    private String name;

    /** 原文件名 */
    @ApiModelProperty("原文件名")
    private String originalName;

    /** 路径 */
    @ApiModelProperty("路径")
    private String url;

    /** 备注 */
    @ApiModelProperty("备注")
    private String remark;
}
