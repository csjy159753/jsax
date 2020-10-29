package com.jinhe.modules.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jinhe.common.util.ResultEnum;
import com.jinhe.common.util.Tree.TreeNode;
import com.jinhe.modules.system.dto.SysRoleDto;
import com.jinhe.modules.system.entity.IRoleTreeNode;
import com.jinhe.modules.system.entity.SysRole;


import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author rls
 * @since 2020-04-14
 */
public interface ISysRoleService extends IService<SysRole> {

    //查询父角色列表
    IPage<HashMap<String,Object>> selectRoleList(Page<SysRoleDto> page, String id,String roleId);

    //删除角色
    ResultEnum deleteRole(String id, String userId);

    //根据UserID查询
    List<SysRole> selectRolesByUserId(Page page, String userId);


}
