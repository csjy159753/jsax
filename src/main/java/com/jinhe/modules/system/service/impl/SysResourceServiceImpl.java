package com.jinhe.modules.system.service.impl;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jinhe.common.util.Mapper;
import com.jinhe.modules.system.dao.SysResourceMapper;
import com.jinhe.modules.system.dto.SysResourceDto;
import com.jinhe.modules.system.entity.SysResource;
import com.jinhe.modules.system.service.ISysResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xk
 * @since 2020-04-15
 */
@Service
public class SysResourceServiceImpl extends ServiceImpl<SysResourceMapper, SysResource> implements ISysResourceService {
    @Autowired
    private SysResourceMapper Sysresmapper;

    @Override
    public Page<SysResource> selectSysResourcepage(Page page) {
        Page<SysResource> pagedate = new Page<>();
        pagedate = Sysresmapper.selectSysResourcepage(page);

        return pagedate;
    }

    @Override
    public SysResourceDto Select_SysRespagebyid(String ID) {
        SysResourceDto sysresdto = new SysResourceDto();

        sysresdto = Sysresmapper.selectSysResourceOne(ID);
        return sysresdto;
    }

    @Override
    public boolean sysresourcesave(SysResourceDto sysresdto) {

        boolean flags = false;

        SysResource sysres1 = new SysResource();

        Mapper.MapToModel(sysresdto,sysres1);

        flags = this.save(sysres1);

        return flags;
    }
}
