package com.lkyl.island.common.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * 字典类型表(SysDictType)请求实体DTO
 *
 * @author author
 * @since 2022-06-04 20:29:56
 */
@Data
@ApiModel("字典类型表请求体")
@ToString
public class SysDictTypeAddDTO implements Serializable {
    private static final long serialVersionUID = -51068348993438142L;
        @ApiModelProperty("字典名称")
        private String dictName;
        @ApiModelProperty("字典类型")
        private String dictType;
}

