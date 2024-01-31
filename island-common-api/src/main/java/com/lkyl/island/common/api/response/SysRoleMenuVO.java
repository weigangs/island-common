package com.lkyl.island.common.api.response;

import java.io.Serializable;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;
import lombok.ToString;
import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 角色和菜单关联表(SysRoleMenu)响应实体VO
 *
 * @author author
 * @since 2022-06-04 20:29:56
 */
@Data
@ApiModel("角色和菜单关联表响应体")
@ToString
public class SysRoleMenuVO implements Serializable {
    private static final long serialVersionUID = 410185919420841751L;
         @ApiModelProperty("角色ID")
     @JsonSerialize(using = ToStringSerializer.class)
         private Long roleId;
         @ApiModelProperty("菜单ID")
         private Long menuId;
         @ApiModelProperty("租户")
         private String tenantId;


}

