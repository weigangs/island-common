package com.lkyl.island.common.user.service;

import com.lkyl.island.common.ps.entity.SysUser;
import com.lkyl.oceanframework.common.utils.page.PageArgs;

import java.util.List;
import java.util.Optional;

public interface ISysUserQueryService{

    Optional<SysUser> queryUserByUserCode(String userCode);

    Optional<SysUser> queryUserByUserId(Long userId);

    List<SysUser> queryUserByIds(List<Long> userIds);

    List<SysUser> pageQueryUserByIds(List<Long> userIds, PageArgs pageArgs);
}
