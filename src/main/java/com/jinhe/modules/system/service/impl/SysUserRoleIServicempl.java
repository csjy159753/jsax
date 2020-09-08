package com.jinhe.modules.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jinhe.modules.system.dao.SysUserRoleMapper;
import com.jinhe.modules.system.entity.SysUserRole;
import com.jinhe.modules.system.service.ISysUSerRoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
@Service
@Transactional
public class SysUserRoleIServicempl extends ServiceImpl<SysUserRoleMapper, SysUserRole> implements ISysUSerRoleService {
    @Resource
    SysUserRoleMapper sysUserRoleMapper;
    @Override
    public boolean insertUserRole(List<SysUserRole> list) {
        int insert = sysUserRoleMapper.insertList(list);
        if(insert <0){
            return false;
        }else{
            return true;
        }
    }

    @Override
    public int deleteByUserId(String userId) {
        int i = sysUserRoleMapper.deleteByUserId(userId);
        return i;
    }
}
