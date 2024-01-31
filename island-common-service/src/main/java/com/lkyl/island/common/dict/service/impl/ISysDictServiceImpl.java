package com.lkyl.island.common.dict.service.impl;

import com.lkyl.island.common.api.request.*;
import com.lkyl.island.common.api.response.SysDictDataListVO;
import com.lkyl.island.common.api.response.SysDictDataVO;
import com.lkyl.island.common.api.response.SysDictTypeListVO;
import com.lkyl.island.common.api.response.SysDictTypeVO;
import com.lkyl.island.common.dict.converter.SysDictDataConverter;
import com.lkyl.island.common.dict.converter.SysDictTypeConverter;
import com.lkyl.island.common.dict.service.ISysDictQueryService;
import com.lkyl.island.common.dict.service.ISysDictService;
import com.lkyl.island.common.ps.entity.SysDictData;
import com.lkyl.island.common.ps.entity.SysDictDataExample;
import com.lkyl.island.common.ps.entity.SysDictType;
import com.lkyl.island.common.ps.mapper.SysDictDataMapper;
import com.lkyl.island.common.ps.mapper.SysDictTypeMapper;
import com.lkyl.oceanframework.common.utils.annotation.PageSelector;
import com.lkyl.oceanframework.common.utils.enums.YesOrNoEnum;
import com.lkyl.oceanframework.common.utils.page.PageArgs;
import com.lkyl.oceanframework.common.utils.utils.IdGenerator;
import com.lkyl.oceanframework.common.utils.utils.PageCopyUtils;
import com.lkyl.oceanframework.web.context.UserContext;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author nicholas
 * @date 2023/07/22 20:56
 */
@Service
public class ISysDictServiceImpl implements ISysDictService {

    @Resource
    private ISysDictQueryService iSysDictQueryService;

    @Resource
    private SysDictTypeMapper sysDictTypeMapper;

    @Resource
    private SysDictDataMapper sysDictDataMapper;

    @Override
    public int addDictType(SysDictTypeAddDTO sysDictTypeAddDTO) {

        return sysDictTypeMapper.insert(buildDictTypeByDto(sysDictTypeAddDTO));
    }

    private SysDictType buildDictTypeByDto(SysDictTypeAddDTO sysDictTypeAddDTO) {
        SysDictType sysDictType = new SysDictType();
        sysDictType.setId(IdGenerator.next());
        sysDictType.setDictName(sysDictTypeAddDTO.getDictName());
        sysDictType.setDictType(sysDictTypeAddDTO.getDictType());
        sysDictType.setCreateTime(LocalDateTime.now());
        sysDictType.setUpdateTime(LocalDateTime.now());
        sysDictType.setCreateUser(UserContext.getUser().getUserCode());
        sysDictType.setUpdateUser(UserContext.getUser().getUserCode());
        sysDictType.setIsDeleted(YesOrNoEnum.NO.getCode());
        return sysDictType;
    }

    @Override
    public List<SysDictTypeListVO> pageQueryDictType(SysDictTypeListQueryDTO sysDictTypeListQueryDTO, PageArgs pageArgs) {
        return PageCopyUtils.convertProperties(iSysDictQueryService.pageQueryDictType(sysDictTypeListQueryDTO, pageArgs), SysDictTypeConverter.INSTANCE::to, null);
    }

    @Override
    public List<SysDictDataVO> queryDictData(String dictType) {
        SysDictDataExample example = new SysDictDataExample();
        example.createCriteria().andDictTypeEqualTo(dictType).andIsDeletedEqualTo(YesOrNoEnum.NO.getCode());
        example.setOrderByClause("dict_sort asc");
        return SysDictDataConverter.INSTANCE.to(sysDictDataMapper.selectByExample(example));
    }

    @PageSelector
    @Override
    public List<SysDictDataListVO> pageQueryDictData(SysDictDataListQueryDTO sysDictDataListQueryDTO, PageArgs pageArgs) {

        return PageCopyUtils.convertProperties(
                sysDictDataMapper.selectByExample(buildListDictDataExample(sysDictDataListQueryDTO)),
                SysDictDataConverter.INSTANCE::toVo,
                null
        );
    }

    @Override
    public SysDictTypeVO getDictTypeById(Long dictTypeId) {
        return SysDictTypeConverter.INSTANCE.toDetailVo(sysDictTypeMapper.selectByPrimaryKey(dictTypeId));
    }

