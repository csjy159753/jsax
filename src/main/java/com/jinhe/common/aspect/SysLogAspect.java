package com.jinhe.common.aspect;
import com.alibaba.fastjson.JSON;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;

/**
 * 系统日志，切面处理类
 */
@Aspect
@Component
public class SysLogAspect {
	//记录器
	Logger logger = LoggerFactory.getLogger(getClass());


	@Autowired(required=false)
	HttpServletRequest request;

	@Pointcut("@annotation(com.jinhe.common.annotation.SysLog)")
	public void logPointCut() {

	}

	@Around("logPointCut()")
	public Object around(ProceedingJoinPoint point) throws Throwable {
		logger.info("======================================================");
		long beginTime = System.currentTimeMillis();
		// 执行方法
		Object result = point.proceed();
		// 执行时长(毫秒)
		long time = System.currentTimeMillis() - beginTime;

		// 保存日志
		saveSysLog(point, time);
		logger.info("======================================================");
		return result;
	}

	private void saveSysLog(ProceedingJoinPoint joinPoint, long time) {
		MethodSignature signature = (MethodSignature) joinPoint.getSignature();

		// 请求的方法名
		String className = joinPoint.getTarget().getClass().getName();
		String methodName = signature.getName();
		logger.info(className + "." + methodName + "()");

		//请求地址
		String remoteAddr=request.getRemoteAddr();//请求的IP
		logger.info(remoteAddr);
		String method=request.getMethod();        //请求的方法类型(post/get)
		logger.info(method);

		// 请求的参数
		Object[] args = joinPoint.getArgs();
		try {
			String params = JSON.toJSONString(args);
			logger.info(params);
		} catch (Exception e) {

		}

	}
}
