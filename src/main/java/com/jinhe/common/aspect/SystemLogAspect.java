package com.jinhe.common.aspect;

import com.google.gson.Gson;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 系统日志，切面处理类
 */
@Aspect
@Component
public class SystemLogAspect {
	//记录器
	Logger logger = LoggerFactory.getLogger(getClass());



	/**
   * Controller层切点 注解拦截 （"execution（方法返回值类型   包名.类名.方法名（参数类型））"）
   */
	@Pointcut("execution(* com.jinhe.api..*Controller.*(..))")
	public void controllerAspect(){}

	@Around("controllerAspect()")
	public Object around(ProceedingJoinPoint jp) throws Throwable {
		logger.info("----------------------------------------------------------");
		long beginTime = System.currentTimeMillis();
		// 执行方法
		Object result = jp.proceed();
		// 执行时长(毫秒)
		long time = System.currentTimeMillis() - beginTime;

		logger.info("方法耗时:"+time);
		logger.info("----------------------------------------------------------");
		return result;
	}

	/**
	 * 请求的方法名
	 * @param jp
	 */
	public String getRemotePath(JoinPoint jp){
		String className = jp.getTarget().getClass().getName();
		MethodSignature signature = (MethodSignature) jp.getSignature();
		String methodName = signature.getName();
		logger.info(className + "." + methodName + "()");
		return  className + "." + methodName + "()";
	}


	/**
	 * 请求的方法名
	 * @param jp
	 */
	public String getResParams(JoinPoint jp){
		// 请求的参数
		Object[] args = jp.getArgs();
		try {
			String params = new Gson().toJson(args);
			logger.info(params);
			return  params;
		} catch (Exception e) {}
		return null;
	}


	/**
	 * 后置异常通知
	 */
	@AfterThrowing("controllerAspect()")
	public void throwss(JoinPoint jp){
		logger.info("方法异常时执行.....");
	}


	/**
	 * 后置最终通知,final增强，不管是抛出异常或者正常退出都会执行
	 */
	@After("controllerAspect()")
	public void after(JoinPoint jp){
		logger.info("方法最后执行.....");
	}



	/**
	 * 处理完请求，返回内容
	 * @param ret
	 */
	@AfterReturning(returning = "ret", pointcut = "controllerAspect()")
	public void doAfterReturning(Object ret) {
		logger.info("方法的返回值 : " + ret);
	}

}
