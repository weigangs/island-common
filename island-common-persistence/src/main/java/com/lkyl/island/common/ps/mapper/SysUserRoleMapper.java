package com.lkyl.island.common.ps.mapper;

import com.lkyl.island.common.ps.entity.SysUserRole;
import com.lkyl.island.common.ps.entity.SysUserRoleExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface SysUserRoleMapper {
    long countByExample(SysUserRoleExample example);

    int deleteByExample(SysUserRoleExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SysUserRole record);

    int insertSelective(@Param("record") SysUserRole record, @Param("selective") SysUserRole.Column ... selective);

    List<SysUserRole> selectByExampleSelective(@Param("example") SysUserRoleExample example, @Param("selective") SysUserRole.Column ... selective);

    List<SysUserRole> selectByExample(SysUserRoleExample example);

    SysUserRole selectByPrimaryKeySelective(@Param("id") Long id, @Param("selective") SysUserRole.Column ... selective);

    SysUserRole selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SysUserRole record, @Param("example") SysUserRoleExample example, @Param("selective") SysUserRole.Column ... selective);

    int updateByExample(@Param("record") SysUserRole record, @Param("example") SysUserRoleExample example);

    int updateByPrimaryKeySelective(@Param("record") SysUserRole record, @Param("selective") SysUserRole.Column ... selective);

    int updateByPrimaryKey(SysUserRole record);

    int batchInsert(@Param("list") List<SysUserRole> list);

    int batchInsertSelective(@Param("list") List<SysUserRole> list, @Param("selective") SysUserRole.Column ... selective);
}