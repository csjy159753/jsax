package com.jinhe.common.aspect;

import com.jinhe.common.annotation.DuplicateSubmitToken;
import com.jinhe.common.config.SystemType;
import com.jinhe.common.exception.DuplicateSubmitException;
import com.jinhe.config.ResultEnum;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @description 防止表单重复提交拦截器
 */
@Aspect
@Component
public class DuplicateSubmitAspect {
    public static final String DUPLICATE_TOKEN_KEY = "duplicate_token_key";
    public static final long INTERVAL = 5000;
    @Autowired(required = false)
    HttpServletRequest request;
    @Autowired(required = false)
    HttpServletResponse response;
    Logger log = LoggerFactory.getLogger(getClass());

    @Pointcut("execution(public * com.jinhe..*Controller.*(..))")
    public void webLog() {
    }

    //    @Around("webLog() && @annotation(token)")
    @Around("webLog()")
    public Object around(final ProceedingJoinPoint joinPoint) throws Throwable {

        Object[] args = joinPoint.getArgs();
        String uri = request.getRequestURI();
        if (request != null && request.getAttribute(SystemType.USER_ID) != null
                && (uri.toLowerCase().contains(SystemType.SAVE)
                || uri.toLowerCase().contains(SystemType.UPDATE)
                || uri.toLowerCase().contains(SystemType.REMOVE))
                && !uri.toLowerCase().contains(SystemType.SYS_OPERATOR_LOG)
        ) {
            String key = getDuplicateTokenKey(joinPoint);
            Object t = request.getSession().getAttribute(key);
            long i = INTERVAL;
            if (null != t) {
                i = System.currentTimeMillis() - (long) t;
            }
            if (i < INTERVAL) {
                throw new DuplicateSubmitException(ResultEnum.DUPLICATE_SUBMIT);
            } else {
                request.getSession().setAttribute(key.toString(), System.currentTimeMillis());
                log.info("token-key=" + key);
                log.info("token-value=" + System.currentTimeMillis());
            }
        }

        Object result = joinPoint.proceed();
        return result;
    }

    /**
     * 获取重复提交key-->duplicate_token_key+','+请求方法名
     *
     * @param joinPoint
     * @return
     */
    public String getDuplicateTokenKey(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        StringBuilder key = new StringBuilder(request.getAttribute(SystemType.USER_ID).toString());
        key.append(",").append(methodName);
        return key.toString();
    }


    //    @AfterReturning("webLog() && @annotation(token)")
    @AfterReturning("webLog()")

    public void doAfterReturning(JoinPoint joinPoint) {
        // 处理完请求，返回内容
        String uri = request.getRequestURI();
        if (request != null && request.getAttribute(SystemType.USER_ID) != null
                && (uri.toLowerCase().contains(SystemType.SAVE)
                || uri.toLowerCase().contains(SystemType.UPDATE)
                || uri.toLowerCase().contains(SystemType.REMOVE))
                && !uri.toLowerCase().contains(SystemType.SYS_OPERATOR_LOG)
        ) {
            log.info("进入重复提交验证：");
            Object[] args = joinPoint.getArgs();
            String key = getDuplicateTokenKey(joinPoint);
            Object t = request.getSession().getAttribute(key);
            if (null != t) {
                //方法执行完毕移除请求重复标记
                request.getSession(false).removeAttribute(key);
                log.info("方法执行完毕移除请求重复标记！");
            }
        }
    }

    /**
     * 异常
     *
     * @param joinPoint
     * @param e
     */
//    @AfterThrowing(pointcut = "webLog()&& @annotation(token)", throwing = "e")
    @AfterThrowing(pointcut = "webLog()", throwing = "e")
    public void doAfterThrowing(JoinPoint joinPoint, Throwable e) {
        if (e instanceof DuplicateSubmitException == false) {
            //处理处理重复提交本身之外的异常
            Object[] args = joinPoint.getArgs();
            String uri = request.getRequestURI();
            if (request != null && request.getAttribute(SystemType.USER_ID) != null
                    && (uri.toLowerCase().contains(SystemType.SAVE)
                    || uri.toLowerCase().contains(SystemType.UPDATE)
                    || uri.toLowerCase().contains(SystemType.REMOVE))
                    && !uri.toLowerCase().contains(SystemType.SYS_OPERATOR_LOG)
            ) {
                String key = getDuplicateTokenKey(joinPoint);
                Object t = request.getSession().getAttribute(key);
                if (null != t) {
                    //方法执行完毕移除请求重复标记
                    request.getSession(false).removeAttribute(key);
                    log.info("异常情况--移除请求重复标记！");
                }
            }

        }
    }
}