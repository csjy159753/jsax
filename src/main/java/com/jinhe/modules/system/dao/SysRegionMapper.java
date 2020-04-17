package com.jinhe.modules.system.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jinhe.modules.system.dto.SysRegion;
import com.jinhe.modules.system.dto.SysRole;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author rls
 * @since 2020-04-16
 */
@Mapper
public interface SysRegionMapper extends BaseMapper<SysRegion> {

    //查询行政区列表
    List<SysRegion> selectRegionList(HashMap map);
}
