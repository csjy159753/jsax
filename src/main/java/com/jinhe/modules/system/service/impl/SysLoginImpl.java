package com.jinhe.modules.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.jinhe.modules.system.dao.SysLoginMapper;
import com.jinhe.modules.system.dao.SysUserMapper;

import com.jinhe.modules.system.dto.SysLogin;
import com.jinhe.modules.system.dto.SysLoginDto;

import com.jinhe.modules.system.entity.SysUser;
import com.jinhe.modules.system.service.ISysLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author swf
 * @since 2020-05-15
 */
@Service
public class SysLoginImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysLoginService{

    @Autowired
    SysLoginMapper sysLoginMapper;

    @Override
    public SysLoginDto selectUserByLogin(SysLogin login) {
        return sysLoginMapper.selectUserByLogin(login);
    }


}
