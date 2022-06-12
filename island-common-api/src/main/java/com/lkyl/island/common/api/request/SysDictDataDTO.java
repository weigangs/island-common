package com.lkyl.island.common.api.request;

import java.io.Serializable;
import lombok.Data;
import lombok.ToString;
import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 字典数据表(SysDictData)请求实体DTO
 *
 * @author author
 * @since 2022-06-04 20:29:56
 */
@Data
@ApiModel("字典数据表请求体")
@ToString
public class SysDictDataDTO implements Serializable {
    private static final long serialVersionUID = -32950534235335680L;
        @ApiModelProperty("字典编码")
        private Long dictCode;
        @ApiModelProperty("字典排序")
        private Integer dictSort;
        @ApiModelProperty("字典标签")
        private String dictLabel;
        @ApiModelProperty("字典键值")
        private String dictValue;
        @ApiModelProperty("字典类型")
        private String dictType;
        @ApiModelProperty("样式属性（其他样式扩展）")
        private String cssClass;
        @ApiModelProperty("表格回显样式")
        private String listClass;
        @ApiModelProperty("是否默认（Y是 N否）")
        private String isDefault;
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

