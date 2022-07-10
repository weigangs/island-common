package com.lkyl.island.common.service.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lkyl.island.common.api.request.SysMenuDTO;
import com.lkyl.island.common.api.response.MetaVo;
import com.lkyl.island.common.api.response.RouterVO;
import com.lkyl.island.common.api.response.SysMenuVO;
import com.lkyl.island.common.ps.dao.SysMenuDao;
import com.lkyl.island.common.ps.entity.SysMenu;
import com.lkyl.island.common.ps.entity.SysRoleMenu;
import com.lkyl.island.common.service.converter.SysMenuConverter;
import com.lkyl.island.common.service.model.TreeSelect;
import com.lkyl.island.common.service.service.SysMenuService;
import com.lkyl.island.common.service.service.SysRoleMenuService;
import com.lkyl.island.common.service.util.RoleUtil;
import com.lkyl.oceanframework.common.utils.constant.*;
import com.lkyl.oceanframework.common.utils.enums.DelFlagEnum;
import com.lkyl.oceanframework.common.utils.exception.CommonException;
import com.lkyl.oceanframework.common.utils.utils.CollectionUtils;
import com.lkyl.oceanframework.common.utils.utils.PageUtil;
import com.lkyl.oceanframework.mybatis.base.BaseServiceImpl;
import com.lkyl.oceanframework.web.util.BusinessContextUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 菜单权限表(SysMenu)表服务实现类
 *
 * @author author
 * @since 2022-06-12 16:37:41
 */
@Service("sysMenuService")
public class SysMenuServiceImpl extends BaseServiceImpl<SysMenu, Long> implements SysMenuService {
    @Resource
    private SysMenuDao sysMenuDao;

	@Resource
	private SysRoleMenuService sysRoleMenuService;

    @Resource
    public void setSysMenuDao(SysMenuDao sysMenuDao) {
        this.sysMenuDao = sysMenuDao;
        super.setBaseDao(sysMenuDao);
    }

	/**
	* 获取详情
	* @param id 主键ID
	* @return VO
	**/
	@Override
	public SysMenuVO detail(Long id) {
		Optional<SysMenu> sysMenu = super.get(id);

		if(sysMenu.isPresent()) {
            return SysMenuConverter.INSTANCE.to(sysMenu.get());
        } else {
            throw new CommonException(CommonCode.EXCEPTION, "未找到对应记录!");
        }
	}

	/**
	* 查询列表
	* @param sysMenuDTO 请求体DTO
	* @param	pageNum 当前页
	* @param	pageSize 页容
	* @return VO列表
	**/
	@Override
	public PageInfo search(SysMenuDTO sysMenuDTO, String pageNum, String pageSize) {
		int page = PageUtil.parsePage(pageNum, PageConstant.PAGE);
        int perPage = PageUtil.parsePerPage(pageSize, PageConstant.PER_PAGE);
        PageHelper.startPage(page, perPage);
		SysMenu queryEntity = new SysMenu();

        BeanUtils.copyProperties(sysMenuDTO, queryEntity);
        List<SysMenu> sysMenuList = super.list(queryEntity);
		PageInfo pageInfo = new PageInfo<>(sysMenuList);

		if(CollectionUtils.isNotEmpty(pageInfo.getList())) {
            pageInfo.setList(SysMenuConverter.INSTANCE.to(pageInfo.getList()));
        }

		return pageInfo;
	}

	/**
	* 新增
	* @param sysMenuDTO) 新增请求体DTO
	* @return 成功插入条数
	**/
	@Override
	public int insert(SysMenuDTO sysMenuDTO) {
	    sysMenuDTO.setCreateTime(new Date());
        sysMenuDTO.setUpdateTime(new Date());
        return super.commonSave(sysMenuDTO, SysMenu::new);
	}

