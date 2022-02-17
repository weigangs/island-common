package com.lkyl.island.common.ps.service.impl;

import com.lkyl.oceanframework.mybatis.base.BaseServiceImpl;
import com.lkyl.island.common.ps.service.SysUserRoleService;
import com.lkyl.island.common.ps.entity.SysUserRole;
import com.lkyl.island.common.ps.dao.SysUserRoleDao;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;

/**
 * 用户和角色关联表(SysUserRole)表服务实现类
 *
 * @author shiwg
 * @since 2022-02-17 15:05:49
 */
@Service("sysUserRoleService")
public class SysUserRoleServiceImpl extends BaseServiceImpl<SysUserRole, Long> implements SysUserRoleService {
    @Resource
    private SysUserRoleDao sysUserRoleDao;

    @Resource
    public void setSysUserRoleDao(SysUserRoleDao sysUserRoleDao) {
        this.sysUserRoleDao = sysUserRoleDao;
        super.setBaseDao(sysUserRoleDao);
    }
}
