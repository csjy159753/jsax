package com.jinhe.modules.system.dao;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.jinhe.modules.system.dto.SysUserDto;
import com.jinhe.modules.system.dto.SysUserVo;
import com.jinhe.modules.system.entity.SysUser;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author rls
 * @since 2020-03-12
 */
@Mapper
public interface SysUserMapper extends BaseMapper<SysUser> {
//    @Select({"select * from sys_user"})
//    List<SysUser> listAllrls();
//
//    @Select({"select ID,NORMALIZED_USERNAME, 'aaa' as kk_aa  from sys_user"})
//    List<SysUserDto> listDemo();

    List<SysUserVo> selectPageVo(Pagination pagination, SysUserVo sysUserVo);
}
