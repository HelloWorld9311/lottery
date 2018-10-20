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
public @interface AccessSource {

    String[] value();
}
