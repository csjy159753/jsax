package com.jinhe.modules.system.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.jinhe.common.util.Tree.TreeNode;
import com.jinhe.modules.system.dto.SysRegion;
import com.jinhe.modules.system.dto.SysRole;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author rls
 * @since 2020-04-16
 */
public interface ISysRegionService extends IService<SysRegion> {

    //查询行政区列表
    List<TreeNode> selectRegionList(HashMap map);

    //新增行政区
    void addRegion(SysRegion sysRegion);

    //更新行政区
    void updateRegion(SysRegion sysRegion);

    //删除行政区
    void deleteRegion(String regionId);

}
