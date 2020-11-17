package com.jinhe.modules.system.dao;

import com.jinhe.modules.system.dto.SysResourceDTO;
import com.jinhe.modules.system.entity.SysResource;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 资源菜单 Mapper 接口
 * </p>
 *
 * @author rls
 * @since 2020-10-29
 */
public interface SysResourceMapper extends BaseMapper<SysResource> {

    /**
     * 查询所有资源菜单
     * @return
     */
    List<SysResourceDTO> selectPageAll();

    /**
     * 根据用户查询资源菜单
     * @param userId
     * @return
     */
    List<SysResourceDTO> listResource(String userId);
}
