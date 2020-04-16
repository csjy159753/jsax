package com.jinhe.modules.system.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jinhe.modules.system.dto.SysRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.HashMap;
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

    //查询角色列表
    @Select({"select * from sys_role"})
    List<SysRole> selectRoleList(HashMap map);
}
