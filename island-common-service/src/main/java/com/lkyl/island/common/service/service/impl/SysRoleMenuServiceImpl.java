package com.lkyl.island.common.service.service.impl;

import com.lkyl.island.common.api.response.SysRoleMenuVO;
import com.lkyl.oceanframework.mybatis.base.BaseServiceImpl;
import com.lkyl.island.common.ps.entity.SysRoleMenu;
import com.lkyl.island.common.api.request.SysRoleMenuDTO;
import com.lkyl.island.common.service.service.SysRoleMenuService;
import com.lkyl.island.common.service.converter.SysRoleMenuConverter;
import com.lkyl.oceanframework.common.utils.constant.CommonCode;
import com.lkyl.oceanframework.common.utils.constant.PageConstant;
import com.lkyl.oceanframework.common.utils.exception.CommonException;
import com.lkyl.island.common.ps.dao.SysRoleMenuDao;
import javax.annotation.Resource;
import com.lkyl.oceanframework.common.utils.utils.PageUtil;
import com.lkyl.oceanframework.common.utils.utils.CollectionUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import java.util.*;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * 角色和菜单关联表(SysRoleMenu)表服务实现类
 *
 * @author author
 * @since 2022-06-12 16:37:41
 */
@Service("sysRoleMenuService")
public class SysRoleMenuServiceImpl extends BaseServiceImpl<SysRoleMenu, Long> implements SysRoleMenuService {
    @Resource
    private SysRoleMenuDao sysRoleMenuDao;

    @Resource
    public void setSysRoleMenuDao(SysRoleMenuDao sysRoleMenuDao) {
        this.sysRoleMenuDao = sysRoleMenuDao;
        super.setBaseDao(sysRoleMenuDao);
    }

	/**
	* 获取详情
	* @param id 主键ID
	* @return VO
	**/
	@Override
	public SysRoleMenuVO detail(Long id) {
		Optional<SysRoleMenu> sysRoleMenu = super.get(id);

		if(sysRoleMenu.isPresent()) {
            return SysRoleMenuConverter.INSTANCE.to(sysRoleMenu.get());
        } else {
            throw new CommonException(CommonCode.EXCEPTION, "未找到对应记录!");
        }
	}

	/**
	* 查询列表
	* @param sysRoleMenuDTO 请求体DTO
	* @param	pageNum 当前页
	* @param	pageSize 页容
	* @return VO列表
	**/
	@Override
	public PageInfo search(SysRoleMenuDTO sysRoleMenuDTO, String pageNum, String pageSize) {
		int page = PageUtil.parsePage(pageNum, PageConstant.PAGE);
        int perPage = PageUtil.parsePerPage(pageSize, PageConstant.PER_PAGE);
        PageHelper.startPage(page, perPage);
		SysRoleMenu queryEntity = new SysRoleMenu();

        BeanUtils.copyProperties(sysRoleMenuDTO, queryEntity);
        List<SysRoleMenu> sysRoleMenuList = super.list(queryEntity);
		PageInfo pageInfo = new PageInfo<>(sysRoleMenuList);

		if(CollectionUtils.isNotEmpty(pageInfo.getList())) {
            pageInfo.setList(SysRoleMenuConverter.INSTANCE.to(pageInfo.getList()));
        }

		return pageInfo;
	}

	/**
	* 新增
	* @param sysRoleMenuDTO) 新增请求体DTO
	* @return 成功插入条数
	**/
	@Override
	public int insert(SysRoleMenuDTO sysRoleMenuDTO) {
        return super.commonSave(sysRoleMenuDTO, SysRoleMenu::new);
	}

	/**
	* 修改
	* @param @param sysRoleMenu 修改请求体DTO
	* @return 成功更新个数
	**/
	@Override
	public int update(SysRoleMenuDTO sysRoleMenuDTO) {
        return super.commonUpdate(sysRoleMenuDTO, SysRoleMenu::new);
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
