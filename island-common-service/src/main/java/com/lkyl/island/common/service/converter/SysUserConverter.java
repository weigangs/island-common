package com.lkyl.island.common.service.converter;

import com.lkyl.island.common.api.response.SysUserVO;
import com.lkyl.island.common.ps.entity.SysUser;
import com.lkyl.oceanframework.common.utils.mapperstruct.base.BaseMapperConverter;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface SysUserConverter extends BaseMapperConverter<SysUser, SysUserVO> {
    SysUserConverter INSTANCE = Mappers.getMapper(SysUserConverter.class);
}
