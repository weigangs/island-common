package com.lkyl.common.ps.service.impl;

import com.lkyl.oceanframework.mybatis.base.BaseServiceImpl;
import com.lkyl.common.ps.service.SysMenuService;
import com.lkyl.common.ps.entity.SysMenu;
import com.lkyl.common.ps.dao.SysMenuDao;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;

/**
 * 菜单权限表(SysMenu)表服务实现类
 *
 * @author shiwg
 * @since 2022-02-16 23:58:51
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
