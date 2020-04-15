package com.jinhe.modules.system.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.jinhe.modules.system.dto.SysRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author rls
 * @since 2020-04-14
 */
@Mapper
public interface SysRoleMapper extends BaseMapper<SysRole> {

    @Select({"select * from sys_role"})
    List<SysRole> roleList();

}
