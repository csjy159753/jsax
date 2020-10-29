package com.jinhe.modules.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jinhe.common.util.ResultEnum;
import com.jinhe.modules.system.dao.*;
import com.jinhe.modules.system.dto.*;
import com.jinhe.modules.system.entity.SysRole;
import com.jinhe.modules.system.entity.SysUser;
import com.jinhe.modules.system.entity.SysUserRole;
import com.jinhe.modules.system.service.ISysPermissionService;
import com.jinhe.modules.system.service.ISysRoleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.jinhe.common.util.ResultEnum.*;


/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author rls
 * @since 2020-04-14
 */
/*List<TreeNode> l=  Tree.CreateTree(list, new ITree<SysRole>() {
            @Override
            public TreeNode modelTo(SysRole o) {
                TreeNode treeNode=new TreeNode();
                treeNode.setId(o.getId());
                treeNode.setParentId(o.getParentId());
                treeNode.setNodeValue(o);
                return treeNode;
            }
        });*/
        /*List<IRoleTreeNode> l= RoleTree.CreateTree(list, new IRoleTree<SysRole>() {
            @Override
            public IRoleTreeNode modelTo(SysRole o) {
                IRoleTreeNode iRoleTreeNode=new IRoleTreeNode();
                iRoleTreeNode.setId(o.getId());
                iRoleTreeNode.setParentId(o.getParentId());
                iRoleTreeNode.setName(o.getName());
                iRoleTreeNode.setTag(null);
                iRoleTreeNode.setDescription(o.getDescription());
                iRoleTreeNode.setType(o.getType());
                iRoleTreeNode.setState(o.getState());
                iRoleTreeNode.setSort(o.getSort());
                iRoleTreeNode.setLockoutEnabled(o.getLockOutEnabled());
                iRoleTreeNode.setLockoutTime(o.getLockOutTime());
                return iRoleTreeNode;
            }
        });*/
@Service
@Transactional
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements ISysRoleService {

    @Resource
    private SysRoleMapper sysRoleMapper;

    @Resource
    private SysUserMapper sysUserMapper;

    @Resource
    private SysUserRoleMapper sysUserRoleMapper;

    //查询角色列表
    @Override
    public IPage<HashMap<String,Object>> selectRoleList(Page page, String id,String roleId) {
        SysUser sysUser = sysUserMapper.selectById(id);
        if (sysUser == null) {
            return null;
        }
        Integer type = sysUser.getType();
        IPage<HashMap<String,Object>>  list = sysRoleMapper.selectPreantRoleList(page, roleId, type);
        return  list;
    }

    //删除角色
    @Override
    public ResultEnum deleteRole(String id, String userId) {
        SysUser sysUser = sysUserMapper.selectById(userId);
        /**
         * sysUse处理用户授权对象 占时未处理
         */
        QueryWrapper<SysUserRole> SysUserRolequeryWrapper = new QueryWrapper<>();

        SysRole role = this.getById(id);
        if (role == null) {
            return ROLE_NOT_FOUND;
        }
        SysUserRolequeryWrapper.eq("role_id",id);
        List<SysUserRole> listSysUserRole = sysUserRoleMapper.selectList(SysUserRolequeryWrapper);
        if (listSysUserRole.size() > 0) {
            return ROLE_ASSOCIATED_USERS;
        }
        QueryWrapper<SysRole> SysUserqueryWrapper = new QueryWrapper<>();
        SysUserqueryWrapper.eq("PARENT_ID", id);
        SysRole roleCh = this.getOne(SysUserqueryWrapper);
        if (roleCh != null) {
            return ROLE_EXIST_SUBSET_UNABLE_DEL;
        }
        this.removeById(id);
        return SUCCESS;
    }

    @Override
    public List<SysRole> selectRolesByUserId(Page page, String userId) {
        SysUser sysUser = sysUserMapper.selectById(userId);
        Integer type = sysUser.getType();
        if (type == null || type == 99) {
            List<SysRole> sysRoles = new ArrayList<>();
            return sysRoles;
        } else {
            IPage<SysRole> list = sysRoleMapper.selectRolesByUserId(page, userId);
            List<SysRole> records = list.getRecords();
            return records;
        }

    }


}
