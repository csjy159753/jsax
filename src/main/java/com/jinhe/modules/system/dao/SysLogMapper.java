package com.jinhe.modules.system.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jinhe.modules.system.dto.SysUserVo;
import com.jinhe.modules.system.entity.SysLog;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jinhe.modules.system.entity.SysRole;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author rls
 * @since 2020-04-15
 */
public interface SysLogMapper extends BaseMapper<SysLog> {

    IPage<SysLog> selectPageVo(Page<SysLog> page, SysLog sysUserVo);

    List<SysRole> selectSysRoleVo(HashMap map);
}
