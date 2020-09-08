package com.jinhe.modules.system.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jinhe.modules.system.dto.SysOrganDto;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jinhe.modules.system.entity.SysOrgan;
import com.jinhe.modules.system.entity.SysOrganNew;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author rls
 * @since 2020-04-16
 */
public interface SysOrganMapper extends BaseMapper<SysOrgan> {
    IPage<SysOrgan> selectPageOrgan(IPage<SysOrgan> page);


    //根据userId查询
    List<SysOrgan> SelectOrgansByUserId(String userId, Page page);

    Integer updateCustom(@Param("sysOrgan") SysOrganDto sysOrgan);

    Integer deleteByPer(@Param("userId") String userId, @Param("organId") String organId);

    Integer deleteByUser(@Param("userId") String userId, @Param("organId") String organId);

    List<SysOrganNew> selectOrganIdsByIds(@Param("organIds") List<String> organIds);

    IPage<SysOrganDto> selectOrgan(Page<SysOrganDto> page, String code);

    IPage<SysOrganDto> selectOrganByUserId(Page<SysOrganDto> page, @Param("userId") String userId);

    List<SysOrganDto> selectOrganByUserIdAndorganId(@Param("userId") String userId, @Param("organId") String organId);

    List<SysOrganDto> selectOrganByorganId(String organId);

    Integer selectCountByPreantId(@Param("userId") String userId, @Param("organId") String organId);

    List<SysOrgan> GetOrganSubset(@Param("organIds") List<String> organIds,Integer startDepth,Integer endDepth);

  /* //获取当前机构的子集
   List<SysOrgan> GetOrganSubset(@Param("id")List<String> id);*/

}
