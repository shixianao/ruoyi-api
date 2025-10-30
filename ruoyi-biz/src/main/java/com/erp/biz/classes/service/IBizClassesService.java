package com.erp.biz.classes.service;

import com.erp.biz.classes.domain.BizClasses;
import com.erp.biz.classes.vo.BizClassesVo;
import com.erp.biz.classes.bo.BizClassesQueryBo;
import com.erp.biz.classes.bo.BizClassesAddBo;
import com.erp.biz.classes.bo.BizClassesEditBo;
import com.erp.common.core.service.DiBaseService;
import com.erp.common.core.page.TableDataInfo;

import java.util.Collection;
import java.util.List;

/**
 * 班级管理Service接口
 *
 * @author erp
 * @date 2025-10-30
 */
public interface IBizClassesService extends DiBaseService<BizClasses> {
    /**
     * 查询单个
     * @return
     */
    BizClassesVo queryById(Long id);

    /**
     * 查询列表
     */
    TableDataInfo<BizClassesVo> queryPageList(BizClassesQueryBo bo);

    /**
     * 查询列表
     */
    List<BizClassesVo> queryList(BizClassesQueryBo bo);

    /**
     * 根据新增业务对象插入班级管理
     * @param bo 班级管理新增业务对象
     * @return
     */
    Boolean insertByAddBo(BizClassesAddBo bo);

    /**
     * 根据编辑业务对象修改班级管理
     * @param bo 班级管理编辑业务对象
     * @return
     */
    Boolean updateByEditBo(BizClassesEditBo bo);

    /**
     * 批量删除班级管理
     * @param ids 主键集合
     * @return
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}