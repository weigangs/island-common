package com.lkyl.island.common.service.converter;

import com.lkyl.island.common.api.response.SysDictTypeVO;
import com.lkyl.island.common.ps.entity.SysDictType;
import com.lkyl.oceanframework.common.utils.mapperstruct.base.BaseMapperConverter;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 字典类型表(SysDictType)映射接口
 *
 * @author author
 * @since 2022-05-21 18:06:38
 */
@Mapper(componentModel = "spring")
public interface SysDictTypeConverter extends BaseMapperConverter<SysDictType, SysDictTypeVO>{
	SysDictTypeConverter INSTANCE = Mappers.getMapper(SysDictTypeConverter.class);
}
