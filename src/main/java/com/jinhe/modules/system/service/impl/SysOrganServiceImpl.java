package com.jinhe.modules.system.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jinhe.common.util.EntityUtils;
import com.jinhe.common.util.Mapper;
import com.jinhe.common.util.Tree.ITree;
import com.jinhe.common.util.Tree.Tree;
import com.jinhe.common.util.Tree.TreeNode;
import com.jinhe.modules.system.dto.SysOrganDto;
import com.jinhe.modules.system.entity.SysOrgan;
import com.jinhe.modules.system.dao.SysOrganMapper;
import com.jinhe.modules.system.entity.SysResource;
import com.jinhe.modules.system.service.ISysOrganService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author rls
 * @since 2020-04-16
 */
@Service
public class SysOrganServiceImpl extends ServiceImpl<SysOrganMapper, SysOrgan> implements ISysOrganService {
    @Autowired
    private  SysOrganMapper sysorgmap;

    @Override
    public Page<SysOrgan> selectPageOrgan(Page page) {

       Page<SysOrgan> sysorgls =  sysorgmap.selectPageOrgan(page);


        return sysorgls;
    }

    @Override
    public SysOrganDto selectPageOrganByID(String ID) {

        SysOrganDto sysorgdto = new SysOrganDto();

        sysorgdto = sysorgmap.selectPageOrganByID(ID);

        return sysorgdto;
    }

    @Override
    public boolean addsysorgan(SysOrganDto sysorg) {
        boolean flags = false;

        SysOrgan sysorg1 = new SysOrgan();

        Mapper.MapToModel(sysorg,sysorg1);

        flags = this.save(sysorg1);

        return flags;
    }

    @Override
    public List<TreeNode> SelectOrganParent(Page page) {
        List<TreeNode> treenodelist = new ArrayList<>();

        Map map = new HashMap();

        map = EntityUtils.entityToMap(page);

        List<SysOrganDto> pagelist = sysorgmap.selectlistparent(map);

        treenodelist=  Tree.CreateTree(pagelist, new ITree<SysOrgan>() {
            @Override
            public TreeNode<SysOrgan> modelTo(SysOrgan o) {
                TreeNode treeNode=new TreeNode();
                treeNode.setId(o.getId());
                treeNode.setParentId(o.getParentId());
                treeNode.setNodeValue(o);
                return treeNode;
            }
        });

        return treenodelist;
    }

    @Override
    public Integer DeleteOrganByid(String id) {

        Integer flags = sysorgmap.deleteById(id);

        return flags;
    }

    @Override
    public Integer UpdateOranByid(SysOrganDto dto) {
        SysOrgan sysorgan = new SysOrgan();

        Mapper.MapToModel(dto,sysorgan);

        Integer flags = sysorgmap.updateById(sysorgan);

        return flags;
    }

}
