package com.lkyl.island.common.role.converter;

import com.lkyl.island.common.api.response.RoleNameVO;
import com.lkyl.island.common.api.response.SysRoleDetailVO;
import com.lkyl.island.common.api.response.SysRoleListQueryVO;
import com.lkyl.island.common.ps.entity.SysRole;
import com.lkyl.oceanframework.common.utils.mapperstruct.base.BaseMapperConverter;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface SysRoleConverter extends BaseMapperConverter<SysRole, SysRoleListQueryVO> {
    SysRoleConverter INSTANCE = Mappers.getMapper(SysRoleConverter.class);

    @Mappings(@Mapping(source = "id", target = "roleId"))
    @Override
    SysRoleListQueryVO to(SysRole sysRole);

    @Mappings(@Mapping(source = "id", target = "roleId"))
    SysRoleDetailVO toDetail(SysRole sysRole);

    @Mappings(@Mapping(source = "id", target = "roleId"))
    RoleNameVO toRoleNameVO(SysRole sysRole);
}
