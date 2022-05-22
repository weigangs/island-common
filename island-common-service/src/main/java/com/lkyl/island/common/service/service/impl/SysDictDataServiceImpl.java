package com.lkyl.island.common.service.service.impl;

import com.lkyl.oceanframework.mybatis.base.BaseServiceImpl;
import com.lkyl.island.common.service.service.SysDictDataService;
import com.lkyl.island.common.ps.entity.SysDictData;
import com.lkyl.island.common.ps.dao.SysDictDataDao;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;

/**
 * 字典数据表(SysDictData)表服务实现类
 *
 * @author author
 * @since 2022-05-21 17:02:48
 */
@Service("sysDictDataService")
public class SysDictDataServiceImpl extends BaseServiceImpl<SysDictData, Long> implements SysDictDataService {
    @Resource
    private SysDictDataDao sysDictDataDao;

    @Resource
    public void setSysDictDataDao(SysDictDataDao sysDictDataDao) {
        this.sysDictDataDao = sysDictDataDao;
        super.setBaseDao(sysDictDataDao);
    }
}
