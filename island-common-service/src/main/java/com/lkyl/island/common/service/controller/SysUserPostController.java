package com.lkyl.island.common.service.controller;

import com.lkyl.island.common.api.request.SysUserPostDTO;
import com.lkyl.island.common.service.service.SysUserPostService;
import com.lkyl.oceanframework.web.util.CommonResultUtil;
import lombok.extern.slf4j.Slf4j;
import com.alibaba.fastjson.JSON;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 用户与岗位关联表(SysUserPost)表控制层
 *
 * @author author
 * @since 2022-06-12 15:54:07
 */
@Slf4j
@RestController
@RequestMapping("sysUserPost")
public class SysUserPostController {
    /**
     * 服务对象
    */
    @Resource
    private SysUserPostService sysUserPostService;

	/**
     * 根据主键ID查询
     *
     * @param id
     * @return 查询结果
    */
	@GetMapping("/getById/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Long id) {
		if(log.isInfoEnabled()) {
            log.info("get SysUserPost info start...");
			log.info("request param:{}", id);
        }

        try{
            return CommonResultUtil.success("获取成功", this.sysUserPostService.detail(id));
        } catch (Exception e) {
            log.error("error:", e);
            throw e;
        }
    }

	/**
     * 分页查询
     *
     * @param sysUserPostDTO 筛选条件
     * @return 查询结果
     */
	@PostMapping("/search")
    public ResponseEntity<?> search(@RequestParam(value = "pageNum", required = false, defaultValue = "1") String pageNum,
                            @RequestParam(value = "pageSize", required = false, defaultValue = "10") String pageSize,
                            @RequestBody(required = false) SysUserPostDTO sysUserPostDTO) {
        if(log.isInfoEnabled()) {
            log.info("search SysUserPost start...");
			log.info("request param:{}", JSON.toJSONString(sysUserPostDTO));
        }

		try{
            return CommonResultUtil.pagingSuccess("查询成功", this.sysUserPostService.search(sysUserPostDTO, pageNum, pageSize));
        } catch (Exception e) {
            log.error("error:", e);
            throw e;
        }
    }

	/**
     * 新增
     * @param sysUserPostDTO
     * @return
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseEntity<?> save(@RequestBody SysUserPostDTO sysUserPostDTO) {
        if(log.isInfoEnabled()) {
            log.info("save SysUserPost start...");
			log.info("request param:{}", JSON.toJSONString(sysUserPostDTO));
        }

		try{
            return CommonResultUtil.success("新增成功", this.sysUserPostService.insert(sysUserPostDTO));
        } catch (Exception e) {
            log.error("error:", e);
            throw e;
        }

    }

	/**
     * 修改
     * @param sysUserPostDTO
     * @return
     */
    @PostMapping("/update")
    public ResponseEntity<?> update(@RequestBody SysUserPostDTO sysUserPostDTO) {
        if(log.isInfoEnabled()) {
            log.info("update SysUserPost start....");
			log.info("request param:{}", JSON.toJSONString(sysUserPostDTO));
        }

		try{
            return CommonResultUtil.success("更新成功", this.sysUserPostService.update(sysUserPostDTO));
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
            log.info("remove SysUserPost by id start...");
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
