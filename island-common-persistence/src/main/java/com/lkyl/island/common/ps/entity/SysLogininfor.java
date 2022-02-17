package com.lkyl.island.common.ps.entity;

import java.io.Serializable;
import com.lkyl.oceanframework.mybatis.base.BaseDomain;
import lombok.Data;
import lombok.ToString;
import java.util.Date;

@Data
@ToString
public class SysLogininfor implements Serializable {
    private static final long serialVersionUID = -72851962513785345L;
    /**
     * 访问ID
     */
    private Long infoId;
    /**
     * 用户账号
     */
    private String userName;
    /**
     * 登录IP地址
     */
    private String ipaddr;
    /**
     * 登录地点
     */
    private String loginLocation;
    /**
     * 浏览器类型
     */
    private String browser;
    /**
     * 操作系统
     */
    private String os;
    /**
     * 登录状态（0成功 1失败）
     */
    private String status;
    /**
     * 提示消息
     */
    private String msg;
    /**
     * 访问时间
     */
    private Date loginTime;


}

