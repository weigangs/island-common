package com.lkyl.island.common.api.response;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * 角色信息表(SysRole)响应实体VO
 *
 * @author author
 * @since 2022-06-04 20:29:56
 */
@Data
@ApiModel("角色信息表响应体")
@ToString
public class RoleNameVO implements Serializable {
    private static final long serialVersionUID = -36628282553789285L;
         @ApiModelProperty("角色ID")
         @JsonSerialize(using = ToStringSerializer.class)
         private Long roleId;
         @ApiModelProperty("角色名称")
         private String roleName;
}

