package com.lkyl.island.common.user.service.impl;

import com.lkyl.island.common.api.request.SysUserQueryListDTO;
import com.lkyl.island.common.api.request.SysUserUpdateDTO;
import com.lkyl.island.common.api.response.SysUserQueryListVO;
import com.lkyl.island.common.api.response.SysUserVO;
import com.lkyl.island.common.api.response.SysUserWithRoleNameListVO;
import com.lkyl.island.common.common.enums.CommonExceptionEnum;
import com.lkyl.island.common.ps.entity.SysUserExample;
import com.lkyl.island.common.ps.entity.SysUserRole;
import com.lkyl.island.common.ps.entity.SysUserRoleExample;
import com.lkyl.island.common.ps.mapper.SysUserMapper;
import com.lkyl.island.common.ps.mapper.SysUserRoleMapper;
import com.lkyl.island.common.role.converter.SysRoleConverter;
import com.lkyl.island.common.role.service.ISysRoleQueryService;
import com.lkyl.island.common.user.converter.SysUserConverter;
import com.lkyl.island.common.ps.entity.SysUser;
import com.lkyl.island.common.user.service.ISysUserQueryService;
import com.lkyl.island.common.user.service.ISysUserService;
import com.lkyl.island.common.utils.RoleUtil;
import com.lkyl.oceanframework.common.utils.annotation.PageSelector;
import com.lkyl.oceanframework.common.utils.enums.YesOrNoEnum;
import com.lkyl.oceanframework.common.utils.exception.BusinessExceptionFactory;
import com.lkyl.oceanframework.common.utils.page.PageArgs;
import com.lkyl.oceanframework.common.utils.utils.IdGenerator;
import com.lkyl.oceanframework.common.utils.utils.PageCopyUtils;
import com.lkyl.oceanframework.web.context.UserContext;
import com.mysql.cj.exceptions.ExceptionFactory;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author nicholas
 * @date 2023/07/13 23:03
 */
@Service
public class SysUserServiceImpl implements ISysUserService {

    @Resource
    private ISysUserQueryService iSysUserQueryService;

    @Resource
    private ISysRoleQueryService iSysRoleQueryService;
    @Resource
    private SysUserMapper sysUserMapper;

    @Resource
    private SysUserRoleMapper sysUserRoleMapper;

    @Override
    public SysUserVO getUserByUserId(Long userId) {
        return SysUserConverter.INSTANCE.to(iSysUserQueryService.queryUserByUserId(userId).orElseGet(SysUser::new));
    }

    @PageSelector
    @Override
    public List<SysUserQueryListVO> queryList(SysUserQueryListDTO sysUserQueryListDTO, PageArgs pageArgs) {
        return PageCopyUtils.convertProperties(
                sysUserMapper.selectByExample(buildQueryListExample(sysUserQueryListDTO)),
                SysUserConverter.INSTANCE::toSysUserQueryListVO,null
        );
    }

    @Override
    public SysUserWithRoleNameListVO getUserInfowithRoleList(Long userId) {
        SysUserWithRoleNameListVO result = iSysUserQueryService.queryUserByUserId(userId)
                .map(SysUserConverter.INSTANCE::toSysUserWithRoleNameListVO)
                .orElseThrow(() -> BusinessExceptionFactory.getException(CommonExceptionEnum.RECORD_NOT_FOUND));

        if (RoleUtil.isAdmin()) {
            result.setRoles(iSysRoleQueryService.queryAllSysRoleExceptDeleted().stream()
                    .map(SysRoleConverter.INSTANCE::toRoleNameVO).collect(Collectors.toList()));
        } else {
            result.setRoles(
                    iSysRoleQueryService.queryRolesByUserId(userId).stream()
                            .map(SysRoleConverter.INSTANCE::toRoleNameVO).collect(Collectors.toList())
            );
        }

        return result;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int updateUser(SysUserUpdateDTO sysUserUpdateDTO) {
        int result = sysUserMapper.updateByPrimaryKeySelective(buildSysUserUpdateRecord(sysUserUpdateDTO));

        List<SysUserRole> sysUserRoleLists = Stream.of(sysUserUpdateDTO.getRoleIds())
                .map(roleId -> buildSysUserRole(sysUserUpdateDTO.getUserId(), roleId))
                .collect(Collectors.toList());
        if (result > 0) {
            sysUserRoleMapper.deleteByExample(buildDeleteSysUserRoleExample(sysUserUpdateDTO.getUserId()));
            result += sysUserRoleMapper.batchInsert(sysUserRoleLists);

        }

        if (result != (sysUserRoleLists.size() + 1)) {
            throw BusinessExceptionFactory.getException(CommonExceptionEnum.UPDATE_FAILED);
        }

        return result;
    }

    private SysUser buildSysUserUpdateRecord(SysUserUpdateDTO sysUserUpdateDTO) {
        SysUser result = new SysUser();
        result.setId(sysUserUpdateDTO.getUserId());
        result.setUserName(sysUserUpdateDTO.getUserName());
        result.setPhoneNumber(sysUserUpdateDTO.getPhoneNumber());
        result.setStatus(sysUserUpdateDTO.getStatus());
        result.setEmail(sysUserUpdateDTO.getEmail());
        result.setRemark(sysUserUpdateDTO.getRemark());
        result.setSex(sysUserUpdateDTO.getSex());

        result.setUpdateTime(LocalDateTime.now());
        result.setUpdateUser(UserContext.getUser().getUserCode());
        return result;
    }

    private SysUserRoleExample buildDeleteSysUserRoleExample(Long userId) {
        SysUserRoleExample result = new SysUserRoleExample();
        result.createCriteria().andUserIdEqualTo(userId);
        return result;
    }

    private SysUserRole buildSysUserRole(Long userId, Long roleId) {
        SysUserRole result = new SysUserRole();
        result.setId(IdGenerator.next());
        result.setUserId(userId);
        result.setRoleId(roleId);
        return result;
    }

    private SysUserExample buildQueryListExample(SysUserQueryListDTO sysUserQueryListDTO) {
        SysUserExample result = new SysUserExample();
        SysUserExample.Criteria criteria = result.createCriteria();

        if (StringUtils.isNotBlank(sysUserQueryListDTO.getUserName())) {
            criteria.andUserNameLike(sysUserQueryListDTO.getUserName() + "%");
        }

        if (StringUtils.isNotBlank(sysUserQueryListDTO.getPhoneNumber())) {
            criteria.andPhoneNumberEqualTo(sysUserQueryListDTO.getPhoneNumber());
        }

        if (StringUtils.isNotBlank(sysUserQueryListDTO.getStatus())) {
            criteria.andStatusEqualTo(sysUserQueryListDTO.getStatus());
        }

        criteria.andIsDeletedEqualTo(YesOrNoEnum.NO.getCode());

        result.setOrderByClause("update_time desc");
        return result;
    }
}
