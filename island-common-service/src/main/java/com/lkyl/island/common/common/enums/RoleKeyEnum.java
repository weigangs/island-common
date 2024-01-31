package com.lkyl.island.common.common.enums;

import com.lkyl.oceanframework.common.cache.EnumCache;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * TODO
 *
 * @version 1.0
 * @author: nicholas
 * @createTime: 2022年05月20日 0:16
 */
@Getter
@AllArgsConstructor
public enum RoleKeyEnum {
    //超级管理员
    ADMIN("admin", "超级管理员");

    private String key;

    private String msg;

    static {
        // 通过名称构建缓存,通过EnumCache.findByName(StatusEnum.class,"SUCCESS",null);调用能获取枚举
        EnumCache.registerByName(RoleKeyEnum.class, RoleKeyEnum.values());
        // 通过code构建缓存,通过EnumCache.findByValue(StatusEnum.class,"S",null);调用能获取枚举
        EnumCache.registerByValue(RoleKeyEnum.class, RoleKeyEnum.values(), RoleKeyEnum::getKey);
    }

}
