package com.lkyl.island.common.service.controller;

import com.lkyl.island.common.ps.entity.SysPost;
import com.lkyl.island.common.api.request.SysPostDTO;
import com.lkyl.island.common.service.service.SysPostService;
import com.lkyl.island.common.service.converter.SysPostConverter;
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
 * 岗位信息表(SysPost)表控制层
 *
 * @author author
 * @since 2022-05-21 18:11:52
 */
@Slf4j
@RestController
@RequestMapping("sysPost")
public class SysPostController {
    /**
     * 服务对象
    */
    @Resource
    private SysPostService sysPostService;

	/**
     * 根据主键ID查询
     *
     * @param id
     * @return 查询结果
    */
	@GetMapping("/getById/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Long id) {
        log.info("get SysPost info start...");
        Optional<SysPost> sysPost = this.sysPostService.get(id);

        if(null == sysPost){
            throw new CommonException(CommonCode.EXCEPTION, "返回对象为NULL!");
        }

        return CommonResultUtil.success("获取成功", sysPost);
    }

	/**
     * 分页查询
     *
     * @param sysPostDTO 筛选条件
     * @return 查询结果
     */
	@PostMapping("/search")
    public ResponseEntity<?> search(@RequestParam(value = "page", required = false, defaultValue = "1") String pageString,
                            @RequestParam(value = "per_page", required = false, defaultValue = "10") String perPageString,
                            @RequestBody(required = false) SysPostDTO sysPostDTO) {
        if(log.isInfoEnabled()) {
            log.info("search SysPost start...");
        }

        int page = PageUtil.parsePage(pageString, PageConstant.PAGE);
        int perPage = PageUtil.parsePerPage(perPageString, PageConstant.PER_PAGE);
        PageHelper.startPage(page, perPage);
		//PageHelper.startPage(page, perPage, "update_time desc");
		SysPost queryEntity = new SysPost();

        BeanUtils.copyProperties(sysPostDTO, queryEntity);
        List<SysPost> sysPostList = this.sysPostService.list(queryEntity);
		PageInfo pageInfo = new PageInfo<>(sysPostList);

		if(CollectionUtils.isNotEmpty(pageInfo.getList())) {
            pageInfo.setList(SysPostConverter.INSTANCE.to(pageInfo.getList()));
        }

        return CommonResultUtil.pagingSuccess("查询成功", pageInfo);
    }

	/**
     * 新增
     * @param sysPostDTO
     * @return
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseEntity<?> save(@RequestBody SysPostDTO sysPostDTO) {
        if(log.isInfoEnabled()) {
            log.info("save SysPost start...");
        }
		SysPost saveEntity = new SysPost();

        BeanUtils.copyProperties(sysPostDTO, saveEntity);
		saveEntity.setCreateTime(new Date());
		saveEntity.setUpdateTime(new Date());
        if (this.sysPostService.save(saveEntity) != 1) {
            throw new CommonException(CommonCode.EXCEPTION, "新增失败!");
        }

        return CommonResultUtil.success("新增成功", saveEntity);
    }

	/**
     * 修改
     * @param sysPostDTO
     * @return
     */
    @PostMapping("/update")
    public ResponseEntity<?> update(@RequestBody SysPostDTO sysPostDTO) {
        if(log.isInfoEnabled()) {
            log.info("update SysPost start....");
        }
		SysPost updateEntity = new SysPost();

        BeanUtils.copyProperties(sysPostDTO, updateEntity);
		updateEntity.setUpdateTime(new Date());
        if(this.sysPostService.updateById(updateEntity) != 1) {
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
            log.info("remove SysPost by id start...");
        }
        if (this.sysPostService.remove(id) != 1) {
            throw new CommonException(CommonCode.EXCEPTION, "删除失败!");
        }

        return CommonResultUtil.success("删除成功");
    }
}
