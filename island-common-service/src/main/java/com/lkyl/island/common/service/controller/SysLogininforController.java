package com.lkyl.island.common.service.controller;

import com.lkyl.island.common.ps.entity.SysLogininfor;
import com.lkyl.island.common.api.request.SysLogininforDTO;
import com.lkyl.island.common.ps.service.SysLogininforService;
import com.lkyl.oceanframework.web.config.CommonResult;
import com.lkyl.oceanframework.web.config.PaginatedResult;
import com.lkyl.oceanframework.web.common.CommonCode;
import com.lkyl.oceanframework.web.exception.CommonException;
import com.lkyl.oceanframework.web.constant.PageConstant;
import com.lkyl.oceanframework.web.util.PageUtil;
import org.springframework.beans.BeanUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.*;

import javax.annotation.Resource;

/**
 * 系统访问记录(SysLogininfor)表控制层
 *
 * @author shiwg
 * @since 2022-02-17 15:04:25
 */
@Slf4j
@RestController
@RequestMapping("sysLogininfor")
public class SysLogininforController {
    /**
     * 服务对象
     */
    @Resource
    private SysLogininforService sysLogininforService;

	    /**
     * 根据主键ID查询
     *
     * @param id
     * @return 查询结果
     */
	    @GetMapping("/getById/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Long id) {
        log.info("get SysParam info from token start...");
        Optional<SysLogininfor> sysLogininfor = this.sysLogininforService.get(id);

        if(null == sysLogininfor){
            throw new CommonException(CommonCode.EXCEPTION, "返回对象为NULL!");
        }
        return ResponseEntity.ok(new CommonResult().
        setCode(CommonCode.SUCCESS).
        setMsg("获取成功").
        setData(sysLogininfor));
    }


	    /**
     * 分页查询
     *
     * @param sysLogininforDTO 筛选条件
     * @return 查询结果
     */
	@PostMapping("/search")
    public ResponseEntity<?> search(@RequestParam(value = "page", required = false, defaultValue = "1") String pageString,
                            @RequestParam(value = "per_page", required = false, defaultValue = "10") String perPageString,
                            @RequestBody(required = false) SysLogininforDTO sysLogininforDTO) {
        if(log.isInfoEnabled()) {
            log.info("search SysLogininfor start...");
        }

        int page = PageUtil.parsePage(pageString, PageConstant.PAGE);
        int perPage = PageUtil.parsePerPage(perPageString, PageConstant.PER_PAGE);
        PageHelper.startPage(page, perPage);
		//PageHelper.startPage(page, perPage, "update_time desc");
		SysLogininfor queryEntity = new SysLogininfor();
        BeanUtils.copyProperties(sysLogininforDTO, queryEntity);
        List<SysLogininfor> sysLogininforList = this.sysLogininforService.list(queryEntity);
        PageInfo<SysLogininfor> pageInfo = new PageInfo<>(sysLogininforList);
        return ResponseEntity.ok(new PaginatedResult()
                .setCode(CommonCode.SUCCESS)
                .setMsg("查询成功")
                .setData(pageInfo.getList())
                .setCurrentPage(pageInfo.getPageNum())
                .setTotalPage(pageInfo.getPages())
                .setTotalCount(pageInfo.getTotal()));

    }


	    /**
     * 新增
     * @param sysLogininforDTO
     * @return
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseEntity<?> save(@RequestBody SysLogininforDTO sysLogininforDTO) {
        if(log.isInfoEnabled()) {
            log.info("save SysLogininfor start...");
        }
		SysLogininfor saveEntity = new SysLogininfor();
        BeanUtils.copyProperties(sysLogininforDTO, saveEntity);
		saveEntity.setCreateTime(new Date());
		saveEntity.setUpdateTime(new Date());
        if (this.sysLogininforService.save(saveEntity) != 1) {
            throw new CommonException(CommonCode.EXCEPTION, "新增失败!");
        }
        return ResponseEntity.ok(new CommonResult()
                .setCode(CommonCode.SUCCESS)
                .setMsg("新增成功")
                .setData(saveEntity));
    }

	    /**
     * 修改
     * @param sysLogininforDTO
     * @return
     */
    @PostMapping("/update")
    public ResponseEntity<?> update(@RequestBody SysLogininforDTO sysLogininforDTO) {
        if(log.isInfoEnabled()) {
            log.info("update SysLogininfor start....");
        }
		SysLogininfor updateEntity = new SysLogininfor();
        BeanUtils.copyProperties(sysLogininforDTO, updateEntity);
		updateEntity.setUpdateTime(new Date());
        if(this.sysLogininforService.updateById(updateEntity) != 1) {
            throw new CommonException(CommonCode.EXCEPTION, "更新失败!");
        }
        return ResponseEntity.ok(new CommonResult()
                .setCode(CommonCode.SUCCESS)
                .setMsg("更新成功")
                .setData(updateEntity));
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
            log.info("remove SysLogininfor by id start...");
        }
        if (this.sysLogininforService.remove(id) != 1) {
            throw new CommonException(CommonCode.EXCEPTION, "删除失败!");
        }
        return ResponseEntity.ok(new CommonResult()
        .setCode(CommonCode.SUCCESS)
        .setMsg("删除成功"));
    }


}

