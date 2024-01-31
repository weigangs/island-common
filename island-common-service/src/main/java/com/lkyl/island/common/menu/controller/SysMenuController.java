package com.lkyl.island.common.menu.controller;

import com.lkyl.island.common.api.request.SysMenuAddDTO;
import com.lkyl.island.common.api.request.SysMenuListQueryDTO;
import com.lkyl.island.common.api.request.SysMenuUpdateDTO;
import com.lkyl.island.common.api.response.MenuTreeVO;
import com.lkyl.island.common.api.response.SysMenuVO;
import com.lkyl.island.common.menu.service.ISysMenuService;
import com.lkyl.oceanframework.common.utils.result.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 菜单权限表(SysMenu)表控制层
 *
 * @author author
 * @since 2022-06-12 15:54:06
 */
@Slf4j
@RestController
@RequestMapping("system/menu")
public class SysMenuController {
    /**
     * 服务对象
    */
    @Resource
    private ISysMenuService iSysMenuService;

	/**
     * 列表查询
     *
     * @param sysMenuListQueryDTO 筛选条件
     * @return 查询结果
     */
	@GetMapping("/list")
    public CommonResult<List<SysMenuVO>> search(SysMenuListQueryDTO sysMenuListQueryDTO) {
        return CommonResult.ok(this.iSysMenuService.search(sysMenuListQueryDTO));
    }

    @GetMapping("/{menuId}")
    public CommonResult<SysMenuVO> getById(@PathVariable("menuId") Long menuId) {
        return CommonResult.ok(this.iSysMenuService.detail(menuId));
    }

    @GetMapping("/treeselect")
    public CommonResult<List<MenuTreeVO>> treeselect() {
        return CommonResult.ok(this.iSysMenuService.treeselect());
    }

    @GetMapping("/getCheckedMenuIds/{roleId}")
    public CommonResult<List<Long>> getCheckedMenuIds(@PathVariable("roleId") Long roleId) {
        return CommonResult.ok(this.iSysMenuService.getCheckedMenuIds(roleId));
    }

    @PreAuthorize("hasRole('admin') || hasAuthority('system:menu:add')")
    @PostMapping("/add")
    public CommonResult<String> add(@RequestBody SysMenuAddDTO sysMenuAddDTO) {
        this.iSysMenuService.add(sysMenuAddDTO);
        return CommonResult.ok();
    }

    @PreAuthorize("hasRole('admin') || hasAuthority('system:menu:edit')")
    @PostMapping("/update")
    public CommonResult<String> update(@RequestBody SysMenuUpdateDTO sysMenuUpdateDTO) {
        this.iSysMenuService.update(sysMenuUpdateDTO);
        return CommonResult.ok();
    }

    @PreAuthorize("hasRole('admin') || hasAuthority('system:menu:remove')")
    @PostMapping("/delete/{menuId}")
    public CommonResult<String> logicDeleteByMenuId(@PathVariable("menuId") Long menuId) {
        this.iSysMenuService.logicDeleteByMenuId(menuId);
        return CommonResult.ok();
    }
}
