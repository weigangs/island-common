package com.lkyl.island.common.service.service.impl;

import com.lkyl.oceanframework.mybatis.base.BaseServiceImpl;
import com.lkyl.island.common.service.service.SysUserRoleService;
import com.lkyl.island.common.ps.entity.SysUserRole;
import com.lkyl.island.common.ps.dao.SysUserRoleDao;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;

/**
 * 用户和角色关联表(SysUserRole)表服务实现类
 *
 * @author author
 * @since 2022-05-21 17:02:49
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
