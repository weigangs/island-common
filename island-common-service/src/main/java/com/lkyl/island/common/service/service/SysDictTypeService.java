package com.lkyl.island.common.service.service;

import com.github.pagehelper.PageInfo;
import com.lkyl.island.common.api.response.SysDictTypeVO;
import com.lkyl.island.common.ps.entity.SysDictType;
import com.lkyl.island.common.api.request.SysDictTypeDTO;
import com.lkyl.oceanframework.mybatis.base.BaseService;

/**
 * 字典类型表(SysDictType)表服务接口
 *
 * @author author
 * @since 2022-06-12 15:54:06
 */
public interface SysDictTypeService extends BaseService<SysDictType, Long>{

	/**
	* 获取详情
	* @param id 主键ID
	* @return VO
	**/
	SysDictTypeVO detail(Long id);

	/**
	* 查询列表
	* @param sysDictTypeDTO 请求体DTO
	* @param	pageNum 当前页
	* @param	pageSize 页容
	* @return VO列表
	**/
	PageInfo search(SysDictTypeDTO sysDictTypeDTO, String pageNum, String pageSize);

	/**
	* 新增
	* @param sysDictTypeDTO) 新增请求体DTO
	* @return 成功插入条数
	**/
	int insert(SysDictTypeDTO sysDictTypeDTO);

	/**
	* 修改
	* @param @param sysDictType 修改请求体DTO
	* @return 成功更新个数
	**/
	int update(SysDictTypeDTO sysDictTypeDTO);

	/**
	* 删除
	* @param id 主键ID
	* @return 成功删除个数
	**/
	int remove(Long id);

}
