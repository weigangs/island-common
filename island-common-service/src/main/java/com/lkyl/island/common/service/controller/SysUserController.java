package com.lkyl.island.common.service.controller;

import com.lkyl.island.common.api.request.SysUserDTO;
import com.lkyl.island.common.service.service.SysUserService;
import com.lkyl.oceanframework.web.util.CommonResultUtil;
import lombok.extern.slf4j.Slf4j;
import com.alibaba.fastjson.JSON;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 用户信息表(SysUser)表控制层
 *
 * @author author
 * @since 2022-06-12 15:54:07
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
		if(log.isInfoEnabled()) {
            log.info("get SysUser info start...");
			log.info("request param:{}", id);
        }

        try{
            return CommonResultUtil.success("获取成功", this.sysUserService.detail(id));
        } catch (Exception e) {
            log.error("error:", e);
            throw e;
        }
    }

	/**
     * 分页查询
     *
     * @param sysUserDTO 筛选条件
     * @return 查询结果
     */
	@PostMapping("/search")
    public ResponseEntity<?> search(@RequestParam(value = "pageNum", required = false, defaultValue = "1") String pageNum,
                            @RequestParam(value = "pageSize", required = false, defaultValue = "10") String pageSize,
                            @RequestBody(required = false) SysUserDTO sysUserDTO) {
        if(log.isInfoEnabled()) {
            log.info("search SysUser start...");
			log.info("request param:{}", JSON.toJSONString(sysUserDTO));
        }

		try{
            return CommonResultUtil.pagingSuccess("查询成功", this.sysUserService.search(sysUserDTO, pageNum, pageSize));
        } catch (Exception e) {
            log.error("error:", e);
            throw e;
        }
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
			log.info("request param:{}", JSON.toJSONString(sysUserDTO));
        }

		try{
            return CommonResultUtil.success("新增成功", this.sysUserService.insert(sysUserDTO));
        } catch (Exception e) {
            log.error("error:", e);
            throw e;
        }

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
			log.info("request param:{}", JSON.toJSONString(sysUserDTO));
        }

		try{
            return CommonResultUtil.success("更新成功", this.sysUserService.update(sysUserDTO));
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
            log.info("remove SysUser by id start...");
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
