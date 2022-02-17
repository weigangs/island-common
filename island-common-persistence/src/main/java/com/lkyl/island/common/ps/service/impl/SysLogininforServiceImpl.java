package com.lkyl.island.common.ps.service.impl;

import com.lkyl.oceanframework.mybatis.base.BaseServiceImpl;
import com.lkyl.island.common.ps.service.SysLogininforService;
import com.lkyl.island.common.ps.entity.SysLogininfor;
import com.lkyl.island.common.ps.dao.SysLogininforDao;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;

/**
 * 系统访问记录(SysLogininfor)表服务实现类
 *
 * @author shiwg
 * @since 2022-02-17 15:04:25
 */
@Service("sysLogininforService")
public class SysLogininforServiceImpl extends BaseServiceImpl<SysLogininfor, Long> implements SysLogininforService {
    @Resource
    private SysLogininforDao sysLogininforDao;

    @Resource
    public void setSysLogininforDao(SysLogininforDao sysLogininforDao) {
        this.sysLogininforDao = sysLogininforDao;
        super.setBaseDao(sysLogininforDao);
    }
}
