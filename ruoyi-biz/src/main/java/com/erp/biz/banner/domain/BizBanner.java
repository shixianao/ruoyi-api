package com.erp.biz.banner.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;
import com.erp.common.core.domain.DiBaseEntity;

/**
 * 轮播Banner对象 biz_banner
 *
 * @author erp
 * @date 2022-06-24
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@TableName("biz_banner")
public class BizBanner extends DiBaseEntity implements Serializable {

    private static final long serialVersionUID = -1;

    /** 名称 */
    private String name;
    /** 链接 */
    private String url;
    /** 类型（p图片 v视频） */
    private String type;
    /** 显示顺序 */
    private Long sort;
    /** 状态（0正常 1停用） */
    private String status;
    /** 删除标志（0代表存在 2代表删除） */
    @TableLogic
    private String delFlag;
    /** 备注 */
    private String remark;
    
    @Override
    public BizBanner setId(Long id) {
        super.setId(id);
        return this;
    }
}
