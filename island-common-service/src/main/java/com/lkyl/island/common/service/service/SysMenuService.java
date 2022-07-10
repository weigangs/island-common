package com.lkyl.island.common.service.service;

import com.github.pagehelper.PageInfo;
import com.lkyl.island.common.api.response.RouterVO;
import com.lkyl.island.common.api.response.SysMenuVO;
import com.lkyl.island.common.ps.entity.SysMenu;
import com.lkyl.island.common.api.request.SysMenuDTO;
import com.lkyl.island.common.service.model.TreeSelect;
import com.lkyl.oceanframework.mybatis.base.BaseService;

import java.util.List;

/**
 * 菜单权限表(SysMenu)表服务接口
 *
 * @author author
 * @since 2022-06-12 15:54:06
 */
public interface SysMenuService extends BaseService<SysMenu, Long>{

	/**
	* 获取详情
	* @param id 主键ID
	* @return VO
	**/
	SysMenuVO detail(Long id);

	/**
	* 查询列表
	* @param sysMenuDTO 请求体DTO
	* @param	pageNum 当前页
	* @param	pageSize 页容
	* @return VO列表
	**/
	PageInfo search(SysMenuDTO sysMenuDTO, String pageNum, String pageSize);

	/**
	* 新增
	* @param sysMenuDTO) 新增请求体DTO
	* @return 成功插入条数
	**/
	int insert(SysMenuDTO sysMenuDTO);

	/**
	* 修改
	* @param @param sysMenu 修改请求体DTO
	* @return 成功更新个数
	**/
	int update(SysMenuDTO sysMenuDTO);

	/**
	* 删除
	* @param id 主键ID
	* @return 成功删除个数
	**/
	int remove(Long id);


	/**
	 * 构建前端路由所需要的菜单
	 *
	 * @param menus 菜单列表
	 * @return 路由列表
	 */
	List<RouterVO> buildMenus(List<SysMenuVO> menus);

	/**
	 * 构建前端所需要树结构
	 *
	 * @param menus 菜单列表
	 * @return 树结构列表
	 */
	List<SysMenuVO> buildMenuTree(List<SysMenuVO> menus);

	/**
	 * 构建前端所需要下拉树结构
	 *
	 * @param menus 菜单列表
	 * @return 下拉树结构列表
	 */
	List<TreeSelect> buildMenuTreeSelect(List<SysMenuVO> menus);
	/**
	 * 根据父节点的ID获取所有子节点
	 *
	 * @param list 分类表
	 * @param parentId 传入的父节点ID
	 * @return String
	 */
	List<SysMenuVO> getChildPerms(List<SysMenuVO> list, int parentId);

	/**
	 * 根据用户ID获取可操作菜单列表
	 * @param userId
	 * @return
	 */
	List<RouterVO> getMenusByUserId(String userId);

}
