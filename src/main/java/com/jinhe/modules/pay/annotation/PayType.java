package com.jinhe.modules.pay.annotation;


import java.lang.annotation.*;

/**
 * 系统日志注解
 */
@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface PayType {
	String value() default "";
}
