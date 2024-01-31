package com.lkyl.island.common.role.controller;

import com.lkyl.island.common.api.request.SysRoleAddDTO;
import com.lkyl.island.common.api.request.SysRoleListQueryDTO;
import com.lkyl.island.common.api.request.SysRoleUpdateDTO;
import com.lkyl.island.common.api.response.RoleNameVO;
import com.lkyl.island.common.api.response.SysRoleAllocatedUserListVO;
import com.lkyl.island.common.api.response.SysRoleDetailVO;
import com.lkyl.island.common.api.response.SysRoleListQueryVO;
import com.lkyl.island.common.role.service.ISysRoleQueryService;
import com.lkyl.island.common.role.service.ISysRoleService;
import com.lkyl.oceanframework.common.utils.page.PageArgs;
import com.lkyl.oceanframework.common.utils.result.CommonResult;
import com.lkyl.oceanframework.common.utils.result.PageResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * @author nicholas
 * @date 2023/07/22 23:56
 */

@RequestMapping("system/role")
@RestController
public class SysRoleController {

    @Resource
    private ISysRoleQueryService iSysRoleQueryService;

    @Resource
    private ISysRoleService iSysRoleService;

    @GetMapping("/list")
    public PageResult<SysRoleListQueryVO> pageQueryRoleList(SysRoleListQueryDTO sysRoleListQueryDTO, PageArgs pageArgs) {
        return PageResult.page(iSysRoleQueryService.pageQueryRoleList(sysRoleListQueryDTO, pageArgs));
    }

    @GetMapping("/{roleId}")
    public CommonResult<SysRoleDetailVO> queryRoleDetail(@PathVariable Long roleId) {
        return CommonResult.ok(iSysRoleService.queryRoleDetailById(roleId));
    }

    @PostMapping("/add")
    public CommonResult<String> addRole(SysRoleAddDTO sysRoleAddDTO) {
        iSysRoleService.addRole(sysRoleAddDTO);
        return CommonResult.ok();
    }

    @PostMapping("/update")
    public CommonResult<String> updateRole(@Valid  @RequestBody SysRoleUpdateDTO sysRoleUpdateDTO) {
        iSysRoleService.updateRole(sysRoleUpdateDTO);
        return CommonResult.ok();
    }

    @GetMapping("/authUser/allocatedList")
    public PageResult<SysRoleAllocatedUserListVO> queryAllOrgAndUserList(@RequestParam(name = "roleId") Long roleId, PageArgs pageArgs) {
        return PageResult.page(iSysRoleService.pageQueryAllocatedUserList(roleId, pageArgs));
    }


    @GetMapping("/roleNameList")
    public CommonResult<List<RoleNameVO>> queryAllRoleNameListCurUserCanView() {
        return CommonResult.ok(iSysRoleService.queryAllRoleNameListCurUserCanView());

    }



}
