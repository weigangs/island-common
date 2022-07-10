package com.lkyl.island.common.ps.dao;

import com.lkyl.island.common.ps.entity.SysDept;
import com.lkyl.oceanframework.mybatis.base.BaseDao;
import org.apache.ibatis.annotations.Mapper;

/**
 * 部门表(SysDept)表数据库访问层
 *
 * @author author
 * @since 2022-05-21 16:24:02
 */
 @Mapper
public interface SysDeptDao extends BaseDao<SysDept, Long>{

}

