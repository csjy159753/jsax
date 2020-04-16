package com.jinhe.modules.system.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jinhe.modules.system.dto.SysOrganDto;
import com.jinhe.modules.system.entity.SysOrgan;
import com.jinhe.modules.system.dao.SysOrganMapper;
import com.jinhe.modules.system.service.ISysOrganService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public Page<SysOrgan> selectPageOrgan(Page page) {

       Page<SysOrgan> sysorgls =  sysorgmap.selectPageOrgan(page);


        return sysorgls;
    }

    @Override
    public SysOrganDto selectPageOrganByID(String ID) {

        SysOrganDto sysorgdto = new SysOrganDto();

        sysorgdto = sysorgmap.selectPageOrganByID(ID);

        return sysorgdto;
    }
}
