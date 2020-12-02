package com.jinhe.modules.sys.dao;

import com.jinhe.modules.sys.dto.SysUserDTO;
import com.jinhe.modules.system.entity.SysUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 用户 Mapper 接口
 * </p>
 *
 * @author rls
 * @since 2020-10-29
 */
public interface SysUserMapper extends BaseMapper<SysUser> {

    List<SysUserDTO> listByOrganId(String organId, Integer state);
}
