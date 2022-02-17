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
    private static final long serialVersionUID = -54999629518299544L;
        @ApiModelProperty("角色ID")
        private Long roleId;
        @ApiModelProperty("菜单ID")
        private Long menuId;


}

