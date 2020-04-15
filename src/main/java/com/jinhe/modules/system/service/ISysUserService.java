package com.jinhe.modules.system.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.jinhe.common.utils.PageUtils;
import com.jinhe.modules.system.dto.SysRole;
import com.jinhe.modules.system.dto.SysUser;
import com.baomidou.mybatisplus.service.IService;
import com.jinhe.modules.system.dto.SysUserDto;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author rls
 * @since 2020-03-12
 */
public interface ISysUserService extends IService<SysUser> {

    //分页
    //PageUtils queryPage(Map<String, Object> params);

     PageUtils queryPage(Map<String,Object> parms);

    //查询所有用户列表
  /*  List<SysUserDto>  listAllrls();*/

    //Demo
    List<SysUserDto> listDemo();

    //新增用户
    void addUser(SysUserDto sysUserDto);

    //关键字查询
    List<SysUserDto> selectByWords(String normalizedUserName,String organName,String roleName);

   //更新用户信息
    void updateUser(SysUserDto sysUserDto);

    //重置密码
    void updatePassword(String oldPassword,String newPassword,String userId);

    //恢复/禁用账户
    void ableUserById(String userId);

    //删除用户
    void deleteUserById(String userId);

    //查询被禁用户列表
    List<SysUserDto> disableUserList();

}
