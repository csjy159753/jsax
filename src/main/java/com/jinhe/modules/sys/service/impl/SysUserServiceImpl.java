package com.jinhe.modules.sys.service.impl;

import com.jinhe.modules.sys.dto.SysUserDTO;
import com.jinhe.modules.sys.service.ISysUserService;
import com.jinhe.modules.system.entity.SysUser;
import com.jinhe.modules.sys.dao.SysUserMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 用户 服务实现类
 * </p>
 *
 * @author rls
 * @since 2020-10-29
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    public List<SysUserDTO> listByOrganId(String organId) {
        return sysUserMapper.listByOrganId(organId);
    }
}
