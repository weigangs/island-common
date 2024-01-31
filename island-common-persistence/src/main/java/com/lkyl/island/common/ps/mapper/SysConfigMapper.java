package com.lkyl.island.common.ps.mapper;

import com.lkyl.island.common.ps.entity.SysConfig;
import com.lkyl.island.common.ps.entity.SysConfigExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface SysConfigMapper {
    long countByExample(SysConfigExample example);

    int deleteByExample(SysConfigExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SysConfig record);

    int insertSelective(SysConfig record);

    List<SysConfig> selectByExampleSelective(@Param("example") SysConfigExample example, @Param("selective") SysConfig.Column ... selective);

    List<SysConfig> selectByExample(SysConfigExample example);

    SysConfig selectByPrimaryKeySelective(@Param("id") Long id, @Param("selective") SysConfig.Column ... selective);

    SysConfig selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SysConfig record, @Param("example") SysConfigExample example);

    int updateByExample(@Param("record") SysConfig record, @Param("example") SysConfigExample example);

    int updateByPrimaryKeySelective(SysConfig record);

    int updateByPrimaryKey(SysConfig record);

    int batchInsert(@Param("list") List<SysConfig> list);

    int batchInsertSelective(@Param("list") List<SysConfig> list, @Param("selective") SysConfig.Column ... selective);
}