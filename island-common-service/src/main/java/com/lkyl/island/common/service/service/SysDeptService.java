package com.lkyl.island.common.service.service;

import com.github.pagehelper.PageInfo;
import com.lkyl.island.common.api.response.SysDeptVO;
import com.lkyl.island.common.ps.entity.SysDept;
import com.lkyl.island.common.api.request.SysDeptDTO;
import com.lkyl.oceanframework.mybatis.base.BaseService;

/**
 * 部门表(SysDept)表服务接口
 *
 * @author author
 * @since 2022-06-12 15:54:06
 */
public interface SysDeptService extends BaseService<SysDept, Long>{

	/**
	* 获取详情
	* @param id 主键ID
	* @return VO
	**/
	SysDeptVO detail(Long id);

	/**
	* 查询列表
	* @param sysDeptDTO 请求体DTO
	* @param	pageNum 当前页
	* @param	pageSize 页容
	* @return VO列表
	**/
	PageInfo search(SysDeptDTO sysDeptDTO, String pageNum, String pageSize);

	/**
	* 新增
	* @param sysDeptDTO) 新增请求体DTO
	* @return 成功插入条数
	**/
	int insert(SysDeptDTO sysDeptDTO);

	/**
	* 修改
	* @param @param sysDept 修改请求体DTO
	* @return 成功更新个数
	**/
	int update(SysDeptDTO sysDeptDTO);

	/**
	* 删除
	* @param id 主键ID
	* @return 成功删除个数
	**/
	int remove(Long id);

}
