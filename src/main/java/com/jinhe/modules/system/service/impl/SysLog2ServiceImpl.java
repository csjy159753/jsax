package com.jinhe.modules.system.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jinhe.common.util.Tree.ITree;
import com.jinhe.common.util.Tree.Tree;
import com.jinhe.common.util.Tree.TreeNode;
import com.jinhe.modules.system.dao.SysLogMapper;
import com.jinhe.modules.system.entity.SysLog;
import com.jinhe.modules.system.entity.SysRole;
import com.jinhe.modules.system.service.ISysLog2Service;
import com.jinhe.modules.system.service.ISysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author rls
 * @since 2020-04-15
 */
@Service
@CacheConfig(cacheNames = "SysLog2ServiceImpl")//抽取缓存的公共配置
public class SysLog2ServiceImpl extends ServiceImpl<SysLogMapper, SysLog> implements ISysLog2Service {
    @Autowired
    private SysLogMapper sysLogMapper;

    @Override
    public IPage<SysLog> selectPageVo(Page<SysLog> page, SysLog sysLog) {
        return sysLogMapper.selectPageVo(page, sysLog);
    }

    @Override
    public List<TreeNode> selectSysRoleVo(HashMap map) {
        if (map == null) {
            map = new HashMap();
        }
        IPage<SysRole> page = new Page<>(0, 1000);
        List<SysRole> list = sysLogMapper.selectSysRoleVo(page, map);
        List<TreeNode> l = Tree.CreateTree(list, new ITree<SysRole>() {
            @Override
            public TreeNode modelTo(SysRole o) {
                TreeNode treeNode = new TreeNode();
                treeNode.setId(o.getId());
                treeNode.setParentId(o.getParentId());
                treeNode.setNodeValue(o);
                return treeNode;
            }
        });
        return l;
    }
    @Override
    @Cacheable()
    public List<SysLog> listAll() {
        return this.list();
    }

    @Override
    @Cacheable(key = "#id")
    public SysLog getOneC(String id) {
        return this.getById(id);
    }

    @Override
    public Boolean DelById(String id) {
        return this.removeById(id);
    }

}
