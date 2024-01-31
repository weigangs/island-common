package com.lkyl.island.common.role.service;

import com.lkyl.island.common.api.request.SysRoleAddDTO;
import com.lkyl.island.common.api.request.SysRoleUpdateDTO;
import com.lkyl.island.common.api.response.RoleNameVO;
import com.lkyl.island.common.api.response.SysRoleAllocatedUserListVO;
import com.lkyl.island.common.api.response.SysRoleDetailVO;
import com.lkyl.oceanframework.common.utils.page.PageArgs;

import java.util.List;

public interface ISysRoleService {

    SysRoleDetailVO queryRoleDetailById(Long roleId);

    int addRole(SysRoleAddDTO sysRoleAddDTO);

    int updateRole(SysRoleUpdateDTO sysRoleUpdateDTO);

    List<SysRoleAllocatedUserListVO> pageQueryAllocatedUserList(Long roleId, PageArgs pageArgs);

    List<RoleNameVO> queryAllRoleNameListCurUserCanView();
}
