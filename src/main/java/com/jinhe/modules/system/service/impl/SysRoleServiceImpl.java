package com.jinhe.modules.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jinhe.common.util.Tree.ITree;
import com.jinhe.common.util.Tree.Tree;
import com.jinhe.common.util.Tree.TreeNode;
import com.jinhe.modules.system.dao.SysRoleMapper;
import com.jinhe.modules.system.dto.SysRole;
import com.jinhe.modules.system.service.ISysRoleService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;


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
    public List<TreeNode> selectRoleList(HashMap map) {
        if(map==null){
            map=new HashMap();
        }
        List<SysRole> list=sysRoleMapper.selectRoleList(map);
        List<TreeNode> l=  Tree.CreateTree(list, new ITree<SysRole>() {
            @Override
            public TreeNode modelTo(SysRole o) {
                TreeNode treeNode=new TreeNode();
                treeNode.setId(o.getId());
                treeNode.setParentId(o.getParentId());
                treeNode.setNodeValue(o);
                return treeNode;
            }
        });
        return l;

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
