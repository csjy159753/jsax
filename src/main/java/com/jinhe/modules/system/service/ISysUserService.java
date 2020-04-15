package com.jinhe.modules.system.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jinhe.modules.system.dto.SysUser;

import com.jinhe.modules.system.dto.SysUserDto;

import java.util.List;


/**
 * <p>
 *  服务类
 * </p>
 *
 * @author rls
 * @since 2020-03-12
 */
public interface ISysUserService extends IService<SysUser> {

    //分页查询所有用户列表
     IPage<SysUserDto> userList(Page<SysUserDto> page, SysUserDto sysUserDto);

    //关键字查询
    IPage<SysUserDto> selectByWords(Page<SysUserDto> page, SysUserDto sysUserDto,String normalizedUserName,String organName,String roleName);

    //查询被禁用户列表
    IPage<SysUserDto> disableUserList(Page<SysUserDto> page, SysUserDto sysUserDto);

    //新增用户
    void addUser(SysUserDto sysUserDto);

   //更新用户信息
    void updateUser(SysUserDto sysUserDto);

    //重置密码
    void updatePassword(String oldPassword,String newPassword,String userId);

    //恢复/禁用账户
    void ableUserById(String userId);

    //删除用户
    void deleteUserById(String userId);



}
