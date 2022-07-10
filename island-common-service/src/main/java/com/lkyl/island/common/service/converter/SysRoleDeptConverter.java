package com.lkyl.island.common.service.converter;

import com.lkyl.island.common.api.response.SysRoleDeptVO;
import com.lkyl.island.common.ps.entity.SysRoleDept;
import com.lkyl.oceanframework.common.utils.mapperstruct.base.BaseMapperConverter;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 角色和部门关联表(SysRoleDept)映射接口
 *
 * @author author
 * @since 2022-05-21 18:06:38
 */
@Mapper(componentModel = "spring")
public interface SysRoleDeptConverter extends BaseMapperConverter<SysRoleDept, SysRoleDeptVO>{
	SysRoleDeptConverter INSTANCE = Mappers.getMapper(SysRoleDeptConverter.class);
}
