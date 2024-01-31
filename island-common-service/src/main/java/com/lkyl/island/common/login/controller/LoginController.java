package com.lkyl.island.common.login.controller;

import com.lkyl.island.common.api.request.LoginDTO;
import com.lkyl.island.common.api.response.RouterVO;
import com.lkyl.island.common.api.response.SysUserVO;
import com.lkyl.island.common.login.service.ILoginService;
import com.lkyl.island.common.menu.service.ISysMenuService;
import com.lkyl.oceanframework.common.utils.result.CommonResult;
import com.lkyl.oceanframework.web.context.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

/**
 * @author nicholas
 * @date 2023/07/14 22:47
 */
@RestController
public class LoginController {

    @Autowired
    private ILoginService iLoginService;

    @Resource
    private ISysMenuService iSysMenuService;

    @PostMapping("/login")
    public CommonResult<SysUserVO> login(@RequestBody @Valid LoginDTO loginDto, HttpServletRequest request) {
            return CommonResult.ok(iLoginService.login(loginDto, request));

    }

    @GetMapping("/getInfo")
    public CommonResult<SysUserVO> getInfo() {
        return CommonResult.ok(iLoginService.getInfo());
    }

    /**
     * 根据当前用户获取可用菜单
     * @return CommonResult
     */
    @GetMapping("/getMenus/{userId}")
    public CommonResult<List<RouterVO>> listMenus(@PathVariable(name = "userId") Long userId) {

        return CommonResult.ok(this.iSysMenuService.getMenusByUserId(userId));

    }

    /**
     * 根据当前用户获取可用菜单
     * @return CommonResult
     */
    @GetMapping("/getRouters")
    public CommonResult<List<RouterVO>> getRouters() {

        return CommonResult.ok(this.iSysMenuService.getMenusByUserId(Long.parseLong(UserContext.getUser().getUserId())));

    }

    @PostMapping("/logout")
    public CommonResult<String> logout(HttpServletRequest request) {
        iLoginService.logout(request);
        return CommonResult.ok();
    }


}
