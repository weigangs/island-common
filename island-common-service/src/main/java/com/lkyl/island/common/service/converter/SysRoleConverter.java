package com.lkyl.island.common.service.converter;

import com.lkyl.island.common.api.response.SysRoleVO;
import com.lkyl.island.common.ps.entity.SysRole;
import com.lkyl.oceanframework.common.utils.mapperstruct.base.BaseMapperConverter;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 角色信息表(SysRole)映射接口
 *
 * @author author
 * @since 2022-05-21 18:06:38
 */
@Mapper(componentModel = "spring")
public interface SysRoleConverter extends BaseMapperConverter<SysRole, SysRoleVO>{
	SysRoleConverter INSTANCE = Mappers.getMapper(SysRoleConverter.class);
}
