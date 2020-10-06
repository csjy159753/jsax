package com.jinhe.service;

import com.jinhe.datasources.DataSourceNames;
import com.jinhe.datasources.annotation.DataSource;

import com.jinhe.modules.system.entity.SysLog;
import com.jinhe.modules.system.service.ISysLog2Service;
import com.jinhe.modules.system.service.ISysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * 测试多数据源
 */
@Service
public class DataSourceTestService {
    @Autowired
    private ISysLogService sysLogService;
    @Autowired
    private ISysLog2Service sysLogService2;

    public SysLog queryLog(Long LogId) {
        return sysLogService.getById(LogId);
    }

    @DataSource(name = DataSourceNames.SECOND)
    public SysLog queryLog2(Long LogId) {
        return sysLogService.getById(LogId);
    }


    @Transactional
//    @DataSource(name = DataSourceNames.FIRST)
    public boolean insertLog1(SysLog log) {
        return sysLogService.save(log);
    }


    @Transactional
    @DataSource(name = DataSourceNames.SECOND)
    public boolean insertLog2(SysLog log) {
        sysLogService.save(log);
        double gg = 2 / 0;
        return true;
    }

    //	@Transactional
    public boolean insertLog3(SysLog log) throws Exception {
        sysLogService.save(log);
//        double gg = 2 / 0;
        return true;
    }

//    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public boolean insertLog4(SysLog log) throws Exception {
        save1(log);
        save2(log);
//        double gg = 2 / 0;
        return true;
    }

    private void   save1(SysLog log){
        sysLogService.save(log);
    }
    @DataSource(name = DataSourceNames.SECOND)
    private void   save2(SysLog log){
        sysLogService2.save(log);
    }
}
