package com.lkyl.island.common.menu.service.impl;

import com.lkyl.island.common.api.request.SysMenuAddDTO;
import com.lkyl.island.common.api.request.SysMenuListQueryDTO;
import com.lkyl.island.common.api.request.SysMenuUpdateDTO;
import com.lkyl.island.common.api.response.MenuTreeVO;
import com.lkyl.island.common.api.response.RouterVO;
import com.lkyl.island.common.api.response.SysMenuVO;
import com.lkyl.island.common.common.enums.CommonExceptionEnum;
import com.lkyl.island.common.menu.converter.SysMenuConverter;
import com.lkyl.island.common.menu.service.ISysMenuCommonService;
import com.lkyl.island.common.menu.service.ISysMenuQueryService;
import com.lkyl.island.common.menu.service.ISysMenuService;
import com.lkyl.island.common.ps.entity.SysMenu;
import com.lkyl.island.common.ps.entity.SysMenuExample;
import com.lkyl.island.common.ps.entity.SysRoleMenuExample;
import com.lkyl.island.common.ps.mapper.SysMenuMapper;
import com.lkyl.island.common.ps.mapper.SysRoleMenuMapper;
import com.lkyl.oceanframework.common.utils.enums.YesOrNoEnum;
import com.lkyl.oceanframework.common.utils.exception.BusinessExceptionFactory;
import com.lkyl.oceanframework.common.utils.utils.IdGenerator;
import com.lkyl.oceanframework.common.utils.utils.PageCopyUtils;
import com.lkyl.oceanframework.web.context.UserContext;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 菜单权限表(SysMenu)表服务实现类
 *
 * @author author
 * @since 2022-06-12 16:37:41
 */
@Service
public class SysMenuServiceImpl implements ISysMenuService {


	@Resource
	private ISysMenuCommonService iSysMenuCommonService;

	@Resource
	private ISysMenuQueryService iSysMenuQueryService;

	@Resource
	private SysMenuMapper sysMenuMapper;

	@Resource
	private SysRoleMenuMapper sysRoleMenuMapper;

	/**
	* 获取详情
	* @param id 主键ID
	* @return VO
	**/
	@Override
	public SysMenuVO detail(Long id) {
		return iSysMenuQueryService.queryMenuByMenuId(id).map(SysMenuConverter.INSTANCE::to)
				.orElseThrow(() -> BusinessExceptionFactory.getException(CommonExceptionEnum.RECORD_NOT_FOUND));
	}

	/**
	* 查询列表
	* @param sysMenuListQueryDTO 请求体DTO
	* @return VO列表
	**/
	@Override
	public List<SysMenuVO> search(SysMenuListQueryDTO sysMenuListQueryDTO) {

		return SysMenuConverter.INSTANCE.to(iSysMenuQueryService.querySysMenuByParam(buildSearchExample(sysMenuListQueryDTO)));
	}

	private SysMenuExample buildSearchExample(SysMenuListQueryDTO sysMenuListQueryDTO) {
		SysMenuExample result = new SysMenuExample();
		SysMenuExample.Criteria criteria = result.createCriteria();

		if (StringUtils.isNotBlank(sysMenuListQueryDTO.getMenuName())) {
			criteria.andMenuNameLike(sysMenuListQueryDTO.getMenuName() + "%");
		}

		if (StringUtils.isNotBlank(sysMenuListQueryDTO.getStatus())) {
			criteria.andStatusEqualTo(sysMenuListQueryDTO.getStatus());
		}

		criteria.andIsDeletedEqualTo(YesOrNoEnum.NO.getCode());

		result.setOrderByClause("order_num asc");
		return result;
	}

	@Override
	public List<RouterVO> getMenusByUserId(Long userId) {
		List<SysMenuVO> viewList = SysMenuConverter.INSTANCE.to(iSysMenuCommonService.getMenusByUserId(userId));
		return iSysMenuCommonService.buildMenus(iSysMenuCommonService.getChildPerms(viewList, 0));
	}

	@Override
	public List<MenuTreeVO> treeselect() {
		return iSysMenuCommonService.buildMenuTreeSelect(
				iSysMenuCommonService.getMenusByUserId(Long.parseLong(UserContext.getUser().getUserId()))
		);
	}

	@Override
	public List<Long> getCheckedMenuIds(Long roleId) {
		return iSysMenuQueryService.queryMenuByRoleId(roleId).stream().map(SysMenu::getId).collect(Collectors.toList());
	}

	@Override
	public int add(SysMenuAddDTO sysMenuAddDTO) {

		return sysMenuMapper.insert(buildAddSysMenu(sysMenuAddDTO));
	}

