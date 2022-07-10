package com.lkyl.island.common.service.converter;

import com.lkyl.island.common.api.response.SysPostVO;
import com.lkyl.island.common.ps.entity.SysPost;
import com.lkyl.oceanframework.common.utils.mapperstruct.base.BaseMapperConverter;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 岗位信息表(SysPost)映射接口
 *
 * @author author
 * @since 2022-05-21 18:06:38
 */
@Mapper(componentModel = "spring")
public interface SysPostConverter extends BaseMapperConverter<SysPost, SysPostVO>{
	SysPostConverter INSTANCE = Mappers.getMapper(SysPostConverter.class);
}
