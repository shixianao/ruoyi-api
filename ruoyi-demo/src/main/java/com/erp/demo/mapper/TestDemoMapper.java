package com.erp.demo.mapper;

import com.erp.common.core.page.BaseMapperPlus;
import com.erp.demo.domain.TestDemo;
import com.erp.common.core.mybatisplus.MybatisPlusRedisCache;
import org.apache.ibatis.annotations.CacheNamespace;

/**
 * 测试单Mapper接口
 *
 * @author erp
 * @date 2022-03-19
 */
@CacheNamespace(implementation = MybatisPlusRedisCache.class, eviction = MybatisPlusRedisCache.class)
public interface TestDemoMapper extends BaseMapperPlus<TestDemo> {

}
