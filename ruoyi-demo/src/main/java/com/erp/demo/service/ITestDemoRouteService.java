package com.erp.demo.service;

import com.erp.demo.domain.TestDemoRoute;
import com.erp.demo.vo.TestDemoRouteVo;
import com.erp.demo.bo.TestDemoRouteQueryBo;
import com.erp.demo.bo.TestDemoRouteAddBo;
import com.erp.demo.bo.TestDemoRouteEditBo;
import com.erp.common.core.service.DiBaseService;
import com.erp.common.core.page.TableDataInfo;

import java.util.Collection;
import java.util.List;

/**
 * 测试单RouteService接口
 *
 * @author erp
 * @date 2022-04-15
 */
public interface ITestDemoRouteService extends DiBaseService<TestDemoRoute> {
	/**
	 * 查询单个
	 * @return
	 */
	TestDemoRouteVo queryById(Long id);

	/**
	 * 查询列表
	 */
	TableDataInfo<TestDemoRouteVo> queryPageList(TestDemoRouteQueryBo bo);

	/**
	 * 查询列表
	 */
	List<TestDemoRouteVo> queryList(TestDemoRouteQueryBo bo);

	/**
	 * 根据新增业务对象插入测试单Route
	 * @param bo 测试单Route新增业务对象
	 * @return
	 */
	Boolean insertByAddBo(TestDemoRouteAddBo bo);

	/**
	 * 根据编辑业务对象修改测试单Route
	 * @param bo 测试单Route编辑业务对象
	 * @return
	 */
	Boolean updateByEditBo(TestDemoRouteEditBo bo);

	/**
	 * 校验并删除数据
	 * @param ids 主键集合
	 * @param isValid 是否校验,true-删除前校验,false-不校验
	 * @return
	 */
	Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
	
	/**
	 * 变更数据，需要走审批流程
	 * 
	 * @param bo
	 * @return
	 */
	public Boolean changeByEditBo(TestDemoRouteEditBo bo);
}
