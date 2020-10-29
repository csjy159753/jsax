package com.jinhe.modules.system.service.impl;

import com.jinhe.modules.system.entity.SysLoginCount;
import com.jinhe.modules.system.dao.SysLoginCountMapper;
import com.jinhe.modules.system.service.ISysLoginCountService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 登录统计 服务实现类
 * </p>
 *
 * @author rls
 * @since 2020-10-29
 */
@Service
public class SysLoginCountServiceImpl extends ServiceImpl<SysLoginCountMapper, SysLoginCount> implements ISysLoginCountService {

}
