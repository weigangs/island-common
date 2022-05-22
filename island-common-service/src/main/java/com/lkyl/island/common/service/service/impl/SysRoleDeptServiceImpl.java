package com.lkyl.island.common.service.service.impl;

import com.lkyl.oceanframework.mybatis.base.BaseServiceImpl;
import com.lkyl.island.common.service.service.SysRoleDeptService;
import com.lkyl.island.common.ps.entity.SysRoleDept;
import com.lkyl.island.common.ps.dao.SysRoleDeptDao;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;

/**
 * 角色和部门关联表(SysRoleDept)表服务实现类
 *
 * @author author
 * @since 2022-05-21 17:02:48
 */
@Service("sysRoleDeptService")
public class SysRoleDeptServiceImpl extends BaseServiceImpl<SysRoleDept, Long> implements SysRoleDeptService {
    @Resource
    private SysRoleDeptDao sysRoleDeptDao;

    @Resource
    public void setSysRoleDeptDao(SysRoleDeptDao sysRoleDeptDao) {
        this.sysRoleDeptDao = sysRoleDeptDao;
        super.setBaseDao(sysRoleDeptDao);
    }
}
