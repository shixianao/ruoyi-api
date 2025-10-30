package com.erp.biz.file.mapper;

import com.diboot.core.mapper.BaseCrudMapper;
import com.erp.biz.file.domain.SysFile;
// import com.erp.common.core.mybatisplus.MybatisPlusRedisCache;
// import org.apache.ibatis.annotations.CacheNamespace;

/**
 * 文件管理Mapper接口
 *
 * @author erp
 * @date 2023-11-22
 */
// @CacheNamespace(implementation = MybatisPlusRedisCache.class, eviction = MybatisPlusRedisCache.class)
public interface SysFileMapper extends BaseCrudMapper<SysFile> {

}
