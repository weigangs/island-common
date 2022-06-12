package com.lkyl.island.common.service.service.impl;

import com.lkyl.island.common.api.response.SysUserVO;
import com.lkyl.oceanframework.mybatis.base.BaseServiceImpl;
import com.lkyl.island.common.ps.entity.SysUser;
import com.lkyl.island.common.api.request.SysUserDTO;
import com.lkyl.island.common.service.service.SysUserService;
import com.lkyl.island.common.service.converter.SysUserConverter;
import com.lkyl.oceanframework.common.utils.constant.CommonCode;
import com.lkyl.oceanframework.common.utils.constant.PageConstant;
import com.lkyl.oceanframework.common.utils.exception.CommonException;
import com.lkyl.island.common.ps.dao.SysUserDao;
import javax.annotation.Resource;
import com.lkyl.oceanframework.common.utils.utils.PageUtil;
import com.lkyl.oceanframework.common.utils.utils.CollectionUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import java.util.*;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * 用户信息表(SysUser)表服务实现类
 *
 * @author author
 * @since 2022-06-12 16:37:41
 */
@Service("sysUserService")
public class SysUserServiceImpl extends BaseServiceImpl<SysUser, Long> implements SysUserService {
    @Resource
    private SysUserDao sysUserDao;

    @Resource
    public void setSysUserDao(SysUserDao sysUserDao) {
        this.sysUserDao = sysUserDao;
        super.setBaseDao(sysUserDao);
    }

	/**
	* 获取详情
	* @param id 主键ID
	* @return VO
	**/
	@Override
	public SysUserVO detail(Long id) {
		Optional<SysUser> sysUser = super.get(id);

		if(sysUser.isPresent()) {
            return SysUserConverter.INSTANCE.to(sysUser.get());
        } else {
            throw new CommonException(CommonCode.EXCEPTION, "未找到对应记录!");
        }
	}

	/**
	* 查询列表
	* @param sysUserDTO 请求体DTO
	* @param	pageNum 当前页
	* @param	pageSize 页容
	* @return VO列表
	**/
	@Override
	public PageInfo search(SysUserDTO sysUserDTO, String pageNum, String pageSize) {
		int page = PageUtil.parsePage(pageNum, PageConstant.PAGE);
        int perPage = PageUtil.parsePerPage(pageSize, PageConstant.PER_PAGE);
        PageHelper.startPage(page, perPage);
		SysUser queryEntity = new SysUser();

        BeanUtils.copyProperties(sysUserDTO, queryEntity);
        List<SysUser> sysUserList = super.list(queryEntity);
		PageInfo pageInfo = new PageInfo<>(sysUserList);

		if(CollectionUtils.isNotEmpty(pageInfo.getList())) {
            pageInfo.setList(SysUserConverter.INSTANCE.to(pageInfo.getList()));
        }

		return pageInfo;
	}

	/**
	* 新增
	* @param sysUserDTO) 新增请求体DTO
	* @return 成功插入条数
	**/
	@Override
	public int insert(SysUserDTO sysUserDTO) {
	    sysUserDTO.setCreateTime(new Date());
        sysUserDTO.setUpdateTime(new Date());
        return super.commonSave(sysUserDTO, SysUser::new);
	}

	/**
	* 修改
	* @param @param sysUser 修改请求体DTO
	* @return 成功更新个数
	**/
	@Override
	public int update(SysUserDTO sysUserDTO) {
		sysUserDTO.setUpdateTime(new Date());
        return super.commonUpdate(sysUserDTO, SysUser::new);
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
