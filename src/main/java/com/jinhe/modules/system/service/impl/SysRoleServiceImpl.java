package com.jinhe.modules.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jinhe.common.util.PageUtils;
import com.jinhe.modules.system.dao.SysRoleMapper;
import com.jinhe.modules.system.dto.SysRole;
import com.jinhe.modules.system.dto.SysUser;
import com.jinhe.modules.system.service.ISysRoleService;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

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

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        return null;
    }

    @Override
    public List<SysRole> roleList(){
        return sysRoleMapper.roleList();
    }

    @Override
    public void addRole(SysRole sysRole) {
        sysRoleMapper.insert(sysRole);
    }

    @Override
    public void updateRole(SysRole sysRole) {
        sysRoleMapper.updateById(sysRole);
    }

    @Override
    public void deleteRole(String userId) {
        sysRoleMapper.deleteById(userId);

    }
}
