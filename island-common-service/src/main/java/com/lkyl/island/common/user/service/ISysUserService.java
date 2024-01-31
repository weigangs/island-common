package com.lkyl.island.common.user.service;

import com.lkyl.island.common.api.request.SysUserQueryListDTO;
import com.lkyl.island.common.api.request.SysUserUpdateDTO;
import com.lkyl.island.common.api.response.SysUserQueryListVO;
import com.lkyl.island.common.api.response.SysUserVO;
import com.lkyl.island.common.api.response.SysUserWithRoleNameListVO;
import com.lkyl.oceanframework.common.utils.page.PageArgs;

import java.util.List;

public interface ISysUserService {

    SysUserVO getUserByUserId(Long userId);

    List<SysUserQueryListVO> queryList(SysUserQueryListDTO sysUserQueryListDTO, PageArgs pageArgs);

    SysUserWithRoleNameListVO getUserInfowithRoleList(Long userId);

    int updateUser(SysUserUpdateDTO sysUserUpdateDTO);
}
