package com.lkyl.island.common.service.controller;

import com.lkyl.island.common.ps.entity.SysUserPost;
import com.lkyl.island.common.api.request.SysUserPostDTO;
import com.lkyl.island.common.service.service.SysUserPostService;
import com.lkyl.island.common.service.converter.SysUserPostConverter;
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
 * 用户与岗位关联表(SysUserPost)表控制层
 *
 * @author author
 * @since 2022-05-21 18:11:52
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
        log.info("get SysUserPost info start...");
        Optional<SysUserPost> sysUserPost = this.sysUserPostService.get(id);

        if(null == sysUserPost){
            throw new CommonException(CommonCode.EXCEPTION, "返回对象为NULL!");
        }

        return CommonResultUtil.success("获取成功", sysUserPost);
    }

	/**
     * 分页查询
     *
     * @param sysUserPostDTO 筛选条件
     * @return 查询结果
     */
	@PostMapping("/search")
    public ResponseEntity<?> search(@RequestParam(value = "page", required = false, defaultValue = "1") String pageString,
                            @RequestParam(value = "per_page", required = false, defaultValue = "10") String perPageString,
                            @RequestBody(required = false) SysUserPostDTO sysUserPostDTO) {
        if(log.isInfoEnabled()) {
            log.info("search SysUserPost start...");
        }

        int page = PageUtil.parsePage(pageString, PageConstant.PAGE);
        int perPage = PageUtil.parsePerPage(perPageString, PageConstant.PER_PAGE);
        PageHelper.startPage(page, perPage);
		//PageHelper.startPage(page, perPage, "update_time desc");
		SysUserPost queryEntity = new SysUserPost();

        BeanUtils.copyProperties(sysUserPostDTO, queryEntity);
        List<SysUserPost> sysUserPostList = this.sysUserPostService.list(queryEntity);
		PageInfo pageInfo = new PageInfo<>(sysUserPostList);

		if(CollectionUtils.isNotEmpty(pageInfo.getList())) {
            pageInfo.setList(SysUserPostConverter.INSTANCE.to(pageInfo.getList()));
        }

        return CommonResultUtil.pagingSuccess("查询成功", pageInfo);
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
        }
		SysUserPost saveEntity = new SysUserPost();

        BeanUtils.copyProperties(sysUserPostDTO, saveEntity);
        if (this.sysUserPostService.save(saveEntity) != 1) {
            throw new CommonException(CommonCode.EXCEPTION, "新增失败!");
        }

        return CommonResultUtil.success("新增成功", saveEntity);
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
        }
		SysUserPost updateEntity = new SysUserPost();

        BeanUtils.copyProperties(sysUserPostDTO, updateEntity);
        if(this.sysUserPostService.updateById(updateEntity) != 1) {
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
            log.info("remove SysUserPost by id start...");
        }
        if (this.sysUserPostService.remove(id) != 1) {
            throw new CommonException(CommonCode.EXCEPTION, "删除失败!");
        }

        return CommonResultUtil.success("删除成功");
    }
}
