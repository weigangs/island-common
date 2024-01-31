package com.lkyl.island.common.user.service.impl;

import com.lkyl.island.common.ps.entity.SysUser;
import com.lkyl.island.common.ps.entity.SysUserExample;
import com.lkyl.island.common.ps.mapper.SysUserMapper;
import com.lkyl.island.common.user.service.ISysUserQueryService;
import com.lkyl.oceanframework.common.utils.annotation.PageSelector;
import com.lkyl.oceanframework.common.utils.enums.YesOrNoEnum;
import com.lkyl.oceanframework.common.utils.page.PageArgs;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class SysUserQueryServiceImpl implements ISysUserQueryService {

    @Autowired
    private SysUserMapper sysUserMapper;



    @Override
    public Optional<SysUser> queryUserByUserCode(String userCode) {
        if (StringUtils.isBlank(userCode)) {
            return Optional.empty();
        }

        SysUserExample example = new SysUserExample();
        example.createCriteria().andUserCodeEqualTo(userCode).andIsDeletedEqualTo(YesOrNoEnum.NO.getCode());
        List<SysUser> users = sysUserMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(users)) {
            return Optional.empty();
        }
        return Optional.of(users.get(0));
    }

    @Override
    public Optional<SysUser> queryUserByUserId(Long userId) {
        if (Objects.isNull(userId)) {
            return Optional.empty();
        }
        return Optional.ofNullable(sysUserMapper.selectByPrimaryKey(userId));
    }

    @Override
    public List<SysUser> queryUserByIds(List<Long> userIds) {
        if (CollectionUtils.isEmpty(userIds)) {
            return Collections.emptyList();
        }
        return sysUserMapper.selectByExample(buildSysUserExampleByIds(userIds));
    }

    @PageSelector
    @Override
    public List<SysUser> pageQueryUserByIds(List<Long> userIds, PageArgs pageArgs) {
        return queryUserByIds(userIds);
    }

    private SysUserExample buildSysUserExampleByIds(List<Long> userIds) {
        SysUserExample example = new SysUserExample();
        example.createCriteria().andIdIn(userIds).andIsDeletedEqualTo(YesOrNoEnum.NO.getCode());
        return example;
    }
}
