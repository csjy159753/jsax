package com.jinhe.modules.system.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jinhe.modules.system.entity.SysUserRole;
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
public interface SysUserRoleMapper extends BaseMapper<SysUserRole> {
    //新增
    int insertList(@Param("list") List<SysUserRole> list);

    List<SysUserRole> selectByRoleId(String id);

    int deleteByUserId(String userId);

    int deleteByUserIdAndRoleId(String userId, String organId);
}
