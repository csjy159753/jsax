package com.jinhe.service;

import com.jinhe.datasources.DataSourceNames;
import com.jinhe.datasources.annotation.DataSource;
import com.jinhe.modules.sys.entity.SysLogEntity;
import com.jinhe.modules.sys.service.SysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

/**
 * 测试多数据源中单数据源添加事务
 */
@Service
public class TransationTestService {
	@Autowired
	private SysLogService sysLogService;

	public SysLogEntity queryLog(Long LogId) {
		return sysLogService.selectById(LogId);
	}

	@Transactional
	public boolean insertLog(SysLogEntity log){
		boolean insert = false;
		try {
			insert = sysLogService.insert(log);
			System.out.println("--------------------------------------------"+insert);
			sysLogService.insert(log);
		} catch (Exception e) {
			insert = false;
			e.printStackTrace();
			//System.out.println(e.getMessage());
			//回滚,不影响事物正常执行
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		return insert;
	}

	@DataSource(name = DataSourceNames.SECOND)
	@Transactional
	public boolean insertLog2(SysLogEntity log){
		boolean insert = false;
		try {
			insert = sysLogService.insert(log);
			System.out.println("--------------------------------------------"+insert);
			sysLogService.insert(log);
		} catch (Exception e) {
			insert = false;
			e.printStackTrace();
			//System.out.println(e.getMessage());
			//回滚,不影响事物正常执行
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		return insert;
	}
}
