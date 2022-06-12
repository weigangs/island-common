package com.lkyl.island.common.service.service;

import com.github.pagehelper.PageInfo;
import com.lkyl.island.common.api.response.SysUserRoleVO;
import com.lkyl.island.common.ps.entity.SysUserRole;
import com.lkyl.island.common.api.request.SysUserRoleDTO;
import com.lkyl.oceanframework.mybatis.base.BaseService;

/**
 * 用户和角色关联表(SysUserRole)表服务接口
 *
 * @author author
 * @since 2022-06-12 15:54:07
 */
public interface SysUserRoleService extends BaseService<SysUserRole, Long>{

	/**
	* 获取详情
	* @param id 主键ID
	* @return VO
	**/
	SysUserRoleVO detail(Long id);

	/**
	* 查询列表
	* @param sysUserRoleDTO 请求体DTO
	* @param	pageNum 当前页
	* @param	pageSize 页容
	* @return VO列表
	**/
	PageInfo search(SysUserRoleDTO sysUserRoleDTO, String pageNum, String pageSize);

	/**
	* 新增
	* @param sysUserRoleDTO) 新增请求体DTO
	* @return 成功插入条数
	**/
	int insert(SysUserRoleDTO sysUserRoleDTO);

	/**
	* 修改
	* @param @param sysUserRole 修改请求体DTO
	* @return 成功更新个数
	**/
	int update(SysUserRoleDTO sysUserRoleDTO);

	/**
	* 删除
	* @param id 主键ID
	* @return 成功删除个数
	**/
	int remove(Long id);

}
