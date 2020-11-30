package com.jinhe.modules.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import com.jinhe.modules.system.dao.SysAppResourceMapper
import com.jinhe.modules.system.entity.SysAppResource
import com.jinhe.modules.system.service.ISysAppResourceService
import org.springframework.stereotype.Service

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author rls
 * @since 2020-11-11
 */
@Service
open class SysAppResourceServiceImpl : ServiceImpl<SysAppResourceMapper, SysAppResource>(), ISysAppResourceService {

}
