package com.lkyl.island.common.api.request;

import java.io.Serializable;
import lombok.Data;
import lombok.ToString;
import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
@Data
@ApiModel("岗位信息表请求体")
@ToString
public class SysPostDTO implements Serializable {
    private static final long serialVersionUID = 339005095450658173L;
        @ApiModelProperty("岗位ID")
        private Long postId;
        @ApiModelProperty("岗位编码")
        private String postCode;
        @ApiModelProperty("岗位名称")
        private String postName;
        @ApiModelProperty("显示顺序")
        private Integer postSort;
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

