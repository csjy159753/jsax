package com.jinhe.modules.system.service.impl;

import com.jinhe.modules.system.entity.SysAppPermission;
import com.jinhe.modules.system.dao.SysAppPermissionMapper;
import com.jinhe.modules.system.service.ISysAppPermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色授权 服务实现类
 * </p>
 *
 * @author rls
 * @since 2020-11-11
 */
@Service
open class SysAppPermissionServiceImpl : ServiceImpl<SysAppPermissionMapper, SysAppPermission>(), ISysAppPermissionService {

}
