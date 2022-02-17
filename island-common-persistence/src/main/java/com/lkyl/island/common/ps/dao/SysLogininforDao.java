package com.lkyl.island.common.ps.dao;

import com.lkyl.island.common.ps.entity.SysLogininfor;
import com.lkyl.oceanframework.mybatis.base.BaseDao;
import org.apache.ibatis.annotations.Mapper;

/**
 * 系统访问记录(SysLogininfor)表数据库访问层
 *
 * @author shiwg
 * @since 2022-02-17 15:04:25
 */
 @Mapper
public interface SysLogininforDao extends BaseDao<SysLogininfor, Long>{

}

