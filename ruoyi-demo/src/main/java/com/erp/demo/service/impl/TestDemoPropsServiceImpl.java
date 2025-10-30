package com.erp.demo.service.impl;

import org.springframework.stereotype.Service;

import com.erp.common.core.service.impl.DiBaseServiceImpl;
import com.erp.demo.domain.TestDemoProps;
import com.erp.demo.mapper.TestDemoPropsMapper;
import com.erp.demo.service.ITestDemoPropsService;

/**
 * 测试单Service业务层处理
 *
 * @author erp
 * @date 2022-03-19
 */
@Service
public class TestDemoPropsServiceImpl extends DiBaseServiceImpl<TestDemoPropsMapper, TestDemoProps>
		implements ITestDemoPropsService {

}
