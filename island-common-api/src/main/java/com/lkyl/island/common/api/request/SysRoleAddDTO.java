package com.lkyl.island.common.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * 角色信息表(SysRole)请求实体DTO
 *
 * @author author
 * @since 2022-06-04 20:29:56
 */
@Data
@ApiModel("角色信息表请求体")
@ToString
public class SysRoleAddDTO implements Serializable {
    private static final long serialVersionUID = -81400754570874657L;
    @NotBlank(message = "角艷名称不能数为空")
        @ApiModelProperty("角色名称")
        private String roleName;
    @NotBlank(message = "角色权限不能为空")
        @ApiModelProperty("角色权限字符串")
        private String roleKey;
    @NotNull(message = "排序不能为空")
        @ApiModelProperty("显示顺序")
        private Integer roleSort;
    @NotBlank(message = "角色状态不能为空")
        @ApiModelProperty("角色状态（1正常 0停用）")
        private String status;

    @ApiModelProperty("菜单")
    private Long[] menuIds;
    private String remark;
}

