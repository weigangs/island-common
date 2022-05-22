package com.lkyl.island.common.service.service.impl;

import com.lkyl.island.common.ps.entity.SysRole;
import com.lkyl.island.common.ps.entity.SysUser;
import com.lkyl.island.common.ps.entity.SysUserRole;
import com.lkyl.island.common.service.service.SysRoleService;
import com.lkyl.island.common.service.service.SysUserRoleService;
import com.lkyl.island.common.service.service.SysUserService;
import com.lkyl.oceanframework.common.utils.enums.DelFlagEnum;
import com.lkyl.oceanframework.common.utils.utils.CollectionUtils;
import com.lkyl.oceanframework.security.security.OceanUserPrincipal;
import com.lkyl.oceanframework.security.security.UserPrincipal;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsPasswordService;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Oauth2 user login and change password
 */
@Service
public class UserDetailServiceImpl implements UserDetailsService, UserDetailsPasswordService {

    @Resource
    private SysUserRoleService sysUserRoleService;

    @Resource
    private SysUserService sysUserService;

    @Resource
    private SysRoleService sysRoleService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser sysUserQuery = new SysUser();
        sysUserQuery.setUserName(username);
        sysUserQuery.setDelFlag(DelFlagEnum.NO.getCode());

        SysUser sysUser = sysUserService.getByParam(sysUserQuery);

        if(ObjectUtils.isEmpty(sysUser))
            return null;

        SysUserRole sysUserRoleQuery = new SysUserRole();
        sysUserRoleQuery.setUserId(sysUser.getUserId());
        List<SysUserRole> userRoles = sysUserRoleService.list(sysUserRoleQuery);

        List<SysRole> roleList = null;

        if(!CollectionUtils.isEmpty(userRoles)){

            SysRole role = null;
            roleList = new ArrayList<>();

            for(SysUserRole item : userRoles){
                role = sysRoleService.getById(item.getRoleId());
                if(!ObjectUtils.isEmpty(role)){
                    roleList.add(role);
                }
            }
        }

        return this.convertSysUser(sysUser, roleList);
    }


    public UserPrincipal convertSysUser(SysUser sysUser, List<SysRole> sysRoles){

        List<SimpleGrantedAuthority> authorities = null;
        if(!CollectionUtils.isEmpty(sysRoles)){
            authorities = new ArrayList<>();
            for(SysRole role : sysRoles){
                authorities.add(new SimpleGrantedAuthority(role.getRoleId().toString()));
            }
        }

        OceanUserPrincipal userPrincipal = new OceanUserPrincipal();
        BeanUtils.copyProperties(sysUser, userPrincipal);
        UserPrincipal principal = new UserPrincipal(userPrincipal,
                authorities,
                true,
                true,
                true,
                true);

        return principal;
    }

    @Override
    public UserDetails updatePassword(UserDetails user, String newPassword) {

        return null;
    }
}
