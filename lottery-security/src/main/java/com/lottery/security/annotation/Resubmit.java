package com.lottery.security.annotation;

import java.lang.annotation.*;

/**
 * @Prject: lottery
 * @Package: cn.lottery.security.annotation
 * @Description: TODO
 * @Date: 2018-10-20
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Resubmit {

    /**
     * 防止重复提交的参数设定, 默认值10s
     * 参数以秒为单位
     * @return
     */
    long value() default 10;
}
