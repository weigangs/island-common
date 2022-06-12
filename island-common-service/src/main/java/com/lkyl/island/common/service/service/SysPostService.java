package com.lkyl.island.common.service.service;

import com.github.pagehelper.PageInfo;
import com.lkyl.island.common.api.response.SysPostVO;
import com.lkyl.island.common.ps.entity.SysPost;
import com.lkyl.island.common.api.request.SysPostDTO;
import com.lkyl.oceanframework.mybatis.base.BaseService;

/**
 * 岗位信息表(SysPost)表服务接口
 *
 * @author author
 * @since 2022-06-12 15:54:06
 */
public interface SysPostService extends BaseService<SysPost, Long>{

	/**
	* 获取详情
	* @param id 主键ID
	* @return VO
	**/
	SysPostVO detail(Long id);

	/**
	* 查询列表
	* @param sysPostDTO 请求体DTO
	* @param	pageNum 当前页
	* @param	pageSize 页容
	* @return VO列表
	**/
	PageInfo search(SysPostDTO sysPostDTO, String pageNum, String pageSize);

	/**
	* 新增
	* @param sysPostDTO) 新增请求体DTO
	* @return 成功插入条数
	**/
	int insert(SysPostDTO sysPostDTO);

	/**
	* 修改
	* @param @param sysPost 修改请求体DTO
	* @return 成功更新个数
	**/
	int update(SysPostDTO sysPostDTO);

	/**
	* 删除
	* @param id 主键ID
	* @return 成功删除个数
	**/
	int remove(Long id);

}
