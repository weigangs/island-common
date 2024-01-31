package com.lkyl.island.common.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDate;
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
public class SysUserQueryListDTO implements Serializable {
    private static final long serialVersionUID = -59091222127665624L;


        @ApiModelProperty("用户账号")
        private String userName;
        @ApiModelProperty("用户昵称")
        private String nickName;

        @ApiModelProperty("手机号码")
        private String phoneNumber;
        @ApiModelProperty("帐号状态（1正常 0停用）")
        private String status;
        @ApiModelProperty("创建者")
        private LocalDate startDate;
        @ApiModelProperty("更新者")
        private LocalDate endDate;


}

