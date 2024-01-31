package com.lkyl.island.common.role.service.impl;

import com.lkyl.island.common.api.request.SysRoleListQueryDTO;
import com.lkyl.island.common.api.response.SysRoleListQueryVO;
import com.lkyl.island.common.common.enums.CommonStatusEnum;
import com.lkyl.island.common.ps.mapper.SysRoleMapper;
import com.lkyl.island.common.ps.mapper.SysUserRoleMapper;
import com.lkyl.island.common.ps.entity.SysRole;
import com.lkyl.island.common.ps.entity.SysRoleExample;
import com.lkyl.island.common.ps.entity.SysUserRole;
import com.lkyl.island.common.ps.entity.SysUserRoleExample;
import com.lkyl.island.common.role.converter.SysRoleConverter;
import com.lkyl.island.common.role.service.ISysRoleQueryService;
import com.lkyl.island.common.utils.RoleUtil;
import com.lkyl.oceanframework.common.utils.annotation.PageSelector;
import com.lkyl.oceanframework.common.utils.enums.YesOrNoEnum;
import com.lkyl.oceanframework.common.utils.page.PageArgs;
import com.lkyl.oceanframework.common.utils.utils.PageCopyUtils;
import com.lkyl.oceanframework.web.context.UserContext;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class SysRoleQueryServiceImpl implements ISysRoleQueryService {

    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Override
    public List<SysRole> queryRolesByUserId(Long userId) {

        if (Objects.isNull(userId)) {
            return Collections.emptyList();
        }

        List<Long> roleIds = queryUserRolesByUserId(userId).stream().map(SysUserRole::getRoleId).collect(Collectors.toList());

        if (CollectionUtils.isEmpty(roleIds)) {
            return Collections.emptyList();
        }
        SysRoleExample example = new SysRoleExample();
        example.createCriteria().andIdIn(roleIds)
                .andStatusEqualTo(CommonStatusEnum.NORMAL.getCode())
                .andIsDeletedEqualTo(YesOrNoEnum.NO.getCode());

        return sysRoleMapper.selectByExample(example);
    }

    @Override
    public List<SysRole> queryAllSysRoleExceptDeleted() {

        SysRoleExample example = new SysRoleExample();
        example.createCriteria().andIsDeletedEqualTo(YesOrNoEnum.NO.getCode());

        return sysRoleMapper.selectByExample(example);
    }

    @Override
    public List<SysUserRole> queryUserRolesByUserId(Long userId) {

        if (Objects.isNull(userId)) {
            return Collections.emptyList();
        }

        SysUserRoleExample example = new SysUserRoleExample();
        example.createCriteria().andUserIdEqualTo(userId);

        return sysUserRoleMapper.selectByExample(example);
    }

    @PageSelector
    @Override
    public List<SysRoleListQueryVO> pageQueryRoleList(SysRoleListQueryDTO sysRoleListQueryDTO, PageArgs pageArgs) {
        return PageCopyUtils.convertProperties(
                sysRoleMapper.selectByExample(buildPageQueryExample(sysRoleListQueryDTO)),
                SysRoleConverter.INSTANCE::to,
                null
        );
    }

    @Override
    public Optional<SysRole> queryRoleById(Long roleId) {
        return Optional.ofNullable(roleId).map(sysRoleMapper::selectByPrimaryKey);
    }

    private SysRoleExample buildPageQueryExample(SysRoleListQueryDTO sysRoleListQueryDTO) {
        SysRoleExample example = new SysRoleExample();
        SysRoleExample.Criteria criteria = example.createCriteria();
        if (StringUtils.isNotBlank(sysRoleListQueryDTO.getRoleName())) {
            criteria.andRoleNameLike(sysRoleListQueryDTO.getRoleName() + "%");
        }
        if (StringUtils.isNotBlank(sysRoleListQueryDTO.getRoleKey())) {
            criteria.andRoleKeyLike(sysRoleListQueryDTO.getRoleKey() + "%");
        }

        if (StringUtils.isNotBlank(sysRoleListQueryDTO.getStatus())) {
            criteria.andStatusEqualTo(sysRoleListQueryDTO.getStatus());
        }

        criteria.andIsDeletedEqualTo(YesOrNoEnum.NO.getCode());
        example.setOrderByClause("role_sort asc");
        return example;
    }

}
