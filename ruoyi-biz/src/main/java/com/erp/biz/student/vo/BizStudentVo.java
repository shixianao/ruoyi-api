package com.erp.biz.student.vo;

import com.erp.common.annotation.Excel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.util.Date;

/**
 * 学生管理视图对象 biz_student
 *
 * @author erp
 * @date 2025-10-30
 */
@Data
@ApiModel("学生管理视图对象")
public class BizStudentVo {

    /** ID */
    @ApiModelProperty("ID")
    private Long id;

    /** 学生姓名 */
    @Excel(name = "学生姓名")
    @ApiModelProperty("学生姓名")
    private String studentName;

    /** 性别（0男 1女） */
    @Excel(name = "性别", readConverterExp = "0=男,1=女")
    @ApiModelProperty("性别（0男 1女）")
    private String gender;

    /** 出生日期 */
    @Excel(name = "出生日期", width = 30, dateFormat = "yyyy-MM-dd")
    @ApiModelProperty("出生日期")
    private Date birthday;

    /** 班级ID */
    @Excel(name = "班级ID")
    @ApiModelProperty("班级ID")
    private Long classId;

    /** 班级名称 */
    @Excel(name = "班级名称")
    @ApiModelProperty("班级名称")
    private String className;

    /** 学号 */
    @Excel(name = "学号")
    @ApiModelProperty("学号")
    private String studentNo;

    /** 联系方式 */
    @Excel(name = "联系方式")
    @ApiModelProperty("联系方式")
    private String contact;

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