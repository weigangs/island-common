package com.lkyl.island.common.menu.service;

import com.lkyl.island.common.api.request.SysMenuAddDTO;
import com.lkyl.island.common.api.request.SysMenuListQueryDTO;
import com.lkyl.island.common.api.request.SysMenuUpdateDTO;
import com.lkyl.island.common.api.response.MenuTreeVO;
import com.lkyl.island.common.api.response.RouterVO;
import com.lkyl.island.common.api.response.SysMenuVO;

import java.util.List;

/**
 * 菜单权限表(SysMenu)表服务接口
 *
 * @author author
 * @since 2022-06-12 15:54:06
 */
public interface ISysMenuService {

	/**
	* 获取详情
	* @param id 主键ID
	* @return VO
	**/
	SysMenuVO detail(Long id);

	/**
	* 查询列表
	* @param sysMenuListQueryDTO 请求体DTO
	* @return VO列表
	**/
	List<SysMenuVO> search(SysMenuListQueryDTO sysMenuListQueryDTO);

	/**
	 * 根据用户ID获取可操作菜单列表
	 * @param userId
	 * @return
	 */
	List<RouterVO> getMenusByUserId(Long userId);


	/**
	 * 获取所有菜单列表
	 * @return
	 */
	List<MenuTreeVO> treeselect();

	/**
	 * 根据角色查菜单
	 * @param roleId roleId
	 * @return List
	 */
	List<Long> getCheckedMenuIds(Long roleId);

	/**
	 * 添加菜单
	 * @param sysMenuAddDTO  sysMenuAddDTO
	 * @return int
	 */
	int add(SysMenuAddDTO sysMenuAddDTO);

	/**	 *

	 * @param sysMenuUpdateDTO sysMenuUpdateDTO
	 * @return int
	 */
	int update(SysMenuUpdateDTO sysMenuUpdateDTO);

	/**
	 * 删除菜单
	 * @param menuId menuId
	 * @return int
	 */
	int logicDeleteByMenuId(Long menuId);
}
