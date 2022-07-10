package com.lkyl.island.common.ps.entity;

import java.io.Serializable;
import com.lkyl.oceanframework.mybatis.base.BaseDomain;
import lombok.Data;
import lombok.ToString;
import java.util.Date;

/**
 * 角色和部门关联表(SysRoleDept)实体类DO
 *
 * @author author
 * @since 2022-06-04 20:29:56
 */

@Data
@ToString
public class SysRoleDept implements Serializable {
    private static final long serialVersionUID = -52365406300656001L;
    /**
     * 角色ID
     */
    private Long roleId;
    /**
     * 部门ID
     */
    private Long deptId;
    /**
     * 租户
     */
    private String tenantId;


}

