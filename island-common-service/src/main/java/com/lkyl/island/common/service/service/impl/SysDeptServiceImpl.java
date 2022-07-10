package com.lkyl.island.common.service.service.impl;

import com.lkyl.island.common.api.response.SysDeptVO;
import com.lkyl.oceanframework.mybatis.base.BaseServiceImpl;
import com.lkyl.island.common.ps.entity.SysDept;
import com.lkyl.island.common.api.request.SysDeptDTO;
import com.lkyl.island.common.service.service.SysDeptService;
import com.lkyl.island.common.service.converter.SysDeptConverter;
import com.lkyl.oceanframework.common.utils.constant.CommonCode;
import com.lkyl.oceanframework.common.utils.constant.PageConstant;
import com.lkyl.oceanframework.common.utils.exception.CommonException;
import com.lkyl.island.common.ps.dao.SysDeptDao;
import javax.annotation.Resource;
import com.lkyl.oceanframework.common.utils.utils.PageUtil;
import com.lkyl.oceanframework.common.utils.utils.CollectionUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import java.util.*;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * 部门表(SysDept)表服务实现类
 *
 * @author author
 * @since 2022-06-12 16:37:41
 */
@Service("sysDeptService")
public class SysDeptServiceImpl extends BaseServiceImpl<SysDept, Long> implements SysDeptService {
    @Resource
    private SysDeptDao sysDeptDao;

    @Resource
    public void setSysDeptDao(SysDeptDao sysDeptDao) {
        this.sysDeptDao = sysDeptDao;
        super.setBaseDao(sysDeptDao);
    }

	/**
	* 获取详情
	* @param id 主键ID
	* @return VO
	**/
	@Override
	public SysDeptVO detail(Long id) {
		Optional<SysDept> sysDept = super.get(id);

		if(sysDept.isPresent()) {
            return SysDeptConverter.INSTANCE.to(sysDept.get());
        } else {
            throw new CommonException(CommonCode.EXCEPTION, "未找到对应记录!");
        }
	}

	/**
	* 查询列表
	* @param sysDeptDTO 请求体DTO
	* @param	pageNum 当前页
	* @param	pageSize 页容
	* @return VO列表
	**/
	@Override
	public PageInfo search(SysDeptDTO sysDeptDTO, String pageNum, String pageSize) {
		int page = PageUtil.parsePage(pageNum, PageConstant.PAGE);
        int perPage = PageUtil.parsePerPage(pageSize, PageConstant.PER_PAGE);
        PageHelper.startPage(page, perPage);
		SysDept queryEntity = new SysDept();

        BeanUtils.copyProperties(sysDeptDTO, queryEntity);
        List<SysDept> sysDeptList = super.list(queryEntity);
		PageInfo pageInfo = new PageInfo<>(sysDeptList);

		if(CollectionUtils.isNotEmpty(pageInfo.getList())) {
            pageInfo.setList(SysDeptConverter.INSTANCE.to(pageInfo.getList()));
        }

		return pageInfo;
	}

	/**
	* 新增
	* @param sysDeptDTO) 新增请求体DTO
	* @return 成功插入条数
	**/
	@Override
	public int insert(SysDeptDTO sysDeptDTO) {
	    sysDeptDTO.setCreateTime(new Date());
        sysDeptDTO.setUpdateTime(new Date());
        return super.commonSave(sysDeptDTO, SysDept::new);
	}

	/**
	* 修改
	* @param @param sysDept 修改请求体DTO
	* @return 成功更新个数
	**/
	@Override
	public int update(SysDeptDTO sysDeptDTO) {
		sysDeptDTO.setUpdateTime(new Date());
        return super.commonUpdate(sysDeptDTO, SysDept::new);
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
