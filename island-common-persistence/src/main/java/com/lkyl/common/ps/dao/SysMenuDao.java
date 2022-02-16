package com.lkyl.common.ps.dao;

import com.lkyl.common.ps.entity.SysMenu;
import com.lkyl.oceanframework.mybatis.base.BaseDao;
import org.apache.ibatis.annotations.Mapper;

/**
 * 菜单权限表(SysMenu)表数据库访问层
 *
 * @author shiwg
 * @since 2022-02-16 23:58:51
 */
 @Mapper
public interface SysMenuDao extends BaseDao<SysMenu, Long>{

}

