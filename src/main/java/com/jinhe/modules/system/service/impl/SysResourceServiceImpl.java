package com.jinhe.modules.system.service.impl;

import com.jinhe.config.LongSwingConstants;
import com.jinhe.modules.sys.dao.SysUserMapper;
import com.jinhe.modules.system.dto.SysResourceDTO;
import com.jinhe.modules.system.entity.SysResource;
import com.jinhe.modules.system.dao.SysResourceMapper;
import com.jinhe.modules.system.entity.SysUser;
import com.jinhe.modules.system.service.ISysResourceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    public List<SysResourceDTO> selectPageAll() {
        return sysResourceMapper.selectPageAll();
    }

    @Override
    public List<SysResourceDTO> listResource(String userId) {
        List<SysResourceDTO> list = new ArrayList<>();
        SysUser sysUser = sysUserMapper.selectById(userId);
        if (sysUser == null) {
            return null;
        }

        Integer type = sysUser.getType();

        if (type.equals(LongSwingConstants.USER_TYPE_ROOT_ADMIN)) {
            List<Integer> li = new ArrayList<>();
            li.add(2);
            list = sysResourceMapper.listResourceAdmin(li);
        } else if (type.equals(LongSwingConstants.USER_TYPE_ADMIN)) {
            List<Integer> li = new ArrayList<>();
            li.add(1);
            li.add(3);
            list = sysResourceMapper.listResourceAdmin(li);
        } else {
            list = sysResourceMapper.listResource(userId);
        }


        return list;
    }

    @Override
    public List<SysResourceDTO> listResourceAdmin() {
        List<SysResourceDTO> listtree = new ArrayList<>();
        List<Integer> li = new ArrayList<>();
        li.add(1);
        listtree = sysResourceMapper.listResourceAdmin(li);
        return listtree;
    }

    @Override
    public List<SysResourceDTO> listByRole(String roleId) {
        List<SysResourceDTO> list = new ArrayList<>();
        list = sysResourceMapper.listByRole(roleId);
        return list;
    }
}
