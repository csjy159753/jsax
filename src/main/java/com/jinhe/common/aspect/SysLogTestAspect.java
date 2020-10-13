package com.jinhe.common.aspect;

import com.alibaba.fastjson.JSON;
import com.jinhe.common.annotation.SysLogTest;
import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * 系统日志，切面处理类
 */
@Aspect
@Component
public class SysLogTestAspect {
    //记录器
    Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private AmqpTemplate rabbitTemplate;

    @Autowired(required = false)
    HttpServletRequest request;

    @Pointcut("@annotation(com.jinhe.common.annotation.SysLogTest)")
    public void logPointCut() {

    }

    @Around("logPointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        logger.info("======================================================");
        long beginTime = System.currentTimeMillis();
        // 执行方法
        Object objs = point.getArgs();
        Object result = point.proceed();
        // 执行时长(毫秒)
        long time = System.currentTimeMillis() - beginTime;

        // 保存日志
        saveSysLog(point, time);
        logger.info("======================================================");


        return result;
    }

    private void saveSysLog(ProceedingJoinPoint joinPoint, long time) {


        try {
            //1.获取到所有的参数值的数组
            Object[] args = joinPoint.getArgs();
            Signature signature = joinPoint.getSignature();
            MethodSignature methodSignature = (MethodSignature) signature;
            //2.获取到方法的所有参数名称的字符串数组
            String[] parameterNames = methodSignature.getParameterNames();
            Method method = methodSignature.getMethod();
            System.out.println("---------------参数列表开始-------------------------");
            List<String> liststr=new ArrayList<>();
            liststr.add("测试消息队列");
            for (int i = 0, len = parameterNames.length; i < len; i++) {
                System.out.println("参数名：" + parameterNames[i] + " = " + args[i]);
                liststr.add("参数名：" + parameterNames[i] + " = " + args[i]);
            }
            System.out.println("---------------参数列表结束-------------------------");
            SysLogTest sysLog = (SysLogTest) method.getAnnotation(SysLogTest.class);
            System.out.println("自定义注解 key:" + sysLog.value());
            Class cla = method.getClass();
            this.rabbitTemplate.convertAndSend("exchange", "topic.messages", StringUtils.join(liststr, ","));

            if (cla.isAnnotationPresent(SysLogTest.class)) {
                SysLogTest redisHandel = (SysLogTest) cla.getAnnotation(SysLogTest.class);
                String key = redisHandel.value();
                System.out.println("key = " + key);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        MethodSignature signature = (MethodSignature) joinPoint.getSignature();

        // 请求的方法名
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = signature.getName();
        logger.info(className + "." + methodName + "()");

        //请求地址
        String remoteAddr = request.getRemoteAddr();//请求的IP
        logger.info(remoteAddr);
        String method = request.getMethod();        //请求的方法类型(post/get)
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
