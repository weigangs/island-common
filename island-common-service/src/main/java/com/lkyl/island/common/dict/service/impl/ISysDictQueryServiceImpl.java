package com.lkyl.island.common.dict.service.impl;

import com.lkyl.island.common.api.request.SysDictTypeListQueryDTO;
import com.lkyl.island.common.dict.service.ISysDictQueryService;
import com.lkyl.island.common.ps.entity.SysDictType;
import com.lkyl.island.common.ps.entity.SysDictTypeExample;
import com.lkyl.island.common.ps.mapper.SysDictTypeMapper;
import com.lkyl.oceanframework.common.utils.annotation.PageSelector;
import com.lkyl.oceanframework.common.utils.enums.YesOrNoEnum;
import com.lkyl.oceanframework.common.utils.page.PageArgs;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author nicholas
 * @date 2023/07/22 20:59
 */
@Service
public class ISysDictQueryServiceImpl implements ISysDictQueryService {

    @Resource
    private SysDictTypeMapper sysDictTypeMapper;

    @PageSelector
    @Override
    public List<SysDictType> pageQueryDictType(SysDictTypeListQueryDTO sysDictTypeListQueryDTO, PageArgs pageArgs) {



        return sysDictTypeMapper.selectByExample(buildQueryExample(sysDictTypeListQueryDTO));
    }

    private SysDictTypeExample buildQueryExample(SysDictTypeListQueryDTO sysDictTypeListQueryDTO) {

        SysDictTypeExample result = new SysDictTypeExample();
        SysDictTypeExample.Criteria criteria = result.createCriteria();

        // 对dictTypeListQueryDTO变量判断所有成员if isNotBlank
        if (StringUtils.isNotBlank(sysDictTypeListQueryDTO.getDictName())) {
            criteria.andDictNameLike(sysDictTypeListQueryDTO.getDictName() + "%");
        }
        if (StringUtils.isNotBlank(sysDictTypeListQueryDTO.getDictType())) {
            criteria.andDictTypeLike(sysDictTypeListQueryDTO.getDictType() + "%");
        }

        criteria.andIsDeletedEqualTo(YesOrNoEnum.NO.getCode());

        result.setOrderByClause("update_time desc");

        return result;
    }
}
