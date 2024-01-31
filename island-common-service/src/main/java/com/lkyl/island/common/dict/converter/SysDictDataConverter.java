package com.lkyl.island.common.dict.converter;

import com.lkyl.island.common.api.response.SysDictDataListVO;
import com.lkyl.island.common.api.response.SysDictDataVO;
import com.lkyl.island.common.ps.entity.SysDictData;
import com.lkyl.oceanframework.common.utils.mapperstruct.base.BaseMapperConverter;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface SysDictDataConverter extends BaseMapperConverter<SysDictData, SysDictDataVO> {
    SysDictDataConverter INSTANCE = Mappers.getMapper(SysDictDataConverter.class);


    @Mappings(@Mapping(source = "id", target = "dictDataId"))
    SysDictDataListVO toVo(SysDictData source);
}
