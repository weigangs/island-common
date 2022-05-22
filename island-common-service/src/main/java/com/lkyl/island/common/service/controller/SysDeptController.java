package com.lkyl.island.common.service.controller;

import com.lkyl.island.common.ps.entity.SysDept;
import com.lkyl.island.common.api.request.SysDeptDTO;
import com.lkyl.island.common.service.service.SysDeptService;
import com.lkyl.island.common.service.converter.SysDeptConverter;
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
 * 部门表(SysDept)表控制层
 *
 * @author author
 * @since 2022-05-21 18:11:52
 */
@Slf4j
@RestController
@RequestMapping("sysDept")
public class SysDeptController {
    /**
     * 服务对象
    */
    @Resource
    private SysDeptService sysDeptService;

	/**
     * 根据主键ID查询
     *
     * @param id
     * @return 查询结果
    */
	@GetMapping("/getById/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Long id) {
        log.info("get SysDept info start...");
        Optional<SysDept> sysDept = this.sysDeptService.get(id);

        if(null == sysDept){
            throw new CommonException(CommonCode.EXCEPTION, "返回对象为NULL!");
        }

        return CommonResultUtil.success("获取成功", sysDept);
    }

	/**
     * 分页查询
     *
     * @param sysDeptDTO 筛选条件
     * @return 查询结果
     */
	@PostMapping("/search")
    public ResponseEntity<?> search(@RequestParam(value = "page", required = false, defaultValue = "1") String pageString,
                            @RequestParam(value = "per_page", required = false, defaultValue = "10") String perPageString,
                            @RequestBody(required = false) SysDeptDTO sysDeptDTO) {
        if(log.isInfoEnabled()) {
            log.info("search SysDept start...");
        }

        int page = PageUtil.parsePage(pageString, PageConstant.PAGE);
        int perPage = PageUtil.parsePerPage(perPageString, PageConstant.PER_PAGE);
        PageHelper.startPage(page, perPage);
		//PageHelper.startPage(page, perPage, "update_time desc");
		SysDept queryEntity = new SysDept();

        BeanUtils.copyProperties(sysDeptDTO, queryEntity);
        List<SysDept> sysDeptList = this.sysDeptService.list(queryEntity);
		PageInfo pageInfo = new PageInfo<>(sysDeptList);

		if(CollectionUtils.isNotEmpty(pageInfo.getList())) {
            pageInfo.setList(SysDeptConverter.INSTANCE.to(pageInfo.getList()));
        }

        return CommonResultUtil.pagingSuccess("查询成功", pageInfo);
    }

	/**
     * 新增
     * @param sysDeptDTO
     * @return
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseEntity<?> save(@RequestBody SysDeptDTO sysDeptDTO) {
        if(log.isInfoEnabled()) {
            log.info("save SysDept start...");
        }
		SysDept saveEntity = new SysDept();

        BeanUtils.copyProperties(sysDeptDTO, saveEntity);
		saveEntity.setCreateTime(new Date());
		saveEntity.setUpdateTime(new Date());
        if (this.sysDeptService.save(saveEntity) != 1) {
            throw new CommonException(CommonCode.EXCEPTION, "新增失败!");
        }

        return CommonResultUtil.success("新增成功", saveEntity);
    }

	/**
     * 修改
     * @param sysDeptDTO
     * @return
     */
    @PostMapping("/update")
    public ResponseEntity<?> update(@RequestBody SysDeptDTO sysDeptDTO) {
        if(log.isInfoEnabled()) {
            log.info("update SysDept start....");
        }
		SysDept updateEntity = new SysDept();

        BeanUtils.copyProperties(sysDeptDTO, updateEntity);
		updateEntity.setUpdateTime(new Date());
        if(this.sysDeptService.updateById(updateEntity) != 1) {
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
            log.info("remove SysDept by id start...");
        }
        if (this.sysDeptService.remove(id) != 1) {
            throw new CommonException(CommonCode.EXCEPTION, "删除失败!");
        }

        return CommonResultUtil.success("删除成功");
    }
}
