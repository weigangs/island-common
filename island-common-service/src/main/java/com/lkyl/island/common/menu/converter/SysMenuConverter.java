package com.lkyl.island.common.menu.converter;

import com.lkyl.island.common.api.response.SysMenuVO;
import com.lkyl.island.common.ps.entity.SysMenu;
import com.lkyl.oceanframework.common.utils.mapperstruct.base.BaseMapperConverter;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface SysMenuConverter extends BaseMapperConverter<SysMenu, SysMenuVO> {
    SysMenuConverter INSTANCE = Mappers.getMapper(SysMenuConverter.class);

    @Mappings(@Mapping(source = "id", target = "menuId"))
    @Override
    SysMenuVO to(SysMenu sysMenu);
}
