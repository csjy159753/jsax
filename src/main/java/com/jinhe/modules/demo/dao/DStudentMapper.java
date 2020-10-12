package com.jinhe.modules.demo.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jinhe.modules.demo.dto.DStudentDto;
import com.jinhe.modules.demo.entity.DStudent;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jinhe.modules.system.entity.SysLog;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author rls
 * @since 2020-10-11
 */
public interface DStudentMapper extends BaseMapper<DStudent> {

    IPage<DStudent> getListbyName(Page page, String name);

    IPage<DStudentDto> ListDly(Page page, String name);
}
