package com.lkyl.island.common.role.service.impl;

import com.lkyl.island.common.api.request.SysRoleAddDTO;
import com.lkyl.island.common.api.request.SysRoleUpdateDTO;
import com.lkyl.island.common.api.response.RoleNameVO;
import com.lkyl.island.common.api.response.SysRoleAllocatedUserListVO;
import com.lkyl.island.common.api.response.SysRoleDetailVO;
import com.lkyl.island.common.common.enums.CommonExceptionEnum;
import com.lkyl.island.common.ps.entity.*;
import com.lkyl.island.common.ps.mapper.SysRoleMapper;
import com.lkyl.island.common.ps.mapper.SysRoleMenuMapper;
import com.lkyl.island.common.ps.mapper.SysUserRoleMapper;
import com.lkyl.island.common.role.converter.SysRoleConverter;
import com.lkyl.island.common.role.service.ISysRoleQueryService;
import com.lkyl.island.common.role.service.ISysRoleService;
import com.lkyl.island.common.user.converter.SysUserConverter;
import com.lkyl.island.common.user.service.ISysUserQueryService;
import com.lkyl.island.common.utils.RoleUtil;
import com.lkyl.oceanframework.common.utils.annotation.PageSelector;
import com.lkyl.oceanframework.common.utils.enums.YesOrNoEnum;
import com.lkyl.oceanframework.common.utils.exception.BusinessExceptionFactory;
import com.lkyl.oceanframework.common.utils.page.PageArgs;
import com.lkyl.oceanframework.common.utils.utils.IdGenerator;
import com.lkyl.oceanframework.common.utils.utils.PageCopyUtils;
import com.lkyl.oceanframework.web.context.UserContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author nicholas
 * @date 2023/07/23 23:45
 */
@Service
public class SysRoleServiceImpl implements ISysRoleService {

    @Resource
    private ISysRoleQueryService iSysRoleQueryService;

    @Resource
    private SysRoleMapper sysRoleMapper;

    @Resource
    private SysRoleMenuMapper sysRoleMenuMapper;

    @Resource
    private SysUserRoleMapper sysUserRoleMapper;

    @Resource
    private ISysUserQueryService iSysUserQueryService;

