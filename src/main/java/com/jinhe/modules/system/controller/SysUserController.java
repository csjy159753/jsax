package com.jinhe.modules.system.controller;

import com.jinhe.common.annotation.SysLog;
import com.jinhe.common.util.PageUtils;
import com.jinhe.modules.system.dto.SysUserDto;
import com.jinhe.modules.system.service.ISysUserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;
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
   /* *//**
     * 查询用户列表
     * @return
     *//*
    @ApiOperation(value="查询用户列表", notes="查询用户列表")
    @RequestMapping(value = "List", method = RequestMethod.GET)
    @SysLog(value = "测试注解日志切面查询用户列表")
    public PageUtils List (Map<String, Object> params){
         return sysUserService.queryPage(params);

    }*/

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

    /**
     * 查询用户列表
     * @return
     */
    @ApiOperation(value="查询用户列表listAll", notes="查询用户列表listAll")
    @RequestMapping(value = "listAll", method = RequestMethod.GET)
    @SysLog(value = "测试注解日志切面查询用户列表listAll")
    public PageUtils listAll(Map<String,Object> parms) {

        return null;

    }
    /**
     * 查询被禁用户列表
     * @return
     */
    @ApiOperation(value="查询被禁用户列表", notes="查询被禁用户列表")
    @RequestMapping(value = "disableUserList", method = RequestMethod.GET)
    @SysLog(value = "测试注解日志切面查询被禁用户列表disableUserList")
    public List<SysUserDto> disableUserList(){
        return sysUserService.disableUserList();
    }

    /**
     * 新增用户
     * @return
     */
    @ApiOperation(value="新增用户", notes="新增用户")
    @RequestMapping(value = "addUser", method =RequestMethod.GET)
    @SysLog(value = "测试注解日志切面新增用户addUser")
    public void addUser(@RequestBody SysUserDto sysUserDto){
          sysUserService.addUser(sysUserDto);
    }
    /**
     * 关键字查询
     * @return
     */
    @ApiOperation(value="关键字查询", notes="关键字查询")
    @PostMapping("selectByWords")
    @SysLog(value = "测试注解日志切面关键字查询selectByWords")
    public List<SysUserDto> selectByWords(@RequestBody SysUserDto sysUserDto){
         String normalizedUserName= "%"+sysUserDto.getNormalizedUsername()+"%";
         String organName= "%"+sysUserDto.getOrganName()+"%";
         String roleName="%"+sysUserDto.getRoleName()+"%";
          return sysUserService.selectByWords(normalizedUserName,organName,roleName);

}
    /**
     * 更新用户信息
     * @return
     */
    @ApiOperation(value="更新用户信息", notes="更新用户信息")
    @RequestMapping(value = "updateUser", method =RequestMethod.GET)
    @SysLog(value = "测试注解日志切面更新用户信息updateUser")
    public void updateUser(@RequestBody SysUserDto sysUserDto){
        sysUserService.updateUser(sysUserDto);
    }
    /**
     * 重置密码
     * @return
     */
    @ApiOperation(value="重置密码", notes="重置密码")
    @RequestMapping(value = "updatePassword", method =RequestMethod.GET)
    @SysLog(value = "测试注解日志切面重置密码updatePassword")
    public void updatePassword(String oldPassword,String newPassword,String userId){
        oldPassword=DigestUtils.md5DigestAsHex(oldPassword.getBytes());
        newPassword=DigestUtils.md5DigestAsHex(newPassword.getBytes());
        sysUserService.updatePassword(oldPassword,newPassword,userId);
    }
    /**
     * 禁用/恢复账户
     * @return
     */
    @ApiOperation(value="禁用/恢复账户", notes="禁用/恢复账户")
    @RequestMapping(value = "ableUserById", method =RequestMethod.GET)
    @SysLog(value = "测试注解日志切面禁用/恢复账户ableUserById")
    public void ableUserById(String userId){
        sysUserService.ableUserById(userId);
    }
    /**
     * 删除用户
     * @return
     */
    @ApiOperation(value="删除用户", notes="删除账户")
    @RequestMapping(value = "deleteUserById", method =RequestMethod.GET)
    @SysLog(value = "测试注解日志切面删除账户deleteUserById")
    public void deleteUserById( String userId){
        sysUserService.deleteUserById(userId);
    }
}
