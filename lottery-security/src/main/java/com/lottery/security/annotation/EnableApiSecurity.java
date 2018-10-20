package com.lottery.security.annotation;

import com.lottery.security.config.ApiSecurityConfig;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @Prject: lottery
 * @Package: cn.lottery.security.annotation
 * @Description: TODO
 * @Date: 2018-10-20
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import({ApiSecurityConfig.class})
public @interface EnableApiSecurity {

}
