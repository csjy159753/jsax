package com.jinhe.modules.sys.service.impl;

import com.jinhe.modules.sys.service.ISysUserService;
import com.jinhe.modules.system.entity.SysUser;
import com.jinhe.modules.sys.dao.SysUserMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

}
