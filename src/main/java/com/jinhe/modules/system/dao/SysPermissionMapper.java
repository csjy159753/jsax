package com.jinhe.modules.system.dao;

import com.jinhe.modules.system.dto.SysResourceDto;
import com.jinhe.modules.system.entity.SysPermission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author rls
 * @since 2020-05-19
 */
public interface SysPermissionMapper extends BaseMapper<SysPermission> {

    boolean addByUserId(List<SysPermission> sysPers);

    boolean addByOrganId(List<SysPermission> sysPer);

    boolean addByRoleId(List<SysPermission> sysPer);

    List<SysResourceDto> listByRoleId(List<String> item);

    boolean add(@Param("sysPer") SysPermission sysPer);

    SysPermission getById(String id);

    List<SysResourceDto> resourceIdByRoleId(String roleId);

    List<String> resourceIdByOrganId(String orgionId);

    void deleteByRoleId(String roleId);

    void deleteByOrganId(String organId);
}
