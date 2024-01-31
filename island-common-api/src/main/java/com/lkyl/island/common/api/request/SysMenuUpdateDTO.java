package com.lkyl.island.common.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 菜单权限表(SysMenu)请求实体DTO
 *
 * @author author
 * @since 2022-06-04 20:29:56
 */
@Data
@ApiModel("菜单权限表请求体")
@ToString
public class SysMenuUpdateDTO implements Serializable {
    private static final long serialVersionUID = 239759664696176298L;



    @NotNull(message = "菜单ID不能数为空")
    @ApiModelProperty("菜单ID")
    private Long menuId;
        @ApiModelProperty("父菜单ID")
        private Long parentId;

        @ApiModelProperty("菜单类型（M目录 C菜单 F按钮）")
        private String menuType;
        @ApiModelProperty("图标")
        private String icon;
    @ApiModelProperty("菜单名称")
    private String menuName;
    @ApiModelProperty("显示顺序")
    private Integer orderNum;
    @ApiModelProperty("是否为外链（1是 0否）")
    private String isFrame;
    @ApiModelProperty("路由地址")
    private String path;
    @ApiModelProperty("组件路径")
    private String component;
    @ApiModelProperty("权限")
    private String perms;
//    @ApiModelProperty("路由参数")
//    private String query;
    @ApiModelProperty("是否缓存（1缓存 0不缓存）")
    private String isCache;

    @ApiModelProperty("菜单状态（1显示 0隐藏）")
        private String isVisible;
        @ApiModelProperty("菜单状态（1正常 0停用）")
        private String status;




}

