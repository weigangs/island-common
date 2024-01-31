package com.lkyl.island.common.menu.service;

import com.lkyl.island.common.api.response.MenuTreeVO;
import com.lkyl.island.common.api.response.RouterVO;
import com.lkyl.island.common.api.response.SysMenuVO;
import com.lkyl.island.common.ps.entity.SysMenu;

import java.util.List;
import java.util.Set;

public interface ISysMenuCommonService {

    /**
     * getMenusByUserId
     * @param userId userId
     * @return list
     */
    List<SysMenu> getMenusByUserId(Long userId);

    /**
     * getLoginUserPermissions
     * @param userId userId
     * @return List
     */
    Set<String> getLoginUserPermissions(Long userId);


    /**
     * 构建前端路由所需要的菜单
     *
     * @param menus 菜单列表
     * @return 路由列表
     */
    List<RouterVO> buildMenus(List<SysMenuVO> menus);

    /**
     * 构建前端所需要树结构
     *
     * @param menus 菜单列表
     * @return 树结构列表
     */
    List<SysMenuVO> buildMenuTree(List<SysMenuVO> menus);

    /**
     * 构建前端所需要下拉树结构
     *
     * @param menus 菜单列表
     * @return 下拉树结构列表
     */
    List<MenuTreeVO> buildMenuTreeSelect(List<SysMenu> menus);
    /**
     * 根据父节点的ID获取所有子节点
     *
     * @param list 分类表
     * @param parentId 传入的父节点ID
     * @return String
     */
    List<SysMenuVO> getChildPerms(List<SysMenuVO> list, int parentId);
}
