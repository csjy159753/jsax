package com.jinhe.modules.sys.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jinhe.modules.sys.dto.SysUserDTO;
import com.jinhe.modules.system.entity.SysUser;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 用户 服务类
 * </p>
 *
 * @author rls
 * @since 2020-10-29
 */
public interface ISysUserService extends IService<SysUser> {

    /**
     * 根据机构查询用户信息
     *
     * @return
     */
    IPage<SysUserDTO> listByOrganId(Page<SysUserDTO> page, String organId, int state, String keyWord);
}
