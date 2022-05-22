package com.lkyl.island.common.service.service.impl;

import com.lkyl.oceanframework.mybatis.base.BaseServiceImpl;
import com.lkyl.island.common.service.service.SysRoleMenuService;
import com.lkyl.island.common.ps.entity.SysRoleMenu;
import com.lkyl.island.common.ps.dao.SysRoleMenuDao;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;

/**
 * 角色和菜单关联表(SysRoleMenu)表服务实现类
 *
 * @author author
 * @since 2022-05-21 17:02:48
 */
@Service("sysRoleMenuService")
public class SysRoleMenuServiceImpl extends BaseServiceImpl<SysRoleMenu, Long> implements SysRoleMenuService {
    @Resource
    private SysRoleMenuDao sysRoleMenuDao;

    @Resource
    public void setSysRoleMenuDao(SysRoleMenuDao sysRoleMenuDao) {
        this.sysRoleMenuDao = sysRoleMenuDao;
        super.setBaseDao(sysRoleMenuDao);
    }
}
