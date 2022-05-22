package com.lkyl.island.common.service.service.impl;

import com.lkyl.oceanframework.mybatis.base.BaseServiceImpl;
import com.lkyl.island.common.service.service.SysDeptService;
import com.lkyl.island.common.ps.entity.SysDept;
import com.lkyl.island.common.ps.dao.SysDeptDao;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;

/**
 * 部门表(SysDept)表服务实现类
 *
 * @author author
 * @since 2022-05-21 17:02:26
 */
@Service("sysDeptService")
public class SysDeptServiceImpl extends BaseServiceImpl<SysDept, Long> implements SysDeptService {
    @Resource
    private SysDeptDao sysDeptDao;

    @Resource
    public void setSysDeptDao(SysDeptDao sysDeptDao) {
        this.sysDeptDao = sysDeptDao;
        super.setBaseDao(sysDeptDao);
    }
}
