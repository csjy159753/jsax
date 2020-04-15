package com.jinhe.modules.system.service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jinhe.modules.system.dto.SysRole;


import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author rls
 * @since 2020-04-14
 */
public interface ISysRoleService extends IService<SysRole> {

    //查询角色列表
    IPage<SysRole> roleList(Page<SysRole> page, SysRole sysRole);

    //新增角色
    void addRole(SysRole sysRole);

    //更新角色
    void updateRole(SysRole sysRole);

    //删除角色
    void deleteRole(String userId);
}
