package com.jinhe.modules.system.service.impl;

import com.jinhe.common.util.Tree.ITree;
import com.jinhe.common.util.Tree.Tree;
import com.jinhe.common.util.Tree.TreeNode;
import com.jinhe.modules.system.dto.SysResourceDto;
import com.jinhe.modules.system.entity.PermissionItem;
import com.jinhe.modules.system.entity.SysPermission;
import com.jinhe.modules.system.dao.SysPermissionMapper;
import com.jinhe.modules.system.service.ISysPermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author rls
 * @since 2020-05-19
 */
@Service
public class SysPermissionServiceImpl extends ServiceImpl<SysPermissionMapper, SysPermission> implements ISysPermissionService {
    @Autowired
    private SysPermissionMapper sysPerMap;

    @Override
    public boolean addByUserId(String userId, List<String> resourceIds) {
        SysPermission sysPer = new SysPermission();
        List<SysPermission> sysPers = new ArrayList<>();
        resourceIds.forEach(x -> {
            String s = UUID.randomUUID().toString().replace("-", "");
            sysPer.setId(s);
            sysPer.setUserId(userId);
            sysPer.setResourceId(x);
            sysPers.add(sysPer);
        });
        boolean flag = sysPerMap.addByUserId(sysPers);
        return flag;
    }

    @Override
    @Transactional
    public boolean addByOrganId(String orgionId, List<PermissionItem> PermissionItem) {
        List<SysPermission> sysPers = new ArrayList<>();
        PermissionItem.forEach(x -> {
            SysPermission sysPer = new SysPermission();
            String s = UUID.randomUUID().toString().replace("-", "");
            sysPer.setId(s);
            sysPer.setOrgionId(orgionId);
            sysPer.setResourceId(x.getSysPermissionId());
            sysPer.setItemIds(x.getItemId());
            sysPer.setUpdateTime(new Date());
            sysPer.setCreateTime(new Date());
            sysPers.add(sysPer);
        });
//        this.saveBatch(sysPers);
        boolean flag =  this.saveBatch(sysPers);
        return flag;
    }

    @Override
    @Transactional
    public boolean addByRoleId(String roleId, List<PermissionItem> permissionItem) {
        List<SysPermission> sysPers = new ArrayList<>();
        permissionItem.forEach(x -> {
            SysPermission sysPer = new SysPermission();
            String s = UUID.randomUUID().toString().replace("-", "");
            sysPer.setId(s);
            sysPer.setRoleId(roleId);
            sysPer.setResourceId(x.getSysPermissionId());
            sysPer.setItemIds(x.getItemId());
            sysPers.add(sysPer);
        });
        boolean flag = sysPerMap.addByRoleId(sysPers);
        return flag;
    }

    @Override
    public List<TreeNode> listByOrganId(String orgionId) {
        List<TreeNode> treenodelist = new ArrayList<>();
        List<String> resourceIds = sysPerMap.resourceIdByOrganId(orgionId);
        if (!resourceIds.isEmpty()) {
            List<SysResourceDto> pagelist = sysPerMap.listByRoleId(resourceIds);
            treenodelist = Tree.CreateTree(pagelist, new ITree<SysResourceDto>() {
                @Override
                public TreeNode<SysResourceDto> modelTo(SysResourceDto o) {
                    TreeNode treeNode = new TreeNode();
                    treeNode.setId(o.getId());
                    treeNode.setParentId(o.getParentId());
                    treeNode.setNodeValue(o);
                    return treeNode;
                }
            });
        }
        return treenodelist;
    }

    @Override
    public List<TreeNode> listByRoleId(String roleId) {
        List<TreeNode> treenodelist = new ArrayList<>();
        List<String> resourceIds = sysPerMap.resourceIdByRoleId(roleId);
        if (!resourceIds.isEmpty()) {
            List<SysResourceDto> pagelist = sysPerMap.listByRoleId(resourceIds);
            treenodelist = Tree.CreateTree(pagelist, new ITree<SysResourceDto>() {
                @Override
                public TreeNode<SysResourceDto> modelTo(SysResourceDto o) {
                    TreeNode treeNode = new TreeNode();
                    treeNode.setId(o.getId());
                    treeNode.setParentId(o.getParentId());
                    treeNode.setNodeValue(o);
                    return treeNode;
                }
            });
        }
        return treenodelist;
    }

    @Override
    public boolean add(SysPermission sysPer) {
        boolean flags = sysPerMap.add(sysPer);
        return flags;
    }

    @Override
    public SysPermission getById(String id) {
        SysPermission sysPer = sysPerMap.getById(id);
        return sysPer;
    }

    @Override
    public Integer deleteById(String id) {
        Integer flags = sysPerMap.deleteById(id);
        return flags;
    }

    @Override
    public void deleteByRoleId(String roleId) {
        sysPerMap.deleteByRoleId(roleId);
    }

    @Override
    public void deleteByOrganId(String organId) {
        sysPerMap.deleteByOrganId(organId);
    }
}
