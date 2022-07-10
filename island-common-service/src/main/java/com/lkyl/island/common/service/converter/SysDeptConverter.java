package com.lkyl.island.common.service.converter;

import com.lkyl.island.common.api.response.SysDeptVO;
import com.lkyl.island.common.ps.entity.SysDept;
import com.lkyl.oceanframework.common.utils.mapperstruct.base.BaseMapperConverter;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 部门表(SysDept)映射接口
 *
 * @author author
 * @since 2022-05-21 18:06:38
 */
@Mapper(componentModel = "spring")
public interface SysDeptConverter extends BaseMapperConverter<SysDept, SysDeptVO>{
	SysDeptConverter INSTANCE = Mappers.getMapper(SysDeptConverter.class);
}
