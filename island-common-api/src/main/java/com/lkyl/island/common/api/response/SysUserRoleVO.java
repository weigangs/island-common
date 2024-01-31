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
 * 用户和角色关联表(SysUserRole)响应实体VO
 *
 * @author author
 * @since 2022-06-04 20:29:56
 */
@Data
@ApiModel("用户和角色关联表响应体")
@ToString
public class SysUserRoleVO implements Serializable {
    private static final long serialVersionUID = -76750135583317314L;
         @ApiModelProperty("用户ID")
         @JsonSerialize(using = ToStringSerializer.class)
         private Long userId;
         @ApiModelProperty("角色ID")
         private Long roleId;
         @ApiModelProperty("租户")
         private String tenantId;


}

