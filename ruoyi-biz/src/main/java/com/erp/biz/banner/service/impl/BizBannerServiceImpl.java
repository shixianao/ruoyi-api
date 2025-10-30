package com.erp.biz.banner.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.erp.common.utils.PageUtils;
import com.erp.common.core.page.TableDataInfo;
import com.erp.common.core.service.impl.DiBaseServiceImpl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.diboot.core.binding.QueryBuilder;
import com.diboot.core.vo.Pagination;
import com.erp.biz.banner.bo.BizBannerAddBo;
import com.erp.biz.banner.bo.BizBannerQueryBo;
import com.erp.biz.banner.bo.BizBannerEditBo;
import com.erp.biz.banner.domain.BizBanner;
import com.erp.biz.banner.mapper.BizBannerMapper;
import com.erp.biz.banner.vo.BizBannerVo;
import com.erp.biz.banner.service.IBizBannerService;

import java.util.List;
import java.util.Collection;

/**
 * 轮播BannerService业务层处理
 *
 * @author erp
 * @date 2022-06-24
 */
@Service
public class BizBannerServiceImpl extends DiBaseServiceImpl<BizBannerMapper, BizBanner> implements IBizBannerService {

    @Override
    public BizBannerVo queryById(Long id){
        return getViewObject(id, BizBannerVo.class);
    }

    @Override
    public TableDataInfo<BizBannerVo> queryPageList(BizBannerQueryBo bo) {
        @SuppressWarnings("unchecked")
        QueryWrapper<BizBannerQueryBo> queryWrapper = QueryBuilder.toQueryWrapper(bo);
		queryWrapper.orderByAsc("sort");
        Pagination pagination = PageUtils.buildDiPagination();
		pagination.setOrderBy(null);
        List<BizBannerVo> list = getViewObjectList(queryWrapper, pagination, BizBannerVo.class);
        return PageUtils.buildDiDataInfo(list, pagination);
    }

    @Override
    public List<BizBannerVo> queryList(BizBannerQueryBo bo) {
        @SuppressWarnings("unchecked")
        QueryWrapper<BizBannerQueryBo> queryWrapper = QueryBuilder.toQueryWrapper(bo);
        Pagination pagination = PageUtils.buildDiPagination();
        List<BizBannerVo> list = getViewObjectList(queryWrapper, pagination, BizBannerVo.class);
        return list;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long insertByAddBo(BizBannerAddBo bo) {
        BizBanner add = BeanUtil.toBean(bo, BizBanner.class);
        validEntityBeforeSave(add);
        createEntity(add);
        return add.getId();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean updateByEditBo(BizBannerEditBo bo) {
        BizBanner update = BeanUtil.toBean(bo, BizBanner.class);
        validEntityBeforeSave(update);
        return updateEntity(update);
    }

    /**
     * 保存前的数据校验
     *
     * @param entity 实体类数据
     */
    private void validEntityBeforeSave(BizBanner entity){
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
