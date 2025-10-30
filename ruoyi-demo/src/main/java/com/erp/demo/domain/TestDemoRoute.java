package com.erp.demo.domain;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.erp.common.core.domain.DiBaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 测试单Route对象 test_demo
 *
 * @author erp
 * @date 2022-04-15
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@TableName("test_demo")
public class TestDemoRoute extends DiBaseEntity implements Serializable {

    private static final long serialVersionUID = -1;
    
    /** 用户id */
    private Long userId;
    /** 名称 */
    private String name;
    /** 头像 */
    private String img;
    /** 状态 */
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
    /** 删除标志 */
    @TableLogic
    private String delFlag;
    /** 备注 */
    private String remark;

    /** 审批流程ID **/
    private String processInstanceId;
    
    /** 审批通过时通过这个值修改对应ID的数据（0正常数据 -1表示新建审核 其它要求改的数据ID。只有审批单这个字段值才会大于0 */
    private Long processToChangeId;
    
    @Override
    public TestDemoRoute setId(Long id) {
        super.setId(id);
        return this;
    }
}
