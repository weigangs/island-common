package com.lkyl.island.common.ps.dao;

import com.lkyl.island.common.ps.entity.SysRoleMenu;
import com.lkyl.oceanframework.mybatis.base.BaseDao;
import org.apache.ibatis.annotations.Mapper;

/**
 * 角色和菜单关联表(SysRoleMenu)表数据库访问层
 *
 * @author author
 * @since 2022-05-21 16:25:13
 */
 @Mapper
public interface SysRoleMenuDao extends BaseDao<SysRoleMenu, Long>{

}

