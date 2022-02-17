package com.lkyl.island.common.ps.dao;

import com.lkyl.island.common.ps.entity.SysRole;
import com.lkyl.oceanframework.mybatis.base.BaseDao;
import org.apache.ibatis.annotations.Mapper;

/**
 * 角色信息表(SysRole)表数据库访问层
 *
 * @author shiwg
 * @since 2022-02-17 15:04:53
 */
 @Mapper
public interface SysRoleDao extends BaseDao<SysRole, Long>{

}

