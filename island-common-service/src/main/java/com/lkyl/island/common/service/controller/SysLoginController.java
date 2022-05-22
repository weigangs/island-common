package com.lkyl.island.common.service.controller;

import com.lkyl.island.common.api.response.RouterVO;
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
@RequestMapping("sysLogin")
public class SysLoginController {
    @Resource
    private SysRoleMenuService sysRoleMenuService;
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
            if(((OAuth2Authentication) principal).getPrincipal() instanceof OceanUserPrincipal)
                userVO.setUserId(((OceanUserPrincipal) ((OAuth2Authentication) principal).getPrincipal()).getUserId());
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
        if(StringUtils.isBlank(userId)) {
            throw new CommonException("CM003");
        }
        SysMenu menuQuery = new SysMenu();
        menuQuery.setDelFlag(DelFlagEnum.NO.getCode());
        List<SysMenu> menus = null;
        if(RoleUtil.isAdmin()) {
            menus = sysMenuService.list(new SysMenu());
        }else {
            Map<String, Object> roleMenuMapQuery = new HashMap<>();
            List<Long> roleList = new ArrayList<>();
            if (CollectionUtils.isNotEmpty(roleList)) {
                roleMenuMapQuery.put(MybatisConstant.ID_LIST, roleList);
                roleMenuMapQuery.put(MybatisConstant.TENANT_ID, ContextUtil.getTenantId());
                List<SysRoleMenu> roleMenuList = sysRoleMenuService.queryByIdList(roleMenuMapQuery);
                if(CollectionUtils.isNotEmpty(roleList)) {
                    Map<String, Object> menuMapQuery = new HashMap<>();
                    menuMapQuery.put(MybatisConstant.ID_LIST, roleMenuList.stream().map(SysRoleMenu::getMenuId).collect(Collectors.toList()));
                    menuMapQuery.put(MybatisConstant.TENANT_ID, ContextUtil.getTenantId());
                    menuMapQuery.put(MybatisConstant.DEL_FLAG, DelFlagEnum.NO.getCode());
                    menus = sysMenuService.queryByIdList(menuMapQuery);
                }
            }
        }

        List<SysMenuVO> viewList = SysMenuConverter.INSTANCE.to(menus);
        List<RouterVO> routerVOS = sysMenuService.buildMenus(sysMenuService.getChildPerms(viewList, 0));
        return CommonResultUtil.success(routerVOS);
    }
}
