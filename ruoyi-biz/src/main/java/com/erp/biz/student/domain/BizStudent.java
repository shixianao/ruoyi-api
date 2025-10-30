package com.erp.biz.student.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.util.Date;
import com.erp.common.core.domain.DiBaseEntity;

/**
 * 学生管理对象 biz_student
 *
 * @author erp
 * @date 2025-10-30
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@TableName("biz_student")
public class BizStudent extends DiBaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 学生姓名 */
    private String studentName;
    /** 性别（0男 1女） */
    private String gender;
    /** 出生日期 */
    private Date birthday;
    /** 班级ID */
    private Long classId;
    /** 班级名称 */
    @TableField(exist = false)
    private String className;
    /** 学号 */
    private String studentNo;
    /** 联系方式 */
    private String contact;
    /** 状态（0正常 1停用） */
    private String status;
    /** 删除标志（0代表存在 2代表删除） */
    @TableLogic
    private String delFlag;
    /** 备注 */
    private String remark;
    
    @Override
    public BizStudent setId(Long id) {
        super.setId(id);
        return this;
    }
}