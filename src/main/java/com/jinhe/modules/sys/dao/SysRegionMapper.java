package com.jinhe.modules.sys.dao;

import com.jinhe.modules.sys.dto.SysRegionDTO;
import com.jinhe.modules.system.entity.SysRegion;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 行政区编码 Mapper 接口
 * </p>
 *
 * @author rls
 * @since 2020-10-29
 */
public interface SysRegionMapper extends BaseMapper<SysRegion> {

    /**
     * 根据行政区编码查询行政区划
     *
     * @param code
     * @return
     */
    List<SysRegionDTO> listRegionCode(String code);
}
