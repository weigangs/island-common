package com.lkyl.island.common.user.converter;

import com.lkyl.island.common.api.response.SysRoleAllocatedUserListVO;
import com.lkyl.island.common.api.response.SysUserQueryListVO;
import com.lkyl.island.common.api.response.SysUserVO;
import com.lkyl.island.common.api.response.SysUserWithRoleNameListVO;
import com.lkyl.island.common.ps.entity.SysUser;
import com.lkyl.oceanframework.common.utils.mapperstruct.base.BaseMapperConverter;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface SysUserConverter extends BaseMapperConverter<SysUser, SysUserVO> {
    SysUserConverter INSTANCE = Mappers.getMapper(SysUserConverter.class);

    @Mappings(@Mapping(source = "id", target = "userId"))
    SysRoleAllocatedUserListVO toRoleAllocatedUserListVO(SysUser sysUser);

    @Mappings(@Mapping(source = "id", target = "userId"))
    SysUserQueryListVO toSysUserQueryListVO(SysUser sysUser);

    @Mappings(@Mapping(source = "id", target = "userId"))
    SysUserWithRoleNameListVO toSysUserWithRoleNameListVO(SysUser sysUser);
}
