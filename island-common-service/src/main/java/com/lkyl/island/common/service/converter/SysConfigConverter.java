package com.lkyl.island.common.service.converter;

import com.lkyl.island.common.api.response.SysConfigVO;
import com.lkyl.island.common.ps.entity.SysConfig;
import com.lkyl.oceanframework.common.utils.mapperstruct.base.BaseMapperConverter;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 参数配置表(SysConfig)映射接口
 *
 * @author author
 * @since 2022-05-21 18:06:38
 */
@Mapper(componentModel = "spring")
public interface SysConfigConverter extends BaseMapperConverter<SysConfig, SysConfigVO>{
	SysConfigConverter INSTANCE = Mappers.getMapper(SysConfigConverter.class);
}
