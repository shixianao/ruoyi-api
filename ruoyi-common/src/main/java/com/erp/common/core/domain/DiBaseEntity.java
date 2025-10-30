package com.erp.common.core.domain;

import java.util.Date;
import java.util.Map;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.TableField;
import com.diboot.core.config.Cons;
import com.diboot.core.entity.AbstractEntity;
import com.diboot.core.util.BeanUtils;
import com.diboot.core.util.ContextHelper;
import com.diboot.core.util.JSON;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * Entity基础父类
 *
 */
@Getter
@Setter
@Accessors(chain = true)
public abstract class DiBaseEntity extends AbstractEntity<Long> {
    private static final long serialVersionUID = 10203L;

    @Override
    public DiBaseEntity setId(Long id) {
        super.setId(id);
        return this;
    }

    @Override
    public Long getId() {
        return super.getId();
    }

    /**
     * 默认记录创建时间字段，新建时由数据库赋值
     */
    @TableField(insertStrategy = FieldStrategy.IGNORED, updateStrategy = FieldStrategy.NEVER, fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(insertStrategy = FieldStrategy.IGNORED, updateStrategy = FieldStrategy.IGNORED, fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    @TableField(insertStrategy = FieldStrategy.NOT_EMPTY, updateStrategy = FieldStrategy.NEVER, fill = FieldFill.INSERT)
    private String createBy;

    @TableField(insertStrategy = FieldStrategy.NOT_EMPTY, updateStrategy = FieldStrategy.NOT_NULL, fill = FieldFill.INSERT_UPDATE)
    private String updateBy;    /** 创建者 */

    /***
     * Entity对象转为map
     * @return
     */
    public Map<String, Object> toMap() {
        String jsonStr = JSON.stringify(this);
        return JSON.toMap(jsonStr);
    }

    /**
     * 获取主键值
     *
     * @return
     */
    @JsonIgnore
    public Object getPrimaryKeyVal() {
        String pk = ContextHelper.getIdFieldName(this.getClass());
        if (pk == null) {
            return null;
        }
        if (Cons.FieldName.id.name().equals(pk)) {
            return getId();
        }
        return BeanUtils.getProperty(this, pk);
    }
}