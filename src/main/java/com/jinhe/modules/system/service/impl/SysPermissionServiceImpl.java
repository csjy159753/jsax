package com.jinhe.modules.system.service.impl;

import com.jinhe.common.util.StringUtils;
import com.jinhe.common.util.Tree.ITree;
import com.jinhe.common.util.Tree.MapTree;
import com.jinhe.common.util.Tree.Tree;
import com.jinhe.common.util.Tree.TreeNode;
import com.jinhe.modules.system.dto.SysResourceDTO;
import com.jinhe.modules.system.dto.PermissionItemDTO;
import com.jinhe.modules.system.entity.SysPermission;
import com.jinhe.modules.system.dao.SysPermissionMapper;
import com.jinhe.modules.system.entity.SysPermissionItem;
import com.jinhe.modules.system.service.ISysPermissionItemService;
import com.jinhe.modules.system.service.ISysPermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author rls
 * @since 2020-05-19
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SysPermissionServiceImpl extends ServiceImpl<SysPermissionMapper, SysPermission> implements ISysPermissionService {
    @Autowired
    private SysPermissionMapper sysPerMap;
    @Autowired
    private ISysPermissionItemService iSysPermissionItemService;

    @Override
    public boolean saveByRoleId(String roleId, List<PermissionItemDTO> permissionItem) {
        //----开始前先删除改角色菜单使用下面新的菜单设置了主外键级联删除 删除菜单授权item自定删除-----
        this.removeByIds(permissionItem.stream().map(d -> d.getSysPermissionId()).collect(Collectors.toList()));
        //----保存新菜单信息--------------------------
        List<SysPermission> sysPers = new ArrayList<>();
        List<SysPermissionItem> sysPermissionItemList = new ArrayList<>();
        permissionItem.forEach(x -> {
            SysPermission sysPer = new SysPermission();
            String guid = StringUtils.getGUID();
            sysPer.setId(guid);
            sysPer.setRoleId(roleId);
            sysPer.setResourceId(x.getSysPermissionId());
            if (x.getItemIds() != null && x.getItemIds().size() > 0) {
                x.getItemIds().forEach(d -> {
                    SysPermissionItem sysPermissionItem = new SysPermissionItem();
                    sysPermissionItem.setPermissionId(sysPer.getId());
                    sysPermissionItem.setId(StringUtils.getGUID());
                    sysPermissionItem.setResourceItemId(d);
                    sysPermissionItemList.add(sysPermissionItem);
                });
            }
            sysPers.add(sysPer);
        });

        this.saveOrUpdateBatch(sysPers);
        iSysPermissionItemService.saveBatch(sysPermissionItemList);
        return true;
    }

    @Override
    public List<ConcurrentHashMap<String, Object>> listByRoleId(String roleId) {

        List<SysResourceDTO> resourceIds = sysPerMap.resourceIdByRoleId(roleId);
        List<ConcurrentHashMap<String, Object>> list = MapTree.CreateTree(resourceIds);
        return list;
    }
}
