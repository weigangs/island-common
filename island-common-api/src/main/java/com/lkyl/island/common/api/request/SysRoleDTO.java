package com.lkyl.island.common.api.request;

import java.io.Serializable;
import lombok.Data;
import lombok.ToString;
import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
@Data
@ApiModel("角色信息表请求体")
@ToString
public class SysRoleDTO implements Serializable {
    private static final long serialVersionUID = -31174539854662927L;
        @ApiModelProperty("角色ID")
        private Long roleId;
        @ApiModelProperty("角色名称")
        private String roleName;
        @ApiModelProperty("角色权限字符串")
        private String roleKey;
        @ApiModelProperty("显示顺序")
        private Integer roleSort;
        @ApiModelProperty("数据范围（1：全部数据权限 2：自定数据权限 3：本部门数据权限 4：本部门及以下数据权限）")
        private String dataScope;
        @ApiModelProperty("菜单树选择项是否关联显示")
        private Integer menuCheckStrictly;
        @ApiModelProperty("部门树选择项是否关联显示")
        private Integer deptCheckStrictly;
        @ApiModelProperty("角色状态（0正常 1停用）")
        private String status;
        @ApiModelProperty("删除标识")
        private String delFlag;
        @ApiModelProperty("创建时间")
        private Date createTime;
        @ApiModelProperty("更新时间")
        private Date updateTime;
        @ApiModelProperty("租户")
        private String tenantId;
        @ApiModelProperty("备注")
        private String remark;
        @ApiModelProperty("创建者")
        private String createUser;
        @ApiModelProperty("更新者")
        private String updateUser;


}

