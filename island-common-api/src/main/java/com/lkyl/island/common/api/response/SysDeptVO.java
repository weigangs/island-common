package com.lkyl.island.common.api.response;

import java.io.Serializable;
import lombok.Data;
import lombok.ToString;
import java.util.Date;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Data
@ApiModel("部门表响应体")
@ToString
public class SysDeptVO implements Serializable {
    private static final long serialVersionUID = 361446688669097213L;
         @ApiModelProperty("部门id")
         private Long deptId;
         @ApiModelProperty("父部门id")
         private Long parentId;
         @ApiModelProperty("祖级列表")
         private String ancestors;
         @ApiModelProperty("部门名称")
         private String deptName;
         @ApiModelProperty("显示顺序")
         private Integer orderNum;
         @ApiModelProperty("负责人")
         private String leader;
         @ApiModelProperty("联系电话")
         private String phone;
         @ApiModelProperty("邮箱")
         private String email;
         @ApiModelProperty("部门状态（0正常 1停用）")
         private String status;
         @ApiModelProperty("删除标志")
         private String delFlag;
         @ApiModelProperty("创建时间")
         private Date createTime;
         @ApiModelProperty("更新时间")
         private Date updateTime;
         @ApiModelProperty("租户")
         private String tenantId;
         @ApiModelProperty("创建者")
         private String createUser;
         @ApiModelProperty("更新者")
         private String updateUser;
     @ApiModelProperty("子部门列表")
     private List<SysDeptVO> childList;


}

