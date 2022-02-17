package com.lkyl.island.common.ps.dao;

import com.lkyl.island.common.ps.entity.SysRoleMenu;
import com.lkyl.oceanframework.mybatis.base.BaseDao;
import org.apache.ibatis.annotations.Mapper;

/**
 * 角色和菜单关联表(SysRoleMenu)表数据库访问层
 *
 * @author shiwg
 * @since 2022-02-17 15:05:19
 */
 @Mapper
public interface SysRoleMenuDao extends BaseDao<SysRoleMenu, Long>{

}

