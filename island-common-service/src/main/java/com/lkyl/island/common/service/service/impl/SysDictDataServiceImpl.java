package com.lkyl.island.common.service.service.impl;

import com.lkyl.island.common.api.response.SysDictDataVO;
import com.lkyl.oceanframework.mybatis.base.BaseServiceImpl;
import com.lkyl.island.common.ps.entity.SysDictData;
import com.lkyl.island.common.api.request.SysDictDataDTO;
import com.lkyl.island.common.service.service.SysDictDataService;
import com.lkyl.island.common.service.converter.SysDictDataConverter;
import com.lkyl.oceanframework.common.utils.constant.CommonCode;
import com.lkyl.oceanframework.common.utils.constant.PageConstant;
import com.lkyl.oceanframework.common.utils.exception.CommonException;
import com.lkyl.island.common.ps.dao.SysDictDataDao;
import javax.annotation.Resource;
import com.lkyl.oceanframework.common.utils.utils.PageUtil;
import com.lkyl.oceanframework.common.utils.utils.CollectionUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import java.util.*;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * 字典数据表(SysDictData)表服务实现类
 *
 * @author author
 * @since 2022-06-12 16:37:41
 */
@Service("sysDictDataService")
public class SysDictDataServiceImpl extends BaseServiceImpl<SysDictData, Long> implements SysDictDataService {
    @Resource
    private SysDictDataDao sysDictDataDao;

    @Resource
    public void setSysDictDataDao(SysDictDataDao sysDictDataDao) {
        this.sysDictDataDao = sysDictDataDao;
        super.setBaseDao(sysDictDataDao);
    }

	/**
	* 获取详情
	* @param id 主键ID
	* @return VO
	**/
	@Override
	public SysDictDataVO detail(Long id) {
		Optional<SysDictData> sysDictData = super.get(id);

		if(sysDictData.isPresent()) {
            return SysDictDataConverter.INSTANCE.to(sysDictData.get());
        } else {
            throw new CommonException(CommonCode.EXCEPTION, "未找到对应记录!");
        }
	}

	/**
	* 查询列表
	* @param sysDictDataDTO 请求体DTO
	* @param	pageNum 当前页
	* @param	pageSize 页容
	* @return VO列表
	**/
	@Override
	public PageInfo search(SysDictDataDTO sysDictDataDTO, String pageNum, String pageSize) {
		int page = PageUtil.parsePage(pageNum, PageConstant.PAGE);
        int perPage = PageUtil.parsePerPage(pageSize, PageConstant.PER_PAGE);
        PageHelper.startPage(page, perPage);
		SysDictData queryEntity = new SysDictData();

        BeanUtils.copyProperties(sysDictDataDTO, queryEntity);
        List<SysDictData> sysDictDataList = super.list(queryEntity);
		PageInfo pageInfo = new PageInfo<>(sysDictDataList);

		if(CollectionUtils.isNotEmpty(pageInfo.getList())) {
            pageInfo.setList(SysDictDataConverter.INSTANCE.to(pageInfo.getList()));
        }

		return pageInfo;
	}

	/**
	* 新增
	* @param sysDictDataDTO) 新增请求体DTO
	* @return 成功插入条数
	**/
	@Override
	public int insert(SysDictDataDTO sysDictDataDTO) {
	    sysDictDataDTO.setCreateTime(new Date());
        sysDictDataDTO.setUpdateTime(new Date());
        return super.commonSave(sysDictDataDTO, SysDictData::new);
	}

	/**
	* 修改
	* @param @param sysDictData 修改请求体DTO
	* @return 成功更新个数
	**/
	@Override
	public int update(SysDictDataDTO sysDictDataDTO) {
		sysDictDataDTO.setUpdateTime(new Date());
        return super.commonUpdate(sysDictDataDTO, SysDictData::new);
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
