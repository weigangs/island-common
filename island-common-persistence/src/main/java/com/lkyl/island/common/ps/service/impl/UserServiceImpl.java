//package com.lkyl.island.common.ps.service.impl;
//
//import com.lkyl.island.common.ps.entity.SysRole;
//import com.lkyl.island.common.ps.entity.SysUser;
//import com.lkyl.island.common.ps.entity.SysUserRole;
//import com.lkyl.island.common.ps.service.SysRoleService;
//import com.lkyl.island.common.ps.service.SysUserRoleService;
//import com.lkyl.island.common.ps.service.SysUserService;
//import com.lkyl.island.common.ps.service.UserService;
//import com.lkyl.oceanframework.common.utils.constant.DelFlagEnum;
//import com.lkyl.oceanframework.security.security.UserPrincipal;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.stereotype.Service;
//import org.springframework.util.CollectionUtils;
//import org.springframework.util.ObjectUtils;
//
//import javax.annotation.Resource;
//import java.util.ArrayList;
//import java.util.List;
//
//@Service
//public class UserServiceImpl implements UserService {
//
//    @Resource
//    private SysUserService sysUserService;
//
//    @Resource
//    private SysRoleService sysRoleService;
//
//    @Resource
//    private SysUserRoleService sysUserRoleService;
//
//    @Override
//    public UserPrincipal login(String username) {
//
//        SysUser queryEntity = new SysUser();
//        queryEntity.setUserName(username);
//        queryEntity.setDelFlag(DelFlagEnum.NO.getCode());
//
//        SysUser sysUser = sysUserService.getByParam(queryEntity);
//
//        if(ObjectUtils.isEmpty(sysUser))
//            return null;
//
//        SysUserRole queryEntity2 = new SysUserRole();
//        queryEntity2.setUserId(sysUser.getUserId());
//        List<SysUserRole> userRoles = sysUserRoleService.list(queryEntity2);
//
//        List<SysRole> roleList = null;
//
//        if(!CollectionUtils.isEmpty(userRoles)){
//
//            SysRole role = null;
//            roleList = new ArrayList<>();
//
//            for(SysUserRole item : userRoles){
//                role = sysRoleService.getById(item.getRoleId());
//                if(!ObjectUtils.isEmpty(role)){
//                    roleList.add(role);
//                }
//            }
//        }
//
//        return this.convertSysUser(sysUser, roleList);
//    }
//
//
//    public UserPrincipal convertSysUser(SysUser sysUser, List<SysRole> sysRoles){
//
//        List<SimpleGrantedAuthority> authorities = null;
//        if(!CollectionUtils.isEmpty(sysRoles)){
//            authorities = new ArrayList<>();
//            for(SysRole role : sysRoles){
//                authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
//            }
//        }
//        UserPrincipal principal = new UserPrincipal(authorities,
//                sysUser.getPassword(),
//                sysUser.getUserName(),
//                true,
//                true,
//                true,
//                true);
//
//        return principal;
//    }
//}
