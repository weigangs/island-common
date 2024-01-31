package com.lkyl.island.common.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * 用户信息表(SysUser)请求实体DTO
 *
 * @author author
 * @since 2022-06-04 20:29:56
 */
@Data
@ApiModel("用户信息表请求体")
@ToString
public class SysUserUpdateDTO implements Serializable {
    private static final long serialVersionUID = -59091222127665624L;
        @ApiModelProperty("用户ID")
        @NotNull(message = "用户id不能为空")
        private Long userId;
        @ApiModelProperty("")
        private String userName;
        @ApiModelProperty("用户邮箱")
        private String email;
        @ApiModelProperty("手机号码")
        private String phoneNumber;
        @ApiModelProperty("用户性别（0男 1女 2未知）")
        private String sex;
        @ApiModelProperty("帐号状态（0正常 1停用）")
        private String status;
        @ApiModelProperty("备注")
        private String remark;

        @NotEmpty(message = "角色不能为空")
        Long [] roleIds;

}

