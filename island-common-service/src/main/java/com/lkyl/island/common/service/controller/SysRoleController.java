package com.lkyl.island.common.service.controller;

import com.lkyl.island.common.ps.entity.SysRole;
import com.lkyl.island.common.api.request.SysRoleDTO;
import com.lkyl.island.common.service.service.SysRoleService;
import com.lkyl.island.common.service.converter.SysRoleConverter;
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
 * 角色信息表(SysRole)表控制层
 *
 * @author author
 * @since 2022-05-21 18:11:52
 */
@Slf4j
@RestController
@RequestMapping("sysRole")
public class SysRoleController {
    /**
     * 服务对象
    */
    @Resource
    private SysRoleService sysRoleService;

	/**
     * 根据主键ID查询
     *
     * @param id
     * @return 查询结果
    */
	@GetMapping("/getById/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Long id) {
        log.info("get SysRole info start...");
        Optional<SysRole> sysRole = this.sysRoleService.get(id);

        if(null == sysRole){
            throw new CommonException(CommonCode.EXCEPTION, "返回对象为NULL!");
        }

        return CommonResultUtil.success("获取成功", sysRole);
    }

	/**
     * 分页查询
     *
     * @param sysRoleDTO 筛选条件
     * @return 查询结果
     */
	@PostMapping("/search")
    public ResponseEntity<?> search(@RequestParam(value = "page", required = false, defaultValue = "1") String pageString,
                            @RequestParam(value = "per_page", required = false, defaultValue = "10") String perPageString,
                            @RequestBody(required = false) SysRoleDTO sysRoleDTO) {
        if(log.isInfoEnabled()) {
            log.info("search SysRole start...");
        }

        int page = PageUtil.parsePage(pageString, PageConstant.PAGE);
        int perPage = PageUtil.parsePerPage(perPageString, PageConstant.PER_PAGE);
        PageHelper.startPage(page, perPage);
		//PageHelper.startPage(page, perPage, "update_time desc");
		SysRole queryEntity = new SysRole();

        BeanUtils.copyProperties(sysRoleDTO, queryEntity);
        List<SysRole> sysRoleList = this.sysRoleService.list(queryEntity);
		PageInfo pageInfo = new PageInfo<>(sysRoleList);

		if(CollectionUtils.isNotEmpty(pageInfo.getList())) {
            pageInfo.setList(SysRoleConverter.INSTANCE.to(pageInfo.getList()));
        }

        return CommonResultUtil.pagingSuccess("查询成功", pageInfo);
    }

	/**
     * 新增
     * @param sysRoleDTO
     * @return
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseEntity<?> save(@RequestBody SysRoleDTO sysRoleDTO) {
        if(log.isInfoEnabled()) {
            log.info("save SysRole start...");
        }
		SysRole saveEntity = new SysRole();

        BeanUtils.copyProperties(sysRoleDTO, saveEntity);
		saveEntity.setCreateTime(new Date());
		saveEntity.setUpdateTime(new Date());
        if (this.sysRoleService.save(saveEntity) != 1) {
            throw new CommonException(CommonCode.EXCEPTION, "新增失败!");
        }

        return CommonResultUtil.success("新增成功", saveEntity);
    }

	/**
     * 修改
     * @param sysRoleDTO
     * @return
     */
    @PostMapping("/update")
    public ResponseEntity<?> update(@RequestBody SysRoleDTO sysRoleDTO) {
        if(log.isInfoEnabled()) {
            log.info("update SysRole start....");
        }
		SysRole updateEntity = new SysRole();

        BeanUtils.copyProperties(sysRoleDTO, updateEntity);
		updateEntity.setUpdateTime(new Date());
        if(this.sysRoleService.updateById(updateEntity) != 1) {
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
            log.info("remove SysRole by id start...");
        }
        if (this.sysRoleService.remove(id) != 1) {
            throw new CommonException(CommonCode.EXCEPTION, "删除失败!");
        }

        return CommonResultUtil.success("删除成功");
    }
}
