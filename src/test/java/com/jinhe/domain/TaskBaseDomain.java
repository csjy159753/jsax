package com.jinhe.domain;

import com.jinhe.config.TaskBase;
import com.jinhe.modules.system.service.ISysLogService;

public class TaskBaseDomain extends TaskBase {
    private ISysLogService sysLogService;

    public TaskBaseDomain(String identifier, ISysLogService sysLogService) {
        super(identifier);
        this.sysLogService = sysLogService;
    }

    public void process() {
        sysLogService.getById("111");
        System.out.println("执行结束");
    }
}
