package com.jinhe.modules.system.service;

import com.jinhe.modules.system.entity.SysLoginLog;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 登录统计日志 服务类
 * </p>
 *
 * @author rls
 * @since 2020-10-29
 */
public interface ISysLoginLogService extends IService<SysLoginLog> {
    /**
     * 保存log登录信息
     *
     * @param userId 用户id
     * @param userName 用户名称
     * @param type 类型 0未知 1手机登录 2pc登录3微信登录4授权码登录
     * @param clientIp  客户端ip
     */
    void saveInfo(String userId, String userName, int type, String clientIp);
}
