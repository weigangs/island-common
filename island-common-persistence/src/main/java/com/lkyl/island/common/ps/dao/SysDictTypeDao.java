package com.lkyl.island.common.ps.dao;

import com.lkyl.island.common.ps.entity.SysDictType;
import com.lkyl.oceanframework.mybatis.base.BaseDao;
import org.apache.ibatis.annotations.Mapper;

/**
 * 字典类型表(SysDictType)表数据库访问层
 *
 * @author author
 * @since 2022-05-21 16:24:22
 */
 @Mapper
public interface SysDictTypeDao extends BaseDao<SysDictType, Long>{

}

