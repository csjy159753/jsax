package com.jinhe.modules.system.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jinhe.modules.system.dto.SysUser;
import com.jinhe.modules.system.dto.SysUserDto;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author rls
 * @since 2020-03-12
 */
@Mapper
public interface SysUserMapper extends BaseMapper<SysUser> {

    //查询所有用户列表
    IPage<SysUserDto> userList(Page<SysUserDto> page, SysUserDto sysUserDto);

    //关键字查询
    IPage<SysUserDto> selectByWords(Page<SysUserDto> page, SysUserDto sysUserDto,String normalizedUserName,String organName,String roleName);

    //查询被禁用户列表
    IPage<SysUserDto> disableUserList(Page<SysUserDto> page, SysUserDto sysUserDto);

    //新增用户
    void addUser(SysUserDto sysUserDto);

   //更新用户
    void updateUser(SysUserDto sysUserDto);

    //重置密码
    void updatePassword(String oldPassword,String newPassword,String userId);

    //禁用/恢复账户
    void ableUserById(String userId,int x);

    //查询用户状态
    int selectStateById(String userId);

    //删除用户
    void deleteUserById(String userId);

}
