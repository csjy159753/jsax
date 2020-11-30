package com.jinhe.modules.sys.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jinhe.modules.sys.dao.SysOrganMapper;
import com.jinhe.modules.sys.dto.SysOrganAddDTO;
import com.jinhe.modules.system.entity.SysOrganRole;
import com.jinhe.modules.sys.dao.SysOrganRoleMapper;
import com.jinhe.modules.sys.service.ISysOrganRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 组织结构关联角色 服务实现类
 * </p>
 *
 * @author rls
 * @since 2020-10-29
 */
@Service
public class SysOrganRoleServiceImpl extends ServiceImpl<SysOrganRoleMapper, SysOrganRole> implements ISysOrganRoleService {
    @Autowired
    private SysOrganMapper sysOrganMapper;

    @Override
    public IPage<SysOrganAddDTO> selectOrganByOrganId(Page page, Integer type, String organId) {
        return sysOrganMapper.selectOrganByOrganId(page, type, organId);
    }
}
