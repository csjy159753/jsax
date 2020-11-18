package com.jinhe.modules.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jinhe.common.util.Tree.TreeNode;
import com.jinhe.modules.sys.dao.SysUserMapper;
import com.jinhe.modules.system.dto.SysResourceDTO;
import com.jinhe.modules.system.entity.SysResource;
import com.jinhe.modules.system.dao.SysResourceMapper;
import com.jinhe.modules.system.entity.SysUser;
import com.jinhe.modules.system.service.ISysResourceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 资源菜单 服务实现类
 * </p>
 *
 * @author rls
 * @since 2020-10-29
 */
@Service
public class SysResourceServiceImpl extends ServiceImpl<SysResourceMapper, SysResource> implements ISysResourceService {

    @Autowired
    private SysResourceMapper sysResourceMapper;
    @Autowired
    private SysUserMapper sysUserMapper;
    private Integer userTypeSuperAdmin = 99;
    private Integer userTypeAdmin = 98;

    @Override
    public List<SysResourceDTO> selectPageAll() {
        return sysResourceMapper.selectPageAll();
    }

    @Override
    public List<SysResourceDTO> listResource(String userId) {
        List<SysResourceDTO> list = new ArrayList<>();
        SysUser sysUser = sysUserMapper.selectById(userId);
        if (sysUser == null) {
            return null;
        }
        Integer type = sysUser.getType();
        if (type.equals(userTypeSuperAdmin)) {
            list = sysResourceMapper.listResourceAdmin(1);
        } else if (type.equals(userTypeAdmin)) {
            list = sysResourceMapper.listResourceAdmin(2);
        } else {
            list = sysResourceMapper.listResource(userId);
        }


        return list;
    }

    @Override
    public List<SysResourceDTO> listResourceAdmin() {
        List<SysResourceDTO> listtree = new ArrayList<>();
        listtree = sysResourceMapper.listResourceAdmin(2);
        return listtree;
    }

    @Override
    public List<SysResourceDTO> listByRole(String roleId) {
        List<SysResourceDTO> list = new ArrayList<>();
        list = sysResourceMapper.listByRole(roleId);
        return list;
    }
}
