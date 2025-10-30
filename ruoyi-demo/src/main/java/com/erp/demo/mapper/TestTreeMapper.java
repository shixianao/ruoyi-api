package com.erp.demo.mapper;

import com.erp.common.core.mybatisplus.MybatisPlusRedisCache;
import com.erp.common.core.page.BaseMapperPlus;
import com.erp.demo.domain.TestTree;
import org.apache.ibatis.annotations.CacheNamespace;

/**
 * 测试树表Mapper接口
 *
 * @author Lion Li
 * @date 2021-05-30
 */
@CacheNamespace(implementation = MybatisPlusRedisCache.class, eviction = MybatisPlusRedisCache.class)
public interface TestTreeMapper extends BaseMapperPlus<TestTree> {

}
