package com.jinhe.modules.system.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jinhe.modules.system.entity.SysUserOrgan;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author rls
 * @since 2020-04-16
 */
@Mapper
public interface SysUserOrganMapper extends BaseMapper<SysUserOrgan> {

    //新增
    int insertList(@Param("list") List<SysUserOrgan> list);

    //根据userId删除
    int deleteByUserId(String userId);
    int deleteByUserIdAndOrganId(String userId, String organId);
}
