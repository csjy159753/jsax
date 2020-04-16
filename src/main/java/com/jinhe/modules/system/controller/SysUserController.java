package com.jinhe.modules.system.controller;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jinhe.common.annotation.SysLog;
import com.jinhe.common.util.ResultUtil;
import com.jinhe.common.vo.Result;
import com.jinhe.modules.system.dto.SysUserDto;
import com.jinhe.modules.system.service.ISysUserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;

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
     * 测试日志
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
    @RequestMapping(value = "userList", method = RequestMethod.GET)
    @SysLog(value = "测试注解日志切面查询用户列表userList")
    public Result userList(Page page) {
        SysUserDto sysUserDto=new SysUserDto();
        IPage <SysUserDto> userListAll =sysUserService.userList(page, sysUserDto);
        return ResultUtil.success(userListAll);
    }
    /**
     * 关键字查询
     * @return
     */
    @ApiOperation(value="关键字查询", notes="关键字查询")
    @RequestMapping(value = "selectByWords/{normalizedUserName}&{organName}&{roleName}", method =RequestMethod.GET)
    @SysLog(value = "测试注解日志切面关键字查询selectByWords")
    public  Result selectByWords(Page page, @PathVariable String normalizedUserName,String organName,String roleName){
        SysUserDto sysUserDto=new SysUserDto();
        IPage <SysUserDto> userList =sysUserService.selectByWords(page,sysUserDto,normalizedUserName,organName,roleName);
        return ResultUtil.success(userList);
    }
    /**
     * 查询被禁用户列表
     * @return
     */
    @ApiOperation(value="查询被禁用户列表", notes="查询被禁用户列表")
    @RequestMapping(value = "disableUserList", method = RequestMethod.GET)
    @SysLog(value = "测试注解日志切面查询被禁用户列表disableUserList")
    public Result disableUserList(Page page) {
        SysUserDto sysUserDto = new SysUserDto();
        IPage <SysUserDto> disableUserList = sysUserService.disableUserList(page, sysUserDto);
        return ResultUtil.success(disableUserList);
    }
    /**
     * 新增用户
     * @return
     */
    @ApiOperation(value="新增用户", notes="新增用户")
    @RequestMapping(value = "addUser", method =RequestMethod.PUT)
    @SysLog(value = "测试注解日志切面新增用户addUser")
    public void addUser(SysUserDto sysUserDto){
          sysUserService.addUser(sysUserDto);
    }

    /**
     * 更新用户信息
     * @return
     */
    @ApiOperation(value="更新用户信息", notes="更新用户信息")
    @RequestMapping(value = "updateUser", method =RequestMethod.POST)
    @SysLog(value = "测试注解日志切面更新用户信息updateUser")
    public void updateUser(@RequestBody SysUserDto sysUserDto){
        sysUserService.updateUser(sysUserDto);
    }
    /**
     * 重置密码
     * @return
     */
    @ApiOperation(value="重置密码", notes="重置密码")
    @RequestMapping(value = "updatePassword/{oldPassword}&{newPassword}&{id}", method =RequestMethod.POST)
    @SysLog(value = "测试注解日志切面重置密码updatePassword")
    public void updatePassword(@PathVariable String oldPassword,String newPassword,String id){
        oldPassword=DigestUtils.md5DigestAsHex(oldPassword.getBytes());
        newPassword=DigestUtils.md5DigestAsHex(newPassword.getBytes());
        sysUserService.updatePassword(oldPassword,newPassword,id);
    }
    /**
     * 禁用/恢复账户
     * @return
     */
    @ApiOperation(value="禁用/恢复账户", notes="禁用/恢复账户")
    @RequestMapping(value = "ableUserById/{id}", method =RequestMethod.POST)
    @SysLog(value = "测试注解日志切面禁用/恢复账户ableUserById")
    public void ableUserById(@PathVariable String id){
        sysUserService.ableUserById(id);
    }
    /**
     * 删除用户
     * @return
     */
    @ApiOperation(value="删除用户", notes="删除账户")
    @RequestMapping(value = "deleteUserById/{id}", method =RequestMethod.DELETE)
    @SysLog(value = "测试注解日志切面删除账户deleteUserById")
    public void deleteUserById(@PathVariable String id){
        sysUserService.deleteUserById(id);
    }
}
