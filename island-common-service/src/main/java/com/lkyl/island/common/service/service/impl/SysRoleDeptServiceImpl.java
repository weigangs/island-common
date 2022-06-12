package com.lkyl.island.common.service.service.impl;

import com.lkyl.island.common.api.response.SysRoleDeptVO;
import com.lkyl.oceanframework.mybatis.base.BaseServiceImpl;
import com.lkyl.island.common.ps.entity.SysRoleDept;
import com.lkyl.island.common.api.request.SysRoleDeptDTO;
import com.lkyl.island.common.service.service.SysRoleDeptService;
import com.lkyl.island.common.service.converter.SysRoleDeptConverter;
import com.lkyl.oceanframework.common.utils.constant.CommonCode;
import com.lkyl.oceanframework.common.utils.constant.PageConstant;
import com.lkyl.oceanframework.common.utils.exception.CommonException;
import com.lkyl.island.common.ps.dao.SysRoleDeptDao;
import javax.annotation.Resource;
import com.lkyl.oceanframework.common.utils.utils.PageUtil;
import com.lkyl.oceanframework.common.utils.utils.CollectionUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import java.util.*;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * 角色和部门关联表(SysRoleDept)表服务实现类
 *
 * @author author
 * @since 2022-06-12 16:37:41
 */
@Service("sysRoleDeptService")
public class SysRoleDeptServiceImpl extends BaseServiceImpl<SysRoleDept, Long> implements SysRoleDeptService {
    @Resource
    private SysRoleDeptDao sysRoleDeptDao;

    @Resource
    public void setSysRoleDeptDao(SysRoleDeptDao sysRoleDeptDao) {
        this.sysRoleDeptDao = sysRoleDeptDao;
        super.setBaseDao(sysRoleDeptDao);
    }

	/**
	* 获取详情
	* @param id 主键ID
	* @return VO
	**/
	@Override
	public SysRoleDeptVO detail(Long id) {
		Optional<SysRoleDept> sysRoleDept = super.get(id);

		if(sysRoleDept.isPresent()) {
            return SysRoleDeptConverter.INSTANCE.to(sysRoleDept.get());
        } else {
            throw new CommonException(CommonCode.EXCEPTION, "未找到对应记录!");
        }
	}

	/**
	* 查询列表
	* @param sysRoleDeptDTO 请求体DTO
	* @param	pageNum 当前页
	* @param	pageSize 页容
	* @return VO列表
	**/
	@Override
	public PageInfo search(SysRoleDeptDTO sysRoleDeptDTO, String pageNum, String pageSize) {
		int page = PageUtil.parsePage(pageNum, PageConstant.PAGE);
        int perPage = PageUtil.parsePerPage(pageSize, PageConstant.PER_PAGE);
        PageHelper.startPage(page, perPage);
		SysRoleDept queryEntity = new SysRoleDept();

        BeanUtils.copyProperties(sysRoleDeptDTO, queryEntity);
        List<SysRoleDept> sysRoleDeptList = super.list(queryEntity);
		PageInfo pageInfo = new PageInfo<>(sysRoleDeptList);

		if(CollectionUtils.isNotEmpty(pageInfo.getList())) {
            pageInfo.setList(SysRoleDeptConverter.INSTANCE.to(pageInfo.getList()));
        }

		return pageInfo;
	}

	/**
	* 新增
	* @param sysRoleDeptDTO) 新增请求体DTO
	* @return 成功插入条数
	**/
	@Override
	public int insert(SysRoleDeptDTO sysRoleDeptDTO) {
        return super.commonSave(sysRoleDeptDTO, SysRoleDept::new);
	}

	/**
	* 修改
	* @param @param sysRoleDept 修改请求体DTO
	* @return 成功更新个数
	**/
	@Override
	public int update(SysRoleDeptDTO sysRoleDeptDTO) {
        return super.commonUpdate(sysRoleDeptDTO, SysRoleDept::new);
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
