package com.lkyl.island.common.api.response;

import java.io.Serializable;
import lombok.Data;
import lombok.ToString;
import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Data
@ApiModel("角色和菜单关联表响应体")
@ToString
public class SysRoleMenuVO implements Serializable {
    private static final long serialVersionUID = -20854140300158375L;
         @ApiModelProperty("角色ID")
         private Long roleId;
         @ApiModelProperty("菜单ID")
         private Long menuId;


}

