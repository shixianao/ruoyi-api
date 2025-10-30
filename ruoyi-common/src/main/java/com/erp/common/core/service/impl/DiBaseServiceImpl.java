package com.erp.common.core.service.impl;

import com.diboot.core.mapper.BaseCrudMapper;
import com.diboot.core.service.impl.BaseServiceImpl;
import com.erp.common.core.service.DiBaseService;

public class DiBaseServiceImpl<M extends BaseCrudMapper<T>, T> extends BaseServiceImpl<M, T> implements DiBaseService<T> {

}
