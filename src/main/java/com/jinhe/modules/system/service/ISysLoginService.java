package com.jinhe.modules.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jinhe.modules.system.dto.SysLogin;
import com.jinhe.modules.system.dto.SysLoginDto;
import com.jinhe.modules.system.entity.SysUser;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author swf
 * @since 2020-05-15
 */
public interface ISysLoginService extends  IService<SysUser>  {
    SysLoginDto selectUserByLogin(SysLogin login);
}

