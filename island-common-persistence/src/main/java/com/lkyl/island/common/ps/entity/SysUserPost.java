package com.lkyl.island.common.ps.entity;

import java.io.Serializable;
import com.lkyl.oceanframework.mybatis.base.BaseDomain;
import lombok.Data;
import lombok.ToString;
import java.util.Date;

/**
 * 用户与岗位关联表(SysUserPost)实体类DO
 *
 * @author author
 * @since 2022-06-04 20:29:56
 */

@Data
@ToString
public class SysUserPost implements Serializable {
    private static final long serialVersionUID = 768095879420667485L;
    /**
     * 用户ID
     */
    private Long userId;
    /**
     * 岗位ID
     */
    private Long postId;
    /**
     * 租户
     */
    private String tenantId;


}

