package com.lkyl.island.common.service.service;

import com.github.pagehelper.PageInfo;
import com.lkyl.island.common.api.response.SysRoleVO;
import com.lkyl.island.common.ps.entity.SysRole;
import com.lkyl.island.common.api.request.SysRoleDTO;
import com.lkyl.oceanframework.mybatis.base.BaseService;

/**
 * 角色信息表(SysRole)表服务接口
 *
 * @author author
 * @since 2022-06-12 15:54:07
 */
public interface SysRoleService extends BaseService<SysRole, Long>{

	/**
	* 获取详情
	* @param id 主键ID
	* @return VO
	**/
	SysRoleVO detail(Long id);

	/**
	* 查询列表
	* @param sysRoleDTO 请求体DTO
	* @param	pageNum 当前页
	* @param	pageSize 页容
	* @return VO列表
	**/
	PageInfo search(SysRoleDTO sysRoleDTO, String pageNum, String pageSize);

	/**
	* 新增
	* @param sysRoleDTO) 新增请求体DTO
	* @return 成功插入条数
	**/
	int insert(SysRoleDTO sysRoleDTO);

	/**
	* 修改
	* @param @param sysRole 修改请求体DTO
	* @return 成功更新个数
	**/
	int update(SysRoleDTO sysRoleDTO);

	/**
	* 删除
	* @param id 主键ID
	* @return 成功删除个数
	**/
	int remove(Long id);

}
