package com.lkyl.island.common.ps.dao;

import com.lkyl.island.common.ps.entity.SysPost;
import com.lkyl.oceanframework.mybatis.base.BaseDao;
import org.apache.ibatis.annotations.Mapper;

/**
 * 岗位信息表(SysPost)表数据库访问层
 *
 * @author author
 * @since 2022-05-21 16:24:42
 */
 @Mapper
public interface SysPostDao extends BaseDao<SysPost, Long>{

}

