package com.lkyl.island.common.ps.entity;

import java.io.Serializable;
import com.lkyl.oceanframework.mybatis.base.BaseDomain;
import lombok.Data;
import lombok.ToString;
import java.util.Date;

@Data
@ToString
public class SysRoleMenu implements Serializable {
    private static final long serialVersionUID = -59985905955278461L;
    /**
     * 角色ID
     */
    private Long roleId;
    /**
     * 菜单ID
     */
    private Long menuId;
    /**
     * 租户
     */
    private String tenantId;


}

