package com.erp.demo.mapper;

import com.diboot.core.mapper.BaseCrudMapper;
import com.erp.demo.domain.TestDemoProps;

/**
 * 测试单Mapper接口
 *
 * @author erp
 * @date 2022-03-19
 */
//@CacheNamespace(implementation = MybatisPlusRedisCache.class, eviction = MybatisPlusRedisCache.class)
public interface TestDemoPropsMapper extends BaseCrudMapper<TestDemoProps> {

}
