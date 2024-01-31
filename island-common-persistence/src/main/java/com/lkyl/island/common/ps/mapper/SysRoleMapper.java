package com.lkyl.island.common.ps.mapper;

import com.lkyl.island.common.ps.entity.SysRole;
import com.lkyl.island.common.ps.entity.SysRoleExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface SysRoleMapper {
    long countByExample(SysRoleExample example);

    int deleteByExample(SysRoleExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SysRole record);

    int insertSelective(SysRole record);

    List<SysRole> selectByExampleSelective(@Param("example") SysRoleExample example, @Param("selective") SysRole.Column ... selective);

    List<SysRole> selectByExample(SysRoleExample example);

    SysRole selectByPrimaryKeySelective(@Param("id") Long id, @Param("selective") SysRole.Column ... selective);

    SysRole selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SysRole record, @Param("example") SysRoleExample example);

    int updateByExample(@Param("record") SysRole record, @Param("example") SysRoleExample example);

    int updateByPrimaryKeySelective(SysRole record);

    int updateByPrimaryKey(SysRole record);

    int batchInsert(@Param("list") List<SysRole> list);

    int batchInsertSelective(@Param("list") List<SysRole> list, @Param("selective") SysRole.Column ... selective);
}