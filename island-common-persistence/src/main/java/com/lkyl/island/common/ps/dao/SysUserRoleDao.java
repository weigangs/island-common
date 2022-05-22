package com.lkyl.island.common.ps.dao;

import com.lkyl.island.common.ps.entity.SysUserRole;
import com.lkyl.oceanframework.mybatis.base.BaseDao;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户和角色关联表(SysUserRole)表数据库访问层
 *
 * @author author
 * @since 2022-05-21 16:25:40
 */
 @Mapper
public interface SysUserRoleDao extends BaseDao<SysUserRole, Long>{

}

