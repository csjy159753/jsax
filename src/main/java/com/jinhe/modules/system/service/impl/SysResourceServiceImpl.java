package com.jinhe.modules.system.service.impl;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jinhe.common.util.ListSub;
import com.jinhe.common.util.Mapper;
import com.jinhe.common.util.Tree.ITree;
import com.jinhe.common.util.Tree.Tree;
import com.jinhe.common.util.Tree.TreeNode;
import com.jinhe.modules.system.dao.SysResourceMapper;
import com.jinhe.modules.system.dao.SysUserMapper;
import com.jinhe.modules.system.dto.SysResourceDTO;
import com.jinhe.modules.system.entity.SysUser;
import com.jinhe.modules.system.entity.*;
import com.jinhe.modules.system.service.ISysResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author xk
 * @since 2020-04-15
 */
@Service
public class SysResourceServiceImpl extends ServiceImpl<SysResourceMapper, SysResource> implements ISysResourceService {
    @Autowired
    private SysResourceMapper Sysresmapper;

    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    public ListSub selectSysResourcepage(String userid) {
        List<TreeNode> treenodelist = new ArrayList<>();
        List<SysResourceDTO> listtree = new ArrayList<>();
        SysUser sysUser = sysUserMapper.selectById(userid);
        if (sysUser == null) {
            return null;
        }
        Integer type = sysUser.getType();
        if (type == 99) listtree = Sysresmapper.selectPageAll();
        else listtree = Sysresmapper.selectSysResourcepage(userid);
        treenodelist = Tree.CreateTree(listtree, new ITree<SysResourceDTO>() {
            @Override
            public TreeNode<SysResourceDTO> modelTo(SysResourceDTO o) {
                TreeNode treeNode = new TreeNode();
                treeNode.setId(o.getId());
                treeNode.setParentId(o.getParentId());
                treeNode.setNodeValue(o);
                return treeNode;
            }
        });
        ListSub listSub = new ListSub();
        listSub.setTotal(treenodelist.size());
        listSub.setList(treenodelist);
        listSub.setTree(true);
        return listSub;
    }


    @Override
    public SysResourceDTO Select_SysRespagebyid(String ID) {
        SysResourceDTO sysresdto = new SysResourceDTO();
        sysresdto = Sysresmapper.selectSysResourceOne(ID);
        return sysresdto;
    }

    @Override
    public boolean sysresourcesave(SysResourceDTO sysresdto) {
        boolean flags = false;
        SysResource sysres1 = new SysResource();
        Timestamp time = new Timestamp(System.currentTimeMillis());//获取系统当前时间
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String timeStr = df.format(time);
        time = Timestamp.valueOf(timeStr);
        sysresdto.setUpdateTime(time);
        Mapper.MapToModel(sysresdto, sysres1);
        flags = this.save(sysres1);
        return flags;
    }

    @Override
    public List<TreeNode> SysResourceTree(Page page) {
        List<TreeNode> treelist = new ArrayList<>();
        Map mapsys = new HashMap();
        mapsys = Mapper.entityToMap(page);
        List<SysResource> listtree = Sysresmapper.selectsysresTree(mapsys);
        treelist = Tree.CreateTree(listtree, new ITree<SysResource>() {
            @Override
            public TreeNode<SysResource> modelTo(SysResource o) {
                TreeNode treeNode = new TreeNode();
                treeNode.setId(o.getId());
                treeNode.setParentId(o.getParentId());
                treeNode.setNodeValue(o);
                return treeNode;
            }
        });
        return treelist;
    }

    @Override
    public Integer DeleteResourceByid(String id) {
        Integer flags = Sysresmapper.deleteById(id);
        return flags;
    }

    @Override
    public Integer UpdateOranByid(SysResourceDTO dto) {
        SysResource sysresource = new SysResource();
        Mapper.MapToModel(dto, sysresource);
        Integer flags = Sysresmapper.updateById(sysresource);
        return flags;
    }

    @Override
    public Integer modify(SysResource sysRes, String id) {
        Integer flags = 0;
        sysRes.setId(id);
        flags = Sysresmapper.updateById(sysRes);
        return flags;
    }
}
