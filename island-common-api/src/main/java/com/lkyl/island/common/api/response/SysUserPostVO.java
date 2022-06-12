package com.lkyl.island.common.api.response;

import java.io.Serializable;
import lombok.Data;
import lombok.ToString;
import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 用户与岗位关联表(SysUserPost)响应实体VO
 *
 * @author author
 * @since 2022-06-04 20:29:56
 */
@Data
@ApiModel("用户与岗位关联表响应体")
@ToString
public class SysUserPostVO implements Serializable {
    private static final long serialVersionUID = -17041690165626577L;
         @ApiModelProperty("用户ID")
         private Long userId;
         @ApiModelProperty("岗位ID")
         private Long postId;
         @ApiModelProperty("租户")
         private String tenantId;


}

