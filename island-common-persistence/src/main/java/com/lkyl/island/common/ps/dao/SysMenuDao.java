package com.lkyl.island.common.ps.dao;

import com.lkyl.island.common.ps.entity.SysMenu;
import com.lkyl.oceanframework.mybatis.base.BaseDao;
import org.apache.ibatis.annotations.Mapper;

/**
 * 菜单权限表(SysMenu)表数据库访问层
 *
 * @author author
 * @since 2022-05-21 16:24:30
 */
 @Mapper
public interface SysMenuDao extends BaseDao<SysMenu, Long>{

}

