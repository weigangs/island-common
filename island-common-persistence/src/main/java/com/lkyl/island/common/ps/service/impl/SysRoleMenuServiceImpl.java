package com.lkyl.island.common.ps.service.impl;

import com.lkyl.oceanframework.mybatis.base.BaseServiceImpl;
import com.lkyl.island.common.ps.service.SysRoleMenuService;
import com.lkyl.island.common.ps.entity.SysRoleMenu;
import com.lkyl.island.common.ps.dao.SysRoleMenuDao;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;

/**
 * 角色和菜单关联表(SysRoleMenu)表服务实现类
 *
 * @author shiwg
 * @since 2022-02-17 15:05:20
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
