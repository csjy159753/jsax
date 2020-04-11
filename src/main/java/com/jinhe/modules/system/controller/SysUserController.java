package com.jinhe.modules.system.controller;


import com.jinhe.common.annotation.SysLog;
import com.jinhe.common.utils.PageUtils;
import com.jinhe.modules.system.dto.SysUserDto;
import com.jinhe.modules.system.entity.SysUser;
import com.jinhe.modules.system.service.ISysUserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author rls
 * @since 2020-03-12
 */
@RestController
@RequestMapping("/system/sys-user")
public class SysUserController {

    @Resource
    private ISysUserService sysUserService;
    /**
     * 查询用户列表
     * @return
     */
    @ApiOperation(value="测试日志方法", notes="测试日志方法")
    @RequestMapping(value = "testrls", method = RequestMethod.GET)
    @SysLog(value = "测试注解日志切面")
    public String testLog (String param,int num){
        return param+num;
    }
    /**
     * 查询用户列表
     * @return
     */
    @ApiOperation(value="查询用户列表", notes="查询用户列表")
    @RequestMapping(value = "List", method = RequestMethod.GET)
    @SysLog(value = "测试注解日志切面查询用户列表")
    public PageUtils List (Map<String, Object> params){
         return sysUserService.queryPage(params);

    }
    /**
     * 查询用户列表
     * @return
     */
    @ApiOperation(value="查询用户列表listAll", notes="查询用户列表listAll")
    @RequestMapping(value = "listAll", method = RequestMethod.GET)
    @SysLog(value = "测试注解日志切面查询用户列表listAll")
    public List<SysUser> listAll(){
        return sysUserService.listAllrls();
    }

    /**
     * 查询用户列表
     * @return
     */
    @ApiOperation(value="查询用户列表listDemo", notes="查询用户列表listDemo")
    @RequestMapping(value = "listDemo", method = RequestMethod.GET)
    @SysLog(value = "测试注解日志切面查询用户列表listAll")
    public List<SysUserDto> listDemo(){
        return sysUserService.listDemo();
    }

}
