package com.jinhe.modules.system.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jinhe.modules.system.dto.SysUserDto;
import com.jinhe.modules.system.dto.SysUserVo;
import com.jinhe.modules.system.entity.SysUser;
import javafx.scene.control.Pagination;
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

    IPage<SysUserVo> selectPageVo(Page<SysUserVo> page, SysUserVo sysUserVo);
}
