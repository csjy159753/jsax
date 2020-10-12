package com.jinhe.modules.demo.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jinhe.modules.demo.dto.DStudentDto;
import com.jinhe.modules.demo.entity.DStudent;
import com.jinhe.modules.demo.dao.DStudentMapper;
import com.jinhe.modules.demo.service.IDStudentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jinhe.modules.system.entity.SysLog;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author rls
 * @since 2020-10-11
 */
@Service
public class DStudentServiceImpl extends ServiceImpl<DStudentMapper, DStudent> implements IDStudentService {

    @Autowired
    private DStudentMapper dStudentMapper;

    @Override
    public IPage<DStudent> getlistbyName(Page page, String name) {
        return dStudentMapper.getListbyName(page,name);
    }

    @Override
    public IPage<DStudentDto> listDly(Page page, String name) {
        return dStudentMapper.getListDly1(page,name);
    }

    @Override
    public IPage<DStudentDto> getListDly2(Page page, List<String> ids) {
        return dStudentMapper.getListDly2(page,ids);
    }

    @Override
    public IPage<DStudentDto> getListScore(Page page, int score) {
        return dStudentMapper.getListScore(page,score);
    }
}