    @Override
    public SysRoleDetailVO queryRoleDetailById(Long roleId) {
        return iSysRoleQueryService.queryRoleById(roleId).map(SysRoleConverter.INSTANCE::toDetail)
                .orElseGet(SysRoleDetailVO::new);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int addRole(SysRoleAddDTO sysRoleAddDTO) {
        SysRole insertPo = buildAddSysRole(sysRoleAddDTO);
        List<SysRoleMenu> roleMenuInsertPos = buildAddSysRoleMenu(sysRoleAddDTO, insertPo.getId());
        int result = sysRoleMapper.insert(insertPo);
        if (result > 0 && !CollectionUtils.isEmpty(roleMenuInsertPos)) {
            result += sysRoleMenuMapper.batchInsert(roleMenuInsertPos);
        }

        if (result != (roleMenuInsertPos.size() + 1)) {
            throw BusinessExceptionFactory.getException(CommonExceptionEnum.UPDATE_FAILED);
        }

        return result;
    }

    @Override
    public int updateRole(SysRoleUpdateDTO sysRoleUpdateDTO) {
        SysRole updatePo = buildUpdateSysRole(sysRoleUpdateDTO);
        List<SysRoleMenu> roleMenuUpdatePos = buildUpdateSysRoleMenu(sysRoleUpdateDTO, updatePo.getId());
        int result = sysRoleMapper.updateByPrimaryKeySelective(updatePo);
        if (result > 0 && !CollectionUtils.isEmpty(roleMenuUpdatePos)) {
            sysRoleMenuMapper.deleteByExample(buildDeleteByRoleIdExample(sysRoleUpdateDTO.getRoleId()));
            result += sysRoleMenuMapper.batchInsert(roleMenuUpdatePos);
        }

        if (result != (roleMenuUpdatePos.size() + 1)) {
            throw BusinessExceptionFactory.getException(CommonExceptionEnum.UPDATE_FAILED);
        }

        return result;
    }

    @Override
    public List<SysRoleAllocatedUserListVO> pageQueryAllocatedUserList(Long roleId, PageArgs pageArgs) {

        List<Long> userIds = sysUserRoleMapper.selectByExample(buildUserRoleQueryExample(roleId)).stream()
                .map(SysUserRole::getUserId).distinct().collect(Collectors.toList());

        if (!CollectionUtils.isEmpty(userIds)) {
            List<SysUser> userList = iSysUserQueryService.pageQueryUserByIds(userIds, pageArgs);
            return PageCopyUtils.convertProperties(userList, SysUserConverter.INSTANCE::toRoleAllocatedUserListVO, null);
        }

        return Collections.emptyList();
    }

    @Override
    public List<RoleNameVO> queryAllRoleNameListCurUserCanView() {
        if (RoleUtil.isAdmin()) {
            return iSysRoleQueryService.queryAllSysRoleExceptDeleted().stream()
                    .map(SysRoleConverter.INSTANCE::toRoleNameVO).collect(Collectors.toList());
        }
        return iSysRoleQueryService.queryRolesByUserId(Long.parseLong(UserContext.getUser().getUserId())).stream()
                .map(SysRoleConverter.INSTANCE::toRoleNameVO).collect(Collectors.toList());

    }

    private SysUserRoleExample buildUserRoleQueryExample(Long roleId){
        SysUserRoleExample example = new SysUserRoleExample();
        example.createCriteria().andRoleIdEqualTo(roleId);
        return example;
    }

    private SysRoleMenuExample buildDeleteByRoleIdExample(Long roleId){
        SysRoleMenuExample example = new SysRoleMenuExample();
        example.createCriteria().andRoleIdEqualTo(roleId);
        return example;
    }


    private List<SysRoleMenu> buildAddSysRoleMenu(SysRoleAddDTO sysRoleAddDTO, Long roleId){
        List<SysRoleMenu> resultList = new ArrayList<>();
        if (sysRoleAddDTO.getMenuIds() != null) {

            for (Long menuId : sysRoleAddDTO.getMenuIds()) {
                SysRoleMenu result = buildAddSysRoleMenu(roleId, menuId);
                resultList.add(result);
            }
        }
        return resultList;
    }

    private List<SysRoleMenu> buildUpdateSysRoleMenu(SysRoleUpdateDTO sysRoleUpdateDTO, Long roleId){
        List<SysRoleMenu> resultList = new ArrayList<>();
        if (sysRoleUpdateDTO.getMenuIds() != null) {

            for (Long menuId : sysRoleUpdateDTO.getMenuIds()) {
                SysRoleMenu result = buildAddSysRoleMenu(roleId, menuId);
                resultList.add(result);
            }
        }
        return resultList;
    }

    private SysRoleMenu buildAddSysRoleMenu(Long roleId, Long menuId){
        SysRoleMenu result = new SysRoleMenu();
        result.setId(IdGenerator.next());
        result.setRoleId(roleId);
        result.setMenuId(menuId);
        return result;
    }
    private SysRole buildAddSysRole(SysRoleAddDTO sysRoleAddDTO){
        SysRole result = new SysRole();
        result.setId(IdGenerator.next());
        result.setRoleName(sysRoleAddDTO.getRoleName());
        result.setRoleKey(sysRoleAddDTO.getRoleKey());
        result.setRoleSort(sysRoleAddDTO.getRoleSort());
        result.setRemark(sysRoleAddDTO.getRemark());
        result.setStatus(sysRoleAddDTO.getStatus());

        result.setCreateTime(LocalDateTime.now());
        result.setUpdateTime(LocalDateTime.now());
        result.setCreateUser(UserContext.getUser().getUserCode());
        result.setUpdateUser(UserContext.getUser().getUserCode());
        result.setIsDeleted(YesOrNoEnum.NO.getCode());
        return result;
    }

    private SysRole buildUpdateSysRole(SysRoleUpdateDTO sysRoleUpdateDTO){
        SysRole result = new SysRole();
        result.setId(sysRoleUpdateDTO.getRoleId());
        result.setRoleName(sysRoleUpdateDTO.getRoleName());
        result.setRoleKey(sysRoleUpdateDTO.getRoleKey());
        result.setRoleSort(sysRoleUpdateDTO.getRoleSort());
        result.setRemark(sysRoleUpdateDTO.getRemark());
        result.setStatus(sysRoleUpdateDTO.getStatus());

        result.setUpdateTime(LocalDateTime.now());
        result.setUpdateUser(UserContext.getUser().getUserCode());
        return result;
    }
}
