package com.lkyl.island.common.dict.service;

import com.lkyl.island.common.api.request.SysDictTypeListQueryDTO;
import com.lkyl.island.common.ps.entity.SysDictType;
import com.lkyl.oceanframework.common.utils.page.PageArgs;

import java.util.List;

public interface ISysDictQueryService {

    List<SysDictType> pageQueryDictType(SysDictTypeListQueryDTO sysDictTypeListQueryDTO, PageArgs pageArgs);
}
