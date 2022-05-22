package com.lkyl.island.common.service.controller;

import com.lkyl.island.common.ps.entity.SysRoleDept;
import com.lkyl.island.common.api.request.SysRoleDeptDTO;
import com.lkyl.island.common.service.service.SysRoleDeptService;
import com.lkyl.island.common.service.converter.SysRoleDeptConverter;
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
 * 角色和部门关联表(SysRoleDept)表控制层
 *
 * @author author
 * @since 2022-05-21 18:11:52
 */
@Slf4j
@RestController
@RequestMapping("sysRoleDept")
public class SysRoleDeptController {
    /**
     * 服务对象
    */
    @Resource
    private SysRoleDeptService sysRoleDeptService;

	/**
     * 根据主键ID查询
     *
     * @param id
     * @return 查询结果
    */
	@GetMapping("/getById/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Long id) {
        log.info("get SysRoleDept info start...");
        Optional<SysRoleDept> sysRoleDept = this.sysRoleDeptService.get(id);

        if(null == sysRoleDept){
            throw new CommonException(CommonCode.EXCEPTION, "返回对象为NULL!");
        }

        return CommonResultUtil.success("获取成功", sysRoleDept);
    }

	/**
     * 分页查询
     *
     * @param sysRoleDeptDTO 筛选条件
     * @return 查询结果
     */
	@PostMapping("/search")
    public ResponseEntity<?> search(@RequestParam(value = "page", required = false, defaultValue = "1") String pageString,
                            @RequestParam(value = "per_page", required = false, defaultValue = "10") String perPageString,
                            @RequestBody(required = false) SysRoleDeptDTO sysRoleDeptDTO) {
        if(log.isInfoEnabled()) {
            log.info("search SysRoleDept start...");
        }

        int page = PageUtil.parsePage(pageString, PageConstant.PAGE);
        int perPage = PageUtil.parsePerPage(perPageString, PageConstant.PER_PAGE);
        PageHelper.startPage(page, perPage);
		//PageHelper.startPage(page, perPage, "update_time desc");
		SysRoleDept queryEntity = new SysRoleDept();

        BeanUtils.copyProperties(sysRoleDeptDTO, queryEntity);
        List<SysRoleDept> sysRoleDeptList = this.sysRoleDeptService.list(queryEntity);
		PageInfo pageInfo = new PageInfo<>(sysRoleDeptList);

		if(CollectionUtils.isNotEmpty(pageInfo.getList())) {
            pageInfo.setList(SysRoleDeptConverter.INSTANCE.to(pageInfo.getList()));
        }

        return CommonResultUtil.pagingSuccess("查询成功", pageInfo);
    }

	/**
     * 新增
     * @param sysRoleDeptDTO
     * @return
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseEntity<?> save(@RequestBody SysRoleDeptDTO sysRoleDeptDTO) {
        if(log.isInfoEnabled()) {
            log.info("save SysRoleDept start...");
        }
		SysRoleDept saveEntity = new SysRoleDept();

        BeanUtils.copyProperties(sysRoleDeptDTO, saveEntity);
        if (this.sysRoleDeptService.save(saveEntity) != 1) {
            throw new CommonException(CommonCode.EXCEPTION, "新增失败!");
        }

        return CommonResultUtil.success("新增成功", saveEntity);
    }

	/**
     * 修改
     * @param sysRoleDeptDTO
     * @return
     */
    @PostMapping("/update")
    public ResponseEntity<?> update(@RequestBody SysRoleDeptDTO sysRoleDeptDTO) {
        if(log.isInfoEnabled()) {
            log.info("update SysRoleDept start....");
        }
		SysRoleDept updateEntity = new SysRoleDept();

        BeanUtils.copyProperties(sysRoleDeptDTO, updateEntity);
        if(this.sysRoleDeptService.updateById(updateEntity) != 1) {
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
            log.info("remove SysRoleDept by id start...");
        }
        if (this.sysRoleDeptService.remove(id) != 1) {
            throw new CommonException(CommonCode.EXCEPTION, "删除失败!");
        }

        return CommonResultUtil.success("删除成功");
    }
}
