package com.lkyl.island.common.service.controller;

import com.lkyl.island.common.api.request.SysDeptDTO;
import com.lkyl.island.common.service.service.SysDeptService;
import com.lkyl.oceanframework.web.util.CommonResultUtil;
import lombok.extern.slf4j.Slf4j;
import com.alibaba.fastjson.JSON;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 部门表(SysDept)表控制层
 *
 * @author author
 * @since 2022-06-12 15:54:06
 */
@Slf4j
@RestController
@RequestMapping("sysDept")
public class SysDeptController {
    /**
     * 服务对象
    */
    @Resource
    private SysDeptService sysDeptService;

	/**
     * 根据主键ID查询
     *
     * @param id
     * @return 查询结果
    */
	@GetMapping("/getById/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Long id) {
		if(log.isInfoEnabled()) {
            log.info("get SysDept info start...");
			log.info("request param:{}", id);
        }

        try{
            return CommonResultUtil.success("获取成功", this.sysDeptService.detail(id));
        } catch (Exception e) {
            log.error("error:", e);
            throw e;
        }
    }

	/**
     * 分页查询
     *
     * @param sysDeptDTO 筛选条件
     * @return 查询结果
     */
	@PostMapping("/search")
    public ResponseEntity<?> search(@RequestParam(value = "pageNum", required = false, defaultValue = "1") String pageNum,
                            @RequestParam(value = "pageSize", required = false, defaultValue = "10") String pageSize,
                            @RequestBody(required = false) SysDeptDTO sysDeptDTO) {
        if(log.isInfoEnabled()) {
            log.info("search SysDept start...");
			log.info("request param:{}", JSON.toJSONString(sysDeptDTO));
        }

		try{
            return CommonResultUtil.pagingSuccess("查询成功", this.sysDeptService.search(sysDeptDTO, pageNum, pageSize));
        } catch (Exception e) {
            log.error("error:", e);
            throw e;
        }
    }

	/**
     * 新增
     * @param sysDeptDTO
     * @return
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseEntity<?> save(@RequestBody SysDeptDTO sysDeptDTO) {
        if(log.isInfoEnabled()) {
            log.info("save SysDept start...");
			log.info("request param:{}", JSON.toJSONString(sysDeptDTO));
        }

		try{
            return CommonResultUtil.success("新增成功", this.sysDeptService.insert(sysDeptDTO));
        } catch (Exception e) {
            log.error("error:", e);
            throw e;
        }

    }

	/**
     * 修改
     * @param sysDeptDTO
     * @return
     */
    @PostMapping("/update")
    public ResponseEntity<?> update(@RequestBody SysDeptDTO sysDeptDTO) {
        if(log.isInfoEnabled()) {
            log.info("update SysDept start....");
			log.info("request param:{}", JSON.toJSONString(sysDeptDTO));
        }

		try{
            return CommonResultUtil.success("更新成功", this.sysDeptService.update(sysDeptDTO));
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
            log.info("remove SysDept by id start...");
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
