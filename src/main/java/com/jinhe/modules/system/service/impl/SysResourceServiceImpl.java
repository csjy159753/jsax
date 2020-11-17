package com.jinhe.modules.system.service.impl;

import com.jinhe.common.util.ListSub;
import com.jinhe.common.util.Tree.ITree;
import com.jinhe.common.util.Tree.MapTree;
import com.jinhe.common.util.Tree.Tree;
import com.jinhe.common.util.Tree.TreeNode;
import com.jinhe.modules.system.dao.SysUserMapper;
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

    @Override
    public List<SysResourceDTO> selectPageAll() {
        return sysResourceMapper.selectPageAll();
    }

    @Override
    public List<SysResourceDTO> listResource(String userId) {
        List<TreeNode> treenodelist = new ArrayList<>();
        List<SysResourceDTO> listtree = new ArrayList<>();
        SysUser sysUser = sysUserMapper.selectById(userId);
        if (sysUser == null) {
            return null;
        }
        Integer type = sysUser.getType();
        if (type == 99) {
            listtree = sysResourceMapper.selectPageAll();
        } else {
            listtree = sysResourceMapper.listResource(userId);
        }


        return listtree;
    }
}
