package com.lkyl.island.common.service.service.impl;

import com.lkyl.island.common.api.response.SysUserPostVO;
import com.lkyl.oceanframework.mybatis.base.BaseServiceImpl;
import com.lkyl.island.common.ps.entity.SysUserPost;
import com.lkyl.island.common.api.request.SysUserPostDTO;
import com.lkyl.island.common.service.service.SysUserPostService;
import com.lkyl.island.common.service.converter.SysUserPostConverter;
import com.lkyl.oceanframework.common.utils.constant.CommonCode;
import com.lkyl.oceanframework.common.utils.constant.PageConstant;
import com.lkyl.oceanframework.common.utils.exception.CommonException;
import com.lkyl.island.common.ps.dao.SysUserPostDao;
import javax.annotation.Resource;
import com.lkyl.oceanframework.common.utils.utils.PageUtil;
import com.lkyl.oceanframework.common.utils.utils.CollectionUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import java.util.*;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * 用户与岗位关联表(SysUserPost)表服务实现类
 *
 * @author author
 * @since 2022-06-12 16:37:41
 */
@Service("sysUserPostService")
public class SysUserPostServiceImpl extends BaseServiceImpl<SysUserPost, Long> implements SysUserPostService {
    @Resource
    private SysUserPostDao sysUserPostDao;

    @Resource
    public void setSysUserPostDao(SysUserPostDao sysUserPostDao) {
        this.sysUserPostDao = sysUserPostDao;
        super.setBaseDao(sysUserPostDao);
    }

	/**
	* 获取详情
	* @param id 主键ID
	* @return VO
	**/
	@Override
	public SysUserPostVO detail(Long id) {
		Optional<SysUserPost> sysUserPost = super.get(id);

		if(sysUserPost.isPresent()) {
            return SysUserPostConverter.INSTANCE.to(sysUserPost.get());
        } else {
            throw new CommonException(CommonCode.EXCEPTION, "未找到对应记录!");
        }
	}

	/**
	* 查询列表
	* @param sysUserPostDTO 请求体DTO
	* @param	pageNum 当前页
	* @param	pageSize 页容
	* @return VO列表
	**/
	@Override
	public PageInfo search(SysUserPostDTO sysUserPostDTO, String pageNum, String pageSize) {
		int page = PageUtil.parsePage(pageNum, PageConstant.PAGE);
        int perPage = PageUtil.parsePerPage(pageSize, PageConstant.PER_PAGE);
        PageHelper.startPage(page, perPage);
		SysUserPost queryEntity = new SysUserPost();

        BeanUtils.copyProperties(sysUserPostDTO, queryEntity);
        List<SysUserPost> sysUserPostList = super.list(queryEntity);
		PageInfo pageInfo = new PageInfo<>(sysUserPostList);

		if(CollectionUtils.isNotEmpty(pageInfo.getList())) {
            pageInfo.setList(SysUserPostConverter.INSTANCE.to(pageInfo.getList()));
        }

		return pageInfo;
	}

	/**
	* 新增
	* @param sysUserPostDTO) 新增请求体DTO
	* @return 成功插入条数
	**/
	@Override
	public int insert(SysUserPostDTO sysUserPostDTO) {
        return super.commonSave(sysUserPostDTO, SysUserPost::new);
	}

	/**
	* 修改
	* @param @param sysUserPost 修改请求体DTO
	* @return 成功更新个数
	**/
	@Override
	public int update(SysUserPostDTO sysUserPostDTO) {
        return super.commonUpdate(sysUserPostDTO, SysUserPost::new);
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
