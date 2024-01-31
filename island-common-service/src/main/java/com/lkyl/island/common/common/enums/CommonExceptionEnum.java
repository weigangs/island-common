package com.lkyl.island.common.common.enums;

import com.lkyl.oceanframework.common.utils.exception.base.IBaseException;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CommonExceptionEnum implements IBaseException {
    // RECORD_NOT_FOUND
    RECORD_NOT_FOUND(20001, "未找到对应的记录！"),
    //
    LOGIN_RETURN_EMPTY(20002, "登录响应信息为空！"),
    //
    LOGIN_RETURN_ERR(20003, "登录失败！"),
    //
    UPDATE_FAILED(20004, "更新失败！"),

    //
    AMAP_RESPONSE_EMPTY(20005, "高德定位返回信息为空！"),
    //
    USER_CODE_PSW_ERR(20006, "用户名或密码错误！"),
    //
    USER_ID_NOT_NULL(20007, "用户id不能为空！"),
    //
    CAPTCHA_ERR(20008, "验证码生成异常"),
    //
    USER_STATUS_ABNORMAL(20009, "用户已停用！"),;

    private int code;

    private String msg;
}
