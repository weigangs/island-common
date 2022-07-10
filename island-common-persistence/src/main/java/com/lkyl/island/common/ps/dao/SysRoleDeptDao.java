package com.lkyl.island.common.ps.dao;

import com.lkyl.island.common.ps.entity.SysRoleDept;
import com.lkyl.oceanframework.mybatis.base.BaseDao;
import org.apache.ibatis.annotations.Mapper;

/**
 * 角色和部门关联表(SysRoleDept)表数据库访问层
 *
 * @author author
 * @since 2022-05-21 16:25:05
 */
 @Mapper
public interface SysRoleDeptDao extends BaseDao<SysRoleDept, Long>{

}

