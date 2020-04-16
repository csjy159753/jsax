package com.jinhe.modules.system.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jinhe.modules.system.dto.SysResourceDto;
import com.jinhe.modules.system.entity.SysResource;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;


/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author rls
 * @since 2020-04-13
 */
public interface SysResourceMapper extends BaseMapper<SysResource> {
   Page<SysResource> selectSysResourcepage(Page page);

   SysResourceDto selectSysResourceOne(String ID);

   List<SysResource> selectsysresTree(Map map);
}
