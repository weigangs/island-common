package com.lkyl.island.common.api.response;

import java.io.Serializable;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;
import lombok.ToString;
import java.util.Date;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 用户信息表(SysUser)响应实体VO
 *
 * @author author
 * @since 2022-06-04 20:29:56
 */
@Data
@ApiModel("用户信息表响应体")
@ToString
public class SysUserVO implements Serializable {
    private static final long serialVersionUID = -56673766171624768L;
         @ApiModelProperty("用户ID")
         @JsonSerialize(using = ToStringSerializer.class)
         private Long userId;
         @ApiModelProperty("用户账号")
         private String userCode;
         @ApiModelProperty("用户昵称")
         private String userName;
         @ApiModelProperty("用户类型（00系统用户）")
         private String userType;
         @ApiModelProperty("用户邮箱")
         private String email;
         @ApiModelProperty("手机号码")
         private String phoneNumber;
         @ApiModelProperty("用户性别（0男 1女 2未知）")
         private String sex;
         @ApiModelProperty("头像地址")
         private String avatar;
         @ApiModelProperty("最后登录时间")
         private Date loginDate;
         @ApiModelProperty("角色列表")
         private List<String> roles;
         private String token;
         private List<String> permissions;

}

