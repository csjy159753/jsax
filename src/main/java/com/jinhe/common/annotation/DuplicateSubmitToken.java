package com.jinhe.common.annotation;

import java.lang.annotation.*;

/**
 * @author Administrator
 * @description 防止表单重复提交注解
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface DuplicateSubmitToken {

    /**
     * 保存重复提交标记 默认为需要保存
     * @return
     */
    boolean save() default true;
}