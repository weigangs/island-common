package com.lkyl.island.common.common.enums;

import com.lkyl.oceanframework.common.cache.EnumCache;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CommonStatusEnum {

    NORMAL("1", "正常"),
    DISCARD("0", "停用");

    private String code;

    private String msg;

    static {
        // 通过名称构建缓存,通过EnumCache.findByName(StatusEnum.class,"SUCCESS",null);调用能获取枚举
        EnumCache.registerByName(CommonStatusEnum.class, CommonStatusEnum.values());
        // 通过code构建缓存,通过EnumCache.findByValue(StatusEnum.class,"S",null);调用能获取枚举
        EnumCache.registerByValue(CommonStatusEnum.class, CommonStatusEnum.values(), CommonStatusEnum::getCode);
    }
}
