package com.jinhe.modules.system.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jinhe.common.util.Tree.TreeNode;
import com.jinhe.modules.system.dto.SysOrganDto;
import com.jinhe.modules.system.entity.SysOrgan;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jinhe.modules.system.entity.SysResource;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author rls
 * @since 2020-04-16
 */
public interface ISysOrganService extends IService<SysOrgan> {
     Page<SysOrgan> selectPageOrgan(Page page);

     SysOrganDto selectPageOrganByID(String ID);

     boolean addsysorgan(SysOrganDto sysorg);

     List<TreeNode> SelectOrganParent(Page page);

     Integer DeleteOrganByid(String id);

     Integer UpdateOranByid(SysOrganDto dto);
}
