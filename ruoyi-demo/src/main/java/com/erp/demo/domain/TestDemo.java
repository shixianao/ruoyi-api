package com.erp.demo.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;

/**
 * 测试单对象 test_demo
 *
 * @author erp
 * @date 2022-03-19
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@TableName("test_demo")
public class TestDemo implements Serializable {

    private static final long serialVersionUID=1L;


    /** 主键 */
    @TableId(value = "id")
    private Long id;

    /** 用户id */
    private Long userId;

    /** 名称 */
    private String name;

    /** 头像 */
    private String img;

    /** 状态（0正常 1停用） */
    private String status;

    /** 出生日期 */
    private Date birthday;

    /** 性别 */
    private String sex;

    /** 爱好 */
    private String love;

    /** 附件 */
    private String attachFiles;

    /** 简介 */
    private String info;

    /** 删除标志（0代表存在 2代表删除） */
    @TableLogic
    private String delFlag;

    /** 创建者 */
    @TableField(fill = FieldFill.INSERT)
    private String createBy;

    /** 创建时间 */
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    /** 更新者 */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String updateBy;

    /** 更新时间 */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    /** 备注 */
    private String remark;

}
