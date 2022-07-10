package com.lkyl.island.common.ps.dao;

import com.lkyl.island.common.ps.entity.SysUser;
import com.lkyl.oceanframework.mybatis.base.BaseDao;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户信息表(SysUser)表数据库访问层
 *
 * @author author
 * @since 2022-05-21 16:25:21
 */
 @Mapper
public interface SysUserDao extends BaseDao<SysUser, Long>{

}

