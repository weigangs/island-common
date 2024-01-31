package com.lkyl.island.common.login.service.impl;

import com.lkyl.island.common.api.request.LoginDTO;
import com.lkyl.island.common.api.response.SysUserVO;
import com.lkyl.island.common.common.enums.CommonExceptionEnum;
import com.lkyl.island.common.common.enums.CommonStatusEnum;
import com.lkyl.island.common.login.service.ILoginService;
import com.lkyl.island.common.menu.service.ISysMenuCommonService;
import com.lkyl.island.common.ps.entity.SysRole;
import com.lkyl.island.common.ps.entity.SysUser;
import com.lkyl.island.common.ps.mapper.SysUserMapper;
import com.lkyl.island.common.role.service.ISysRoleQueryService;
import com.lkyl.island.common.user.service.ISysUserQueryService;
import com.lkyl.oceanframework.common.auth.properties.OceanOauth2Properties;
import com.lkyl.oceanframework.common.auth.token.TokenService;
import com.lkyl.oceanframework.common.utils.exception.BusinessExceptionFactory;
import com.lkyl.oceanframework.common.utils.principal.UserPrincipal;
import com.lkyl.oceanframework.common.utils.utils.IpUtil;
import com.lkyl.oceanframework.web.context.UserContext;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author nicholas
 * @date 2023/07/14 22:56
 */
@Service
public class LoginServiceImpl implements ILoginService {

    @Resource
    private TokenService tokenService;

    @Resource
    private ISysUserQueryService iSysUserQueryService;

    @Resource
    private ISysRoleQueryService iSysRoleQueryService;

//    @Value("${ocean.security.oauth2.privateKey}")
//    private String privateKey;

    @Resource
    private OceanOauth2Properties oceanOauth2Properties;

    @Resource
    private ISysMenuCommonService iSysMenuCommonService;

    @Resource
    private SysUserMapper sysUserMapper;

    @Override
    public SysUserVO login(LoginDTO loginDTO, HttpServletRequest request)
    {
        // 校验验证码

        // 查找用户名密码
        SysUser sysUser = iSysUserQueryService.queryUserByUserCode(loginDTO.getUsername())
                .orElseThrow(() -> BusinessExceptionFactory.getException(CommonExceptionEnum.USER_CODE_PSW_ERR));

        //
        checkUserStatusIsNormal(sysUser);
        // 校驗密码
//        boolean matchFlag = ThreePhaseEncoder.getInstance().matches(loginDTO.getPassword(), sysUser.getPassword(), privateKey);
//
//        if (!matchFlag) {
//            throw BusinessExceptionFactory.getException(CommonExceptionEnum.USER_CODE_PSW_ERR);
//        }
        // 校验成功，用户放redis

        // 查询角色
        List<String> roleList = iSysRoleQueryService.queryRolesByUserId(sysUser.getId())
                .stream().map(SysRole::getRoleKey).collect(Collectors.toList());

        // 查询权限
        List<String> permissionList = new ArrayList<>(iSysMenuCommonService.getLoginUserPermissions(sysUser.getId()));

        String token = tokenService.createJwtToken(transformToPrincipal(sysUser, roleList, permissionList));

        //
        updateUserLastLoginInfo(sysUser, request);

        // 转换用户信息
        return convertSysUser(sysUser, roleList, token, permissionList);
    }

    private void updateUserLastLoginInfo(SysUser sysUser, HttpServletRequest request) {

        sysUserMapper.updateByPrimaryKeySelective(buildUpdateUserLoginInfoRecord(sysUser, IpUtil.parseRequestIpAddress(request)),
                SysUser.Column.loginIp, SysUser.Column.loginDate, SysUser.Column.updateUser, SysUser.Column.updateTime);

    }



    private SysUser buildUpdateUserLoginInfoRecord(SysUser sysUser, String loginIpAddress) {
        SysUser result = new SysUser();
        result.setId(sysUser.getId());

        result.setLoginIp(loginIpAddress);
        result.setLoginDate(LocalDateTime.now());

        result.setUpdateUser(sysUser.getUserCode());
        result.setUpdateTime(LocalDateTime.now());

        return result;
    }

    private void checkUserStatusIsNormal(SysUser sysUser) {
        if (!CommonStatusEnum.NORMAL.getCode().equals(sysUser.getStatus())) {
            throw BusinessExceptionFactory.getException(CommonExceptionEnum.USER_STATUS_ABNORMAL);
        }
    }

    @Override
    public SysUserVO getInfo() {
        SysUser sysUser = iSysUserQueryService.queryUserByUserId(Long.parseLong(UserContext.getUser().getUserId()))
                .orElseThrow(() -> BusinessExceptionFactory.getException(CommonExceptionEnum.USER_CODE_PSW_ERR));
        // 查询角色
        List<String> roleList = iSysRoleQueryService.queryRolesByUserId(sysUser.getId())
                .stream().map(SysRole::getRoleKey).collect(Collectors.toList());

        // 查询权限
        List<String> permissionList = new ArrayList<>(iSysMenuCommonService.getLoginUserPermissions(sysUser.getId()));

        // 转换用户信息
        return convertSysUser(sysUser, roleList, null, permissionList);
    }

    @Override
    public void logout(HttpServletRequest request) {
        Optional.ofNullable(request.getHeader(oceanOauth2Properties.getTokenKey()))
                .filter(StringUtils::isNotBlank).ifPresent(tokenService::removeToken);
    }

    public SysUserVO convertSysUser(SysUser sysUser, List<String> sysRoles, String token, List<String> permissions){

        SysUserVO userPrincipal = new SysUserVO();
        // userPrincipal set all properties of sysUse
        userPrincipal.setAvatar(sysUser.getAvatar());
        userPrincipal.setUserId(sysUser.getId());
        userPrincipal.setUserCode(sysUser.getUserCode());
        userPrincipal.setUserName(sysUser.getUserName());
        userPrincipal.setEmail(sysUser.getEmail());
        userPrincipal.setUserType(sysUser.getUserType());
        userPrincipal.setUserName(sysUser.getUserName());
        userPrincipal.setPhoneNumber(sysUser.getPhoneNumber());
        userPrincipal.setSex(sysUser.getSex());
        userPrincipal.setRoles(sysRoles);
        userPrincipal.setToken(token);
        userPrincipal.setPermissions(permissions);
        return userPrincipal;
    }

    private UserPrincipal transformToPrincipal(SysUser userInfo, List<String> roleList, List<String> permissionList) {
        UserPrincipal result = new UserPrincipal();
        result.setUserId(userInfo.getId().toString());
        result.setUserCode(userInfo.getUserCode());
        result.setUserName(userInfo.getUserName());
        result.setRoleList(roleList);
        result.setPermissionList(permissionList);
        return result;
    }

    private SysUserVO  convertToVo(UserPrincipal user) {
        SysUserVO result = new SysUserVO();
        result.setUserCode(user.getUserCode());
        result.setUserName(user.getUserName());
        result.setRoles(user.getRoleList());
        result.setPermissions(Collections.emptyList());
        return result;
    }
}
