package com.lkyl.island.common.dict.service;

import com.lkyl.island.common.api.request.*;
import com.lkyl.island.common.api.response.SysDictDataListVO;
import com.lkyl.island.common.api.response.SysDictDataVO;
import com.lkyl.island.common.api.response.SysDictTypeListVO;
import com.lkyl.island.common.api.response.SysDictTypeVO;
import com.lkyl.oceanframework.common.utils.page.PageArgs;

import java.util.List;

public interface ISysDictService {

    int addDictType(SysDictTypeAddDTO sysDictTypeAddDTO);

    List<SysDictTypeListVO> pageQueryDictType(SysDictTypeListQueryDTO sysDictTypeListQueryDTO, PageArgs pageArgs);

    List<SysDictDataVO> queryDictData(String dictType);

    List<SysDictDataListVO> pageQueryDictData(SysDictDataListQueryDTO sysDictDataListQueryDTO, PageArgs pageArgs);

    SysDictTypeVO getDictTypeById(Long dictTypeId);

    int addDictData(SysDictDataAddDTO sysDictDataAddDTO);

    int updateDictData(SysDictDataUpdateDTO sysDictDataUpdateDTO);

    int deleteDictData(SysDictDataDeleteDTO sysDictDataDeleteDTO);

}
