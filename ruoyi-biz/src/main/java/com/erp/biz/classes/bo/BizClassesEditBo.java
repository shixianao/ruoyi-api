package com.erp.biz.classes.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.util.Date;
import javax.validation.constraints.*;


/**
 * 班级管理编辑对象 biz_classes
 *
 * @author erp
 * @date 2025-10-30
 */
@Data
@ApiModel("班级管理编辑对象")
public class BizClassesEditBo {


    /** ID */
    @ApiModelProperty("ID")
    @NotNull(message = "ID不能为空")
    private Long id;

    /** 班级名称 */
    @ApiModelProperty("班级名称")
    @NotBlank(message = "班级名称不能为空")
    private String className;

    /** 班级描述 */
    @ApiModelProperty("班级描述")
    private String description;

    /** 班主任 */
    @ApiModelProperty("班主任")
    private String headTeacher;

    /** 班级人数 */
    @ApiModelProperty("班级人数")
    private Integer studentCount;

    /** 状态（0正常 1停用） */
    @ApiModelProperty("状态（0正常 1停用）")
    private String status;

    /** 备注 */
    @ApiModelProperty("备注")
    private String remark;

}