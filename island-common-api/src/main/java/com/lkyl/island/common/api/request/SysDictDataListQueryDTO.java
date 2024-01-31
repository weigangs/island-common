package com.lkyl.island.common.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

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
public class SysDictDataListQueryDTO implements Serializable {
    private static final long serialVersionUID = -32950534235335680L;
        @ApiModelProperty("字典标签")
        private String dictLabel;

        @ApiModelProperty("字典类型")
        private String dictType;

        @ApiModelProperty("字典类型")
        private String dictName;



}

