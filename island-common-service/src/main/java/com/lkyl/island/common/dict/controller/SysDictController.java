package com.lkyl.island.common.dict.controller;

import com.lkyl.island.common.api.request.*;
import com.lkyl.island.common.api.response.SysDictDataListVO;
import com.lkyl.island.common.api.response.SysDictDataVO;
import com.lkyl.island.common.api.response.SysDictTypeListVO;
import com.lkyl.island.common.api.response.SysDictTypeVO;
import com.lkyl.island.common.dict.service.ISysDictService;
import com.lkyl.oceanframework.common.utils.page.PageArgs;
import com.lkyl.oceanframework.common.utils.result.CommonResult;
import com.lkyl.oceanframework.common.utils.result.PageResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author nicholas
 * @date 2023/07/22 20:32
 */

@RestController
@RequestMapping("system")
public class SysDictController {

    @Resource
    private ISysDictService iSysDictService;

    @GetMapping("/dict/type/list")
    public PageResult<SysDictTypeListVO> typeList(SysDictTypeListQueryDTO sysDictTypeDTO, PageArgs pageArgs) {
        return PageResult.page(iSysDictService.pageQueryDictType(sysDictTypeDTO, pageArgs));
    }

    @PostMapping("/dict/type/add")
    public CommonResult<String> addDictType(@RequestBody SysDictTypeAddDTO sysDictTypeAddDTO) {
        iSysDictService.addDictType(sysDictTypeAddDTO);
        return CommonResult.ok();
    }

    @GetMapping("/dict/data/type/{dictType}")
    public CommonResult<List<SysDictDataVO>> dictDataByType(@PathVariable String dictType) {
        return CommonResult.ok(iSysDictService.queryDictData(dictType));
    }

    @GetMapping("/dict/data/list")
    public PageResult<SysDictDataListVO> dictDataList(SysDictDataListQueryDTO sysDictDataVO, PageArgs pageArgs) {
        return PageResult.page(iSysDictService.pageQueryDictData(sysDictDataVO, pageArgs));
    }

    @GetMapping("/dict/type/{dictTypeId}")
    public CommonResult<SysDictTypeVO> dictTypeById(@PathVariable Long dictTypeId) {
        return CommonResult.ok(iSysDictService.getDictTypeById(dictTypeId));
    }

    @PostMapping("/dict/data/add")
    public CommonResult<String> addDictData(@RequestBody SysDictDataAddDTO sysDictDataAddDTO) {
        iSysDictService.addDictData(sysDictDataAddDTO);
        return CommonResult.ok();
    }

    @PostMapping("/dict/data/update")
    public CommonResult<String> updateDictData(@RequestBody SysDictDataUpdateDTO sysDictDataAddDTO) {
        iSysDictService.updateDictData(sysDictDataAddDTO);
        return CommonResult.ok();
    }

    @PostMapping("/dict/data/delete")
    public CommonResult<String> deleteDictData(@RequestBody SysDictDataDeleteDTO sysDictDataDeleteDTO) {
        iSysDictService.deleteDictData(sysDictDataDeleteDTO);
        return CommonResult.ok();
    }


}
