package com.lkyl.island.common.service.service;

import com.github.pagehelper.PageInfo;
import com.lkyl.island.common.api.response.SysMenuVO;
import com.lkyl.island.common.ps.entity.SysMenu;
import com.lkyl.island.common.api.request.SysMenuDTO;
import com.lkyl.oceanframework.mybatis.base.BaseService;

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

}
