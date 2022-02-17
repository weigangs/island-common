package com.lkyl.island.common.ps.dao;

import com.lkyl.island.common.ps.entity.SysUserRole;
import com.lkyl.oceanframework.mybatis.base.BaseDao;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户和角色关联表(SysUserRole)表数据库访问层
 *
 * @author shiwg
 * @since 2022-02-17 15:05:49
 */
 @Mapper
public interface SysUserRoleDao extends BaseDao<SysUserRole, Long>{

}

