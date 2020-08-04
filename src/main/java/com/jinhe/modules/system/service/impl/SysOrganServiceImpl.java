package com.jinhe.modules.system.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jinhe.common.util.Mapper;
import com.jinhe.modules.system.dao.SysUserMapper;
import com.jinhe.modules.system.dto.*;
import com.jinhe.modules.system.dao.SysOrganMapper;
import com.jinhe.modules.system.entity.SysOrgan;
import com.jinhe.modules.system.entity.SysUser;
import com.jinhe.modules.system.service.ISysOrganService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author rls
 * @since 2020-04-16
 */
@Service
public class SysOrganServiceImpl extends ServiceImpl<SysOrganMapper, SysOrgan> implements ISysOrganService {
    @Autowired
    private SysOrganMapper sysOrganMapper;
    @Resource
    private SysUserMapper sysUserMapper;


    @Override
    public boolean addsysorgan(SysOrganDto sysorg) {
        boolean flags = false;
        SysOrgan sysorg1 = new SysOrgan();
        Mapper.MapToModel(sysorg, sysorg1);
        flags = this.save(sysorg1);
        return flags;
    }


    @Override
    public IPage<HashMap<String, Object>> SelectOrgan(Page<SysOrganDto> page, String userId, String organId) {
        // 根据父机构查询子机构
        SysUser sysUser = sysUserMapper.selectById(userId);
        if (sysUser == null) {
            return null;
        }
        return sysOrganMapper.selectOrgan(page, organId);
    }
   /* @Override
    public List<SysOrgan> GetOrganSubset(List<String> id) {
        List<SysOrgan> organlist = sysOrgan.GetOrganSubset(id);
        return organlist;
    }*/
    @Override
    @Transactional
    public Integer DeleteOrganByOrganId(String userId, String organId) {
        Integer flags = 0;
        try {
            sysOrganMapper.deleteByPer(userId, organId);
            sysOrganMapper.deleteByUser(userId, organId);
            flags = sysOrganMapper.deleteById(organId);
            return flags;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public Integer UpdateOranByid(SysOrganDto dto) {
        SysOrgan sysOrgan = this.sysOrganMapper.selectById(dto.getId());
        Integer flags = sysOrgan == null ? -1 : this.sysOrganMapper.updateCustom(dto);
        return flags;
    }

    public Integer insert(SysOrgan organ) {
        int insert = sysOrganMapper.insert(organ);
        return insert;
    }

    //根据userId查询
    public List<SysOrgan> SelectOrgansByUserId(String userId, Page page) {
        SysUser sysUser = sysUserMapper.selectById(userId);
        Integer type = sysUser.getType();
        if (type == null || type == 99) {
            List<SysOrgan> sysOrgans = new ArrayList<>();
            return sysOrgans;
        } else {
            List<SysOrgan> sysOrgans = sysOrganMapper.SelectOrgansByUserId(userId, page);
            return sysOrgans;
        }
    }

    @Override
    public List<SysOrgan> GetOrganSubset(List<String> organIds) {
        return sysOrganMapper.GetOrganSubset(organIds);
    }

}
