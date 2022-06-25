package com.lkyl.island.common.service.controller;

import com.lkyl.island.common.api.response.SysUserVO;
import com.lkyl.island.common.service.service.SysMenuService;
import com.lkyl.oceanframework.common.utils.constant.CommonCode;
import com.lkyl.oceanframework.common.utils.constant.CommonResult;
import com.lkyl.oceanframework.security.security.OceanUserPrincipal;
import com.lkyl.oceanframework.web.util.CommonResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.security.Principal;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 用户信息表(SysUser)表控制层
 *
 * @author author
 * @since 2022-05-21 16:29:30
 */
@Slf4j
@RestController
@RequestMapping("sysLogin")
public class SysLoginController {

    @Resource
    private SysMenuService sysMenuService;



    /**
     * 获取当前登录用户信息
     * @param authentication
     * @return
     */
    @GetMapping("/user")
    public ResponseEntity<?> principal(Principal principal, Authentication authentication) {
        SysUserVO userVO = new SysUserVO();
        if(principal instanceof OAuth2Authentication) {
            if(((OAuth2Authentication) principal).getPrincipal() instanceof OceanUserPrincipal){
                userVO.setUserId(((OceanUserPrincipal) ((OAuth2Authentication) principal).getPrincipal()).getUserId());
                Optional<String> optionalAvatar = Optional.ofNullable(((OceanUserPrincipal) ((OAuth2Authentication) principal).getPrincipal()).getAvatar());
                optionalAvatar.ifPresentOrElse(e->userVO.setAvatar(e), ()->{
                    userVO.setAvatar("");
                });
            }
        }
        userVO.setUserName(authentication.getName());
        userVO.setRoles(authentication.getAuthorities().stream().map(v -> ((GrantedAuthority) v).getAuthority()).collect(Collectors.toList()));
        return ResponseEntity.ok(new CommonResult().setCode(CommonCode.SUCCESS).setData(userVO));
    }

    /**
     * 根据当前用户获取可用菜单
     * @return
     */
    @GetMapping("/getMenus/{userId}")
    public ResponseEntity<?> listMenus(@PathVariable(name = "userId") String userId) {
        if(log.isInfoEnabled()) {
            log.info("getMenus start...");
        }

        try{
            return CommonResultUtil.success("获取成功", this.sysMenuService.getMenusByUserId(userId));
        } catch (Exception e) {
            log.error("error:", e);
            throw e;
        }
    }
}
