package com.lkyl.island.common.ps.dao;

import com.lkyl.island.common.ps.entity.SysMenu;
import com.lkyl.oceanframework.mybatis.base.BaseDao;
import org.apache.ibatis.annotations.Mapper;

/**
 * 菜单权限表(SysMenu)表数据库访问层
 *
 * @author shiwg
 * @since 2022-02-17 12:12:30
 */
 @Mapper
public interface SysMenuDao extends BaseDao<SysMenu, Long>{

}

