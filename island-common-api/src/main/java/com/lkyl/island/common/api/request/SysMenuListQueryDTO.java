package com.lkyl.island.common.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

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
public class SysMenuListQueryDTO implements Serializable {
    private static final long serialVersionUID = 239759664696176298L;

        @ApiModelProperty("菜单名称")
        private String menuName;
        @ApiModelProperty("菜单状态（0正常 1停用）")
        private String status;




}

