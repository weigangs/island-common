package com.lkyl.island.common.service.service;

import com.github.pagehelper.PageInfo;
import com.lkyl.island.common.api.response.SysUserPostVO;
import com.lkyl.island.common.ps.entity.SysUserPost;
import com.lkyl.island.common.api.request.SysUserPostDTO;
import com.lkyl.oceanframework.mybatis.base.BaseService;

/**
 * 用户与岗位关联表(SysUserPost)表服务接口
 *
 * @author author
 * @since 2022-06-12 15:54:07
 */
public interface SysUserPostService extends BaseService<SysUserPost, Long>{

	/**
	* 获取详情
	* @param id 主键ID
	* @return VO
	**/
	SysUserPostVO detail(Long id);

	/**
	* 查询列表
	* @param sysUserPostDTO 请求体DTO
	* @param	pageNum 当前页
	* @param	pageSize 页容
	* @return VO列表
	**/
	PageInfo search(SysUserPostDTO sysUserPostDTO, String pageNum, String pageSize);

	/**
	* 新增
	* @param sysUserPostDTO) 新增请求体DTO
	* @return 成功插入条数
	**/
	int insert(SysUserPostDTO sysUserPostDTO);

	/**
	* 修改
	* @param @param sysUserPost 修改请求体DTO
	* @return 成功更新个数
	**/
	int update(SysUserPostDTO sysUserPostDTO);

	/**
	* 删除
	* @param id 主键ID
	* @return 成功删除个数
	**/
	int remove(Long id);

}
