package com.lkyl.island.common.ps.service.impl;

import com.lkyl.oceanframework.mybatis.base.BaseServiceImpl;
import com.lkyl.island.common.ps.service.SysUserService;
import com.lkyl.island.common.ps.entity.SysUser;
import com.lkyl.island.common.ps.dao.SysUserDao;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;

/**
 * 用户信息表(SysUser)表服务实现类
 *
 * @author shiwg
 * @since 2022-02-17 15:03:00
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
