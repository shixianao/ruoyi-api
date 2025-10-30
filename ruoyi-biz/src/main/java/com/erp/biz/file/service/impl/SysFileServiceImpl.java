package com.erp.biz.file.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.diboot.core.binding.Binder;
import com.erp.common.utils.PageUtils;
import com.erp.common.core.page.TableDataInfo;
import com.erp.common.core.service.impl.DiBaseServiceImpl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.diboot.core.binding.QueryBuilder;
import com.diboot.core.vo.Pagination;
import com.erp.biz.file.bo.SysFileAddBo;
import com.erp.biz.file.bo.SysFileQueryBo;
import com.erp.biz.file.bo.SysFileEditBo;
import com.erp.biz.file.domain.SysFile;
import com.erp.biz.file.mapper.SysFileMapper;
import com.erp.biz.file.vo.SysFileVo;
import com.erp.biz.file.service.ISysFileService;

import java.util.List;
import java.util.Collection;

/**
 * 文件管理Service业务层处理
 *
 * @author erp
 * @date 2023-11-22
 */
@Service
public class SysFileServiceImpl extends DiBaseServiceImpl<SysFileMapper, SysFile> implements ISysFileService {

    @Override
    public SysFileVo queryById(Long id){
        return getViewObject(id, SysFileVo.class);
    }

    @Override
    public TableDataInfo<SysFileVo> queryPageList(SysFileQueryBo bo) {
        @SuppressWarnings("unchecked")
        QueryWrapper<SysFileQueryBo> queryWrapper = QueryBuilder.toQueryWrapper(bo);
        queryWrapper.orderByDesc("id");
        Pagination pagination = PageUtils.buildDiPagination();
        List<SysFileVo> list = null;
        if (pagination.getPageSize() >= 1000) {
            List<SysFile> entityList = getEntityList(queryWrapper);
            list = Binder.convertAndBindRelations(entityList, SysFileVo.class);
        } else {
            list = getViewObjectList(queryWrapper, pagination, SysFileVo.class);
        }
        return PageUtils.buildDiDataInfo(list, pagination);
    }

    @Override
    public List<SysFileVo> queryList(SysFileQueryBo bo) {
        @SuppressWarnings("unchecked")
        QueryWrapper<SysFileQueryBo> queryWrapper = QueryBuilder.toQueryWrapper(bo);
        queryWrapper.orderByDesc("id");
        List<SysFile> entityList = getEntityList(queryWrapper);
        return BeanUtil.copyToList(entityList, SysFileVo.class, null);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long insertByAddBo(SysFileAddBo bo) {
        SysFile add = BeanUtil.toBean(bo, SysFile.class);
        validEntityBeforeSave(add);
        createEntity(add);
        return add.getId();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean updateByEditBo(SysFileEditBo bo) {
        SysFile update = BeanUtil.toBean(bo, SysFile.class);
        validEntityBeforeSave(update);
        return updateEntity(update);
    }

    /**
     * 保存前的数据校验
     *
     * @param entity 实体类数据
     */
    private void validEntityBeforeSave(SysFile entity){
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
