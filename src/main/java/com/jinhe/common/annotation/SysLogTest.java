package com.jinhe.common.annotation;

import java.lang.annotation.*;

/**
 * 系统日志注解
 */
@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SysLogTest {
	String value() default "";
}
