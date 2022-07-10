package com.lkyl.island.common.service.service.impl;

import com.lkyl.island.common.api.response.SysDictTypeVO;
import com.lkyl.oceanframework.mybatis.base.BaseServiceImpl;
import com.lkyl.island.common.ps.entity.SysDictType;
import com.lkyl.island.common.api.request.SysDictTypeDTO;
import com.lkyl.island.common.service.service.SysDictTypeService;
import com.lkyl.island.common.service.converter.SysDictTypeConverter;
import com.lkyl.oceanframework.common.utils.constant.CommonCode;
import com.lkyl.oceanframework.common.utils.constant.PageConstant;
import com.lkyl.oceanframework.common.utils.exception.CommonException;
import com.lkyl.island.common.ps.dao.SysDictTypeDao;
import javax.annotation.Resource;
import com.lkyl.oceanframework.common.utils.utils.PageUtil;
import com.lkyl.oceanframework.common.utils.utils.CollectionUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import java.util.*;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * 字典类型表(SysDictType)表服务实现类
 *
 * @author author
 * @since 2022-06-12 16:37:41
 */
@Service("sysDictTypeService")
public class SysDictTypeServiceImpl extends BaseServiceImpl<SysDictType, Long> implements SysDictTypeService {
    @Resource
    private SysDictTypeDao sysDictTypeDao;

    @Resource
    public void setSysDictTypeDao(SysDictTypeDao sysDictTypeDao) {
        this.sysDictTypeDao = sysDictTypeDao;
        super.setBaseDao(sysDictTypeDao);
    }

	/**
	* 获取详情
	* @param id 主键ID
	* @return VO
	**/
	@Override
	public SysDictTypeVO detail(Long id) {
		Optional<SysDictType> sysDictType = super.get(id);

		if(sysDictType.isPresent()) {
            return SysDictTypeConverter.INSTANCE.to(sysDictType.get());
        } else {
            throw new CommonException(CommonCode.EXCEPTION, "未找到对应记录!");
        }
	}

	/**
	* 查询列表
	* @param sysDictTypeDTO 请求体DTO
	* @param	pageNum 当前页
	* @param	pageSize 页容
	* @return VO列表
	**/
	@Override
	public PageInfo search(SysDictTypeDTO sysDictTypeDTO, String pageNum, String pageSize) {
		int page = PageUtil.parsePage(pageNum, PageConstant.PAGE);
        int perPage = PageUtil.parsePerPage(pageSize, PageConstant.PER_PAGE);
        PageHelper.startPage(page, perPage);
		SysDictType queryEntity = new SysDictType();

        BeanUtils.copyProperties(sysDictTypeDTO, queryEntity);
        List<SysDictType> sysDictTypeList = super.list(queryEntity);
		PageInfo pageInfo = new PageInfo<>(sysDictTypeList);

		if(CollectionUtils.isNotEmpty(pageInfo.getList())) {
            pageInfo.setList(SysDictTypeConverter.INSTANCE.to(pageInfo.getList()));
        }

		return pageInfo;
	}

	/**
	* 新增
	* @param sysDictTypeDTO) 新增请求体DTO
	* @return 成功插入条数
	**/
	@Override
	public int insert(SysDictTypeDTO sysDictTypeDTO) {
	    sysDictTypeDTO.setCreateTime(new Date());
        sysDictTypeDTO.setUpdateTime(new Date());
        return super.commonSave(sysDictTypeDTO, SysDictType::new);
	}

	/**
	* 修改
	* @param @param sysDictType 修改请求体DTO
	* @return 成功更新个数
	**/
	@Override
	public int update(SysDictTypeDTO sysDictTypeDTO) {
		sysDictTypeDTO.setUpdateTime(new Date());
        return super.commonUpdate(sysDictTypeDTO, SysDictType::new);
	}

	/**
	* 删除
	* @param id 主键ID
	* @return 成功删除个数
	**/
	@Override
	public int remove(Long id) {
		return super.commonRemove(id);
	}
}
