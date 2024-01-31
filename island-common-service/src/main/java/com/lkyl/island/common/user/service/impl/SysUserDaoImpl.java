package com.lkyl.island.common.user.service.impl;

import com.lkyl.island.common.ps.mapper.SysUserMapper;
import com.lkyl.island.common.ps.entity.SysUser;
import com.lkyl.island.common.ps.entity.SysUserExample;
import com.lkyl.island.common.user.service.ISysUserDao;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class SysUserDaoImpl implements ISysUserDao {

    private static final SysUser.Column [] UPD_PSW_COLUMNS = {
            SysUser.Column.password,
            SysUser.Column.updateUser,
            SysUser.Column.updateTime
    };

    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    public int updateUserPasswordByUserName(String userName, String newPassword) {

        if (StringUtils.isBlank(userName) || StringUtils.isBlank(newPassword)) {
            return 0;
        }

        return sysUserMapper.updateByExampleSelective(buildUpdPswRecord(newPassword), buildUpdPswExample(userName), UPD_PSW_COLUMNS);
    }

    private SysUser buildUpdPswRecord(String newPassword) {
        SysUser result = new SysUser();
        result.setUpdateTime(LocalDateTime.now());
        result.setUpdateUser(SecurityContextHolder.getContext().getAuthentication().getName());
        result.setPassword(newPassword);
        return result;
    }

    private SysUserExample buildUpdPswExample(String userName) {
        SysUserExample result = new SysUserExample();
        result.createCriteria().andUserNameEqualTo(userName);
        return result;
    }
}
