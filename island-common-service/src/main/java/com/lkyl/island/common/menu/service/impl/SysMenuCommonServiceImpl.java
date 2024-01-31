package com.lkyl.island.common.menu.service.impl;

import com.lkyl.island.common.api.response.MenuTreeVO;
import com.lkyl.island.common.api.response.MetaVo;
import com.lkyl.island.common.api.response.RouterVO;
import com.lkyl.island.common.api.response.SysMenuVO;
import com.lkyl.island.common.common.enums.CommonExceptionEnum;
import com.lkyl.island.common.common.enums.MenuComponentEnum;
import com.lkyl.island.common.common.enums.MenuTypeEnum;
import com.lkyl.island.common.common.enums.RoleKeyEnum;
import com.lkyl.island.common.menu.service.ISysMenuCommonService;
import com.lkyl.island.common.menu.service.ISysMenuQueryService;
import com.lkyl.island.common.ps.entity.SysMenu;
import com.lkyl.island.common.ps.entity.SysRole;
import com.lkyl.island.common.role.service.ISysRoleQueryService;
import com.lkyl.island.common.utils.RoleUtil;
import com.lkyl.oceanframework.common.utils.constant.HttpConstant;
import com.lkyl.oceanframework.common.utils.enums.YesOrNoEnum;
import com.lkyl.oceanframework.common.utils.exception.BusinessExceptionFactory;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;


@Service
public class SysMenuCommonServiceImpl implements ISysMenuCommonService {

    @Autowired
    private ISysMenuQueryService iSysMenuQueryService;

    @Autowired
    private ISysRoleQueryService iSysRoleQueryService;

    @Override
    public List<SysMenu> getMenusByUserId(Long userId) {
        if(Objects.isNull(userId)) {
            throw BusinessExceptionFactory.getException(CommonExceptionEnum.USER_ID_NOT_NULL);
        }
//		menuQuery.setDelFlag(DelFlagEnum.NO.getCode());
        List<SysMenu> menus = null;
        if(RoleUtil.isAdmin()) {
            menus = iSysMenuQueryService.queryAllMenus();
        }else {
            List<Long> roleIds = iSysRoleQueryService.queryRolesByUserId(userId).stream().map(SysRole::getId).collect(Collectors.toList());

            menus = iSysMenuQueryService.queryMenuByRoleIds(roleIds);
        }
        return menus;
    }

    @Override
    public Set<String> getLoginUserPermissions(Long userId) {
        List<SysRole> roles = iSysRoleQueryService.queryRolesByUserId(userId);
        Set<String> perms = new HashSet<>();
        if(isAdmin(roles)) {
            perms.add("*:*:*");
        }else {
            List<Long> roleIds = roles.stream().map(SysRole::getId).collect(Collectors.toList());

            perms.addAll(iSysMenuQueryService.queryMenuByRoleIds(roleIds).stream()
                    .map(SysMenu::getPerms).filter(StringUtils::isNotBlank).map(e -> Arrays.asList(e.split(",")))
                    .flatMap(Collection::stream).collect(Collectors.toList()));
        }
        return perms;
    }

    private boolean isAdmin(List<SysRole> roles) {
        return roles.stream()
                .anyMatch(role -> StringUtils.equals(RoleKeyEnum.ADMIN.getKey(), role.getRoleKey()));
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
            router.setHidden(YesOrNoEnum.NO.getCode().equals(menu.getIsVisible()));
            router.setName(getRouteName(menu));
            router.setPath(getRouterPath(menu));
            router.setComponent(getComponent(menu));
            router.setMeta(new MetaVo(menu.getMenuName(), menu.getIcon(), YesOrNoEnum.NO.getCode().equals(menu.getIsCache()), menu.getPath()));
            List<SysMenuVO> cMenus = menu.getChildList();
            if (!CollectionUtils.isEmpty(cMenus) && MenuTypeEnum.M.getCode().equals(menu.getMenuType()))
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
                children.setMeta(new MetaVo(menu.getMenuName(), menu.getIcon(), StringUtils.equals("0", menu.getIsCache() == null ? "" : menu.getIsCache().toString()), menu.getPath()));
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
                children.setComponent(MenuComponentEnum.INNER_LINK.getCode());
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
    public List<MenuTreeVO> buildMenuTreeSelect(List<SysMenu> menus)
    {
        return buildMenuTreeVo(menus, 0L);

    }

    // 写一个菜单树,parentId是父id,children是子节点列表
    List<MenuTreeVO> buildMenuTreeVo(List<SysMenu> menus, Long parentId) {

        List<MenuTreeVO> parent = new ArrayList<>();
        for (SysMenu menu : menus) {
            if (menu.getParentId().equals(parentId)) {
                MenuTreeVO child = new MenuTreeVO();
                child.setMenuId(menu.getId());
                child.setLabel(menu.getMenuName());
                child.setChildren(buildMenuTreeVo(menus, menu.getId()));
                parent.add(child);
            }
        }
        return parent;
    }

    public void traverseMenuTree(List<MenuTreeVO> parent, MenuTreeVO child) {

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
        if (0 == menu.getParentId().intValue() && MenuTypeEnum.M.getCode().equals(menu.getMenuType())
                && YesOrNoEnum.YES.getCode().equals(menu.getIsFrame().toString()))
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
        String component = MenuComponentEnum.LAYOUT.getCode();
        if (StringUtils.isNotEmpty(menu.getComponent()) && !isMenuFrame(menu))
        {
            component = menu.getComponent();
        }
        else if (StringUtils.isEmpty(menu.getComponent()) && menu.getParentId().intValue() != 0 && isInnerLink(menu))
        {
            component = MenuComponentEnum.INNER_LINK.getCode();
        }
        else if (StringUtils.isEmpty(menu.getComponent()) && isParentView(menu))
        {
            component = MenuComponentEnum.PARENT_VIEW.getCode();
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
        return menu.getParentId().intValue() == 0 && MenuTypeEnum.C.getCode().equals(menu.getMenuType())
                && YesOrNoEnum.YES.getCode().equals(menu.getIsFrame());
    }

    /**
     * 是否为内链组件
     *
     * @param menu 菜单信息
     * @return 结果
     */
    public boolean isInnerLink(SysMenuVO menu)
    {
        return YesOrNoEnum.YES.getCode().equals(menu.getIsFrame()) && StringUtils.startsWithAny(menu.getPath(), HttpConstant.HTTP, HttpConstant.HTTPS);
    }

    /**
     * 是否为parent_view组件
     *
     * @param menu 菜单信息
     * @return 结果
     */
    public boolean isParentView(SysMenuVO menu)
    {
        return menu.getParentId().intValue() != 0 && MenuTypeEnum.M.getCode().equals(menu.getMenuType());
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
