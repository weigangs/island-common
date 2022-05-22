package com.lkyl.island.common.service.service.impl;

import com.lkyl.oceanframework.mybatis.base.BaseServiceImpl;
import com.lkyl.island.common.service.service.SysUserService;
import com.lkyl.island.common.ps.entity.SysUser;
import com.lkyl.island.common.ps.dao.SysUserDao;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;

/**
 * 用户信息表(SysUser)表服务实现类
 *
 * @author author
 * @since 2022-05-21 17:02:49
 */
@Service("sysUserService")
public class SysUserServiceImpl extends BaseServiceImpl<SysUser, Long> implements SysUserService {
    @Resource
    private SysUserDao sysUserDao;

    @Resource
    public void setSysUserDao(SysUserDao sysUserDao) {
        this.sysUserDao = sysUserDao;
        super.setBaseDao(sysUserDao);
    }
}
