package com.lkyl.island.common.ps.mapper;

import com.lkyl.island.common.ps.entity.SysDictData;
import com.lkyl.island.common.ps.entity.SysDictDataExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface SysDictDataMapper {
    long countByExample(SysDictDataExample example);

    int deleteByExample(SysDictDataExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SysDictData record);

    int insertSelective(@Param("record") SysDictData record, @Param("selective") SysDictData.Column ... selective);

    List<SysDictData> selectByExampleSelective(@Param("example") SysDictDataExample example, @Param("selective") SysDictData.Column ... selective);

    List<SysDictData> selectByExample(SysDictDataExample example);

    SysDictData selectByPrimaryKeySelective(@Param("id") Long id, @Param("selective") SysDictData.Column ... selective);

    SysDictData selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SysDictData record, @Param("example") SysDictDataExample example, @Param("selective") SysDictData.Column ... selective);

    int updateByExample(@Param("record") SysDictData record, @Param("example") SysDictDataExample example);

    int updateByPrimaryKeySelective(@Param("record") SysDictData record, @Param("selective") SysDictData.Column ... selective);

    int updateByPrimaryKey(SysDictData record);

    int batchInsert(@Param("list") List<SysDictData> list);

    int batchInsertSelective(@Param("list") List<SysDictData> list, @Param("selective") SysDictData.Column ... selective);
}