package com.lkyl.island.common.service.service.impl;

import com.lkyl.oceanframework.mybatis.base.BaseServiceImpl;
import com.lkyl.island.common.service.service.SysPostService;
import com.lkyl.island.common.ps.entity.SysPost;
import com.lkyl.island.common.ps.dao.SysPostDao;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;

/**
 * 岗位信息表(SysPost)表服务实现类
 *
 * @author author
 * @since 2022-05-21 17:02:48
 */
@Service("sysPostService")
public class SysPostServiceImpl extends BaseServiceImpl<SysPost, Long> implements SysPostService {
    @Resource
    private SysPostDao sysPostDao;

    @Resource
    public void setSysPostDao(SysPostDao sysPostDao) {
        this.sysPostDao = sysPostDao;
        super.setBaseDao(sysPostDao);
    }
}
