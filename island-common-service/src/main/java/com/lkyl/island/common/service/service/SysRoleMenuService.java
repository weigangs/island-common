package com.lkyl.island.common.service.service;

import com.github.pagehelper.PageInfo;
import com.lkyl.island.common.api.response.SysRoleMenuVO;
import com.lkyl.island.common.ps.entity.SysRoleMenu;
import com.lkyl.island.common.api.request.SysRoleMenuDTO;
import com.lkyl.oceanframework.mybatis.base.BaseService;

/**
 * 角色和菜单关联表(SysRoleMenu)表服务接口
 *
 * @author author
 * @since 2022-06-12 15:54:07
 */
public interface SysRoleMenuService extends BaseService<SysRoleMenu, Long>{

	/**
	* 获取详情
	* @param id 主键ID
	* @return VO
	**/
	SysRoleMenuVO detail(Long id);

	/**
	* 查询列表
	* @param sysRoleMenuDTO 请求体DTO
	* @param	pageNum 当前页
	* @param	pageSize 页容
	* @return VO列表
	**/
	PageInfo search(SysRoleMenuDTO sysRoleMenuDTO, String pageNum, String pageSize);

	/**
	* 新增
	* @param sysRoleMenuDTO) 新增请求体DTO
	* @return 成功插入条数
	**/
	int insert(SysRoleMenuDTO sysRoleMenuDTO);

	/**
	* 修改
	* @param @param sysRoleMenu 修改请求体DTO
	* @return 成功更新个数
	**/
	int update(SysRoleMenuDTO sysRoleMenuDTO);

	/**
	* 删除
	* @param id 主键ID
	* @return 成功删除个数
	**/
	int remove(Long id);

}
