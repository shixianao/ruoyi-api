package com.erp.biz.classes.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.util.Date;
import com.erp.common.core.domain.DiBaseEntity;

/**
 * 班级管理对象 biz_classes
 *
 * @author erp
 * @date 2025-10-30
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@TableName("biz_classes")
public class BizClasses extends DiBaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 班级名称 */
    private String className;
    /** 班级描述 */
    private String description;
    /** 班主任 */
    private String headTeacher;
    /** 班级人数 */
    private Integer studentCount;
    /** 状态（0正常 1停用） */
    private String status;
    /** 删除标志（0代表存在 2代表删除） */
    @TableLogic
    private String delFlag;
    /** 备注 */
    private String remark;
    
    @Override
    public BizClasses setId(Long id) {
        super.setId(id);
        return this;
    }
}