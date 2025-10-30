package com.erp.demo.service;

import com.erp.common.core.page.IServicePlus;
import com.erp.common.core.page.TableDataInfo;
import com.erp.demo.bo.TestDemoAddBo;
import com.erp.demo.bo.TestDemoEditBo;
import com.erp.demo.bo.TestDemoQueryBo;
import com.erp.demo.domain.TestDemo;
import com.erp.demo.vo.TestDemoVo;

import java.util.Collection;
import java.util.List;

/**
 * 测试单Service接口
 *
 * @author erp
 * @date 2022-03-19
 */
public interface ITestDemoService extends IServicePlus<TestDemo> {
	/**
	 * 查询单个
	 * @return
	 */
	TestDemoVo queryById(Long id);

	/**
	 * 查询列表
	 */
    TableDataInfo<TestDemoVo> queryPageList(TestDemoQueryBo bo);

	/**
	 * 查询列表
	 */
	List<TestDemoVo> queryList(TestDemoQueryBo bo);

	/**
	 * 根据新增业务对象插入测试单
	 * @param bo 测试单新增业务对象
	 * @return
	 */
	Boolean insertByAddBo(TestDemoAddBo bo);

	/**
	 * 根据编辑业务对象修改测试单
	 * @param bo 测试单编辑业务对象
	 * @return
	 */
	Boolean updateByEditBo(TestDemoEditBo bo);

	/**
	 * 校验并删除数据
	 * @param ids 主键集合
	 * @param isValid 是否校验,true-删除前校验,false-不校验
	 * @return
	 */
	Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
