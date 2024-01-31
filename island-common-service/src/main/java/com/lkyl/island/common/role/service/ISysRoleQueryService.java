package com.lkyl.island.common.role.service;

import com.lkyl.island.common.api.request.SysRoleListQueryDTO;
import com.lkyl.island.common.api.response.SysRoleListQueryVO;
import com.lkyl.island.common.ps.entity.SysRole;
import com.lkyl.island.common.ps.entity.SysUserRole;
import com.lkyl.oceanframework.common.utils.page.PageArgs;

import java.util.List;
import java.util.Optional;

public interface ISysRoleQueryService {

    List<SysRole> queryRolesByUserId(Long userId);

    List<SysUserRole> queryUserRolesByUserId(Long userId);

    List<SysRoleListQueryVO> pageQueryRoleList(SysRoleListQueryDTO sysRoleListQueryDTO, PageArgs pageArgs);

    Optional<SysRole> queryRoleById(Long roleId);

    List<SysRole> queryAllSysRoleExceptDeleted();
}
