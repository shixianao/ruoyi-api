package com.erp.demo.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.erp.common.utils.PageUtils;
import com.erp.common.core.page.TableDataInfo;
import com.erp.common.core.service.impl.DiBaseServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.diboot.core.binding.QueryBuilder;
import com.diboot.core.vo.Pagination;
import com.erp.demo.bo.TestDemoRouteAddBo;
import com.erp.demo.bo.TestDemoRouteQueryBo;
import com.erp.demo.bo.TestDemoRouteEditBo;
import com.erp.demo.domain.TestDemoRoute;
import com.erp.demo.mapper.TestDemoRouteMapper;
import com.erp.demo.vo.TestDemoRouteVo;
import com.erp.framework.security.util.AuthUtil;
import com.erp.module.bpm.api.task.BpmProcessInstanceApi;
import com.erp.module.bpm.api.task.dto.BpmProcessInstanceCreateReqDTO;
import com.erp.demo.service.ITestDemoRouteService;

import java.util.List;
import java.util.Collection;

/**
 * 测试单RouteService业务层处理
 *
 * @author erp
 * @date 2022-04-15
 */
@Service
public class TestDemoRouteServiceImpl extends DiBaseServiceImpl<TestDemoRouteMapper, TestDemoRoute>
		implements ITestDemoRouteService {

	public static final String PROCESS_KEY = "demoRoute";

	@Autowired
	private BpmProcessInstanceApi bpmProcessInstanceApi;

	@Override
	public TestDemoRouteVo queryById(Long id) {
		return getViewObject(id, TestDemoRouteVo.class);
	}

	@Override
	public TableDataInfo<TestDemoRouteVo> queryPageList(TestDemoRouteQueryBo bo) {
		@SuppressWarnings("unchecked")
		QueryWrapper<TestDemoRouteQueryBo> queryWrapper = QueryBuilder.toQueryWrapper(bo);
		queryWrapper.eq("process_to_change_id", 0L);
		Pagination pagination = PageUtils.buildDiPagination();
		List<TestDemoRouteVo> list = getViewObjectList(queryWrapper, pagination, TestDemoRouteVo.class);
		return PageUtils.buildDiDataInfo(list, pagination);
	}

	@Override
	public List<TestDemoRouteVo> queryList(TestDemoRouteQueryBo bo) {
		@SuppressWarnings("unchecked")
		QueryWrapper<TestDemoRouteQueryBo> queryWrapper = QueryBuilder.toQueryWrapper(bo);
		queryWrapper.eq("process_to_change_id", 0L);
		Pagination pagination = PageUtils.buildDiPagination();
		List<TestDemoRouteVo> list = getViewObjectList(queryWrapper, pagination, TestDemoRouteVo.class);
		return list;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public Boolean insertByAddBo(TestDemoRouteAddBo bo) {
		TestDemoRoute add = BeanUtil.toBean(bo, TestDemoRoute.class);
		validEntityBeforeSave(add);
		add.setProcessToChangeId(-1L);
		Boolean r = createEntity(add);
		String procInstId = bpmProcessInstanceApi.createProcessInstance(AuthUtil.getUserId(), new BpmProcessInstanceCreateReqDTO()
				.setBusinessKey(String.valueOf(add.getId())).setProcessDefinitionKey(PROCESS_KEY));
		updateEntity(new TestDemoRoute().setProcessInstanceId(procInstId).setId(add.getId()));
		return r;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public Boolean updateByEditBo(TestDemoRouteEditBo bo) {
		TestDemoRoute update = BeanUtil.toBean(bo, TestDemoRoute.class);
		validEntityBeforeSave(update);
		return updateEntity(update);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public Boolean changeByEditBo(TestDemoRouteEditBo bo) {
		TestDemoRoute change = BeanUtil.toBean(bo, TestDemoRoute.class);
		change.setProcessToChangeId(bo.getId());
		change.setId(null);
		validEntityBeforeSave(change);
		Boolean r = createEntity(change);
		String procInstId = bpmProcessInstanceApi.createProcessInstance(AuthUtil.getUserId(), new BpmProcessInstanceCreateReqDTO()
				.setBusinessKey(String.valueOf(change.getId())).setProcessDefinitionKey(PROCESS_KEY));
		updateEntity(new TestDemoRoute().setProcessInstanceId(procInstId).setId(change.getId()));
		return r;
	}

	/**
	 * 保存前的数据校验
	 *
	 * @param entity 实体类数据
	 */
	private void validEntityBeforeSave(TestDemoRoute entity) {
		// TODO 做一些数据校验,如唯一约束
	}

	@Override
	public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
		if (isValid) {
			// TODO 做一些业务上的校验,判断是否需要校验
		}
		return removeByIds(ids);
	}
}
