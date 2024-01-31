package com.lkyl.island.common.api.response;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * @author nicholas
 * @date 2023/07/22 21:42
 */

@Data
@ToString
public class SysDictTypeListVO {
    // Long jsonSerialize 不生效
    @JsonSerialize(using = ToStringSerializer.class)
    private Long dictId;

    private String dictName;

    private String dictType;

    private LocalDateTime createTime;
}
