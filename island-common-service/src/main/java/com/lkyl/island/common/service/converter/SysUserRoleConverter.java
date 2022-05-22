package com.lkyl.island.common.service.converter;

import com.lkyl.island.common.api.response.SysUserRoleVO;
import com.lkyl.island.common.ps.entity.SysUserRole;
import com.lkyl.oceanframework.common.utils.mapperstruct.base.BaseMapperConverter;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 用户和角色关联表(SysUserRole)映射接口
 *
 * @author author
 * @since 2022-05-21 18:06:38
 */
@Mapper(componentModel = "spring")
public interface SysUserRoleConverter extends BaseMapperConverter<SysUserRole, SysUserRoleVO>{
	SysUserRoleConverter INSTANCE = Mappers.getMapper(SysUserRoleConverter.class);
}
