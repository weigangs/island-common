package com.lkyl.island.common.service.service.impl;

import com.lkyl.island.common.api.response.SysConfigVO;
import com.lkyl.oceanframework.mybatis.base.BaseServiceImpl;
import com.lkyl.island.common.ps.entity.SysConfig;
import com.lkyl.island.common.api.request.SysConfigDTO;
import com.lkyl.island.common.service.service.SysConfigService;
import com.lkyl.island.common.service.converter.SysConfigConverter;
import com.lkyl.oceanframework.common.utils.constant.CommonCode;
import com.lkyl.oceanframework.common.utils.constant.PageConstant;
import com.lkyl.oceanframework.common.utils.exception.CommonException;
import com.lkyl.island.common.ps.dao.SysConfigDao;
import javax.annotation.Resource;
import com.lkyl.oceanframework.common.utils.utils.PageUtil;
import com.lkyl.oceanframework.common.utils.utils.CollectionUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import java.util.*;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * 参数配置表(SysConfig)表服务实现类
 *
 * @author author
 * @since 2022-06-12 16:37:41
 */
@Service("sysConfigService")
public class SysConfigServiceImpl extends BaseServiceImpl<SysConfig, Integer> implements SysConfigService {
    @Resource
    private SysConfigDao sysConfigDao;

    @Resource
    public void setSysConfigDao(SysConfigDao sysConfigDao) {
        this.sysConfigDao = sysConfigDao;
        super.setBaseDao(sysConfigDao);
    }

	/**
	* 获取详情
	* @param id 主键ID
	* @return VO
	**/
	@Override
	public SysConfigVO detail(Integer id) {
		Optional<SysConfig> sysConfig = super.get(id);

		if(sysConfig.isPresent()) {
            return SysConfigConverter.INSTANCE.to(sysConfig.get());
        } else {
            throw new CommonException(CommonCode.EXCEPTION, "未找到对应记录!");
        }
	}

	/**
	* 查询列表
	* @param sysConfigDTO 请求体DTO
	* @param	pageNum 当前页
	* @param	pageSize 页容
	* @return VO列表
	**/
	@Override
	public PageInfo search(SysConfigDTO sysConfigDTO, String pageNum, String pageSize) {
		int page = PageUtil.parsePage(pageNum, PageConstant.PAGE);
        int perPage = PageUtil.parsePerPage(pageSize, PageConstant.PER_PAGE);
        PageHelper.startPage(page, perPage);
		SysConfig queryEntity = new SysConfig();

        BeanUtils.copyProperties(sysConfigDTO, queryEntity);
        List<SysConfig> sysConfigList = super.list(queryEntity);
		PageInfo pageInfo = new PageInfo<>(sysConfigList);

		if(CollectionUtils.isNotEmpty(pageInfo.getList())) {
            pageInfo.setList(SysConfigConverter.INSTANCE.to(pageInfo.getList()));
        }

		return pageInfo;
	}

	/**
	* 新增
	* @param sysConfigDTO) 新增请求体DTO
	* @return 成功插入条数
	**/
	@Override
	public int insert(SysConfigDTO sysConfigDTO) {
	    sysConfigDTO.setCreateTime(new Date());
        sysConfigDTO.setUpdateTime(new Date());
        return super.commonSave(sysConfigDTO, SysConfig::new);
	}

	/**
	* 修改
	* @param @param sysConfig 修改请求体DTO
	* @return 成功更新个数
	**/
	@Override
	public int update(SysConfigDTO sysConfigDTO) {
		sysConfigDTO.setUpdateTime(new Date());
        return super.commonUpdate(sysConfigDTO, SysConfig::new);
	}

	/**
	* 删除
	* @param id 主键ID
	* @return 成功删除个数
	**/
	@Override
	public int remove(Integer id) {
		return super.commonRemove(id);
	}
}