	/**
	* 修改
	* @param @param sysMenu 修改请求体DTO
	* @return 成功更新个数
	**/
	@Override
	public int update(SysMenuDTO sysMenuDTO) {
		sysMenuDTO.setUpdateTime(new Date());
        return super.commonUpdate(sysMenuDTO, SysMenu::new);
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

	/**
	 * 构建前端路由所需要的菜单
	 *
	 * @param menus 菜单列表
	 * @return 路由列表
	 */
	@Override
	public List<RouterVO> buildMenus(List<SysMenuVO> menus)
	{
		List<RouterVO> routers = new LinkedList<>();
		for (SysMenuVO menu : menus)
		{
			RouterVO router = new RouterVO();
			router.setHidden("1".equals(menu.getVisible()));
			router.setName(getRouteName(menu));
			router.setPath(getRouterPath(menu));
			router.setComponent(getComponent(menu));
			router.setMeta(new MetaVo(menu.getMenuName(), menu.getIcon(), StringUtils.equals("1", menu.getIsCache() == null ? "" : menu.getIsCache().toString()), menu.getPath()));
			List<SysMenuVO> cMenus = menu.getChildList();
			if (CollectionUtils.isNotEmpty(cMenus) && cMenus.size() > 0 && UserConstants.TYPE_DIR.equals(menu.getMenuType()))
			{
				router.setAlwaysShow(true);
				router.setRedirect("noRedirect");
				router.setChildren(buildMenus(cMenus));
			}
			else if (isMenuFrame(menu))
			{
				router.setMeta(null);
				List<RouterVO> childrenList = new ArrayList<>();
				RouterVO children = new RouterVO();
				children.setPath(menu.getPath());
				children.setComponent(menu.getComponent());
				children.setName(StringUtils.capitalize(menu.getPath()));
				children.setMeta(new MetaVo(menu.getMenuName(), menu.getIcon(), StringUtils.equals("1", menu.getIsCache() == null ? "" : menu.getIsCache().toString()), menu.getPath()));
				childrenList.add(children);
				router.setChildren(childrenList);
			}
			else if (menu.getParentId().intValue() == 0 && isInnerLink(menu))
			{
				router.setMeta(new MetaVo(menu.getMenuName(), menu.getIcon()));
				router.setPath("/");
				List<RouterVO> childrenList = new ArrayList<>();
				RouterVO children = new RouterVO();
				String routerPath = innerLinkReplaceEach(menu.getPath());
				children.setPath(routerPath);
				children.setComponent(UserConstants.INNER_LINK);
				children.setName(StringUtils.capitalize(routerPath));
				children.setMeta(new MetaVo(menu.getMenuName(), menu.getIcon(), menu.getPath()));
				childrenList.add(children);
				router.setChildren(childrenList);
			}
			routers.add(router);
		}
		return routers;
	}

	/**
	 * 构建前端所需要树结构
	 *
	 * @param menus 菜单列表
	 * @return 树结构列表
	 */
	@Override
	public List<SysMenuVO> buildMenuTree(List<SysMenuVO> menus)
	{
		List<SysMenuVO> returnList = new ArrayList<>();
		List<Long> tempList = new ArrayList<Long>();
		for (SysMenuVO dept : menus)
		{
			tempList.add(dept.getMenuId());
		}
		for (Iterator<SysMenuVO> iterator = menus.iterator(); iterator.hasNext();)
		{
			SysMenuVO menu = iterator.next();
			// 如果是顶级节点, 遍历该父节点的所有子节点
			if (!tempList.contains(menu.getParentId()))
			{
				recursionFn(menus, menu);
				returnList.add(menu);
			}
		}
		if (returnList.isEmpty())
		{
			returnList = menus;
		}
		return returnList;
	}

	/**
	 * 构建前端所需要下拉树结构
	 *
	 * @param menus 菜单列表
	 * @return 下拉树结构列表
	 */
	@Override
	public List<TreeSelect> buildMenuTreeSelect(List<SysMenuVO> menus)
	{
		List<SysMenuVO> menuTrees = buildMenuTree(menus);
		return menuTrees.stream().map(TreeSelect::new).collect(Collectors.toList());
	}


	/**
	 * 递归列表
	 *
	 * @param list
	 * @param t
	 */
	private void recursionFn(List<SysMenuVO> list, SysMenuVO t)
	{
		// 得到子节点列表
		List<SysMenuVO> childList = getChildList(list, t);
		t.setChildList(childList);
		for (SysMenuVO tChild : childList)
		{
			if (hasChild(list, tChild))
			{
				recursionFn(list, tChild);
			}
		}
	}

	/**
	 * 得到子节点列表
	 */
	private List<SysMenuVO> getChildList(List<SysMenuVO> list, SysMenuVO t)
	{
		List<SysMenuVO> tlist = new ArrayList<>();
		Iterator<SysMenuVO> it = list.iterator();
		while (it.hasNext())
		{
			SysMenuVO n = it.next();
			if (n.getParentId().longValue() == t.getMenuId().longValue())
			{
				tlist.add(n);
			}
		}
		return tlist;
	}

	/**
	 * 判断是否有子节点
	 */
	private boolean hasChild(List<SysMenuVO> list, SysMenuVO t)
	{
		return getChildList(list, t).size() > 0;
	}



	/**
	 * 获取路由名称
	 *
	 * @param menu 菜单信息
	 * @return 路由名称
	 */
	public String getRouteName(SysMenuVO menu)
	{
		String routerName = StringUtils.capitalize(menu.getPath());
		// 非外链并且是一级目录（类型为目录）
		if (isMenuFrame(menu))
		{
			routerName = StringUtils.EMPTY;
		}
		return routerName;
	}

	/**
	 * 获取路由地址
	 *
	 * @param menu 菜单信息
	 * @return 路由地址
	 */
	public String getRouterPath(SysMenuVO menu)
	{
		String routerPath = menu.getPath();
		// 内链打开外网方式
		if (menu.getParentId().intValue() != 0 && isInnerLink(menu))
		{
			routerPath = innerLinkReplaceEach(routerPath);
		}
		// 非外链并且是一级目录（类型为目录）
		if (0 == menu.getParentId().intValue() && UserConstants.TYPE_DIR.equals(menu.getMenuType())
				&& UserConstants.NO_FRAME.equals(menu.getIsFrame().toString()))
		{
			routerPath = "/" + menu.getPath();
		}
		// 非外链并且是一级目录（类型为菜单）
		else if (isMenuFrame(menu))
		{
			routerPath = "/";
		}
		return routerPath;
	}

	/**
	 * 获取组件信息
	 *
	 * @param menu 菜单信息
	 * @return 组件信息
	 */
	public String getComponent(SysMenuVO menu)
	{
		String component = UserConstants.LAYOUT;
		if (StringUtils.isNotEmpty(menu.getComponent()) && !isMenuFrame(menu))
		{
			component = menu.getComponent();
		}
		else if (StringUtils.isEmpty(menu.getComponent()) && menu.getParentId().intValue() != 0 && isInnerLink(menu))
		{
			component = UserConstants.INNER_LINK;
		}
		else if (StringUtils.isEmpty(menu.getComponent()) && isParentView(menu))
		{
			component = UserConstants.PARENT_VIEW;
		}
		return component;
	}

	/**
	 * 是否为菜单内部跳转
	 *
	 * @param menu 菜单信息
	 * @return 结果
	 */
	public boolean isMenuFrame(SysMenuVO menu)
	{
		return menu.getParentId().intValue() == 0 && UserConstants.TYPE_MENU.equals(menu.getMenuType())
				&& menu.getIsFrame().equals(UserConstants.NO_FRAME);
	}

	/**
	 * 是否为内链组件
	 *
	 * @param menu 菜单信息
	 * @return 结果
	 */
	public boolean isInnerLink(SysMenuVO menu)
	{
		return menu.getIsFrame().equals(UserConstants.NO_FRAME) && StringUtils.startsWithAny(menu.getPath(), HttpConstant.HTTP, HttpConstant.HTTPS);
	}

	/**
	 * 是否为parent_view组件
	 *
	 * @param menu 菜单信息
	 * @return 结果
	 */
	public boolean isParentView(SysMenuVO menu)
	{
		return menu.getParentId().intValue() != 0 && UserConstants.TYPE_DIR.equals(menu.getMenuType());
	}

	/**
	 * 根据父节点的ID获取所有子节点
	 *
	 * @param list 分类表
	 * @param parentId 传入的父节点ID
	 * @return String
	 */
	@Override
	public List<SysMenuVO> getChildPerms(List<SysMenuVO> list, int parentId)
	{
		List<SysMenuVO> returnList = new ArrayList<>();
		for (Iterator<SysMenuVO> iterator = list.iterator(); iterator.hasNext();)
		{
			SysMenuVO t = iterator.next();
			// 一、根据传入的某个父节点ID,遍历该父节点的所有子节点
			if (t.getParentId() == parentId)
			{
				recursionFn(list, t);
				returnList.add(t);
			}
		}
		return returnList;
	}

	@Override
	public List<RouterVO> getMenusByUserId(String userId) {
		if(StringUtils.isBlank(userId)) {
			throw new CommonException("CM003");
		}
		SysMenu menuQuery = new SysMenu();
		menuQuery.setDelFlag(DelFlagEnum.NO.getCode());
		List<SysMenu> menus = null;
		if(RoleUtil.isAdmin()) {
			menus = super.list(new SysMenu());
		}else {
			Map<String, Object> roleMenuMapQuery = new HashMap<>(16);
			List<Long> roleList = new ArrayList<>();
			if (CollectionUtils.isNotEmpty(roleList)) {
				roleMenuMapQuery.put(MybatisConstant.ID_LIST, roleList);
				roleMenuMapQuery.put(MybatisConstant.TENANT_ID, BusinessContextUtil.getTenantId());
				List<SysRoleMenu> roleMenuList = sysRoleMenuService.queryByIdList(roleMenuMapQuery);
				if(CollectionUtils.isNotEmpty(roleList)) {
					Map<String, Object> menuMapQuery = new HashMap<>(16);
					menuMapQuery.put(MybatisConstant.ID_LIST, roleMenuList.stream().map(SysRoleMenu::getMenuId).collect(Collectors.toList()));
					menuMapQuery.put(MybatisConstant.TENANT_ID, BusinessContextUtil.getTenantId());
					menuMapQuery.put(MybatisConstant.DEL_FLAG, DelFlagEnum.NO.getCode());
					menus = super.queryByIdList(menuMapQuery);
				}
			}
		}

		List<SysMenuVO> viewList = SysMenuConverter.INSTANCE.to(menus);
		return this.buildMenus(this.getChildPerms(viewList, 0));
	}

	/**
	 * 内链域名特殊字符替换
	 *
	 * @return
	 */
	public String innerLinkReplaceEach(String path)
	{
		return StringUtils.replaceEach(path, new String[] { HttpConstant.HTTP, HttpConstant.HTTPS },
				new String[] { "", "" });
	}
}
