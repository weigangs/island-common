package com.lkyl.island.common.service.service.impl;

import com.lkyl.island.common.api.response.SysUserRoleVO;
import com.lkyl.oceanframework.mybatis.base.BaseServiceImpl;
import com.lkyl.island.common.ps.entity.SysUserRole;
import com.lkyl.island.common.api.request.SysUserRoleDTO;
import com.lkyl.island.common.service.service.SysUserRoleService;
import com.lkyl.island.common.service.converter.SysUserRoleConverter;
import com.lkyl.oceanframework.common.utils.constant.CommonCode;
import com.lkyl.oceanframework.common.utils.constant.PageConstant;
import com.lkyl.oceanframework.common.utils.exception.CommonException;
import com.lkyl.island.common.ps.dao.SysUserRoleDao;
import javax.annotation.Resource;
import com.lkyl.oceanframework.common.utils.utils.PageUtil;
import com.lkyl.oceanframework.common.utils.utils.CollectionUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import java.util.*;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * 用户和角色关联表(SysUserRole)表服务实现类
 *
 * @author author
 * @since 2022-06-12 16:37:41
 */
@Service("sysUserRoleService")
public class SysUserRoleServiceImpl extends BaseServiceImpl<SysUserRole, Long> implements SysUserRoleService {
    @Resource
    private SysUserRoleDao sysUserRoleDao;

    @Resource
    public void setSysUserRoleDao(SysUserRoleDao sysUserRoleDao) {
        this.sysUserRoleDao = sysUserRoleDao;
        super.setBaseDao(sysUserRoleDao);
    }

	/**
	* 获取详情
	* @param id 主键ID
	* @return VO
	**/
	@Override
	public SysUserRoleVO detail(Long id) {
		Optional<SysUserRole> sysUserRole = super.get(id);

		if(sysUserRole.isPresent()) {
            return SysUserRoleConverter.INSTANCE.to(sysUserRole.get());
        } else {
            throw new CommonException(CommonCode.EXCEPTION, "未找到对应记录!");
        }
	}

	/**
	* 查询列表
	* @param sysUserRoleDTO 请求体DTO
	* @param	pageNum 当前页
	* @param	pageSize 页容
	* @return VO列表
	**/
	@Override
	public PageInfo search(SysUserRoleDTO sysUserRoleDTO, String pageNum, String pageSize) {
		int page = PageUtil.parsePage(pageNum, PageConstant.PAGE);
        int perPage = PageUtil.parsePerPage(pageSize, PageConstant.PER_PAGE);
        PageHelper.startPage(page, perPage);
		SysUserRole queryEntity = new SysUserRole();

        BeanUtils.copyProperties(sysUserRoleDTO, queryEntity);
        List<SysUserRole> sysUserRoleList = super.list(queryEntity);
		PageInfo pageInfo = new PageInfo<>(sysUserRoleList);

		if(CollectionUtils.isNotEmpty(pageInfo.getList())) {
            pageInfo.setList(SysUserRoleConverter.INSTANCE.to(pageInfo.getList()));
        }

		return pageInfo;
	}

	/**
	* 新增
	* @param sysUserRoleDTO) 新增请求体DTO
	* @return 成功插入条数
	**/
	@Override
	public int insert(SysUserRoleDTO sysUserRoleDTO) {
        return super.commonSave(sysUserRoleDTO, SysUserRole::new);
	}

	/**
	* 修改
	* @param @param sysUserRole 修改请求体DTO
	* @return 成功更新个数
	**/
	@Override
	public int update(SysUserRoleDTO sysUserRoleDTO) {
        return super.commonUpdate(sysUserRoleDTO, SysUserRole::new);
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
