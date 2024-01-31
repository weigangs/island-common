package com.lkyl.island.common.api.request;

import lombok.Data;
import lombok.ToString;

import java.time.LocalDate;

/**
 * @author nicholas
 * @date 2023/07/22 20:38
 */
@ToString
@Data
public class SysDictTypeListQueryDTO {

    private String dictName;

    private String dictType;

    private LocalDate startDate;

    private LocalDate endDate;
//
//    private Integer pageNum;
//
//    private Integer pageSize;
}
