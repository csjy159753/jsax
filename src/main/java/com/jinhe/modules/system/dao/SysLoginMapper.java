package com.jinhe.modules.system.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jinhe.modules.system.dto.SysLogin;
import com.jinhe.modules.system.dto.SysLoginDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author swf
 * @since 2020-05-15
 */
@Mapper
public interface SysLoginMapper extends BaseMapper<SysLogin> {
    SysLoginDto selectUserByLogin(@Param("login")SysLogin login);
}
