package com.lkyl.island.common.ps.mapper;

import com.lkyl.island.common.ps.entity.SysUser;
import com.lkyl.island.common.ps.entity.SysUserExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface SysUserMapper {
    long countByExample(SysUserExample example);

    int deleteByExample(SysUserExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SysUser record);

    int insertSelective(@Param("record") SysUser record, @Param("selective") SysUser.Column ... selective);

    List<SysUser> selectByExampleSelective(@Param("example") SysUserExample example, @Param("selective") SysUser.Column ... selective);

    List<SysUser> selectByExample(SysUserExample example);

    SysUser selectByPrimaryKeySelective(@Param("id") Long id, @Param("selective") SysUser.Column ... selective);

    SysUser selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SysUser record, @Param("example") SysUserExample example, @Param("selective") SysUser.Column ... selective);

    int updateByExample(@Param("record") SysUser record, @Param("example") SysUserExample example);

    int updateByPrimaryKeySelective(@Param("record") SysUser record, @Param("selective") SysUser.Column ... selective);

    int updateByPrimaryKey(SysUser record);

    int batchInsert(@Param("list") List<SysUser> list);

    int batchInsertSelective(@Param("list") List<SysUser> list, @Param("selective") SysUser.Column ... selective);
}