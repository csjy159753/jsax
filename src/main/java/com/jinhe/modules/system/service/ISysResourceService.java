package com.jinhe.modules.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jinhe.common.util.ListSub;
import com.jinhe.common.util.Tree.TreeNode;
import com.jinhe.modules.system.dto.SysResourceDto;
import com.jinhe.modules.system.entity.SysResource;
import org.apache.ibatis.annotations.Param;

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

    SysResourceDto Select_SysRespagebyid(String ID);

    boolean sysresourcesave(SysResourceDto sysresdto);

    List<TreeNode> SysResourceTree(Page page);

    Integer DeleteResourceByid(String id);

    Integer UpdateOranByid(SysResourceDto dto);

    Integer modify(SysResource sysRes, String id);
}
