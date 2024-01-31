package com.lkyl.island.common.menu.service.impl;

import com.lkyl.island.common.common.enums.CommonStatusEnum;
import com.lkyl.island.common.menu.service.ISysMenuQueryService;
import com.lkyl.island.common.ps.entity.SysMenu;
import com.lkyl.island.common.ps.entity.SysMenuExample;
import com.lkyl.island.common.ps.entity.SysRoleMenu;
import com.lkyl.island.common.ps.entity.SysRoleMenuExample;
import com.lkyl.island.common.ps.mapper.SysMenuMapper;
import com.lkyl.island.common.ps.mapper.SysRoleMenuMapper;
import com.lkyl.oceanframework.common.utils.enums.YesOrNoEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SysMenuQueryServiceImpl implements ISysMenuQueryService {

    @Autowired
    private SysMenuMapper sysMenuMapper;

    @Autowired
    private SysRoleMenuMapper sysRoleMenuMapper;

    @Override
    public Optional<SysMenu> queryMenuByMenuId(Long menuId) {
        if (Objects.isNull(menuId)) {
            return Optional.empty();
        }
        return Optional.ofNullable(sysMenuMapper.selectByPrimaryKey(menuId));
    }

    @Override
    public List<SysMenu> querySysMenuByParam(SysMenuExample example) {
        return sysMenuMapper.selectByExample(example);
    }

    @Override
    public List<SysRoleMenu> queryRoleMenuByRoleIds(List<Long> roleIds) {
        if (CollectionUtils.isEmpty(roleIds)) {
            return Collections.emptyList();
        }

        SysRoleMenuExample example = new SysRoleMenuExample();
        example.createCriteria().andRoleIdIn(roleIds);

        return sysRoleMenuMapper.selectByExample(example);
    }

    private List<SysRoleMenu> queryRoleMenuByRoleId(Long roleId) {

        SysRoleMenuExample example = new SysRoleMenuExample();
        example.createCriteria().andRoleIdEqualTo(roleId);

        return sysRoleMenuMapper.selectByExample(example);
    }

    @Override
    public List<SysMenu> queryMenuByRoleIds(List<Long> roleIds) {
        if (CollectionUtils.isEmpty(roleIds)) {
            return Collections.emptyList();
        }

        List<Long> menuIds = queryRoleMenuByRoleIds(roleIds).stream().map(SysRoleMenu::getMenuId).collect(Collectors.toList());

        return queryMenuListByMenuIds(menuIds);
    }

    @Override
    public List<SysMenu> queryMenuByRoleId(Long roleId) {
        if (Objects.isNull(roleId)) {
            return Collections.emptyList();
        }

        List<Long> menuIds = queryRoleMenuByRoleId(roleId).stream().map(SysRoleMenu::getMenuId).collect(Collectors.toList());

        return queryMenuListByMenuIds(menuIds);
    }

    private List<SysMenu> queryMenuListByMenuIds(List<Long> menusIds) {
        if (CollectionUtils.isEmpty(menusIds)) {
            return Collections.emptyList();
        }

        SysMenuExample example = new SysMenuExample();
        buildCriteriaWithDefaultCondition(example).andIdIn(menusIds);
        return sysMenuMapper.selectByExample(example);
    }

    @Override
    public List<SysMenu> queryAllMenus() {
        SysMenuExample example = new SysMenuExample();
        buildCriteriaWithDefaultCondition(example);
        return sysMenuMapper.selectByExample(example);
    }

    private SysMenuExample.Criteria buildCriteriaWithDefaultCondition(SysMenuExample example) {
        SysMenuExample.Criteria result = example.createCriteria();
        result.andIsDeletedEqualTo(YesOrNoEnum.NO.getCode())
                .andIsVisibleEqualTo(YesOrNoEnum.YES.getCode())
                .andStatusEqualTo(CommonStatusEnum.NORMAL.getCode());
        example.setOrderByClause("order_num asc");
        return result;
    }


}
