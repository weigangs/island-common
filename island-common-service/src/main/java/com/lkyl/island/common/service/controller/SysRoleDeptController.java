package com.lkyl.island.common.service.controller;

import com.lkyl.island.common.api.request.SysRoleDeptDTO;
import com.lkyl.island.common.service.service.SysRoleDeptService;
import com.lkyl.oceanframework.web.util.CommonResultUtil;
import lombok.extern.slf4j.Slf4j;
import com.alibaba.fastjson.JSON;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 角色和部门关联表(SysRoleDept)表控制层
 *
 * @author author
 * @since 2022-06-12 15:54:07
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
		if(log.isInfoEnabled()) {
            log.info("get SysRoleDept info start...");
			log.info("request param:{}", id);
        }

        try{
            return CommonResultUtil.success("获取成功", this.sysRoleDeptService.detail(id));
        } catch (Exception e) {
            log.error("error:", e);
            throw e;
        }
    }

	/**
     * 分页查询
     *
     * @param sysRoleDeptDTO 筛选条件
     * @return 查询结果
     */
	@PostMapping("/search")
    public ResponseEntity<?> search(@RequestParam(value = "pageNum", required = false, defaultValue = "1") String pageNum,
                            @RequestParam(value = "pageSize", required = false, defaultValue = "10") String pageSize,
                            @RequestBody(required = false) SysRoleDeptDTO sysRoleDeptDTO) {
        if(log.isInfoEnabled()) {
            log.info("search SysRoleDept start...");
			log.info("request param:{}", JSON.toJSONString(sysRoleDeptDTO));
        }

		try{
            return CommonResultUtil.pagingSuccess("查询成功", this.sysRoleDeptService.search(sysRoleDeptDTO, pageNum, pageSize));
        } catch (Exception e) {
            log.error("error:", e);
            throw e;
        }
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
			log.info("request param:{}", JSON.toJSONString(sysRoleDeptDTO));
        }

		try{
            return CommonResultUtil.success("新增成功", this.sysRoleDeptService.insert(sysRoleDeptDTO));
        } catch (Exception e) {
            log.error("error:", e);
            throw e;
        }

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
			log.info("request param:{}", JSON.toJSONString(sysRoleDeptDTO));
        }

		try{
            return CommonResultUtil.success("更新成功", this.sysRoleDeptService.update(sysRoleDeptDTO));
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
            log.info("remove SysRoleDept by id start...");
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
