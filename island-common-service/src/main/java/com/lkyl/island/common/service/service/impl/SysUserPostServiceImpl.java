package com.lkyl.island.common.service.service.impl;

import com.lkyl.oceanframework.mybatis.base.BaseServiceImpl;
import com.lkyl.island.common.service.service.SysUserPostService;
import com.lkyl.island.common.ps.entity.SysUserPost;
import com.lkyl.island.common.ps.dao.SysUserPostDao;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;

/**
 * 用户与岗位关联表(SysUserPost)表服务实现类
 *
 * @author author
 * @since 2022-05-21 17:02:49
 */
@Service("sysUserPostService")
public class SysUserPostServiceImpl extends BaseServiceImpl<SysUserPost, Long> implements SysUserPostService {
    @Resource
    private SysUserPostDao sysUserPostDao;

    @Resource
    public void setSysUserPostDao(SysUserPostDao sysUserPostDao) {
        this.sysUserPostDao = sysUserPostDao;
        super.setBaseDao(sysUserPostDao);
    }
}
