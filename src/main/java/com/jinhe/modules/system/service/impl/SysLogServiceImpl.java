package com.jinhe.modules.system.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jinhe.common.util.Tree.ITree;
import com.jinhe.common.util.Tree.Tree;
import com.jinhe.common.util.Tree.TreeNode;
import com.jinhe.modules.system.dto.SysUserVo;
import com.jinhe.modules.system.entity.SysLog;
import com.jinhe.modules.system.dao.SysLogMapper;
import com.jinhe.modules.system.entity.SysRole;
import com.jinhe.modules.system.service.ISysLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author rls
 * @since 2020-04-15
 */
@Service
public class SysLogServiceImpl extends ServiceImpl<SysLogMapper, SysLog> implements ISysLogService {
    @Autowired
    private SysLogMapper sysLogMapper;

    @Override
    public IPage<SysLog> selectPageVo(Page<SysLog> page, SysLog sysLog) {
        return sysLogMapper.selectPageVo(page,sysLog);
    }

    @Override
    public List<TreeNode>  selectSysRoleVo(HashMap map) {
        if(map==null){
            map=new HashMap();
        }
        List<SysRole> list=sysLogMapper.selectSysRoleVo(map);


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
}
