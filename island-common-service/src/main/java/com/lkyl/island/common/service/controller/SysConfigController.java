package com.lkyl.island.common.service.controller;

import com.lkyl.island.common.ps.entity.SysConfig;
import com.lkyl.island.common.api.request.SysConfigDTO;
import com.lkyl.island.common.service.service.SysConfigService;
import com.lkyl.island.common.service.converter.SysConfigConverter;
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
 * 参数配置表(SysConfig)表控制层
 *
 * @author author
 * @since 2022-05-21 18:11:52
 */
@Slf4j
@RestController
@RequestMapping("sysConfig")
public class SysConfigController {
    /**
     * 服务对象
    */
    @Resource
    private SysConfigService sysConfigService;

	/**
     * 根据主键ID查询
     *
     * @param id
     * @return 查询结果
    */
	@GetMapping("/getById/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Integer id) {
        log.info("get SysConfig info start...");
        Optional<SysConfig> sysConfig = this.sysConfigService.get(id);

        if(null == sysConfig){
            throw new CommonException(CommonCode.EXCEPTION, "返回对象为NULL!");
        }

        return CommonResultUtil.success("获取成功", sysConfig);
    }

	/**
     * 分页查询
     *
     * @param sysConfigDTO 筛选条件
     * @return 查询结果
     */
	@PostMapping("/search")
    public ResponseEntity<?> search(@RequestParam(value = "page", required = false, defaultValue = "1") String pageString,
                            @RequestParam(value = "per_page", required = false, defaultValue = "10") String perPageString,
                            @RequestBody(required = false) SysConfigDTO sysConfigDTO) {
        if(log.isInfoEnabled()) {
            log.info("search SysConfig start...");
        }

        int page = PageUtil.parsePage(pageString, PageConstant.PAGE);
        int perPage = PageUtil.parsePerPage(perPageString, PageConstant.PER_PAGE);
        PageHelper.startPage(page, perPage);
		//PageHelper.startPage(page, perPage, "update_time desc");
		SysConfig queryEntity = new SysConfig();

        BeanUtils.copyProperties(sysConfigDTO, queryEntity);
        List<SysConfig> sysConfigList = this.sysConfigService.list(queryEntity);
		PageInfo pageInfo = new PageInfo<>(sysConfigList);

		if(CollectionUtils.isNotEmpty(pageInfo.getList())) {
            pageInfo.setList(SysConfigConverter.INSTANCE.to(pageInfo.getList()));
        }

        return CommonResultUtil.pagingSuccess("查询成功", pageInfo);
    }

	/**
     * 新增
     * @param sysConfigDTO
     * @return
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseEntity<?> save(@RequestBody SysConfigDTO sysConfigDTO) {
        if(log.isInfoEnabled()) {
            log.info("save SysConfig start...");
        }
		SysConfig saveEntity = new SysConfig();

        BeanUtils.copyProperties(sysConfigDTO, saveEntity);
		saveEntity.setCreateTime(new Date());
		saveEntity.setUpdateTime(new Date());
        if (this.sysConfigService.save(saveEntity) != 1) {
            throw new CommonException(CommonCode.EXCEPTION, "新增失败!");
        }

        return CommonResultUtil.success("新增成功", saveEntity);
    }

	/**
     * 修改
     * @param sysConfigDTO
     * @return
     */
    @PostMapping("/update")
    public ResponseEntity<?> update(@RequestBody SysConfigDTO sysConfigDTO) {
        if(log.isInfoEnabled()) {
            log.info("update SysConfig start....");
        }
		SysConfig updateEntity = new SysConfig();

        BeanUtils.copyProperties(sysConfigDTO, updateEntity);
		updateEntity.setUpdateTime(new Date());
        if(this.sysConfigService.updateById(updateEntity) != 1) {
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
    public ResponseEntity<?> remove(@PathVariable("id") Integer id) {
        if(log.isInfoEnabled()) {
            log.info("remove SysConfig by id start...");
        }
        if (this.sysConfigService.remove(id) != 1) {
            throw new CommonException(CommonCode.EXCEPTION, "删除失败!");
        }

        return CommonResultUtil.success("删除成功");
    }
}
