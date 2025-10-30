package com.erp.demo.mapper;

import com.diboot.core.mapper.BaseCrudMapper;
import com.erp.demo.domain.TestDemoRoute;
// import com.erp.common.core.mybatisplus.MybatisPlusRedisCache;
// import org.apache.ibatis.annotations.CacheNamespace;

/**
 * 测试单RouteMapper接口
 *
 * @author erp
 * @date 2022-04-15
 */
// @CacheNamespace(implementation = MybatisPlusRedisCache.class, eviction = MybatisPlusRedisCache.class)
public interface TestDemoRouteMapper extends BaseCrudMapper<TestDemoRoute> {

}
