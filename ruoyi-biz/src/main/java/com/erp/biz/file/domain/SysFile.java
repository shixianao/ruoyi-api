package com.erp.biz.file.domain;

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
 * 文件管理对象 sys_file
 *
 * @author erp
 * @date 2023-11-22
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@TableName("sys_file")
public class SysFile extends DiBaseEntity implements Serializable {

    private static final long serialVersionUID = -1;

    /** 删除标志（0代表存在 2代表删除） */
    @TableLogic
    private String delFlag;
    /** 存储文件名 */
    private String fileKey;
    /** 大小 */
    private Long fileSize;
    /** 文件类型 */
    private String fileType;
    /** 文件名 */
    private String name;
    /** 原文件名 */
    private String originalName;
    /** 路径 */
    private String url;
    /** 备注 */
    private String remark;
    
    @Override
    public SysFile setId(Long id) {
        super.setId(id);
        return this;
    }
}
