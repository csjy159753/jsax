package com.jinhe.modules.system.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.jinhe.modules.system.dto.SysOrganDTO;
import com.jinhe.modules.system.entity.SysOrgan;
import com.jinhe.modules.system.dao.SysOrganMapper;
import com.jinhe.modules.system.service.ISysOrganService;
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
    public List<SysOrganDTO> selectOrganByOrganId(String organId) {
        return sysOrganMapper.selectOrganByOrganId(organId);
    }
}
