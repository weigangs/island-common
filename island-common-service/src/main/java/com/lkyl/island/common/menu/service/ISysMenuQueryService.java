package com.lkyl.island.common.menu.service;

import com.lkyl.island.common.ps.entity.SysMenu;
import com.lkyl.island.common.ps.entity.SysMenuExample;
import com.lkyl.island.common.ps.entity.SysRoleMenu;
import com.lkyl.oceanframework.common.utils.page.PageArgs;

import java.util.List;
import java.util.Optional;

public interface ISysMenuQueryService {

    Optional<SysMenu> queryMenuByMenuId(Long menuId);

    List<SysMenu> querySysMenuByParam(SysMenuExample example);

    List<SysRoleMenu> queryRoleMenuByRoleIds(List<Long> roleIds);

    List<SysMenu> queryMenuByRoleIds(List<Long> roleIds);

    List<SysMenu> queryMenuByRoleId(Long roleId);

    List<SysMenu> queryAllMenus();

}
