package com.lkyl.island.common.api.request;

import java.io.Serializable;
import lombok.Data;
import lombok.ToString;
import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
@Data
@ApiModel("角色和部门关联表请求体")
@ToString
public class SysRoleDeptDTO implements Serializable {
    private static final long serialVersionUID = 554273528412056728L;
        @ApiModelProperty("角色ID")
        private Long roleId;
        @ApiModelProperty("部门ID")
        private Long deptId;
        @ApiModelProperty("租户")
        private String tenantId;
}

