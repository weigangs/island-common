package com.lkyl.island.common.api.request;

import java.io.Serializable;
import lombok.Data;
import lombok.ToString;
import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 菜单权限表(SysMenu)请求实体DTO
 *
 * @author author
 * @since 2022-06-04 20:29:56
 */
@Data
@ApiModel("菜单权限表请求体")
@ToString
public class SysMenuDTO implements Serializable {
    private static final long serialVersionUID = 239759664696176298L;
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
        private String isVisible;
        @ApiModelProperty("菜单状态（0正常 1停用）")
        private String status;




}

