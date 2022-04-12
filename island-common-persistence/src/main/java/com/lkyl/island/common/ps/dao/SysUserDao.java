package com.lkyl.island.common.ps.dao;

import com.lkyl.island.common.ps.entity.SysUser;
import com.lkyl.oceanframework.mybatis.base.BaseDao;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户信息表(SysUser)表数据库访问层
 *
 * @author shiwg
 * @since 2022-04-12 23:08:38
 */
 @Mapper
public interface SysUserDao extends BaseDao<SysUser, Long>{

}

