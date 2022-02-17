package com.lkyl.island.common.service.controller;

import com.lkyl.island.common.ps.entity.SysMenu;
import com.lkyl.island.common.api.request.SysMenuDTO;
import com.lkyl.island.common.ps.service.SysMenuService;
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
 * 菜单权限表(SysMenu)表控制层
 *
 * @author shiwg
 * @since 2022-02-17 14:54:25
 */
@Slf4j
@RestController
@RequestMapping("sysMenu")
public class SysMenuController {
    /**
     * 服务对象
     */
    @Resource
    private SysMenuService sysMenuService;

	    /**
     * 根据主键ID查询
     *
     * @param id
     * @return 查询结果
     */
	    @GetMapping("/getById/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Long id) {
        log.info("get SysParam info from token start...");
        Optional<SysMenu> sysMenu = this.sysMenuService.get(id);

        if(null == sysMenu){
            throw new CommonException(CommonCode.EXCEPTION, "返回对象为NULL!");
        }
        return ResponseEntity.ok(new CommonResult().
        setCode(CommonCode.SUCCESS).
        setMsg("获取成功").
        setData(sysMenu));
    }


	    /**
     * 分页查询
     *
     * @param sysMenuDTO 筛选条件
     * @return 查询结果
     */
	@PostMapping("/search")
    public ResponseEntity<?> search(@RequestParam(value = "page", required = false, defaultValue = "1") String pageString,
                            @RequestParam(value = "per_page", required = false, defaultValue = "10") String perPageString,
                            @RequestBody(required = false) SysMenuDTO sysMenuDTO) {
        if(log.isInfoEnabled()) {
            log.info("search SysMenu start...");
        }

        int page = PageUtil.parsePage(pageString, PageConstant.PAGE);
        int perPage = PageUtil.parsePerPage(perPageString, PageConstant.PER_PAGE);
        PageHelper.startPage(page, perPage);
		//PageHelper.startPage(page, perPage, "update_time desc");
		SysMenu queryEntity = new SysMenu();
        BeanUtils.copyProperties(sysMenuDTO, queryEntity);
        List<SysMenu> sysMenuList = this.sysMenuService.list(queryEntity);
        PageInfo<SysMenu> pageInfo = new PageInfo<>(sysMenuList);
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
     * @param sysMenuDTO
     * @return
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseEntity<?> save(@RequestBody SysMenuDTO sysMenuDTO) {
        if(log.isInfoEnabled()) {
            log.info("save SysMenu start...");
        }
		SysMenu saveEntity = new SysMenu();
        BeanUtils.copyProperties(sysMenuDTO, saveEntity);
		saveEntity.setCreateTime(new Date());
		saveEntity.setUpdateTime(new Date());
        if (this.sysMenuService.save(saveEntity) != 1) {
            throw new CommonException(CommonCode.EXCEPTION, "新增失败!");
        }
        return ResponseEntity.ok(new CommonResult()
                .setCode(CommonCode.SUCCESS)
                .setMsg("新增成功")
                .setData(saveEntity));
    }

	    /**
     * 修改
     * @param sysMenuDTO
     * @return
     */
    @PostMapping("/update")
    public ResponseEntity<?> update(@RequestBody SysMenuDTO sysMenuDTO) {
        if(log.isInfoEnabled()) {
            log.info("update SysMenu start....");
        }
		SysMenu updateEntity = new SysMenu();
        BeanUtils.copyProperties(sysMenuDTO, updateEntity);
		updateEntity.setUpdateTime(new Date());
        if(this.sysMenuService.updateById(updateEntity) != 1) {
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
            log.info("remove SysMenu by id start...");
        }
        if (this.sysMenuService.remove(id) != 1) {
            throw new CommonException(CommonCode.EXCEPTION, "删除失败!");
        }
        return ResponseEntity.ok(new CommonResult()
        .setCode(CommonCode.SUCCESS)
        .setMsg("删除成功"));
    }


}

