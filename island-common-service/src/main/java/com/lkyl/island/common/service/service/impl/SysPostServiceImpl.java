package com.lkyl.island.common.service.service.impl;

import com.lkyl.island.common.api.response.SysPostVO;
import com.lkyl.oceanframework.mybatis.base.BaseServiceImpl;
import com.lkyl.island.common.ps.entity.SysPost;
import com.lkyl.island.common.api.request.SysPostDTO;
import com.lkyl.island.common.service.service.SysPostService;
import com.lkyl.island.common.service.converter.SysPostConverter;
import com.lkyl.oceanframework.common.utils.constant.CommonCode;
import com.lkyl.oceanframework.common.utils.constant.PageConstant;
import com.lkyl.oceanframework.common.utils.exception.CommonException;
import com.lkyl.island.common.ps.dao.SysPostDao;
import javax.annotation.Resource;
import com.lkyl.oceanframework.common.utils.utils.PageUtil;
import com.lkyl.oceanframework.common.utils.utils.CollectionUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import java.util.*;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * 岗位信息表(SysPost)表服务实现类
 *
 * @author author
 * @since 2022-06-12 16:37:41
 */
@Service("sysPostService")
public class SysPostServiceImpl extends BaseServiceImpl<SysPost, Long> implements SysPostService {
    @Resource
    private SysPostDao sysPostDao;

    @Resource
    public void setSysPostDao(SysPostDao sysPostDao) {
        this.sysPostDao = sysPostDao;
        super.setBaseDao(sysPostDao);
    }

	/**
	* 获取详情
	* @param id 主键ID
	* @return VO
	**/
	@Override
	public SysPostVO detail(Long id) {
		Optional<SysPost> sysPost = super.get(id);

		if(sysPost.isPresent()) {
            return SysPostConverter.INSTANCE.to(sysPost.get());
        } else {
            throw new CommonException(CommonCode.EXCEPTION, "未找到对应记录!");
        }
	}

	/**
	* 查询列表
	* @param sysPostDTO 请求体DTO
	* @param	pageNum 当前页
	* @param	pageSize 页容
	* @return VO列表
	**/
	@Override
	public PageInfo search(SysPostDTO sysPostDTO, String pageNum, String pageSize) {
		int page = PageUtil.parsePage(pageNum, PageConstant.PAGE);
        int perPage = PageUtil.parsePerPage(pageSize, PageConstant.PER_PAGE);
        PageHelper.startPage(page, perPage);
		SysPost queryEntity = new SysPost();

        BeanUtils.copyProperties(sysPostDTO, queryEntity);
        List<SysPost> sysPostList = super.list(queryEntity);
		PageInfo pageInfo = new PageInfo<>(sysPostList);

		if(CollectionUtils.isNotEmpty(pageInfo.getList())) {
            pageInfo.setList(SysPostConverter.INSTANCE.to(pageInfo.getList()));
        }

		return pageInfo;
	}

	/**
	* 新增
	* @param sysPostDTO) 新增请求体DTO
	* @return 成功插入条数
	**/
	@Override
	public int insert(SysPostDTO sysPostDTO) {
	    sysPostDTO.setCreateTime(new Date());
        sysPostDTO.setUpdateTime(new Date());
        return super.commonSave(sysPostDTO, SysPost::new);
	}

	/**
	* 修改
	* @param @param sysPost 修改请求体DTO
	* @return 成功更新个数
	**/
	@Override
	public int update(SysPostDTO sysPostDTO) {
		sysPostDTO.setUpdateTime(new Date());
        return super.commonUpdate(sysPostDTO, SysPost::new);
	}

	/**
	* 删除
	* @param id 主键ID
	* @return 成功删除个数
	**/
	@Override
	public int remove(Long id) {
		return super.commonRemove(id);
	}
}
