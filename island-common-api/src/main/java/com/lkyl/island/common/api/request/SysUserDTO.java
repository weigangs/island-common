package com.lkyl.island.common.api.request;

import java.io.Serializable;
import lombok.Data;
import lombok.ToString;
import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
@Data
@ApiModel("用户信息表请求体")
@ToString
public class SysUserDTO implements Serializable {
    private static final long serialVersionUID = 151402141314293134L;
        @ApiModelProperty("用户ID")
        private Long userId;
        @ApiModelProperty("部门ID")
        private Long deptId;
        @ApiModelProperty("用户账号")
        private String userName;
        @ApiModelProperty("用户昵称")
        private String nickName;
        @ApiModelProperty("用户类型（00系统用户）")
        private String userType;
        @ApiModelProperty("用户邮箱")
        private String email;
        @ApiModelProperty("手机号码")
        private String phonenumber;
        @ApiModelProperty("用户性别（0男 1女 2未知）")
        private String sex;
        @ApiModelProperty("头像地址")
        private String avatar;
        @ApiModelProperty("密码")
        private String password;
        @ApiModelProperty("帐号状态（0正常 1停用）")
        private String status;
        @ApiModelProperty("删除标志（0代表存在 2代表删除）")
        private String delFlag;
        @ApiModelProperty("最后登录IP")
        private String loginIp;
        @ApiModelProperty("最后登录时间")
        private Date loginDate;
        @ApiModelProperty("创建者")
        private String createBy;
        @ApiModelProperty("创建时间")
        private Date createTime;
        @ApiModelProperty("更新者")
        private String updateBy;
        @ApiModelProperty("更新时间")
        private Date updateTime;
        @ApiModelProperty("备注")
        private String remark;


}

