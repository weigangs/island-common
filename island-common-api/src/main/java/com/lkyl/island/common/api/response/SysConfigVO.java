package com.lkyl.island.common.api.response;

import java.io.Serializable;
import lombok.Data;
import lombok.ToString;
import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 参数配置表(SysConfig)响应实体VO
 *
 * @author author
 * @since 2022-06-04 20:29:56
 */
@Data
@ApiModel("参数配置表响应体")
@ToString
public class SysConfigVO implements Serializable {
    private static final long serialVersionUID = 583017690528641504L;
         @ApiModelProperty("参数主键")
         private Integer configId;
         @ApiModelProperty("参数名称")
         private String configName;
         @ApiModelProperty("参数键名")
         private String configKey;
         @ApiModelProperty("参数键值")
         private String configValue;
         @ApiModelProperty("系统内置（Y是 N否）")
         private String configType;
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
         @ApiModelProperty("删除标识")
         private String delFlag;


}

