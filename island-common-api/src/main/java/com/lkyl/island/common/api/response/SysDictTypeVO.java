package com.lkyl.island.common.api.response;

import java.io.Serializable;
import lombok.Data;
import lombok.ToString;
import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Data
@ApiModel("字典类型表响应体")
@ToString
public class SysDictTypeVO implements Serializable {
    private static final long serialVersionUID = -82707142189141853L;
         @ApiModelProperty("字典主键")
         private Long dictId;
         @ApiModelProperty("字典名称")
         private String dictName;
         @ApiModelProperty("字典类型")
         private String dictType;
         @ApiModelProperty("状态（0正常 1停用）")
         private String status;
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

