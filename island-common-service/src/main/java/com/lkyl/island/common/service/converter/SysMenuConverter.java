package com.lkyl.island.common.service.converter;

import com.lkyl.island.common.api.response.SysMenuVO;
import com.lkyl.island.common.ps.entity.SysMenu;
import com.lkyl.oceanframework.common.utils.mapperstruct.base.BaseMapperConverter;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface SysMenuConverter extends BaseMapperConverter<SysMenu, SysMenuVO> {
    SysMenuConverter INSTANCE = Mappers.getMapper(SysMenuConverter.class);

}
