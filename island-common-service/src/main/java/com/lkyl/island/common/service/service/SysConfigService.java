package com.lkyl.island.common.service.service;

import com.github.pagehelper.PageInfo;
import com.lkyl.island.common.api.response.SysConfigVO;
import com.lkyl.island.common.ps.entity.SysConfig;
import com.lkyl.island.common.api.request.SysConfigDTO;
import com.lkyl.oceanframework.mybatis.base.BaseService;

/**
 * 参数配置表(SysConfig)表服务接口
 *
 * @author author
 * @since 2022-06-12 15:54:06
 */
public interface SysConfigService extends BaseService<SysConfig, Integer>{

	/**
	* 获取详情
	* @param id 主键ID
	* @return VO
	**/
	SysConfigVO detail(Integer id);

	/**
	* 查询列表
	* @param sysConfigDTO 请求体DTO
	* @param	pageNum 当前页
	* @param	pageSize 页容
	* @return VO列表
	**/
	PageInfo search(SysConfigDTO sysConfigDTO, String pageNum, String pageSize);

	/**
	* 新增
	* @param sysConfigDTO) 新增请求体DTO
	* @return 成功插入条数
	**/
	int insert(SysConfigDTO sysConfigDTO);

	/**
	* 修改
	* @param @param sysConfig 修改请求体DTO
	* @return 成功更新个数
	**/
	int update(SysConfigDTO sysConfigDTO);

	/**
	* 删除
	* @param id 主键ID
	* @return 成功删除个数
	**/
	int remove(Integer id);

}
