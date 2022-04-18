package com.lkyl.island.common.service.controller;

import com.lkyl.island.common.ps.entity.SysUserRole;
import com.lkyl.island.common.api.request.SysUserRoleDTO;
import com.lkyl.island.common.ps.service.SysUserRoleService;
import com.lkyl.oceanframework.common.utils.constant.CommonCode;
import com.lkyl.oceanframework.common.utils.constant.CommonResult;
import com.lkyl.oceanframework.common.utils.constant.PageConstant;
import com.lkyl.oceanframework.common.utils.constant.PaginatedResult;
import com.lkyl.oceanframework.common.utils.exception.CommonException;
import com.lkyl.oceanframework.common.utils.utils.PageUtil;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
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
 * @author shiwg
 * @since 2022-02-17 15:05:49
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
        log.info("get SysParam info from token start...");
        Optional<SysUserRole> sysUserRole = this.sysUserRoleService.get(id);

        if(null == sysUserRole){
            throw new CommonException(CommonCode.EXCEPTION, "返回对象为NULL!");
        }
        return ResponseEntity.ok(new CommonResult().
        setCode(CommonCode.SUCCESS).
        setMsg("获取成功").
        setData(sysUserRole));
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
        PageInfo<SysUserRole> pageInfo = new PageInfo<>(sysUserRoleList);
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
        return ResponseEntity.ok(new CommonResult()
                .setCode(CommonCode.SUCCESS)
                .setMsg("新增成功")
                .setData(saveEntity));
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
            log.info("remove SysUserRole by id start...");
        }
        if (this.sysUserRoleService.remove(id) != 1) {
            throw new CommonException(CommonCode.EXCEPTION, "删除失败!");
        }
        return ResponseEntity.ok(new CommonResult()
        .setCode(CommonCode.SUCCESS)
        .setMsg("删除成功"));
    }

    public static void main(String []args){
        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        encryptor.setAlgorithm("PBEWITHHMACSHA512ANDAES_256");
        encryptor.setPassword("BdaObXaELXX");
        System.out.println(encryptor.encrypt("root"));
    }

}

