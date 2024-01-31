package com.lkyl.island.common.api.response;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 字典数据表(SysDictData)响应实体VO
 *
 * @author author
 * @since 2022-06-04 20:29:56
 */
@Data
@ApiModel("字典数据表响应体")
@ToString
public class SysDictDataListVO implements Serializable {
    private static final long serialVersionUID = -15982809993667295L;
    @JsonSerialize(using = ToStringSerializer.class)
    private Long dictDataId;
         @ApiModelProperty("字典标签")
         private String dictLabel;
         @ApiModelProperty("字典键值")
         private String dictValue;

    @ApiModelProperty("字典排序")
    private String dictSort;

    private LocalDateTime createTime;
}

