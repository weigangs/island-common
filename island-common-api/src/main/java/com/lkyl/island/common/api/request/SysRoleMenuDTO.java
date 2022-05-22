package com.lkyl.island.common.api.request;

import java.io.Serializable;
import lombok.Data;
import lombok.ToString;
import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
@Data
@ApiModel("角色和菜单关联表请求体")
@ToString
public class SysRoleMenuDTO implements Serializable {
    private static final long serialVersionUID = -41799118706476162L;
        @ApiModelProperty("角色ID")
        private Long roleId;
        @ApiModelProperty("菜单ID")
        private Long menuId;
        @ApiModelProperty("租户")
        private String tenantId;


}

