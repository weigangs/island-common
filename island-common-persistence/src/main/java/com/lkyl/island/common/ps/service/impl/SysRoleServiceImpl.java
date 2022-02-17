package com.lkyl.island.common.ps.service.impl;

import com.lkyl.oceanframework.mybatis.base.BaseServiceImpl;
import com.lkyl.island.common.ps.service.SysRoleService;
import com.lkyl.island.common.ps.entity.SysRole;
import com.lkyl.island.common.ps.dao.SysRoleDao;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;

/**
 * 角色信息表(SysRole)表服务实现类
 *
 * @author shiwg
 * @since 2022-02-17 15:04:53
 */
@Service("sysRoleService")
public class SysRoleServiceImpl extends BaseServiceImpl<SysRole, Long> implements SysRoleService {
    @Resource
    private SysRoleDao sysRoleDao;

    @Resource
    public void setSysRoleDao(SysRoleDao sysRoleDao) {
        this.sysRoleDao = sysRoleDao;
        super.setBaseDao(sysRoleDao);
    }
}
