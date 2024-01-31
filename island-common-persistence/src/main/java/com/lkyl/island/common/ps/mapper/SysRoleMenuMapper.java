package com.lkyl.island.common.ps.mapper;

import com.lkyl.island.common.ps.entity.SysRoleMenu;
import com.lkyl.island.common.ps.entity.SysRoleMenuExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface SysRoleMenuMapper {
    long countByExample(SysRoleMenuExample example);

    int deleteByExample(SysRoleMenuExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SysRoleMenu record);

    int insertSelective(SysRoleMenu record);

    List<SysRoleMenu> selectByExampleSelective(@Param("example") SysRoleMenuExample example, @Param("selective") SysRoleMenu.Column ... selective);

    List<SysRoleMenu> selectByExample(SysRoleMenuExample example);

    SysRoleMenu selectByPrimaryKeySelective(@Param("id") Long id, @Param("selective") SysRoleMenu.Column ... selective);

    SysRoleMenu selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SysRoleMenu record, @Param("example") SysRoleMenuExample example);

    int updateByExample(@Param("record") SysRoleMenu record, @Param("example") SysRoleMenuExample example);

    int updateByPrimaryKeySelective(SysRoleMenu record);

    int updateByPrimaryKey(SysRoleMenu record);

    int batchInsert(@Param("list") List<SysRoleMenu> list);

    int batchInsertSelective(@Param("list") List<SysRoleMenu> list, @Param("selective") SysRoleMenu.Column ... selective);
}