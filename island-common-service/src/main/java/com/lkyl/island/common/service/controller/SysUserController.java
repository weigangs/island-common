package com.lkyl.island.common.service.controller;

import com.lkyl.island.common.ps.entity.SysUser;
import com.lkyl.island.common.api.request.SysUserDTO;
import com.lkyl.island.common.ps.service.SysUserService;
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
 * 用户信息表(SysUser)表控制层
 *
 * @author shiwg
 * @since 2022-02-17 15:02:57
 */
@Slf4j
@RestController
@RequestMapping("sysUser")
public class SysUserController {
    /**
     * 服务对象
     */
    @Resource
    private SysUserService sysUserService;

	    /**
     * 根据主键ID查询
     *
     * @param id
     * @return 查询结果
     */
	    @GetMapping("/getById/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Long id) {
        log.info("get SysParam info from token start...");
        Optional<SysUser> sysUser = this.sysUserService.get(id);

        if(null == sysUser){
            throw new CommonException(CommonCode.EXCEPTION, "返回对象为NULL!");
        }
        return ResponseEntity.ok(new CommonResult().
        setCode(CommonCode.SUCCESS).
        setMsg("获取成功").
        setData(sysUser));
    }


	    /**
     * 分页查询
     *
     * @param sysUserDTO 筛选条件
     * @return 查询结果
     */
	@PostMapping("/search")
    public ResponseEntity<?> search(@RequestParam(value = "page", required = false, defaultValue = "1") String pageString,
                            @RequestParam(value = "per_page", required = false, defaultValue = "10") String perPageString,
                            @RequestBody(required = false) SysUserDTO sysUserDTO) {
        if(log.isInfoEnabled()) {
            log.info("search SysUser start...");
        }

        int page = PageUtil.parsePage(pageString, PageConstant.PAGE);
        int perPage = PageUtil.parsePerPage(perPageString, PageConstant.PER_PAGE);
        PageHelper.startPage(page, perPage);
		//PageHelper.startPage(page, perPage, "update_time desc");
		SysUser queryEntity = new SysUser();
        BeanUtils.copyProperties(sysUserDTO, queryEntity);
        List<SysUser> sysUserList = this.sysUserService.list(queryEntity);
        PageInfo<SysUser> pageInfo = new PageInfo<>(sysUserList);
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
     * @param sysUserDTO
     * @return
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseEntity<?> save(@RequestBody SysUserDTO sysUserDTO) {
        if(log.isInfoEnabled()) {
            log.info("save SysUser start...");
        }
		SysUser saveEntity = new SysUser();
        BeanUtils.copyProperties(sysUserDTO, saveEntity);
		saveEntity.setCreateTime(new Date());
		saveEntity.setUpdateTime(new Date());
        if (this.sysUserService.save(saveEntity) != 1) {
            throw new CommonException(CommonCode.EXCEPTION, "新增失败!");
        }
        return ResponseEntity.ok(new CommonResult()
                .setCode(CommonCode.SUCCESS)
                .setMsg("新增成功")
                .setData(saveEntity));
    }

	    /**
     * 修改
     * @param sysUserDTO
     * @return
     */
    @PostMapping("/update")
    public ResponseEntity<?> update(@RequestBody SysUserDTO sysUserDTO) {
        if(log.isInfoEnabled()) {
            log.info("update SysUser start....");
        }
		SysUser updateEntity = new SysUser();
        BeanUtils.copyProperties(sysUserDTO, updateEntity);
		updateEntity.setUpdateTime(new Date());
        if(this.sysUserService.updateById(updateEntity) != 1) {
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
            log.info("remove SysUser by id start...");
        }
        if (this.sysUserService.remove(id) != 1) {
            throw new CommonException(CommonCode.EXCEPTION, "删除失败!");
        }
        return ResponseEntity.ok(new CommonResult()
        .setCode(CommonCode.SUCCESS)
        .setMsg("删除成功"));
    }


}

