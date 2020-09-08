package com.jinhe.modules.system.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jinhe.modules.system.dto.SysResourceItemDto;
import com.jinhe.modules.system.entity.SysResourceItem;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author rls
 * @since 2020-05-19
 */
public interface SysResourceItemMapper extends BaseMapper<SysResourceItem> {

    IPage<SysResourceItemDto> getListById(Page<SysResourceItemDto> page, @Param("id") String id);

    Integer add(@Param("sysRes") SysResourceItem sysRes);

    Integer modify(@Param("sysRes") SysResourceItem sysRes, @Param("id") String id);

    SysResourceItem select(@Param("id") String id);
}
