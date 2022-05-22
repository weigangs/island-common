package com.lkyl.island.common.api.response;

import java.io.Serializable;
import lombok.Data;
import lombok.ToString;
import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Data
@ApiModel("用户和角色关联表响应体")
@ToString
public class SysUserRoleVO implements Serializable {
    private static final long serialVersionUID = -76975571487835824L;
         @ApiModelProperty("用户ID")
         private Long userId;
         @ApiModelProperty("角色ID")
         private Long roleId;
         @ApiModelProperty("租户")
         private String tenantId;


}

