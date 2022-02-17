package com.lkyl.island.common.ps.entity;

import java.io.Serializable;
import com.lkyl.oceanframework.mybatis.base.BaseDomain;
import lombok.Data;
import lombok.ToString;
import java.util.Date;

@Data
@ToString
public class SysUserRole implements Serializable {
    private static final long serialVersionUID = 357292533912418521L;
    /**
     * 用户ID
     */
    private Long userId;
    /**
     * 角色ID
     */
    private Long roleId;


}

