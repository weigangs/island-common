package com.lkyl.island.common.api.request;

import java.io.Serializable;
import lombok.Data;
import lombok.ToString;
import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 角色和菜单关联表(SysRoleMenu)请求实体DTO
 *
 * @author author
 * @since 2022-06-04 20:29:56
 */
@Data
@ApiModel("角色和菜单关联表请求体")
@ToString
public class SysRoleMenuDTO implements Serializable {
    private static final long serialVersionUID = 927676649117634931L;
        @ApiModelProperty("角色ID")
        private Long roleId;
        @ApiModelProperty("菜单ID")
        private Long menuId;
        @ApiModelProperty("租户")
        private String tenantId;


}

