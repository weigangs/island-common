package com.lkyl.island.common.api.request;

import java.io.Serializable;
import lombok.Data;
import lombok.ToString;
import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
@Data
@ApiModel("用户和角色关联表请求体")
@ToString
public class SysUserRoleDTO implements Serializable {
    private static final long serialVersionUID = -54951247712361330L;
        @ApiModelProperty("用户ID")
        private Long userId;
        @ApiModelProperty("角色ID")
        private Long roleId;
        @ApiModelProperty("租户")
        private String tenantId;


}

