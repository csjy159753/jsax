package com.jinhe.modules.sys.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jinhe.modules.sys.dto.SysOrganAddDTO;
import com.jinhe.modules.sys.dto.SysOrganDTO;
import com.jinhe.modules.system.entity.SysOrgan;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 组织机构 Mapper 接口
 * </p>
 *
 * @author rls
 * @since 2020-10-29
 */
public interface SysOrganMapper extends BaseMapper<SysOrgan> {

    IPage<SysOrganAddDTO> selectOrganByOrganId(Page page, Integer type, String organId);
}
