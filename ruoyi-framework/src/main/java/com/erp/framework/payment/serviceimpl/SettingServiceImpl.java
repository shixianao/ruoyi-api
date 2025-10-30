package com.erp.framework.payment.serviceimpl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.erp.framework.payment.entity.Setting;
import com.erp.framework.payment.mapper.SettingMapper;
import com.erp.framework.payment.service.SettingService;

/**
 * 配置业务层实现
 *
 * @author Chopper
 * @since 2020/11/17 3:52 下午
 */
@Service
public class SettingServiceImpl extends ServiceImpl<SettingMapper, Setting> implements SettingService {

    @Override
    public Setting get(String key) {
        return this.getById(key);
    }

    @Override
    public boolean saveUpdate(Setting setting) {
        return this.saveOrUpdate(setting);
    }
}