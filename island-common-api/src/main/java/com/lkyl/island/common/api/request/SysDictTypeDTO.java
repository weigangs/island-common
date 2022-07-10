package com.lkyl.island.common.api.request;

import java.io.Serializable;
import lombok.Data;
import lombok.ToString;
import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 字典类型表(SysDictType)请求实体DTO
 *
 * @author author
 * @since 2022-06-04 20:29:56
 */
@Data
@ApiModel("字典类型表请求体")
@ToString
public class SysDictTypeDTO implements Serializable {
    private static final long serialVersionUID = -51068348993438142L;
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

