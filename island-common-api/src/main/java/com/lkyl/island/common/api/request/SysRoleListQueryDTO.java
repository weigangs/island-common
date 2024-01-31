package com.lkyl.island.common.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * 角色信息表(SysRole)请求实体DTO
 *
 * @author author
 * @since 2022-06-04 20:29:56
 */
@Data
@ApiModel("角色信息表请求体")
@ToString
public class SysRoleListQueryDTO implements Serializable {
    private static final long serialVersionUID = -81400754570874657L;

        @ApiModelProperty("角色名称")
        private String roleName;
        @ApiModelProperty("角色权限字符串")
        private String roleKey;

        @ApiModelProperty("角色状态（1正常 0停用）")
        private String status;

        private LocalDateTime startDateTime;

        private LocalDateTime endDateTime;

}

