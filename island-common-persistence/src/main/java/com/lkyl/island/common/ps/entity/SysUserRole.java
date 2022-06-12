package com.lkyl.island.common.ps.entity;

import java.io.Serializable;
import com.lkyl.oceanframework.mybatis.base.BaseDomain;
import lombok.Data;
import lombok.ToString;
import java.util.Date;

/**
 * 用户和角色关联表(SysUserRole)实体类DO
 *
 * @author author
 * @since 2022-06-04 20:29:56
 */

@Data
@ToString
public class SysUserRole implements Serializable {
    private static final long serialVersionUID = 173200837170943435L;
    /**
     * 用户ID
     */
    private Long userId;
    /**
     * 角色ID
     */
    private Long roleId;
    /**
     * 租户
     */
    private String tenantId;


}

