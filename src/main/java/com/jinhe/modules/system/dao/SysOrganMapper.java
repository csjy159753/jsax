package com.jinhe.modules.system.dao;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jinhe.modules.system.dto.SysOrganDto;
import com.jinhe.modules.system.entity.SysOrgan;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author rls
 * @since 2020-04-16
 */
public interface SysOrganMapper extends BaseMapper<SysOrgan> {
   Page<SysOrgan> selectPageOrgan(Page page);

   SysOrganDto selectPageOrganByID(String ID);

   List<SysOrganDto> selectlistparent(Map map);

}
