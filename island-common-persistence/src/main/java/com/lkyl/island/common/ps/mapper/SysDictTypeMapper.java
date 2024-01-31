package com.lkyl.island.common.ps.mapper;

import com.lkyl.island.common.ps.entity.SysDictType;
import com.lkyl.island.common.ps.entity.SysDictTypeExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface SysDictTypeMapper {
    long countByExample(SysDictTypeExample example);

    int deleteByExample(SysDictTypeExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SysDictType record);

    int insertSelective(SysDictType record);

    List<SysDictType> selectByExampleSelective(@Param("example") SysDictTypeExample example, @Param("selective") SysDictType.Column ... selective);

    List<SysDictType> selectByExample(SysDictTypeExample example);

    SysDictType selectByPrimaryKeySelective(@Param("id") Long id, @Param("selective") SysDictType.Column ... selective);

    SysDictType selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SysDictType record, @Param("example") SysDictTypeExample example);

    int updateByExample(@Param("record") SysDictType record, @Param("example") SysDictTypeExample example);

    int updateByPrimaryKeySelective(SysDictType record);

    int updateByPrimaryKey(SysDictType record);

    int batchInsert(@Param("list") List<SysDictType> list);

    int batchInsertSelective(@Param("list") List<SysDictType> list, @Param("selective") SysDictType.Column ... selective);
}