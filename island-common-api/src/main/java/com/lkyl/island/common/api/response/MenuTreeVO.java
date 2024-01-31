package com.lkyl.island.common.api.response;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * @author nicholas
 * @date 2023/07/23 21:56
 */
@Data
@ApiModel("菜单权限表响应体")
@ToString
public class MenuTreeVO {
    @ApiModelProperty("菜单ID")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long menuId;

    @ApiModelProperty("菜单名称")
    private String label;

    private List<MenuTreeVO> children;
}
