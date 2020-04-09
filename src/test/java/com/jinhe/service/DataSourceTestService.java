package com.jinhe.service;

import com.jinhe.datasources.DataSourceNames;
import com.jinhe.datasources.annotation.DataSource;

import com.jinhe.modules.system.entity.SysLog;
import com.jinhe.modules.system.entity.SysUser;
import com.jinhe.modules.system.service.ISysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 测试多数据源
 */
@Service
public class DataSourceTestService {
	@Autowired
	private ISysLogService sysLogService;

	public SysLog queryLog(Long LogId) {
		return sysLogService.selectById(LogId);
	}

	@DataSource(name = DataSourceNames.SECOND)
	public SysLog queryLog2(Long LogId) {
		return sysLogService.selectById(LogId);
	}


	public boolean insertLog1(SysLog log){
		return sysLogService.insert(log);
	}
	@DataSource(name = DataSourceNames.SECOND)
	public boolean insertLog2(SysLog log){
		return sysLogService.insert(log);
	}
	public boolean insertLog3(SysLog log){
		return sysLogService.insert(log);
	}
}
