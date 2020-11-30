package com.jinhe.modules.sys.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jinhe.modules.sys.dto.SysOrganAddDTO;
import com.jinhe.modules.sys.dto.SysOrganRoleDTO;
import com.jinhe.modules.system.entity.SysOrganRole;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 组织结构关联角色 服务类
 * </p>
 *
 * @author rls
 * @since 2020-10-29
 */
public interface ISysOrganRoleService extends IService<SysOrganRole> {

    /**
     * 查询用户信息
     *
     * @param page
     * @param type
     * @param organId
     * @return
     */
    IPage<SysOrganRoleDTO> selectOrganByOrganId(Page page, Integer type, String organId);
}
