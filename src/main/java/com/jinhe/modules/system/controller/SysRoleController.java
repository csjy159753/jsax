package com.jinhe.modules.system.controller;


import com.jinhe.common.annotation.SysLog;
import com.jinhe.common.utils.PageUtils;
import com.jinhe.modules.system.dto.SysRole;
import com.jinhe.modules.system.service.ISysRoleService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author rls
 * @since 2020-04-14
 */
@RestController
@RequestMapping("/system/sys-role")
public class SysRoleController {

    @Resource
    private ISysRoleService sysRoleService;

    /**
     * 查询角色列表
     * @return
     */
    @ApiOperation(value="查询角色列表", notes="查询角色列表")
    @RequestMapping(value = "RoleList", method = RequestMethod.GET)
    @SysLog(value = "测试注解日志切面查询角色列表")
    public PageUtils roleList (Map<String, Object> params){
        return sysRoleService.queryPage(params);

    }

    /**
     *新增角色
     * @return
     */
    @ApiOperation(value="新增角色", notes="新增角色")
    @RequestMapping(value = "roleList", method = RequestMethod.PUT)
    @SysLog(value = "测试注解日志切面新增角色")
    public void addRole(SysRole sysRole){
         sysRoleService.addRole(sysRole) ;
    }

    /**
     *更新角色
     * @return
     */
    @ApiOperation(value="更新角色", notes="更新角色")
    @RequestMapping(value = "roleList", method = RequestMethod.POST)
    @SysLog(value = "测试注解日志切面更新角色")
    public void updaRole(SysRole sysRole){
        sysRoleService.updateRole(sysRole) ;
    }
    /**
     *删除角色
     * @return
     */
    @ApiOperation(value="删除角色", notes="删除角色")
    @RequestMapping(value = "roleList", method = RequestMethod.DELETE)
    @SysLog(value = "测试注解日志切面删除角色")
    public void deleteRole(String userId){
        sysRoleService.deleteRole(userId);
    }
}
