package com.erp.system.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.erp.common.core.domain.entity.SysDictData;
import com.erp.common.core.page.BaseMapperPlus;

import java.util.List;

/**
 * 字典表 数据层
 *
 * @author erp
 */
public interface SysDictDataMapper extends BaseMapperPlus<SysDictData> {

	default List<SysDictData> selectDictDataByType(String dictType) {
		return selectList(
			new LambdaQueryWrapper<SysDictData>()
				.eq(SysDictData::getStatus, "0")
				.eq(SysDictData::getDictType, dictType)
				.orderByAsc(SysDictData::getDictSort));
	}
}
