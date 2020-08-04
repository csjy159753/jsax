package com.jinhe.modules.system.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jinhe.modules.system.dto.SysRegionDto;
import com.jinhe.modules.system.entity.SysRegion;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author rls
 * @since 2020-04-16
 */
@Mapper
public interface SysRegionMapper extends BaseMapper<SysRegion> {

    //查询行政区列表
    List<SysRegionDto> selectRegionList(@Param("code") String code);

    Integer selectLevelInfo(@Param("parentCode") String parentCode);

    Integer updateByRegionId(@Param("sysRegion") SysRegion sysRegion, @Param("id") String id);

    Integer delRegion(String id);

    Integer getItemCount(String id);

    Integer updateParentCodeBycode(@Param("code") String code, @Param("newCode") String newCode);

    String selectParentCode(String id);
}
