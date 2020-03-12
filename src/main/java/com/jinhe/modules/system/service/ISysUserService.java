package com.jinhe.modules.system.service;

import com.jinhe.common.utils.PageUtils;
import com.jinhe.modules.system.entity.SysUser;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author rls
 * @since 2020-03-12
 */
public interface ISysUserService extends IService<SysUser> {
    PageUtils queryPage(Map<String, Object> params);
    List<SysUser> listAllrls();
}
