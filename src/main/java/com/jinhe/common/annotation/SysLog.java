package com.jinhe.common.annotation;

import java.lang.annotation.*;

/**
 * 系统日志注解
 * @author Administrator
 */
@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SysLog {
	String value() default "";
}
