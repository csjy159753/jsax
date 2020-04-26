package com.jinhe.modules.system.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jinhe.modules.system.dto.SysRole;
import org.apache.ibatis.annotations.Mapper;
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

       List<SysRole> selectRoleList(HashMap map);
}
