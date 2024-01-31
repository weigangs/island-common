package com.lkyl.island.common.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

/**
 * @author nicholas
 * @date 2023/07/14 22:50
 */

@Data
@ApiModel("登录请求")
@ToString
public class LoginDTO {

    @ApiModelProperty("用户名")
    @NotBlank(message = "登录名不能为空")
    private String username;

    @ApiModelProperty("密码")
    @NotBlank(message = "密码不能数为空")
    private String password;

    @NotBlank(message = "验证码不能数为空")
    @ApiModelProperty("验证码")
    private String code;
}
