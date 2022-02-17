package com.lkyl.island.common.api.response;

import java.io.Serializable;
import lombok.Data;
import lombok.ToString;
import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Data
@ApiModel("系统访问记录响应体")
@ToString
public class SysLogininforVO implements Serializable {
    private static final long serialVersionUID = 682263187777802965L;
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

