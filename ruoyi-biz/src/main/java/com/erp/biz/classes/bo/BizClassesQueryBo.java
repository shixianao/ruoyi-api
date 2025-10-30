package com.erp.biz.classes.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

import com.diboot.core.binding.query.BindQuery;
import com.diboot.core.binding.query.Comparison;



/**
 * 班级管理分页查询对象 biz_classes
 *
 * @author erp
 * @date 2025-10-30
 */
@Data
@ApiModel("班级管理分页查询对象")
public class BizClassesQueryBo {

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

    /** 班级名称 */
    @ApiModelProperty("班级名称")
    @BindQuery(comparison = Comparison.LIKE)
    private String className;

    /** 班级描述 */
    @ApiModelProperty("班级描述")
    @BindQuery(comparison = Comparison.LIKE)
    private String description;

    /** 班主任 */
    @ApiModelProperty("班主任")
    @BindQuery(comparison = Comparison.LIKE)
    private String headTeacher;

    /** 班级人数 */
    @ApiModelProperty("班级人数")
    @BindQuery(comparison = Comparison.EQ)
    private Integer studentCount;

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