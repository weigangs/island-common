package com.lkyl.island.common.api.response;

import java.io.Serializable;
import lombok.Data;
import lombok.ToString;
import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Data
@ApiModel("菜单权限表响应体")
@ToString
public class SysMenuVO implements Serializable {
    private static final long serialVersionUID = 333123353748647184L;
         @ApiModelProperty("菜单ID")
         private Long menuId;
         @ApiModelProperty("菜单名称")
         private String menuName;
         @ApiModelProperty("父菜单ID")
         private Long parentId;
         @ApiModelProperty("显示顺序")
         private Integer orderNum;
         @ApiModelProperty("路由地址")
         private String path;
         @ApiModelProperty("组件路径")
         private String component;
         @ApiModelProperty("是否为外链（0是 1否）")
         private Integer isFrame;
         @ApiModelProperty("是否缓存（0缓存 1不缓存）")
         private Integer isCache;
         @ApiModelProperty("菜单类型（M目录 C菜单 F按钮）")
         private String menuType;
         @ApiModelProperty("菜单状态（0显示 1隐藏）")
         private String visible;
         @ApiModelProperty("菜单状态（0正常 1停用）")
         private String status;
         @ApiModelProperty("权限标识")
         private String perms;
         @ApiModelProperty("菜单图标")
         private String icon;
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

