package com.lkyl.island.common.ps.dao;

import com.lkyl.island.common.ps.entity.SysDictData;
import com.lkyl.oceanframework.mybatis.base.BaseDao;
import org.apache.ibatis.annotations.Mapper;

/**
 * 字典数据表(SysDictData)表数据库访问层
 *
 * @author author
 * @since 2022-05-21 16:24:11
 */
 @Mapper
public interface SysDictDataDao extends BaseDao<SysDictData, Long>{

}

