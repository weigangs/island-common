package com.lkyl.island.common.service.service.impl;

import com.lkyl.island.common.api.response.SysRoleVO;
import com.lkyl.oceanframework.mybatis.base.BaseServiceImpl;
import com.lkyl.island.common.ps.entity.SysRole;
import com.lkyl.island.common.api.request.SysRoleDTO;
import com.lkyl.island.common.service.service.SysRoleService;
import com.lkyl.island.common.service.converter.SysRoleConverter;
import com.lkyl.oceanframework.common.utils.constant.CommonCode;
import com.lkyl.oceanframework.common.utils.constant.PageConstant;
import com.lkyl.oceanframework.common.utils.exception.CommonException;
import com.lkyl.island.common.ps.dao.SysRoleDao;
import javax.annotation.Resource;
import com.lkyl.oceanframework.common.utils.utils.PageUtil;
import com.lkyl.oceanframework.common.utils.utils.CollectionUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import java.util.*;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * 角色信息表(SysRole)表服务实现类
 *
 * @author author
 * @since 2022-06-12 16:37:41
 */
@Service("sysRoleService")
public class SysRoleServiceImpl extends BaseServiceImpl<SysRole, Long> implements SysRoleService {
    @Resource
    private SysRoleDao sysRoleDao;

    @Resource
    public void setSysRoleDao(SysRoleDao sysRoleDao) {
        this.sysRoleDao = sysRoleDao;
        super.setBaseDao(sysRoleDao);
    }

	/**
	* 获取详情
	* @param id 主键ID
	* @return VO
	**/
	@Override
	public SysRoleVO detail(Long id) {
		Optional<SysRole> sysRole = super.get(id);

		if(sysRole.isPresent()) {
            return SysRoleConverter.INSTANCE.to(sysRole.get());
        } else {
            throw new CommonException(CommonCode.EXCEPTION, "未找到对应记录!");
        }
	}

	/**
	* 查询列表
	* @param sysRoleDTO 请求体DTO
	* @param	pageNum 当前页
	* @param	pageSize 页容
	* @return VO列表
	**/
	@Override
	public PageInfo search(SysRoleDTO sysRoleDTO, String pageNum, String pageSize) {
		int page = PageUtil.parsePage(pageNum, PageConstant.PAGE);
        int perPage = PageUtil.parsePerPage(pageSize, PageConstant.PER_PAGE);
        PageHelper.startPage(page, perPage);
		SysRole queryEntity = new SysRole();

        BeanUtils.copyProperties(sysRoleDTO, queryEntity);
        List<SysRole> sysRoleList = super.list(queryEntity);
		PageInfo pageInfo = new PageInfo<>(sysRoleList);

		if(CollectionUtils.isNotEmpty(pageInfo.getList())) {
            pageInfo.setList(SysRoleConverter.INSTANCE.to(pageInfo.getList()));
        }

		return pageInfo;
	}

	/**
	* 新增
	* @param sysRoleDTO) 新增请求体DTO
	* @return 成功插入条数
	**/
	@Override
	public int insert(SysRoleDTO sysRoleDTO) {
	    sysRoleDTO.setCreateTime(new Date());
        sysRoleDTO.setUpdateTime(new Date());
        return super.commonSave(sysRoleDTO, SysRole::new);
	}

	/**
	* 修改
	* @param @param sysRole 修改请求体DTO
	* @return 成功更新个数
	**/
	@Override
	public int update(SysRoleDTO sysRoleDTO) {
		sysRoleDTO.setUpdateTime(new Date());
        return super.commonUpdate(sysRoleDTO, SysRole::new);
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
