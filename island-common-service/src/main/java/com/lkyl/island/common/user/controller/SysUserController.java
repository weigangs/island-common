package com.lkyl.island.common.user.controller;

import com.lkyl.island.common.api.request.SysUserQueryListDTO;
import com.lkyl.island.common.api.request.SysUserUpdateDTO;
import com.lkyl.island.common.api.response.SysUserQueryListVO;
import com.lkyl.island.common.api.response.SysUserVO;
import com.lkyl.island.common.api.response.SysUserWithRoleNameListVO;
import com.lkyl.island.common.user.service.ISysUserService;
import com.lkyl.oceanframework.common.utils.page.PageArgs;
import com.lkyl.oceanframework.common.utils.result.CommonResult;
import com.lkyl.oceanframework.common.utils.result.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * @author nicholas
 * @date 2023/07/15 13:48
 */
@RequestMapping("/sysUser")
@RestController
public class SysUserController {

    @Resource
    private ISysUserService iSysUserService;
    @GetMapping("/{userId}")
    public CommonResult<SysUserVO> get(@PathVariable("userId") Long userId) {
        return CommonResult.ok(iSysUserService.getUserByUserId(userId));
    }

    @GetMapping("/queryList")
    public PageResult<SysUserQueryListVO> queryList(SysUserQueryListDTO sysUserQueryListDTO, PageArgs pageArgs) {
        return PageResult.page(iSysUserService.queryList(sysUserQueryListDTO, pageArgs));
    }

    @GetMapping("/getUserInfowithRoleList/{userId}")
    public CommonResult<SysUserWithRoleNameListVO> getUserInfoAndRoleListCanView(@PathVariable("userId") Long userId) {
        return CommonResult.ok(iSysUserService.getUserInfowithRoleList(userId));
    }

    @PostMapping("/update")
    public CommonResult<String> updateUser(@Valid @RequestBody SysUserUpdateDTO sysUserUpdateDTO) {
        iSysUserService.updateUser(sysUserUpdateDTO);
        return CommonResult.ok();
    }

}
