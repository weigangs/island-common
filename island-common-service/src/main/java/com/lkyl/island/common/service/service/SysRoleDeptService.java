package com.lkyl.island.common.service.service;

import com.github.pagehelper.PageInfo;
import com.lkyl.island.common.api.response.SysRoleDeptVO;
import com.lkyl.island.common.ps.entity.SysRoleDept;
import com.lkyl.island.common.api.request.SysRoleDeptDTO;
import com.lkyl.oceanframework.mybatis.base.BaseService;

/**
 * 角色和部门关联表(SysRoleDept)表服务接口
 *
 * @author author
 * @since 2022-06-12 15:54:07
 */
public interface SysRoleDeptService extends BaseService<SysRoleDept, Long>{

	/**
	* 获取详情
	* @param id 主键ID
	* @return VO
	**/
	SysRoleDeptVO detail(Long id);

	/**
	* 查询列表
	* @param sysRoleDeptDTO 请求体DTO
	* @param	pageNum 当前页
	* @param	pageSize 页容
	* @return VO列表
	**/
	PageInfo search(SysRoleDeptDTO sysRoleDeptDTO, String pageNum, String pageSize);

	/**
	* 新增
	* @param sysRoleDeptDTO) 新增请求体DTO
	* @return 成功插入条数
	**/
	int insert(SysRoleDeptDTO sysRoleDeptDTO);

	/**
	* 修改
	* @param @param sysRoleDept 修改请求体DTO
	* @return 成功更新个数
	**/
	int update(SysRoleDeptDTO sysRoleDeptDTO);

	/**
	* 删除
	* @param id 主键ID
	* @return 成功删除个数
	**/
	int remove(Long id);

}
