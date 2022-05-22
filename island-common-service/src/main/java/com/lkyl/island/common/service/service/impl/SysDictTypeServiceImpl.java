package com.lkyl.island.common.service.service.impl;

import com.lkyl.oceanframework.mybatis.base.BaseServiceImpl;
import com.lkyl.island.common.service.service.SysDictTypeService;
import com.lkyl.island.common.ps.entity.SysDictType;
import com.lkyl.island.common.ps.dao.SysDictTypeDao;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;

/**
 * 字典类型表(SysDictType)表服务实现类
 *
 * @author author
 * @since 2022-05-21 17:02:48
 */
@Service("sysDictTypeService")
public class SysDictTypeServiceImpl extends BaseServiceImpl<SysDictType, Long> implements SysDictTypeService {
    @Resource
    private SysDictTypeDao sysDictTypeDao;

    @Resource
    public void setSysDictTypeDao(SysDictTypeDao sysDictTypeDao) {
        this.sysDictTypeDao = sysDictTypeDao;
        super.setBaseDao(sysDictTypeDao);
    }
}
