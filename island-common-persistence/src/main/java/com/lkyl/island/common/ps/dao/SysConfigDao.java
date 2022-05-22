package com.lkyl.island.common.ps.dao;

import com.lkyl.island.common.ps.entity.SysConfig;
import com.lkyl.oceanframework.mybatis.base.BaseDao;
import org.apache.ibatis.annotations.Mapper;

/**
 * 参数配置表(SysConfig)表数据库访问层
 *
 * @author author
 * @since 2022-05-21 16:23:48
 */
 @Mapper
public interface SysConfigDao extends BaseDao<SysConfig, Integer>{

}

