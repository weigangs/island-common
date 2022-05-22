package com.lkyl.island.common.service.controller;

import com.lkyl.island.common.ps.entity.SysUserRole;
import com.lkyl.island.common.api.request.SysUserRoleDTO;
import com.lkyl.island.common.service.service.SysUserRoleService;
import com.lkyl.island.common.service.converter.SysUserRoleConverter;
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
 * 用户和角色关联表(SysUserRole)表控制层
 *
 * @author author
 * @since 2022-05-21 18:11:52
 */
@Slf4j
@RestController
@RequestMapping("sysUserRole")
public class SysUserRoleController {
    /**
     * 服务对象
    */
    @Resource
    private SysUserRoleService sysUserRoleService;

	/**
     * 根据主键ID查询
     *
     * @param id
     * @return 查询结果
    */
	@GetMapping("/getById/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Long id) {
        log.info("get SysUserRole info start...");
        Optional<SysUserRole> sysUserRole = this.sysUserRoleService.get(id);

        if(null == sysUserRole){
            throw new CommonException(CommonCode.EXCEPTION, "返回对象为NULL!");
        }

        return CommonResultUtil.success("获取成功", sysUserRole);
    }

	/**
     * 分页查询
     *
     * @param sysUserRoleDTO 筛选条件
     * @return 查询结果
     */
	@PostMapping("/search")
    public ResponseEntity<?> search(@RequestParam(value = "page", required = false, defaultValue = "1") String pageString,
                            @RequestParam(value = "per_page", required = false, defaultValue = "10") String perPageString,
                            @RequestBody(required = false) SysUserRoleDTO sysUserRoleDTO) {
        if(log.isInfoEnabled()) {
            log.info("search SysUserRole start...");
        }

        int page = PageUtil.parsePage(pageString, PageConstant.PAGE);
        int perPage = PageUtil.parsePerPage(perPageString, PageConstant.PER_PAGE);
        PageHelper.startPage(page, perPage);
		//PageHelper.startPage(page, perPage, "update_time desc");
		SysUserRole queryEntity = new SysUserRole();

        BeanUtils.copyProperties(sysUserRoleDTO, queryEntity);
        List<SysUserRole> sysUserRoleList = this.sysUserRoleService.list(queryEntity);
		PageInfo pageInfo = new PageInfo<>(sysUserRoleList);

		if(CollectionUtils.isNotEmpty(pageInfo.getList())) {
            pageInfo.setList(SysUserRoleConverter.INSTANCE.to(pageInfo.getList()));
        }

        return CommonResultUtil.pagingSuccess("查询成功", pageInfo);
    }

	/**
     * 新增
     * @param sysUserRoleDTO
     * @return
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseEntity<?> save(@RequestBody SysUserRoleDTO sysUserRoleDTO) {
        if(log.isInfoEnabled()) {
            log.info("save SysUserRole start...");
        }
		SysUserRole saveEntity = new SysUserRole();

        BeanUtils.copyProperties(sysUserRoleDTO, saveEntity);
        if (this.sysUserRoleService.save(saveEntity) != 1) {
            throw new CommonException(CommonCode.EXCEPTION, "新增失败!");
        }

        return CommonResultUtil.success("新增成功", saveEntity);
    }

	/**
     * 修改
     * @param sysUserRoleDTO
     * @return
     */
    @PostMapping("/update")
    public ResponseEntity<?> update(@RequestBody SysUserRoleDTO sysUserRoleDTO) {
        if(log.isInfoEnabled()) {
            log.info("update SysUserRole start....");
        }
		SysUserRole updateEntity = new SysUserRole();

        BeanUtils.copyProperties(sysUserRoleDTO, updateEntity);
        if(this.sysUserRoleService.updateById(updateEntity) != 1) {
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
            log.info("remove SysUserRole by id start...");
        }
        if (this.sysUserRoleService.remove(id) != 1) {
            throw new CommonException(CommonCode.EXCEPTION, "删除失败!");
        }

        return CommonResultUtil.success("删除成功");
    }
}
