package com.erp.biz.student.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

import com.diboot.core.binding.query.BindQuery;
import com.diboot.core.binding.query.Comparison;



/**
 * 学生管理分页查询对象 biz_student
 *
 * @author erp
 * @date 2025-10-30
 */
@Data
@ApiModel("学生管理分页查询对象")
public class BizStudentQueryBo {

    /** 分页大小 */
    @ApiModelProperty("分页大小")
    @BindQuery(ignore = true)
    private Integer pageSize;
    /** 当前页数 */
    @ApiModelProperty("当前页数")
    @BindQuery(ignore = true)
    private Integer pageNum;
    /** 排序列 */
    @ApiModelProperty("排序列")
    @BindQuery(ignore = true)
    private String orderByColumn;
    /** 排序的方向desc或者asc */
    @ApiModelProperty("排序的方向desc或者asc")
    @BindQuery(ignore = true)
    private String isAsc;

    /** ID */
    @ApiModelProperty("ID")
    @BindQuery(comparison = Comparison.EQ)
    private Long id;

    /** 学生姓名 */
    @ApiModelProperty("学生姓名")
    @BindQuery(comparison = Comparison.LIKE)
    private String studentName;

    /** 性别（0男 1女） */
    @ApiModelProperty("性别（0男 1女）")
    @BindQuery(comparison = Comparison.EQ)
    private String gender;

    /** 出生日期 */
    @ApiModelProperty("出生日期")
    @BindQuery(comparison = Comparison.BETWEEN)
    private Date[] birthday;

    /** 班级ID */
    @ApiModelProperty("班级ID")
    @BindQuery(comparison = Comparison.EQ)
    private Long classId;

    /** 班级名称 */
    @ApiModelProperty("班级名称")
    @BindQuery(comparison = Comparison.LIKE)
    private String className;

    /** 学号 */
    @ApiModelProperty("学号")
    @BindQuery(comparison = Comparison.LIKE)
    private String studentNo;

    /** 联系方式 */
    @ApiModelProperty("联系方式")
    @BindQuery(comparison = Comparison.LIKE)
    private String contact;

    /** 状态（0正常 1停用） */
    @ApiModelProperty("状态（0正常 1停用）")
    @BindQuery(comparison = Comparison.EQ)
    private String status;

    /** 创建时间 */
    @ApiModelProperty("创建时间")
    @BindQuery(comparison = Comparison.BETWEEN)
    private Date[] createTime;

    /** 备注 */
    @ApiModelProperty("备注")
    @BindQuery(comparison = Comparison.LIKE)
    private String remark;

}