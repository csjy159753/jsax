package com.jinhe.modules.sys.service.impl;

import com.jinhe.config.ResultEnum;
import com.jinhe.modules.sys.dto.SysOrganDTO;
import com.jinhe.modules.system.entity.Dictionary;
import com.jinhe.modules.system.entity.SysOrgan;
import com.jinhe.modules.sys.dao.SysOrganMapper;
import com.jinhe.modules.sys.service.ISysOrganService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 组织机构 服务实现类
 * </p>
 *
 * @author rls
 * @since 2020-10-29
 */
@Service
public class SysOrganServiceImpl extends ServiceImpl<SysOrganMapper, SysOrgan> implements ISysOrganService {

    @Autowired
    private SysOrganMapper sysOrganMapper;

    @Override
    public List<SysOrganDTO> selectOrganByOrganId(String organId, Integer type) {
        return sysOrganMapper.selectOrganByOrganId(organId, type);
    }

    @Override
    public Integer getChildrenNum(String id) {
        return null;
    }

    @Override
    public ResultEnum saveOrUpdateChildrenNumAndLevel(String id) {
        return null;
    }

    @Override
    public ResultEnum saveOrUpdateChildrenNumAndLevel(Dictionary dictionary) {
        return null;
    }
}
