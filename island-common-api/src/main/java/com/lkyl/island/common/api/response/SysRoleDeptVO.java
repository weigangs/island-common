package com.lkyl.island.common.api.response;

import java.io.Serializable;
import lombok.Data;
import lombok.ToString;
import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 角色和部门关联表(SysRoleDept)响应实体VO
 *
 * @author author
 * @since 2022-06-04 20:29:56
 */
@Data
@ApiModel("角色和部门关联表响应体")
@ToString
public class SysRoleDeptVO implements Serializable {
    private static final long serialVersionUID = -82180618084019200L;
         @ApiModelProperty("角色ID")
         private Long roleId;
         @ApiModelProperty("部门ID")
         private Long deptId;
         @ApiModelProperty("租户")
         private String tenantId;


}

