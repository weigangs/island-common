package com.lkyl.island.common.service.service.impl;

import com.lkyl.island.common.api.response.SysMenuVO;
import com.lkyl.oceanframework.mybatis.base.BaseServiceImpl;
import com.lkyl.island.common.ps.entity.SysMenu;
import com.lkyl.island.common.api.request.SysMenuDTO;
import com.lkyl.island.common.service.service.SysMenuService;
import com.lkyl.island.common.service.converter.SysMenuConverter;
import com.lkyl.oceanframework.common.utils.constant.CommonCode;
import com.lkyl.oceanframework.common.utils.constant.PageConstant;
import com.lkyl.oceanframework.common.utils.exception.CommonException;
import com.lkyl.island.common.ps.dao.SysMenuDao;
import javax.annotation.Resource;
import com.lkyl.oceanframework.common.utils.utils.PageUtil;
import com.lkyl.oceanframework.common.utils.utils.CollectionUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import java.util.*;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * 菜单权限表(SysMenu)表服务实现类
 *
 * @author author
 * @since 2022-06-12 16:37:41
 */
@Service("sysMenuService")
public class SysMenuServiceImpl extends BaseServiceImpl<SysMenu, Long> implements SysMenuService {
    @Resource
    private SysMenuDao sysMenuDao;

    @Resource
    public void setSysMenuDao(SysMenuDao sysMenuDao) {
        this.sysMenuDao = sysMenuDao;
        super.setBaseDao(sysMenuDao);
    }

	/**
	* 获取详情
	* @param id 主键ID
	* @return VO
	**/
	@Override
	public SysMenuVO detail(Long id) {
		Optional<SysMenu> sysMenu = super.get(id);

		if(sysMenu.isPresent()) {
            return SysMenuConverter.INSTANCE.to(sysMenu.get());
        } else {
            throw new CommonException(CommonCode.EXCEPTION, "未找到对应记录!");
        }
	}

	/**
	* 查询列表
	* @param sysMenuDTO 请求体DTO
	* @param	pageNum 当前页
	* @param	pageSize 页容
	* @return VO列表
	**/
	@Override
	public PageInfo search(SysMenuDTO sysMenuDTO, String pageNum, String pageSize) {
		int page = PageUtil.parsePage(pageNum, PageConstant.PAGE);
        int perPage = PageUtil.parsePerPage(pageSize, PageConstant.PER_PAGE);
        PageHelper.startPage(page, perPage);
		SysMenu queryEntity = new SysMenu();

        BeanUtils.copyProperties(sysMenuDTO, queryEntity);
        List<SysMenu> sysMenuList = super.list(queryEntity);
		PageInfo pageInfo = new PageInfo<>(sysMenuList);

		if(CollectionUtils.isNotEmpty(pageInfo.getList())) {
            pageInfo.setList(SysMenuConverter.INSTANCE.to(pageInfo.getList()));
        }

		return pageInfo;
	}

	/**
	* 新增
	* @param sysMenuDTO) 新增请求体DTO
	* @return 成功插入条数
	**/
	@Override
	public int insert(SysMenuDTO sysMenuDTO) {
	    sysMenuDTO.setCreateTime(new Date());
        sysMenuDTO.setUpdateTime(new Date());
        return super.commonSave(sysMenuDTO, SysMenu::new);
	}

	/**
	* 修改
	* @param @param sysMenu 修改请求体DTO
	* @return 成功更新个数
	**/
	@Override
	public int update(SysMenuDTO sysMenuDTO) {
		sysMenuDTO.setUpdateTime(new Date());
        return super.commonUpdate(sysMenuDTO, SysMenu::new);
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
