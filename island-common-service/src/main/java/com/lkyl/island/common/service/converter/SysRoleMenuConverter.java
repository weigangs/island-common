package com.lkyl.island.common.service.converter;

import com.lkyl.island.common.api.response.SysRoleMenuVO;
import com.lkyl.island.common.ps.entity.SysRoleMenu;
import com.lkyl.oceanframework.common.utils.mapperstruct.base.BaseMapperConverter;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 角色和菜单关联表(SysRoleMenu)映射接口
 *
 * @author author
 * @since 2022-05-21 18:06:38
 */
@Mapper(componentModel = "spring")
public interface SysRoleMenuConverter extends BaseMapperConverter<SysRoleMenu, SysRoleMenuVO>{
	SysRoleMenuConverter INSTANCE = Mappers.getMapper(SysRoleMenuConverter.class);
}
