package com.jinhe.service;

import com.jinhe.datasources.DataSourceNames;
import com.jinhe.datasources.annotation.DataSource;
import com.jinhe.modules.sys.entity.SysLogEntity;
import com.jinhe.modules.sys.service.SysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 测试多数据源
 */
@Service
public class DataSourceTestService {
	@Autowired
	private SysLogService sysLogService;

	public SysLogEntity queryLog(Long LogId) {
		return sysLogService.selectById(LogId);
	}

	@DataSource(name = DataSourceNames.SECOND)
	public SysLogEntity queryLog2(Long LogId) {
		return sysLogService.selectById(LogId);
	}


	public boolean insertLog1(SysLogEntity log){
		return sysLogService.insert(log);
	}
	@DataSource(name = DataSourceNames.SECOND)
	public boolean insertLog2(SysLogEntity log){
		return sysLogService.insert(log);
	}
	public boolean insertLog3(SysLogEntity log){
		return sysLogService.insert(log);
	}
}
