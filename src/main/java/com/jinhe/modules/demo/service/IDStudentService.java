package com.jinhe.modules.demo.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jinhe.modules.demo.dto.DStudentDto;
import com.jinhe.modules.demo.entity.DStudent;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jinhe.modules.system.entity.SysLog;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author rls
 * @since 2020-10-11
 */
public interface IDStudentService extends IService<DStudent> {

    IPage<DStudent> getListbyName(Page page, String name);


    IPage<DStudentDto> listDly(Page page, String name);

    /**
     * @param page
     * @param name
     * @return
     */
    IPage<DStudentDto> getListDly1(Page page, String name);

    /**
     * 测试使用方式
     * @param page
     * @param ids
     * @return
     */
    IPage<DStudentDto> getListDly2(Page page, List<String> ids);

    IPage<DStudentDto> getListScore(Page page, int score);
}
