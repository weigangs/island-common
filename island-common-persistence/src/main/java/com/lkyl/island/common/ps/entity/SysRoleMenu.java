package com.lkyl.island.common.ps.entity;

import java.io.Serializable;
import com.lkyl.oceanframework.mybatis.base.BaseDomain;
import lombok.Data;
import lombok.ToString;
import java.util.Date;

/**
 * 角色和菜单关联表(SysRoleMenu)实体类DO
 *
 * @author author
 * @since 2022-06-04 20:29:56
 */

@Data
@ToString
public class SysRoleMenu implements Serializable {
    private static final long serialVersionUID = -38180828526131178L;
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

