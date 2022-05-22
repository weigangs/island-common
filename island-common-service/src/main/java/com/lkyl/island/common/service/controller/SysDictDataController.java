package com.lkyl.island.common.service.controller;

import com.lkyl.island.common.ps.entity.SysDictData;
import com.lkyl.island.common.api.request.SysDictDataDTO;
import com.lkyl.island.common.service.service.SysDictDataService;
import com.lkyl.island.common.service.converter.SysDictDataConverter;
import com.lkyl.oceanframework.common.utils.constant.CommonCode;
import com.lkyl.oceanframework.common.utils.constant.PageConstant;
import com.lkyl.oceanframework.common.utils.exception.CommonException;
import com.lkyl.oceanframework.web.util.CommonResultUtil;
import com.lkyl.oceanframework.common.utils.utils.PageUtil;
import com.lkyl.oceanframework.common.utils.utils.CollectionUtils;
import org.springframework.beans.BeanUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.*;

import javax.annotation.Resource;

/**
 * 字典数据表(SysDictData)表控制层
 *
 * @author author
 * @since 2022-05-21 18:11:52
 */
@Slf4j
@RestController
@RequestMapping("sysDictData")
public class SysDictDataController {
    /**
     * 服务对象
    */
    @Resource
    private SysDictDataService sysDictDataService;

	/**
     * 根据主键ID查询
     *
     * @param id
     * @return 查询结果
    */
	@GetMapping("/getById/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Long id) {
        log.info("get SysDictData info start...");
        Optional<SysDictData> sysDictData = this.sysDictDataService.get(id);

        if(null == sysDictData){
            throw new CommonException(CommonCode.EXCEPTION, "返回对象为NULL!");
        }

        return CommonResultUtil.success("获取成功", sysDictData);
    }

	/**
     * 分页查询
     *
     * @param sysDictDataDTO 筛选条件
     * @return 查询结果
     */
	@PostMapping("/search")
    public ResponseEntity<?> search(@RequestParam(value = "page", required = false, defaultValue = "1") String pageString,
                            @RequestParam(value = "per_page", required = false, defaultValue = "10") String perPageString,
                            @RequestBody(required = false) SysDictDataDTO sysDictDataDTO) {
        if(log.isInfoEnabled()) {
            log.info("search SysDictData start...");
        }

        int page = PageUtil.parsePage(pageString, PageConstant.PAGE);
        int perPage = PageUtil.parsePerPage(perPageString, PageConstant.PER_PAGE);
        PageHelper.startPage(page, perPage);
		//PageHelper.startPage(page, perPage, "update_time desc");
		SysDictData queryEntity = new SysDictData();

        BeanUtils.copyProperties(sysDictDataDTO, queryEntity);
        List<SysDictData> sysDictDataList = this.sysDictDataService.list(queryEntity);
		PageInfo pageInfo = new PageInfo<>(sysDictDataList);

		if(CollectionUtils.isNotEmpty(pageInfo.getList())) {
            pageInfo.setList(SysDictDataConverter.INSTANCE.to(pageInfo.getList()));
        }

        return CommonResultUtil.pagingSuccess("查询成功", pageInfo);
    }

	/**
     * 新增
     * @param sysDictDataDTO
     * @return
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseEntity<?> save(@RequestBody SysDictDataDTO sysDictDataDTO) {
        if(log.isInfoEnabled()) {
            log.info("save SysDictData start...");
        }
		SysDictData saveEntity = new SysDictData();

        BeanUtils.copyProperties(sysDictDataDTO, saveEntity);
		saveEntity.setCreateTime(new Date());
		saveEntity.setUpdateTime(new Date());
        if (this.sysDictDataService.save(saveEntity) != 1) {
            throw new CommonException(CommonCode.EXCEPTION, "新增失败!");
        }

        return CommonResultUtil.success("新增成功", saveEntity);
    }

	/**
     * 修改
     * @param sysDictDataDTO
     * @return
     */
    @PostMapping("/update")
    public ResponseEntity<?> update(@RequestBody SysDictDataDTO sysDictDataDTO) {
        if(log.isInfoEnabled()) {
            log.info("update SysDictData start....");
        }
		SysDictData updateEntity = new SysDictData();

        BeanUtils.copyProperties(sysDictDataDTO, updateEntity);
		updateEntity.setUpdateTime(new Date());
        if(this.sysDictDataService.updateById(updateEntity) != 1) {
            throw new CommonException(CommonCode.EXCEPTION, "更新失败!");
        }

        return CommonResultUtil.success("更新成功", updateEntity);
    }

	/**
     * 根据ID删除
     *
     * @param id
     * @return
     */
    @DeleteMapping("/remove/{id}")
    public ResponseEntity<?> remove(@PathVariable("id") Long id) {
        if(log.isInfoEnabled()) {
            log.info("remove SysDictData by id start...");
        }
        if (this.sysDictDataService.remove(id) != 1) {
            throw new CommonException(CommonCode.EXCEPTION, "删除失败!");
        }

        return CommonResultUtil.success("删除成功");
    }
}
