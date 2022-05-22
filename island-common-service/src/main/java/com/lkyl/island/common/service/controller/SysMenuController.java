package com.lkyl.island.common.service.controller;

import com.lkyl.island.common.api.response.SysMenuVO;
import com.lkyl.island.common.ps.entity.SysMenu;
import com.lkyl.island.common.api.request.SysMenuDTO;
import com.lkyl.island.common.ps.entity.SysRoleMenu;
import com.lkyl.island.common.service.converter.SysMenuConverter;
import com.lkyl.island.common.service.service.SysMenuService;
import com.lkyl.island.common.service.service.SysRoleMenuService;
import com.lkyl.oceanframework.common.utils.constant.CommonCode;
import com.lkyl.oceanframework.common.utils.constant.MybatisConstant;
import com.lkyl.oceanframework.common.utils.constant.PageConstant;
import com.lkyl.oceanframework.common.utils.enums.DelFlagEnum;
import com.lkyl.oceanframework.common.utils.exception.CommonException;
import com.lkyl.oceanframework.common.utils.utils.CollectionUtils;
import com.lkyl.oceanframework.web.util.CommonResultUtil;
import com.lkyl.oceanframework.common.utils.utils.PageUtil;
import com.lkyl.oceanframework.web.util.ContextUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.*;
import java.util.stream.Collectors;

import javax.annotation.Resource;

/**
 * 菜单权限表(SysMenu)表控制层
 *
 * @author author
 * @since 2022-05-21 16:28:37
 */
@Slf4j
@RestController
@RequestMapping("sysMenu")
public class SysMenuController {
    /**
     * 服务对象
    */
    @Resource
    private SysMenuService sysMenuService;

    @Resource
    private SysRoleMenuService sysRoleMenuService;

	/**
     * 根据主键ID查询
     *
     * @param id
     * @return 查询结果
    */
	@GetMapping("/getById/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Long id) {
        log.info("get SysMenu info start...");
        Optional<SysMenu> sysMenu = this.sysMenuService.get(id);

        if(null == sysMenu){
            throw new CommonException(CommonCode.EXCEPTION, "返回对象为NULL!");
        }

        return CommonResultUtil.success("获取成功", sysMenu);
    }

    /**
     * 根据当前用户角色获取可用菜单
     * @return
     */
    @GetMapping("/getMenus/{roleId}")
    public ResponseEntity<?> listMenus(@PathVariable(name = "roleId") String roleId) {
        if(log.isInfoEnabled()) {
            log.info("getMenus start...");
        }
        if(StringUtils.isBlank(roleId)) {
            throw new CommonException("CM004");
        }

        List<SysMenuVO> resultList = null;

        SysRoleMenu roleMenuQuery = new SysRoleMenu();
        roleMenuQuery.setRoleId(Long.valueOf(roleId));
        roleMenuQuery.setTenantId(ContextUtil.getTenantId());
        List<SysRoleMenu> roleMenuList = sysRoleMenuService.list(roleMenuQuery);
        if(CollectionUtils.isNotEmpty(roleMenuList)) {
            Map<String, Object> menuMapQuery = new HashMap<>();
            menuMapQuery.put(MybatisConstant.ID_LIST, roleMenuList.stream().map(SysRoleMenu::getMenuId).collect(Collectors.toList()));
            menuMapQuery.put(MybatisConstant.TENANT_ID, ContextUtil.getTenantId());
            menuMapQuery.put(MybatisConstant.DEL_FLAG, DelFlagEnum.NO.getCode());
            List<SysMenu> sourceList = sysMenuService.queryByIdList(menuMapQuery);
            resultList = SysMenuConverter.INSTANCE.to(sourceList);
        }
        return CommonResultUtil.success(resultList);
    }

	/**
     * 分页查询
     *
     * @param sysMenuDTO 筛选条件
     * @return 查询结果
     */
	@PostMapping("/search")
    public ResponseEntity<?> search(@RequestParam(value = "page", required = false, defaultValue = "1") String pageString,
                            @RequestParam(value = "per_page", required = false, defaultValue = "10") String perPageString,
                            @RequestBody(required = false) SysMenuDTO sysMenuDTO) {
        if(log.isInfoEnabled()) {
            log.info("search SysMenu start...");
        }

        int page = PageUtil.parsePage(pageString, PageConstant.PAGE);
        int perPage = PageUtil.parsePerPage(perPageString, PageConstant.PER_PAGE);
        PageHelper.startPage(page, perPage);
		//PageHelper.startPage(page, perPage, "update_time desc");
		SysMenu queryEntity = new SysMenu();

        BeanUtils.copyProperties(sysMenuDTO, queryEntity);
        List<SysMenu> sysMenuList = this.sysMenuService.list(queryEntity);
        PageInfo<SysMenu> pageInfo = new PageInfo<>(sysMenuList);

        return CommonResultUtil.pagingSuccess("查询成功", pageInfo);
    }

	/**
     * 新增
     * @param sysMenuDTO
     * @return
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseEntity<?> save(@RequestBody SysMenuDTO sysMenuDTO) {
        if(log.isInfoEnabled()) {
            log.info("save SysMenu start...");
        }
		SysMenu saveEntity = new SysMenu();

        BeanUtils.copyProperties(sysMenuDTO, saveEntity);
		saveEntity.setCreateTime(new Date());
		saveEntity.setUpdateTime(new Date());
        if (this.sysMenuService.save(saveEntity) != 1) {
            throw new CommonException(CommonCode.EXCEPTION, "新增失败!");
        }

        return CommonResultUtil.success("新增成功", saveEntity);
    }

	/**
     * 修改
     * @param sysMenuDTO
     * @return
     */
    @PostMapping("/update")
    public ResponseEntity<?> update(@RequestBody SysMenuDTO sysMenuDTO) {
        if(log.isInfoEnabled()) {
            log.info("update SysMenu start....");
        }
		SysMenu updateEntity = new SysMenu();

        BeanUtils.copyProperties(sysMenuDTO, updateEntity);
		updateEntity.setUpdateTime(new Date());
        if(this.sysMenuService.updateById(updateEntity) != 1) {
            throw new CommonException(CommonCode.EXCEPTION, "更新失败!");
        }

        return CommonResultUtil.success("更新成功", updateEntity);
    }

	/**
     * 根据ID删除
     *
     * @param id
     * @return
     */
    @DeleteMapping("/remove/{id}")
    public ResponseEntity<?> remove(@PathVariable("id") Long id) {
        if(log.isInfoEnabled()) {
            log.info("remove SysMenu by id start...");
        }
        if (this.sysMenuService.remove(id) != 1) {
            throw new CommonException(CommonCode.EXCEPTION, "删除失败!");
        }

        return CommonResultUtil.success("删除成功");
    }
}
