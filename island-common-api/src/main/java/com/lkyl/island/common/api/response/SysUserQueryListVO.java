package com.lkyl.island.common.api.response;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * 用户信息表(SysUser)响应实体VO
 *
 * @author author
 * @since 2022-06-04 20:29:56
 */
@Data
@ApiModel("用户信息表响应体")
@ToString
public class SysUserQueryListVO implements Serializable {
    private static final long serialVersionUID = -56673766171624768L;
         @ApiModelProperty("用户ID")
         @JsonSerialize(using = ToStringSerializer.class)
         private Long userId;
         @ApiModelProperty("用户账号")
         private String userCode;
         @ApiModelProperty("用户昵称")
         private String userName;

         @ApiModelProperty("手机号码")
         private String phoneNumber;
         @ApiModelProperty("状态")
         private String status;
         @ApiModelProperty("创建时间")
         private LocalDateTime createTime;

}

