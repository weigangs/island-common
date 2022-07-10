package com.lkyl.island.common.service.service;

import com.github.pagehelper.PageInfo;
import com.lkyl.island.common.api.response.SysDictDataVO;
import com.lkyl.island.common.ps.entity.SysDictData;
import com.lkyl.island.common.api.request.SysDictDataDTO;
import com.lkyl.oceanframework.mybatis.base.BaseService;

/**
 * 字典数据表(SysDictData)表服务接口
 *
 * @author author
 * @since 2022-06-12 15:54:06
 */
public interface SysDictDataService extends BaseService<SysDictData, Long>{

	/**
	* 获取详情
	* @param id 主键ID
	* @return VO
	**/
	SysDictDataVO detail(Long id);

	/**
	* 查询列表
	* @param sysDictDataDTO 请求体DTO
	* @param	pageNum 当前页
	* @param	pageSize 页容
	* @return VO列表
	**/
	PageInfo search(SysDictDataDTO sysDictDataDTO, String pageNum, String pageSize);

	/**
	* 新增
	* @param sysDictDataDTO) 新增请求体DTO
	* @return 成功插入条数
	**/
	int insert(SysDictDataDTO sysDictDataDTO);

	/**
	* 修改
	* @param @param sysDictData 修改请求体DTO
	* @return 成功更新个数
	**/
	int update(SysDictDataDTO sysDictDataDTO);

	/**
	* 删除
	* @param id 主键ID
	* @return 成功删除个数
	**/
	int remove(Long id);

}
