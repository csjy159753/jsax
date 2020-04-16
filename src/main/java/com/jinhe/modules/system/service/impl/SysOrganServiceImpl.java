package com.jinhe.modules.system.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jinhe.modules.system.entity.SysOrgan;
import com.jinhe.modules.system.dao.SysOrganMapper;
import com.jinhe.modules.system.service.ISysOrganService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author rls
 * @since 2020-04-16
 */
@Service
public class SysOrganServiceImpl extends ServiceImpl<SysOrganMapper, SysOrgan> implements ISysOrganService {
    @Autowired
    private  SysOrganMapper sysorgmap;

    @Override
    public Page<SysOrgan> selectSysOrganpage(Page page) {

       Page<SysOrgan> sysorgls =  sysorgmap.selectPage(page);


        return sysorgls;
    }
}
