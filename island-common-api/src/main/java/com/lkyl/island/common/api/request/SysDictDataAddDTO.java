package com.lkyl.island.common.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * 字典数据表(SysDictData)请求实体DTO
 *
 * @author author
 * @since 2022-06-04 20:29:56
 */
@Data
@ApiModel("字典数据表请求体")
@ToString
public class SysDictDataAddDTO implements Serializable {
    private static final long serialVersionUID = -32950534235335680L;

    @NotNull(message = "字典排序不能为空")
    @ApiModelProperty("字典排序")
    private Integer dictSort;
    @NotBlank(message = "字典标签不能为空")
    @ApiModelProperty("字典标签")
    private String dictLabel;
    @NotBlank(message = "字典键值不能为空")
    @ApiModelProperty("字典键值")
    private String dictValue;
    @NotBlank(message = "字典类型不能为空")
    @ApiModelProperty("字典类型")
    private String dictType;
}

