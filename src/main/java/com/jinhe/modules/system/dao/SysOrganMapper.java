package com.jinhe.modules.system.dao;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jinhe.modules.system.dto.SysOrganDto;
import com.jinhe.modules.system.entity.SysOrgan;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author rls
 * @since 2020-04-16
 */
public interface SysOrganMapper extends BaseMapper<SysOrgan> {
   Page<SysOrgan> selectPage(Page page);
}
