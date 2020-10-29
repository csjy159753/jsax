package com.jinhe.modules.system.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jinhe.modules.system.dto.SysRoleDTO;
import com.jinhe.modules.system.entity.SysRole;
import com.jinhe.modules.system.entity.SysUserRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;


/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author rls
 * @since 2020-04-14
 */
@Mapper
public interface SysRoleMapper extends BaseMapper<SysRole> {

    //查询父角色列表
    IPage<HashMap<String,Object>> selectPreantRoleList(Page<SysRoleDTO> page, String code, int type);

    //查询子角色列表
    List<SysRoleDTO> selectRoleList(@Param("roleId") String roleId);

    //删除角色
    int deleteByRoleId(String id);

    //根据userID查询
    IPage<SysRoleDTO> selectRolesByUserId(Page<SysRoleDTO> page, @Param("userId") String userId);

    //查询该角色下是否有子角色
    List<SysRole> selectRoleByParentId(String id);

    //根据userId查询
    List<SysUserRole> selectByUserIds(List<String> ids);
}
