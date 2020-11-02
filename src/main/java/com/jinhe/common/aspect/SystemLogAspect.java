package com.jinhe.common.aspect;

import com.alibaba.fastjson.JSON;
import com.jinhe.common.annotation.SysLogTest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * 系统日志，切面处理类
 *
 * @author Administrator
 */
@Aspect
@Component
@Slf4j
public class SystemLogAspect {
    @Autowired
    private AmqpTemplate rabbitTemplate;
    @Autowired(required = false)
    HttpServletRequest request;

    /**
     *    * Controller层切点 注解拦截 （"execution（方法返回值类型   包名.类名.方法名（参数类型））"）
     *   
     */
    @Pointcut("execution(* com.jinhe..*Controller.*(..))")
    public void controllerAspect() {
    }

    @Around("controllerAspect()")
    public Object around(ProceedingJoinPoint jp) throws Throwable {
        log.info("----------------------------------------------------------");
        long beginTime = System.currentTimeMillis();
        // 执行方法
        Object result = jp.proceed();
        // 执行时长(毫秒)
        long time = System.currentTimeMillis() - beginTime;
        // 保存日志
        saveSysLog(jp, time);
        log.info("方法耗时:" + time);
        log.info("----------------------------------------------------------");
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
            System.out.println("--------SystemLogAspect-------参数列表开始-------------------------");
            List<String> liststr = new ArrayList<>();
            liststr.add("测试消息队列");
            for (int i = 0, len = parameterNames.length; i < len; i++) {
                System.out.println("参数名：" + parameterNames[i] + " = " + args[i]);
                liststr.add("参数名：" + parameterNames[i] + " = " + args[i]);
            }
            System.out.println("-------SystemLogAspect--------参数列表结束-------------------------");
            Class cla = method.getClass();
//            this.rabbitTemplate.convertAndSend("exchange", "topic.messages", StringUtils.join(liststr, ","));

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
        log.info(className + "." + methodName + "()");

        //请求地址
        /**
         * 请求的IP
         */
        String remoteAddr = request.getRemoteAddr();
        log.info(remoteAddr);
        /**
         * 请求的方法类型(post/get)
         */
        String method = request.getMethod();
        log.info(method);

        // 请求的参数
        Object[] args = joinPoint.getArgs();
        try {
            String params = JSON.toJSONString(args);
            log.info(params);
        } catch (Exception e) {

        }

    }

    /**
     * 请求的方法名
     *
     * @param jp
     */
    public String getRemotePath(JoinPoint jp) {
        String className = jp.getTarget().getClass().getName();
        MethodSignature signature = (MethodSignature) jp.getSignature();
        String methodName = signature.getName();
        log.info(className + "." + methodName + "()");
        return className + "." + methodName + "()";
    }


    /**
     * 请求的方法名
     *
     * @param jp
     */
    public String getResParams(JoinPoint jp) {
        // 请求的参数
        Object[] args = jp.getArgs();
        try {
            String params = JSON.toJSONString(args);
            log.info(params);
            return params;
        } catch (Exception e) {
        }
        return null;
    }


    /**
     * 后置异常通知
     */
    @AfterThrowing("controllerAspect()")
    public void throwss(JoinPoint jp) {
        log.info("方法异常时执行.....");
    }


    /**
     * 后置最终通知,final增强，不管是抛出异常或者正常退出都会执行
     */
    @After("controllerAspect()")
    public void after(JoinPoint jp) {
        log.info("方法最后执行.....");
    }


    /**
     * 处理完请求，返回内容
     *
     * @param ret
     */
    @AfterReturning(returning = "ret", pointcut = "controllerAspect()")
    public void doAfterReturning(Object ret) {
        log.info("方法的返回值 : " + ret);
    }

}
