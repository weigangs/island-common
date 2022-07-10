package com.lkyl.island.common.service.converter;

import com.lkyl.island.common.api.response.SysUserPostVO;
import com.lkyl.island.common.ps.entity.SysUserPost;
import com.lkyl.oceanframework.common.utils.mapperstruct.base.BaseMapperConverter;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 用户与岗位关联表(SysUserPost)映射接口
 *
 * @author author
 * @since 2022-05-21 18:06:38
 */
@Mapper(componentModel = "spring")
public interface SysUserPostConverter extends BaseMapperConverter<SysUserPost, SysUserPostVO>{
	SysUserPostConverter INSTANCE = Mappers.getMapper(SysUserPostConverter.class);
}
