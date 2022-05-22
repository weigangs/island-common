package com.lkyl.island.common.service.service.impl;

import com.lkyl.oceanframework.mybatis.base.BaseServiceImpl;
import com.lkyl.island.common.service.service.SysConfigService;
import com.lkyl.island.common.ps.entity.SysConfig;
import com.lkyl.island.common.ps.dao.SysConfigDao;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;

/**
 * 参数配置表(SysConfig)表服务实现类
 *
 * @author author
 * @since 2022-05-21 17:02:03
 */
@Service("sysConfigService")
public class SysConfigServiceImpl extends BaseServiceImpl<SysConfig, Integer> implements SysConfigService {
    @Resource
    private SysConfigDao sysConfigDao;

    @Resource
    public void setSysConfigDao(SysConfigDao sysConfigDao) {
        this.sysConfigDao = sysConfigDao;
        super.setBaseDao(sysConfigDao);
    }
}
