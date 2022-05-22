package com.lkyl.island.common.service.converter;

import com.lkyl.island.common.api.response.SysDictDataVO;
import com.lkyl.island.common.ps.entity.SysDictData;
import com.lkyl.oceanframework.common.utils.mapperstruct.base.BaseMapperConverter;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 字典数据表(SysDictData)映射接口
 *
 * @author author
 * @since 2022-05-21 18:06:38
 */
@Mapper(componentModel = "spring")
public interface SysDictDataConverter extends BaseMapperConverter<SysDictData, SysDictDataVO>{
	SysDictDataConverter INSTANCE = Mappers.getMapper(SysDictDataConverter.class);
}
