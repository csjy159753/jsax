package com.jinhe.modules.system.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jinhe.common.util.ResultEnum;
import com.jinhe.modules.system.dto.SysUserDto;
import com.jinhe.modules.system.entity.SysUser;
import org.apache.ibatis.annotations.*;
import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author rls
 * @since 2020-03-12
 */
@Mapper
public interface SysUserMapper extends BaseMapper<SysUser> {
    //删除用户
    void deleteUserById(String userId);

    IPage<SysUserDto> selectUserByOrganIdRole(Page<SysUserDto> page, @Param("organId") String organIds, @Param("roleId") String roleId, @Param("state") Integer state, @Param("normalizedUserName") String normalizedUserName);


}
