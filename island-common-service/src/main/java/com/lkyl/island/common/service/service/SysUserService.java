package com.lkyl.island.common.service.service;

import com.github.pagehelper.PageInfo;
import com.lkyl.island.common.api.response.SysUserVO;
import com.lkyl.island.common.ps.entity.SysUser;
import com.lkyl.island.common.api.request.SysUserDTO;
import com.lkyl.oceanframework.mybatis.base.BaseService;

/**
 * 用户信息表(SysUser)表服务接口
 *
 * @author author
 * @since 2022-06-12 15:54:07
 */
public interface SysUserService extends BaseService<SysUser, Long>{

	/**
	* 获取详情
	* @param id 主键ID
	* @return VO
	**/
	SysUserVO detail(Long id);

	/**
	* 查询列表
	* @param sysUserDTO 请求体DTO
	* @param	pageNum 当前页
	* @param	pageSize 页容
	* @return VO列表
	**/
	PageInfo search(SysUserDTO sysUserDTO, String pageNum, String pageSize);

	/**
	* 新增
	* @param sysUserDTO) 新增请求体DTO
	* @return 成功插入条数
	**/
	int insert(SysUserDTO sysUserDTO);

	/**
	* 修改
	* @param @param sysUser 修改请求体DTO
	* @return 成功更新个数
	**/
	int update(SysUserDTO sysUserDTO);

	/**
	* 删除
	* @param id 主键ID
	* @return 成功删除个数
	**/
	int remove(Long id);

}
