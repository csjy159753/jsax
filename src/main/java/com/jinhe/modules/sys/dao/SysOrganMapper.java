package com.jinhe.modules.sys.dao;

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

    /**
     * 根据机构id查询子机构
     * @param userId
     * @return
     */
    List<SysOrganDTO> selectOrganByOrganId(String userId);
}
