package com.jinhe.modules.system.service.impl;

import com.jinhe.common.util.Tree.MapTree;
import com.jinhe.modules.system.dto.SysResourceDTO;
import com.jinhe.modules.system.entity.SysResource;
import com.jinhe.modules.system.dao.SysResourceMapper;
import com.jinhe.modules.system.service.ISysResourceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 资源菜单 服务实现类
 * </p>
 *
 * @author rls
 * @since 2020-10-29
 */
@Service
public class SysResourceServiceImpl extends ServiceImpl<SysResourceMapper, SysResource> implements ISysResourceService {

    @Autowired
    private SysResourceMapper sysResourceMapper;

    @Override
    public List<SysResourceDTO> selectPageAll() {
        return sysResourceMapper.selectPageAll();
    }
}
