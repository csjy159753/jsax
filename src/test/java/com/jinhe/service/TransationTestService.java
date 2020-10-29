//package com.jinhe.service;
//
//import com.jinhe.datasources.DataSourceNames;
//import com.jinhe.datasources.annotation.DataSource;
//import com.jinhe.modules.system.entity.SysLog;
//import com.jinhe.modules.system.service.ISysLogService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//import org.springframework.transaction.interceptor.TransactionAspectSupport;
//
///**
// * 测试多数据源中单数据源添加事务
// */
//@Service
//public class TransationTestService {
//	@Autowired
//	private ISysLogService sysLogService;
//
//	public SysLog queryLog(Long LogId) {
//		return sysLogService.getById(LogId);
//	}
//
//	@Transactional
//	public boolean insertLog(SysLog log){
//		boolean insert = false;
//		try {
//			insert = sysLogService.save(log);
//			System.out.println("--------------------------------------------"+insert);
//			sysLogService.save(log);
//		} catch (Exception e) {
//			insert = false;
//			e.printStackTrace();
//			//System.out.println(e.getMessage());
//			//回滚,不影响事物正常执行
//			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
//		}
//		return insert;
//	}
//
//	@DataSource(name = DataSourceNames.SECOND)
//	@Transactional
//	public boolean insertLog2(SysLog log){
//		boolean insert = false;
//		try {
//			insert = sysLogService.save(log);
//			System.out.println("--------------------------------------------"+insert);
////			sysLogService.insert(log);
//		} catch (Exception e) {
//			insert = false;
//			e.printStackTrace();
//			//System.out.println(e.getMessage());
//			//回滚,不影响事物正常执行
//			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
//		}
//		return insert;
//	}
//}
