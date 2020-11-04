package com.jinhe.modules.system.service.impl;

import com.jinhe.modules.system.entity.SysLoginLog;
import com.jinhe.modules.system.dao.SysLoginLogMapper;
import com.jinhe.modules.system.service.ISysLoginLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 * 登录统计日志 服务实现类
 * </p>
 *
 * @author rls
 * @since 2020-10-29
 */
@Service
public class SysLoginLogServiceImpl extends ServiceImpl<SysLoginLogMapper, SysLoginLog> implements ISysLoginLogService {

    @Override
    public void saveInfo(String userId, String userName, int type, String clientIp) {
        SysLoginLog sysLoginLog = new SysLoginLog();
        sysLoginLog.setUserId(userId);
        sysLoginLog.setUserName(userName);
        sysLoginLog.setType(type);
        sysLoginLog.setClientIp(clientIp);
        this.save(sysLoginLog);
    }

}
