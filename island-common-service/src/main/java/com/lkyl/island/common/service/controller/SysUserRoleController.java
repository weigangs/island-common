package com.lkyl.island.common.service.controller;

import com.lkyl.island.common.api.request.SysUserRoleDTO;
import com.lkyl.island.common.service.service.SysUserRoleService;
import com.lkyl.oceanframework.web.util.CommonResultUtil;
import lombok.extern.slf4j.Slf4j;
import com.alibaba.fastjson.JSON;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 用户和角色关联表(SysUserRole)表控制层
 *
 * @author author
 * @since 2022-06-12 15:54:07
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
		if(log.isInfoEnabled()) {
            log.info("get SysUserRole info start...");
			log.info("request param:{}", id);
        }

        try{
            return CommonResultUtil.success("获取成功", this.sysUserRoleService.detail(id));
        } catch (Exception e) {
            log.error("error:", e);
            throw e;
        }
    }

	/**
     * 分页查询
     *
     * @param sysUserRoleDTO 筛选条件
     * @return 查询结果
     */
	@PostMapping("/search")
    public ResponseEntity<?> search(@RequestParam(value = "pageNum", required = false, defaultValue = "1") String pageNum,
                            @RequestParam(value = "pageSize", required = false, defaultValue = "10") String pageSize,
                            @RequestBody(required = false) SysUserRoleDTO sysUserRoleDTO) {
        if(log.isInfoEnabled()) {
            log.info("search SysUserRole start...");
			log.info("request param:{}", JSON.toJSONString(sysUserRoleDTO));
        }

		try{
            return CommonResultUtil.pagingSuccess("查询成功", this.sysUserRoleService.search(sysUserRoleDTO, pageNum, pageSize));
        } catch (Exception e) {
            log.error("error:", e);
            throw e;
        }
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
			log.info("request param:{}", JSON.toJSONString(sysUserRoleDTO));
        }

		try{
            return CommonResultUtil.success("新增成功", this.sysUserRoleService.insert(sysUserRoleDTO));
        } catch (Exception e) {
            log.error("error:", e);
            throw e;
        }

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
			log.info("request param:{}", JSON.toJSONString(sysUserRoleDTO));
        }

		try{
            return CommonResultUtil.success("更新成功", this.sysUserRoleService.update(sysUserRoleDTO));
        } catch (Exception e) {
            log.error("error:", e);
            throw e;
        }

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
			log.info("request param:{}", id);
        }

		try{
            return CommonResultUtil.successMsg("删除成功");
        } catch (Exception e) {
            log.error("error:", e);
            throw e;
        }

    }
}
