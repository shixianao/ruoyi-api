package com.erp.biz.student.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.util.Date;
import javax.validation.constraints.*;


/**
 * 学生管理编辑对象 biz_student
 *
 * @author erp
 * @date 2025-10-30
 */
@Data
@ApiModel("学生管理编辑对象")
public class BizStudentEditBo {


    /** ID */
    @ApiModelProperty("ID")
    @NotNull(message = "ID不能为空")
    private Long id;

    /** 学生姓名 */
    @ApiModelProperty("学生姓名")
    @NotBlank(message = "学生姓名不能为空")
    private String studentName;

    /** 性别（0男 1女） */
    @ApiModelProperty("性别（0男 1女）")
    private String gender;

    /** 出生日期 */
    @ApiModelProperty("出生日期")
    private Date birthday;

    /** 班级ID */
    @ApiModelProperty("班级ID")
    @NotNull(message = "班级ID不能为空")
    private Long classId;

    /** 学号 */
    @ApiModelProperty("学号")
    private String studentNo;

    /** 联系方式 */
    @ApiModelProperty("联系方式")
    private String contact;

    /** 状态（0正常 1停用） */
    @ApiModelProperty("状态（0正常 1停用）")
    private String status;

    /** 备注 */
    @ApiModelProperty("备注")
    private String remark;

}