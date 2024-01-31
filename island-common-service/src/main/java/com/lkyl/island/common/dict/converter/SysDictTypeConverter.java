package com.lkyl.island.common.dict.converter;

import com.lkyl.island.common.api.response.SysDictTypeListVO;
import com.lkyl.island.common.api.response.SysDictTypeVO;
import com.lkyl.island.common.ps.entity.SysDictType;
import com.lkyl.oceanframework.common.utils.mapperstruct.base.BaseMapperConverter;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface SysDictTypeConverter extends BaseMapperConverter<SysDictType, SysDictTypeListVO> {
    SysDictTypeConverter INSTANCE = Mappers.getMapper(SysDictTypeConverter.class);

    @Mappings({
            @Mapping(source = "id", target = "dictId")
    })
    @Override
    SysDictTypeListVO to(SysDictType sysDictType);

    SysDictTypeVO toDetailVo(SysDictType sysDictType);
}
