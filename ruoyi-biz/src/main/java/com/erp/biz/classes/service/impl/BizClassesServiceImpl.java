package com.erp.biz.classes.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.erp.common.utils.PageUtils;
import com.erp.common.core.page.TableDataInfo;
import com.erp.common.core.service.impl.DiBaseServiceImpl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.diboot.core.binding.QueryBuilder;
import com.diboot.core.vo.Pagination;
import com.erp.biz.classes.bo.BizClassesAddBo;
import com.erp.biz.classes.bo.BizClassesQueryBo;
import com.erp.biz.classes.bo.BizClassesEditBo;
import com.erp.biz.classes.domain.BizClasses;
import com.erp.biz.classes.mapper.BizClassesMapper;
import com.erp.biz.classes.vo.BizClassesVo;
import com.erp.biz.classes.service.IBizClassesService;

import java.util.List;
import java.util.Collection;

/**
 * 班级管理Service业务层处理
 *
 * @author erp
 * @date 2025-10-30
 */
@Service
public class BizClassesServiceImpl extends DiBaseServiceImpl<BizClassesMapper, BizClasses> implements IBizClassesService {

    @Override
    public BizClassesVo queryById(Long id){
        return getViewObject(id, BizClassesVo.class);
    }

    @Override
    public TableDataInfo<BizClassesVo> queryPageList(BizClassesQueryBo bo) {
        @SuppressWarnings("unchecked")
        QueryWrapper<BizClassesQueryBo> queryWrapper = QueryBuilder.toQueryWrapper(bo);
        queryWrapper.orderByAsc("create_time");
        Pagination pagination = PageUtils.buildDiPagination();
        pagination.setOrderBy(null);
        List<BizClassesVo> list = getViewObjectList(queryWrapper, pagination, BizClassesVo.class);
        return PageUtils.buildDiDataInfo(list, pagination);
    }

    @Override
    public List<BizClassesVo> queryList(BizClassesQueryBo bo) {
        @SuppressWarnings("unchecked")
        QueryWrapper<BizClassesQueryBo> queryWrapper = QueryBuilder.toQueryWrapper(bo);
        queryWrapper.orderByAsc("create_time");
        Pagination pagination = PageUtils.buildDiPagination();
        List<BizClassesVo> list = getViewObjectList(queryWrapper, pagination, BizClassesVo.class);
        return list;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean insertByAddBo(BizClassesAddBo bo) {
        BizClasses add = BeanUtil.toBean(bo, BizClasses.class);
        validEntityBeforeSave(add);
        return createEntity(add);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean updateByEditBo(BizClassesEditBo bo) {
        BizClasses update = BeanUtil.toBean(bo, BizClasses.class);
        validEntityBeforeSave(update);
        return updateEntity(update);
    }

    /**
     * 保存前的数据校验
     *
     * @param entity 实体类数据
     */
    private void validEntityBeforeSave(BizClasses entity){
        //TODO 做一些数据校验,如唯一约束
    }

    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return removeByIds(ids);
    }
}
