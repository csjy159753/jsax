package com.jinhe.modules.sys.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jinhe.modules.sys.dto.SysUserDTO;
import com.jinhe.modules.system.entity.SysUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 用户 Mapper 接口
 * </p>
 *
 * @author rls
 * @since 2020-10-29
 */
public interface SysUserMapper extends BaseMapper<SysUser> {

    /**
     * 用户信息查询
     *
     * @param page
     * @param organId
     * @param state
     * @param keyWord
     * @return
     */
    IPage<SysUserDTO> listByOrganId(Page page,  String organId,  Integer state,  String keyWord);
}
