package com.lkyl.island.common.service.controller;

import com.lkyl.island.common.api.request.SysMenuDTO;
import com.lkyl.island.common.service.service.SysMenuService;
import com.lkyl.oceanframework.web.util.CommonResultUtil;
import lombok.extern.slf4j.Slf4j;
import com.alibaba.fastjson.JSON;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 菜单权限表(SysMenu)表控制层
 *
 * @author author
 * @since 2022-06-12 15:54:06
 */
@Slf4j
@RestController
@RequestMapping("sysMenu")
public class SysMenuController {
    /**
     * 服务对象
    */
    @Resource
    private SysMenuService sysMenuService;

	/**
     * 根据主键ID查询
     *
     * @param id
     * @return 查询结果
    */
	@GetMapping("/getById/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Long id) {
		if(log.isInfoEnabled()) {
            log.info("get SysMenu info start...");
			log.info("request param:{}", id);
        }

        try{
            return CommonResultUtil.success("获取成功", this.sysMenuService.detail(id));
        } catch (Exception e) {
            log.error("error:", e);
            throw e;
        }
    }

	/**
     * 分页查询
     *
     * @param sysMenuDTO 筛选条件
     * @return 查询结果
     */
	@PostMapping("/search")
    public ResponseEntity<?> search(@RequestParam(value = "pageNum", required = false, defaultValue = "1") String pageNum,
                            @RequestParam(value = "pageSize", required = false, defaultValue = "10") String pageSize,
                            @RequestBody(required = false) SysMenuDTO sysMenuDTO) {
        if(log.isInfoEnabled()) {
            log.info("search SysMenu start...");
			log.info("request param:{}", JSON.toJSONString(sysMenuDTO));
        }

		try{
            return CommonResultUtil.pagingSuccess("查询成功", this.sysMenuService.search(sysMenuDTO, pageNum, pageSize));
        } catch (Exception e) {
            log.error("error:", e);
            throw e;
        }
    }

	/**
     * 新增
     * @param sysMenuDTO
     * @return
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseEntity<?> save(@RequestBody SysMenuDTO sysMenuDTO) {
        if(log.isInfoEnabled()) {
            log.info("save SysMenu start...");
			log.info("request param:{}", JSON.toJSONString(sysMenuDTO));
        }

		try{
            return CommonResultUtil.success("新增成功", this.sysMenuService.insert(sysMenuDTO));
        } catch (Exception e) {
            log.error("error:", e);
            throw e;
        }

    }

	/**
     * 修改
     * @param sysMenuDTO
     * @return
     */
    @PostMapping("/update")
    public ResponseEntity<?> update(@RequestBody SysMenuDTO sysMenuDTO) {
        if(log.isInfoEnabled()) {
            log.info("update SysMenu start....");
			log.info("request param:{}", JSON.toJSONString(sysMenuDTO));
        }

		try{
            return CommonResultUtil.success("更新成功", this.sysMenuService.update(sysMenuDTO));
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
            log.info("remove SysMenu by id start...");
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
