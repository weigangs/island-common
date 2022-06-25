package com.lkyl.island.common.service.controller;

import com.alibaba.fastjson.JSON;
import com.lkyl.island.common.api.request.SysConfigDTO;
import com.lkyl.island.common.service.service.SysConfigService;
import com.lkyl.oceanframework.web.util.CommonResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 参数配置表(SysConfig)表控制层
 *
 * @author author
 * @since 2022-06-12 16:57:57
 */
@Slf4j
@RestController
@RequestMapping("sysConfig")
public class SysConfigController {
    /**
     * 服务对象
    */
    @Resource
    private SysConfigService sysConfigService;

	/**
     * 根据主键ID查询
     *
     * @param id
     * @return 查询结果
    */
	@GetMapping("/getById/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Integer id) {
		if(log.isInfoEnabled()) {
            log.info("get SysConfig info start...");
			log.info("request param:{}", id);
        }

        try{
            return CommonResultUtil.success("获取成功", this.sysConfigService.detail(id));
        } catch (Exception e) {
            log.error("error:", e);
            throw e;
        }
    }

	/**
     * 分页查询
     *
     * @param sysConfigDTO 筛选条件
     * @return 查询结果
     */
	@PostMapping("/search")
    public ResponseEntity<?> search(@RequestParam(value = "pageNum", required = false, defaultValue = "1") String pageNum,
                            @RequestParam(value = "pageSize", required = false, defaultValue = "10") String pageSize,
                            @RequestBody(required = false) SysConfigDTO sysConfigDTO) {
        if(log.isInfoEnabled()) {
            log.info("search SysConfig start...");
			log.info("request param:{}", JSON.toJSONString(sysConfigDTO));
        }

		try{
            return CommonResultUtil.pagingSuccess("查询成功", this.sysConfigService.search(sysConfigDTO, pageNum, pageSize));
        } catch (Exception e) {
            log.error("error:", e);
            throw e;
        }
    }

	/**
     * 新增
     * @param sysConfigDTO
     * @return
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseEntity<?> save(@RequestBody SysConfigDTO sysConfigDTO) {
        if(log.isInfoEnabled()) {
            log.info("save SysConfig start...");
			log.info("request param:{}", JSON.toJSONString(sysConfigDTO));
        }

		try{
            return CommonResultUtil.success("新增成功", this.sysConfigService.insert(sysConfigDTO));
        } catch (Exception e) {
            log.error("error:", e);
            throw e;
        }

    }

	/**
     * 修改
     * @param sysConfigDTO
     * @return
     */
    @PostMapping("/update")
    public ResponseEntity<?> update(@RequestBody SysConfigDTO sysConfigDTO) {
        if(log.isInfoEnabled()) {
            log.info("update SysConfig start....");
			log.info("request param:{}", JSON.toJSONString(sysConfigDTO));
        }

		try{
            return CommonResultUtil.success("更新成功", this.sysConfigService.update(sysConfigDTO));
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
    public ResponseEntity<?> remove(@PathVariable("id") Integer id) {
        if(log.isInfoEnabled()) {
            log.info("remove SysConfig by id start...");
			log.info("request param:{}", id);
        }

		try{
			this.sysConfigService.remove(id);
            return CommonResultUtil.successMsg("删除成功");
        } catch (Exception e) {
            log.error("error:", e);
            throw e;
        }

    }
}
