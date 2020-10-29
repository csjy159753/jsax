package com.jinhe.modules.system.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jinhe.common.util.ListSub;
import com.jinhe.common.util.Tree.TreeNode;
import com.jinhe.modules.system.dto.SysResourceDTO;
import com.jinhe.modules.system.entity.SysResource;

import java.util.List;


/**
 * <p>
 * 服务类
 * </p>
 *
 * @author rls
 * @since 2020-04-13
 */
public interface ISysResourceService extends IService<SysResource> {

    ListSub selectSysResourcepage(String userid);

    SysResourceDTO Select_SysRespagebyid(String ID);

    boolean sysresourcesave(SysResourceDTO sysresdto);

    List<TreeNode> SysResourceTree(Page page);

    Integer DeleteResourceByid(String id);

    Integer UpdateOranByid(SysResourceDTO dto);

    Integer modify(SysResource sysRes, String id);
}