	@Override
	public int update(SysMenuUpdateDTO sysMenuUpdateDTO) {
		return sysMenuMapper.updateByPrimaryKeySelective(buildUpdateSysMenu(sysMenuUpdateDTO));
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public int logicDeleteByMenuId(Long menuId) {
		if (Objects.isNull(menuId)) {
			return 0;
		}

		final int logicDelCount =  sysMenuMapper.updateByExampleSelective(
				buildLogicDeleteSysMenuRecord(menuId),
				buildLogicDeleteSysMenuExample(menuId)
		);

		if (logicDelCount != 1) {
			throw BusinessExceptionFactory.getException(CommonExceptionEnum.UPDATE_FAILED);
		}

		final int roleMenuDeletedCount =  sysRoleMenuMapper.deleteByExample(buildPhysicDeleteRoleMenuExample(menuId));

		return logicDelCount + roleMenuDeletedCount;
	}

	private SysRoleMenuExample buildPhysicDeleteRoleMenuExample(Long menuId) {
		SysRoleMenuExample result = new SysRoleMenuExample();
		result.createCriteria().andMenuIdEqualTo(menuId);
		return result;
	}

	private SysMenu buildLogicDeleteSysMenuRecord(Long menuId) {
		SysMenu result = new SysMenu();
        result.setId(menuId);
        result.setIsDeleted(YesOrNoEnum.YES.getCode());
        result.setUpdateTime(LocalDateTime.now());
        result.setUpdateUser(UserContext.getUser().getUserCode());
        return result;
	}

	private SysMenuExample buildLogicDeleteSysMenuExample(Long menuId) {
		SysMenuExample result = new SysMenuExample();
        SysMenuExample.Criteria criteria = result.createCriteria();
        criteria.andIdEqualTo(menuId).andIsDeletedEqualTo(YesOrNoEnum.NO.getCode());
        return result;
	}

	private SysMenu buildUpdateSysMenu(SysMenuUpdateDTO sysMenuUpdateDTO) {
		SysMenu result = new SysMenu();
		result.setId(sysMenuUpdateDTO.getMenuId());
		result.setMenuName(sysMenuUpdateDTO.getMenuName());
		result.setMenuType(sysMenuUpdateDTO.getMenuType());
		result.setOrderNum(sysMenuUpdateDTO.getOrderNum());
		result.setPerms(sysMenuUpdateDTO.getPerms());
		result.setComponent(sysMenuUpdateDTO.getComponent());
		result.setIcon(sysMenuUpdateDTO.getIcon());
		result.setPath(sysMenuUpdateDTO.getPath());
		result.setIsCache(sysMenuUpdateDTO.getIsCache());
		result.setIsVisible(sysMenuUpdateDTO.getIsVisible());
		result.setIsFrame(sysMenuUpdateDTO.getIsFrame());
		result.setParentId(sysMenuUpdateDTO.getParentId());
		result.setStatus(sysMenuUpdateDTO.getStatus());

		result.setUpdateTime(LocalDateTime.now());
		result.setUpdateUser(UserContext.getUser().getUserCode());
		result.setIsDeleted(YesOrNoEnum.NO.getCode());
		return result;
	}

	private SysMenu buildAddSysMenu(SysMenuAddDTO sysMenuAddDTO) {
		SysMenu result = new SysMenu();
		result.setId(IdGenerator.next());
        result.setMenuName(sysMenuAddDTO.getMenuName());
        result.setMenuType(sysMenuAddDTO.getMenuType());
        result.setOrderNum(sysMenuAddDTO.getOrderNum());
        result.setPerms(sysMenuAddDTO.getPerms());
        result.setComponent(sysMenuAddDTO.getComponent());
        result.setIcon(sysMenuAddDTO.getIcon());
        result.setPath(sysMenuAddDTO.getPath());
        result.setIsCache(sysMenuAddDTO.getIsCache());
		result.setIsVisible(sysMenuAddDTO.getIsVisible());
		result.setIsFrame(sysMenuAddDTO.getIsFrame());
		result.setParentId(sysMenuAddDTO.getParentId());
        result.setStatus(sysMenuAddDTO.getStatus());

        result.setCreateUser(UserContext.getUser().getUserCode());
        result.setCreateTime(LocalDateTime.now());
		result.setUpdateTime(LocalDateTime.now());
		result.setUpdateUser(UserContext.getUser().getUserCode());
		result.setIsDeleted(YesOrNoEnum.NO.getCode());
        return result;
	}
}