    @Override
    public int addDictData(SysDictDataAddDTO sysDictDataAddDTO) {
        return sysDictDataMapper.insert(buildDictDataByDto(sysDictDataAddDTO));
    }

    @Override
    public int updateDictData(SysDictDataUpdateDTO sysDictDataUpdateDTO) {
        return sysDictDataMapper.updateByPrimaryKeySelective(buildDictDataUpdateByDto(sysDictDataUpdateDTO));
    }

    @Override
    public int deleteDictData(SysDictDataDeleteDTO sysDictDataDeleteDTO) {
        return sysDictDataMapper.updateByExampleSelective(
                buildLogicDeleteDictData(),
                buildLogicDeleteDictDataExample(sysDictDataDeleteDTO)
        );
    }

    private SysDictData buildLogicDeleteDictData() {
        SysDictData sysDictData = new SysDictData();

        sysDictData.setIsDeleted(YesOrNoEnum.YES.getCode());
        sysDictData.setUpdateTime(LocalDateTime.now());
        sysDictData.setUpdateUser(UserContext.getUser().getUserCode());
        return sysDictData;
    }

    private SysDictDataExample buildLogicDeleteDictDataExample(SysDictDataDeleteDTO sysDictDataDeleteDTO) {
        SysDictDataExample result = new SysDictDataExample();
        SysDictDataExample.Criteria criteria = result.createCriteria();
        criteria.andIdIn(sysDictDataDeleteDTO.getDictDataIdList());
        criteria.andIsDeletedEqualTo(YesOrNoEnum.NO.getCode());
        return result;
    }

    private SysDictData buildDictDataUpdateByDto(SysDictDataUpdateDTO sysDictDataUpdateDTO) {
        SysDictData sysDictData = new SysDictData();
        sysDictData.setId(sysDictDataUpdateDTO.getDictDataId());
        sysDictData.setDictLabel(sysDictDataUpdateDTO.getDictLabel());
        sysDictData.setDictValue(sysDictDataUpdateDTO.getDictValue());
        sysDictData.setDictType(sysDictDataUpdateDTO.getDictType());
        sysDictData.setDictSort(sysDictDataUpdateDTO.getDictSort());
        sysDictData.setIsDeleted(YesOrNoEnum.NO.getCode());
        sysDictData.setUpdateTime(LocalDateTime.now());
        sysDictData.setUpdateUser(UserContext.getUser().getUserCode());
        return sysDictData;
    }

    private SysDictData buildDictDataByDto(SysDictDataAddDTO sysDictDataAddDTO) {
        SysDictData sysDictData = new SysDictData();
        sysDictData.setId(IdGenerator.next());
        sysDictData.setDictLabel(sysDictDataAddDTO.getDictLabel());
        sysDictData.setDictValue(sysDictDataAddDTO.getDictValue());
        sysDictData.setDictType(sysDictDataAddDTO.getDictType());
        sysDictData.setDictSort(sysDictDataAddDTO.getDictSort());
        sysDictData.setIsDeleted(YesOrNoEnum.NO.getCode());
        sysDictData.setCreateTime(LocalDateTime.now());
        sysDictData.setUpdateTime(LocalDateTime.now());
        sysDictData.setCreateUser(UserContext.getUser().getUserCode());
        sysDictData.setUpdateUser(UserContext.getUser().getUserCode());
        return sysDictData;
    }

    private SysDictDataExample buildListDictDataExample(SysDictDataListQueryDTO sysDictDataListQueryDTO) {
        SysDictDataExample result = new SysDictDataExample();
        SysDictDataExample.Criteria criteria = result.createCriteria();
        if (StringUtils.isNotBlank(sysDictDataListQueryDTO.getDictLabel())) {
            criteria.andDictLabelLike(sysDictDataListQueryDTO.getDictLabel() + "%");
        }

        if (StringUtils.isNotBlank(sysDictDataListQueryDTO.getDictType())) {
            criteria.andDictTypeEqualTo(sysDictDataListQueryDTO.getDictType());
        }

        criteria.andIsDeletedEqualTo(YesOrNoEnum.NO.getCode());
        result.setOrderByClause("dict_sort asc");
        return result;
    }
}
