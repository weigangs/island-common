package com.lkyl.island.common.ps.entity;

import java.io.Serializable;
import com.lkyl.oceanframework.mybatis.base.BaseDomain;
import lombok.Data;
import lombok.ToString;
import java.util.Date;

/**
 * 角色信息表(SysRole)实体类DO
 *
 * @author author
 * @since 2022-06-04 20:29:56
 */

@Data
@ToString
public class SysRole implements Serializable {
    private static final long serialVersionUID = -85439893837344353L;
    /**
     * 角色ID
     */
    private Long roleId;
    /**
     * 角色名称
     */
    private String roleName;
    /**
     * 角色权限字符串
     */
    private String roleKey;
    /**
     * 显示顺序
     */
    private Integer roleSort;
    /**
     * 数据范围（1：全部数据权限 2：自定数据权限 3：本部门数据权限 4：本部门及以下数据权限）
     */
    private String dataScope;
    /**
     * 菜单树选择项是否关联显示
     */
    private Integer menuCheckStrictly;
    /**
     * 部门树选择项是否关联显示
     */
    private Integer deptCheckStrictly;
    /**
     * 角色状态（0正常 1停用）
     */
    private String status;
    /**
     * 删除标识
     */
    private String delFlag;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;
    /**
     * 租户
     */
    private String tenantId;
    /**
     * 备注
     */
    private String remark;
    /**
     * 创建者
     */
    private String createUser;
    /**
     * 更新者
     */
    private String updateUser;


}

