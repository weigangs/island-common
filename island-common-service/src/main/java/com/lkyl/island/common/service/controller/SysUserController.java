package com.lkyl.island.common.service.controller;

import com.lkyl.island.common.api.response.SysMenuVO;
import com.lkyl.island.common.api.response.SysUserVO;
import com.lkyl.island.common.ps.entity.SysMenu;
import com.lkyl.island.common.ps.entity.SysRoleMenu;
import com.lkyl.island.common.ps.entity.SysUser;
import com.lkyl.island.common.api.request.SysUserDTO;
import com.lkyl.island.common.service.converter.SysMenuConverter;
import com.lkyl.island.common.service.converter.SysUserConverter;
import com.lkyl.island.common.service.service.SysMenuService;
import com.lkyl.island.common.service.service.SysRoleMenuService;
import com.lkyl.island.common.service.service.SysUserService;
import com.lkyl.island.common.service.util.RoleUtil;
import com.lkyl.oceanframework.common.utils.constant.CommonCode;
import com.lkyl.oceanframework.common.utils.constant.CommonResult;
import com.lkyl.oceanframework.common.utils.constant.MybatisConstant;
import com.lkyl.oceanframework.common.utils.constant.PageConstant;
import com.lkyl.oceanframework.common.utils.enums.DelFlagEnum;
import com.lkyl.oceanframework.common.utils.exception.CommonException;
import com.lkyl.oceanframework.common.utils.utils.CollectionUtils;
import com.lkyl.oceanframework.security.security.OceanUserPrincipal;
import com.lkyl.oceanframework.web.util.CommonResultUtil;
import com.lkyl.oceanframework.common.utils.utils.PageUtil;
import com.lkyl.oceanframework.web.util.ContextUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.*;
import java.util.stream.Collectors;

import javax.annotation.Resource;

/**
 * 用户信息表(SysUser)表控制层
 *
 * @author author
 * @since 2022-05-21 16:29:30
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
        log.info("get SysUser info start...");
        Optional<SysUser> sysUser = this.sysUserService.get(id);

        if(null == sysUser){
            throw new CommonException(CommonCode.EXCEPTION, "返回对象为NULL!");
        }

        return CommonResultUtil.success("获取成功", sysUser);
    }

	/**
     * 分页查询
     *
     * @param sysUserDTO 筛选条件
     * @return 查询结果
     */
	@PostMapping("/search")
    public ResponseEntity<?> search(@RequestParam(value = "page", required = false, defaultValue = "1") String pageString,
                            @RequestParam(value = "per_page", required = false, defaultValue = "10") String perPageString,
                            @RequestBody(required = false) SysUserDTO sysUserDTO) {
        if(log.isInfoEnabled()) {
            log.info("search SysUser start...");
        }

        int page = PageUtil.parsePage(pageString, PageConstant.PAGE);
        int perPage = PageUtil.parsePerPage(perPageString, PageConstant.PER_PAGE);
        PageHelper.startPage(page, perPage);
		//PageHelper.startPage(page, perPage, "update_time desc");
		SysUser queryEntity = new SysUser();

        BeanUtils.copyProperties(sysUserDTO, queryEntity);
        List<SysUser> sysUserList = this.sysUserService.list(queryEntity);
        PageInfo pageInfo = new PageInfo<>(sysUserList);

        if(CollectionUtils.isNotEmpty(pageInfo.getList())) {
            pageInfo.setList(SysUserConverter.INSTANCE.to(pageInfo.getList()));
        }

        return CommonResultUtil.pagingSuccess("查询成功", pageInfo);
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
        }
		SysUser saveEntity = new SysUser();

        BeanUtils.copyProperties(sysUserDTO, saveEntity);
		saveEntity.setCreateTime(new Date());
		saveEntity.setUpdateTime(new Date());
        if (this.sysUserService.save(saveEntity) != 1) {
            throw new CommonException(CommonCode.EXCEPTION, "新增失败!");
        }

        return CommonResultUtil.success("新增成功", saveEntity);
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
        }
		SysUser updateEntity = new SysUser();

        BeanUtils.copyProperties(sysUserDTO, updateEntity);
		updateEntity.setUpdateTime(new Date());
        if(this.sysUserService.updateById(updateEntity) != 1) {
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
            log.info("remove SysUser by id start...");
        }
        if (this.sysUserService.remove(id) != 1) {
            throw new CommonException(CommonCode.EXCEPTION, "删除失败!");
        }

        return CommonResultUtil.success("删除成功");
    }
}
