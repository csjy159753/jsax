package com.jinhe.modules.sys.service;

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
     *根据机构查询用户信息
     * @return
     */
    List<SysUserDTO> listByOrganId(String organId);
}
