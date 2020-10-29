package com.jinhe.modules.system.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jinhe.common.util.ResultEnum;
import com.jinhe.modules.system.entity.SysUser;

import com.jinhe.modules.system.dto.SysUserDTO;
import com.jinhe.modules.system.dto.SysUserDtoNew;


/**
 * <p>
 * 服务类
 * </p>
 *
 * @author rls
 * @since 2020-03-12
 */
public interface ISysUserService extends IService<SysUser> {

    //查询用户列表
    IPage<SysUserDTO> selectUserList(Page<SysUserDTO> page, String normalizedUserName, String organId, String roleId, Integer state, String userId);

    //新增用户
    int addUser(SysUserDtoNew sysUserDto);

    //删除用户
    ResultEnum deleteUserById(String userId) throws InstantiationException, IllegalAccessException;

    //根据id查询
    SysUser selectById(String id);

    int SaveOrUpdateRole(String userId, String organId);

    int SaveOrUpdateOrgan(String userId, String organId);
}
