package com.lkyl.island.common.service.controller;

import com.lkyl.island.common.ps.entity.SysRoleMenu;
import com.lkyl.island.common.api.request.SysRoleMenuDTO;
import com.lkyl.island.common.ps.service.SysRoleMenuService;
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
 * 角色和菜单关联表(SysRoleMenu)表控制层
 *
 * @author shiwg
 * @since 2022-02-17 15:05:19
 */
@Slf4j
@RestController
@RequestMapping("sysRoleMenu")
public class SysRoleMenuController {
    /**
     * 服务对象
     */
    @Resource
    private SysRoleMenuService sysRoleMenuService;

	    /**
     * 根据主键ID查询
     *
     * @param id
     * @return 查询结果
     */
	    @GetMapping("/getById/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Long id) {
        log.info("get SysParam info from token start...");
        Optional<SysRoleMenu> sysRoleMenu = this.sysRoleMenuService.get(id);

        if(null == sysRoleMenu){
            throw new CommonException(CommonCode.EXCEPTION, "返回对象为NULL!");
        }
        return ResponseEntity.ok(new CommonResult().
        setCode(CommonCode.SUCCESS).
        setMsg("获取成功").
        setData(sysRoleMenu));
    }


	    /**
     * 分页查询
     *
     * @param sysRoleMenuDTO 筛选条件
     * @return 查询结果
     */
	@PostMapping("/search")
    public ResponseEntity<?> search(@RequestParam(value = "page", required = false, defaultValue = "1") String pageString,
                            @RequestParam(value = "per_page", required = false, defaultValue = "10") String perPageString,
                            @RequestBody(required = false) SysRoleMenuDTO sysRoleMenuDTO) {
        if(log.isInfoEnabled()) {
            log.info("search SysRoleMenu start...");
        }

        int page = PageUtil.parsePage(pageString, PageConstant.PAGE);
        int perPage = PageUtil.parsePerPage(perPageString, PageConstant.PER_PAGE);
        PageHelper.startPage(page, perPage);
		//PageHelper.startPage(page, perPage, "update_time desc");
		SysRoleMenu queryEntity = new SysRoleMenu();
        BeanUtils.copyProperties(sysRoleMenuDTO, queryEntity);
        List<SysRoleMenu> sysRoleMenuList = this.sysRoleMenuService.list(queryEntity);
        PageInfo<SysRoleMenu> pageInfo = new PageInfo<>(sysRoleMenuList);
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
     * @param sysRoleMenuDTO
     * @return
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseEntity<?> save(@RequestBody SysRoleMenuDTO sysRoleMenuDTO) {
        if(log.isInfoEnabled()) {
            log.info("save SysRoleMenu start...");
        }
		SysRoleMenu saveEntity = new SysRoleMenu();
        BeanUtils.copyProperties(sysRoleMenuDTO, saveEntity);
        if (this.sysRoleMenuService.save(saveEntity) != 1) {
            throw new CommonException(CommonCode.EXCEPTION, "新增失败!");
        }
        return ResponseEntity.ok(new CommonResult()
                .setCode(CommonCode.SUCCESS)
                .setMsg("新增成功")
                .setData(saveEntity));
    }

	    /**
     * 修改
     * @param sysRoleMenuDTO
     * @return
     */
    @PostMapping("/update")
    public ResponseEntity<?> update(@RequestBody SysRoleMenuDTO sysRoleMenuDTO) {
        if(log.isInfoEnabled()) {
            log.info("update SysRoleMenu start....");
        }
		SysRoleMenu updateEntity = new SysRoleMenu();
        BeanUtils.copyProperties(sysRoleMenuDTO, updateEntity);
        if(this.sysRoleMenuService.updateById(updateEntity) != 1) {
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
            log.info("remove SysRoleMenu by id start...");
        }
        if (this.sysRoleMenuService.remove(id) != 1) {
            throw new CommonException(CommonCode.EXCEPTION, "删除失败!");
        }
        return ResponseEntity.ok(new CommonResult()
        .setCode(CommonCode.SUCCESS)
        .setMsg("删除成功"));
    }


}

