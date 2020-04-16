package com.jinhe.modules.system.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jinhe.modules.system.dao.SysRoleMapper;
import com.jinhe.modules.system.dto.SysRole;
import com.jinhe.modules.system.service.ISysRoleService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;


/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author rls
 * @since 2020-04-14
 */
@Service
@Transactional
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements ISysRoleService {

    @Resource
    private SysRoleMapper sysRoleMapper;

    //查询角色列表
    @Override
    public  IPage<SysRole> roleList(Page<SysRole> page, SysRole sysRole){
        return sysRoleMapper.roleList(page,sysRole);
    }

    //新增角色
    @Override
    public void addRole(SysRole sysRole) {
        sysRoleMapper.insert(sysRole);
    }

    //更新角色
    @Override
    public void updateRole(SysRole sysRole) {
        sysRoleMapper.updateById(sysRole);
    }

    //删除角色
    @Override
    public void deleteRole(String userId) {
        sysRoleMapper.deleteById(userId);

    }
}
