package com.erp.biz.classes.vo;

import com.erp.common.annotation.Excel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.util.Date;

/**
 * 班级管理视图对象 biz_classes
 *
 * @author erp
 * @date 2025-10-30
 */
@Data
@ApiModel("班级管理视图对象")
public class BizClassesVo {

    /** ID */
    @ApiModelProperty("ID")
    private Long id;

    /** 班级名称 */
    @Excel(name = "班级名称")
    @ApiModelProperty("班级名称")
    private String className;

    /** 班级描述 */
    @Excel(name = "班级描述")
    @ApiModelProperty("班级描述")
    private String description;

    /** 班主任 */
    @Excel(name = "班主任")
    @ApiModelProperty("班主任")
    private String headTeacher;

    /** 班级人数 */
    @Excel(name = "班级人数")
    @ApiModelProperty("班级人数")
    private Integer studentCount;

    /** 状态（0正常 1停用） */
    @Excel(name = "状态", readConverterExp = "0=正常,1=停用")
    @ApiModelProperty("状态（0正常 1停用）")
    private String status;

    /** 创建时间 */
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty("创建时间")
    private Date createTime;

    /** 更新时间 */
    @Excel(name = "更新时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty("更新时间")
    private Date updateTime;

    /** 备注 */
    @Excel(name = "备注")
    @ApiModelProperty("备注")
    private String remark;
}