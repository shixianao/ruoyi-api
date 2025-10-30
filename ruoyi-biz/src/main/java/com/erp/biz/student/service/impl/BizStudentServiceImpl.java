package com.erp.biz.student.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.erp.common.utils.PageUtils;
import com.erp.common.core.page.TableDataInfo;
import com.erp.common.core.service.impl.DiBaseServiceImpl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.diboot.core.binding.QueryBuilder;
import com.diboot.core.vo.Pagination;
import com.erp.biz.student.bo.BizStudentAddBo;
import com.erp.biz.student.bo.BizStudentQueryBo;
import com.erp.biz.student.bo.BizStudentEditBo;
import com.erp.biz.student.domain.BizStudent;
import com.erp.biz.student.mapper.BizStudentMapper;
import com.erp.biz.student.vo.BizStudentVo;
import com.erp.biz.student.service.IBizStudentService;
import com.erp.biz.classes.domain.BizClasses;
import com.erp.biz.classes.mapper.BizClassesMapper;

import java.util.List;
import java.util.Collection;
import java.util.stream.Collectors;

/**
 * 学生管理Service业务层处理
 *
 * @author erp
 * @date 2025-10-30
 */
@Service
public class BizStudentServiceImpl extends DiBaseServiceImpl<BizStudentMapper, BizStudent> implements IBizStudentService {

    private final BizClassesMapper bizClassesMapper;

    public BizStudentServiceImpl(BizClassesMapper bizClassesMapper) {
        this.bizClassesMapper = bizClassesMapper;
    }

    @Override
    public BizStudentVo queryById(Long id){
        BizStudentVo studentVo = getViewObject(id, BizStudentVo.class);
        if (studentVo != null && studentVo.getClassId() != null) {
            BizClasses classes = bizClassesMapper.selectById(studentVo.getClassId());
            if (classes != null) {
                studentVo.setClassName(classes.getClassName());
            }
        }
        return studentVo;
    }

    @Override
    public TableDataInfo<BizStudentVo> queryPageList(BizStudentQueryBo bo) {
        @SuppressWarnings("unchecked")
        QueryWrapper<BizStudentQueryBo> queryWrapper = QueryBuilder.toQueryWrapper(bo);
        queryWrapper.orderByAsc("create_time");
        Pagination pagination = PageUtils.buildDiPagination();
        pagination.setOrderBy(null);
        List<BizStudentVo> list = getViewObjectList(queryWrapper, pagination, BizStudentVo.class);
        return PageUtils.buildDiDataInfo(list, pagination);
    }

    @Override
    public List<BizStudentVo> queryList(BizStudentQueryBo bo) {
        @SuppressWarnings("unchecked")
        QueryWrapper<BizStudentQueryBo> queryWrapper = QueryBuilder.toQueryWrapper(bo);
        queryWrapper.orderByAsc("create_time");
        Pagination pagination = PageUtils.buildDiPagination();
        List<BizStudentVo> list = getViewObjectList(queryWrapper, pagination, BizStudentVo.class);
        
        // 获取所有班级ID
        List<Long> classIds = list.stream()
                .map(BizStudentVo::getClassId)
                .filter(classId -> classId != null)
                .distinct()
                .collect(Collectors.toList());
                
        // 批量查询班级信息
        List<BizClasses> classesList = bizClassesMapper.selectBatchIds(classIds);
                
        // 填充班级名称
        list.forEach(student -> {
            if (student.getClassId() != null) {
                BizClasses classes = classesList.stream()
                        .filter(c -> c.getId().equals(student.getClassId()))
                        .findFirst()
                        .orElse(null);
                if (classes != null) {
                    student.setClassName(classes.getClassName());
                }
            }
        });
                
        return list;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean insertByAddBo(BizStudentAddBo bo) {
        BizStudent add = BeanUtil.toBean(bo, BizStudent.class);
        validEntityBeforeSave(add);
        return createEntity(add);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean updateByEditBo(BizStudentEditBo bo) {
        BizStudent update = BeanUtil.toBean(bo, BizStudent.class);
        validEntityBeforeSave(update);
        return updateEntity(update);
    }

    /**
     * 保存前的数据校验
     *
     * @param entity 实体类数据
     */
    private void validEntityBeforeSave(BizStudent entity){
        //TODO 做一些数据校验,如唯一约束
    }

    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){//TODO 做一些业务上的校验,判断是否需要校验
        }
        return removeByIds(ids);
    }
}