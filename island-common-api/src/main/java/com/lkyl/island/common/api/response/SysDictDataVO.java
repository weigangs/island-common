package com.lkyl.island.common.api.response;

import java.io.Serializable;
import lombok.Data;
import lombok.ToString;
import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 字典数据表(SysDictData)响应实体VO
 *
 * @author author
 * @since 2022-06-04 20:29:56
 */
@Data
@ApiModel("字典数据表响应体")
@ToString
public class SysDictDataVO implements Serializable {
    private static final long serialVersionUID = -15982809993667295L;
         @ApiModelProperty("字典标签")
         private String dictLabel;
         @ApiModelProperty("字典键值")
         private String dictValue;


}

