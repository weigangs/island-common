package com.lkyl.island.common.service.enums;

/**
 * TODO
 *
 * @version 1.0
 * @author: nicholas
 * @createTime: 2022年05月20日 0:16
 */
public enum RoleEnum {
    ADMIN("1", "超级管理员");

    private String code;

    private String msg;

    RoleEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }}
