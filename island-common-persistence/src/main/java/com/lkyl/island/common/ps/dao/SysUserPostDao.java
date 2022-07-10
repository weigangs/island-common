package com.lkyl.island.common.ps.dao;

import com.lkyl.island.common.ps.entity.SysUserPost;
import com.lkyl.oceanframework.mybatis.base.BaseDao;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户与岗位关联表(SysUserPost)表数据库访问层
 *
 * @author author
 * @since 2022-05-21 16:25:32
 */
 @Mapper
public interface SysUserPostDao extends BaseDao<SysUserPost, Long>{

}

