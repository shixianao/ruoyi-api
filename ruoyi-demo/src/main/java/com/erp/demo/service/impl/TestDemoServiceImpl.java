package com.erp.demo.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.text.CharSequenceUtil;
import com.erp.common.utils.PageUtils;
import com.erp.demo.bo.TestDemoAddBo;
import com.erp.demo.bo.TestDemoEditBo;
import com.erp.demo.bo.TestDemoQueryBo;
import com.erp.demo.domain.TestDemo;
import com.erp.demo.mapper.TestDemoMapper;
import com.erp.demo.service.ITestDemoService;
import com.erp.demo.vo.TestDemoVo;
import com.erp.common.core.page.PagePlus;
import com.erp.common.core.page.TableDataInfo;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 测试单Service业务层处理
 *
 * @author erp
 * @date 2022-03-19
 */
@Service
public class TestDemoServiceImpl extends ServiceImpl<TestDemoMapper, TestDemo> implements ITestDemoService {

    @Override
    public TestDemoVo queryById(Long id){
        return getVoById(id, TestDemoVo.class);
    }

    @Override
    public TableDataInfo<TestDemoVo> queryPageList(TestDemoQueryBo bo) {
        PagePlus<TestDemo, TestDemoVo> result = pageVo(PageUtils.buildPagePlus(), buildQueryWrapper(bo), TestDemoVo.class);
        return PageUtils.buildDataInfo(result);
    }

    @Override
    public List<TestDemoVo> queryList(TestDemoQueryBo bo) {
        return listVo(buildQueryWrapper(bo), TestDemoVo.class);
    }

    private LambdaQueryWrapper<TestDemo> buildQueryWrapper(TestDemoQueryBo bo) {
        LambdaQueryWrapper<TestDemo> lqw = Wrappers.lambdaQuery();
        lqw.like(CharSequenceUtil.isNotBlank(bo.getName()), TestDemo::getName, bo.getName());
        lqw.eq(CharSequenceUtil.isNotBlank(bo.getImg()), TestDemo::getImg, bo.getImg());
        lqw.eq(CharSequenceUtil.isNotBlank(bo.getStatus()), TestDemo::getStatus, bo.getStatus());
//        lqw.between(params.get("beginBirthday") != null && params.get("endBirthday") != null,
//            TestDemo::getBirthday ,params.get("beginBirthday"), params.get("endBirthday"));
        lqw.eq(CharSequenceUtil.isNotBlank(bo.getSex()), TestDemo::getSex, bo.getSex());
        return lqw;
    }

    @Override
    public Boolean insertByAddBo(TestDemoAddBo bo) {
        TestDemo add = BeanUtil.toBean(bo, TestDemo.class);
        validEntityBeforeSave(add);
        return save(add);
    }

    @Override
    public Boolean updateByEditBo(TestDemoEditBo bo) {
        TestDemo update = BeanUtil.toBean(bo, TestDemo.class);
        validEntityBeforeSave(update);
        return updateById(update);
    }

    /**
     * 保存前的数据校验
     *
     * @param entity 实体类数据
     */
    private void validEntityBeforeSave(TestDemo entity){
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
