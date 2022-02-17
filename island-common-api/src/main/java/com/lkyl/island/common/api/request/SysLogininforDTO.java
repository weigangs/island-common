package com.lkyl.island.common.api.request;

import java.io.Serializable;
import lombok.Data;
import lombok.ToString;
import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
@Data
@ApiModel("系统访问记录请求体")
@ToString
public class SysLogininforDTO implements Serializable {
    private static final long serialVersionUID = -73275113487713646L;
        @ApiModelProperty("访问ID")
        private Long infoId;
        @ApiModelProperty("用户账号")
        private String userName;
        @ApiModelProperty("登录IP地址")
        private String ipaddr;
        @ApiModelProperty("登录地点")
        private String loginLocation;
        @ApiModelProperty("浏览器类型")
        private String browser;
        @ApiModelProperty("操作系统")
        private String os;
        @ApiModelProperty("登录状态（0成功 1失败）")
        private String status;
        @ApiModelProperty("提示消息")
        private String msg;
        @ApiModelProperty("访问时间")
        private Date loginTime;


}

