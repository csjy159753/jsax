package com.jinhe.modules.system.service.impl;


import com.jinhe.common.util.Tree.ITree;
import com.jinhe.common.util.Tree.Tree;
import com.jinhe.common.util.Tree.TreeNode;
import com.jinhe.modules.system.dao.SysRegionMapper;
import com.jinhe.modules.system.dto.SysRegion;
import com.jinhe.modules.system.service.ISysRegionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author rls
 * @since 2020-04-16
 */
@Service
public class SysRegionServiceImpl extends ServiceImpl<SysRegionMapper, SysRegion> implements ISysRegionService {


    @Resource
    private SysRegionMapper sysRegionMapper;

    //查询行政区列表
    @Override
    public List<TreeNode> selectRegionList(HashMap map) {
        if(map==null){
            map=new HashMap();
        }
        List<SysRegion> list=sysRegionMapper.selectRegionList(map);
        List<TreeNode> l=  Tree.CreateTree(list, new ITree<SysRegion>() {
            @Override
            public TreeNode modelTo(SysRegion o) {
                TreeNode treeNode=new TreeNode();
                treeNode.setId(o.getCode());
                treeNode.setParentId(o.getParentCode());
                treeNode.setNodeValue(o);
                return treeNode;
            }
        });
        return l;
    }

    //新增行政区
    @Override
    public void addRegion(SysRegion sysRegion) {
          sysRegionMapper.insert(sysRegion);
    }

    //更新行政区列表
    @Override
    public void updateRegion(SysRegion sysRegion) {
          sysRegionMapper.updateById(sysRegion);
    }

    //删除行政区
    @Override
    public void deleteRegion(String regionId) {
          sysRegionMapper.deleteById(regionId);
    }
}
