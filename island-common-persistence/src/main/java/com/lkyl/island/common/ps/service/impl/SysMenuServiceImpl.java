package com.lkyl.island.common.ps.service.impl;

import com.lkyl.oceanframework.mybatis.base.BaseServiceImpl;
import com.lkyl.island.common.ps.service.SysMenuService;
import com.lkyl.island.common.ps.entity.SysMenu;
import com.lkyl.island.common.ps.dao.SysMenuDao;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;

/**
 * 菜单权限表(SysMenu)表服务实现类
 *
 * @author shiwg
 * @since 2022-02-17 12:12:33
 */
@Service("sysMenuService")
public class SysMenuServiceImpl extends BaseServiceImpl<SysMenu, Long> implements SysMenuService {
    @Resource
    private SysMenuDao sysMenuDao;

    @Resource
    public void setSysMenuDao(SysMenuDao sysMenuDao) {
        this.sysMenuDao = sysMenuDao;
        super.setBaseDao(sysMenuDao);
    }
}
