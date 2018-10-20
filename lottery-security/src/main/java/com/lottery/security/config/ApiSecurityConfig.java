package com.lottery.security.config;

import com.lottery.common.util.RedisUtil;
import com.lottery.security.ApiSecurityService;
import com.lottery.security.intercepter.ApiSecurityInterceptor;
import com.lottery.security.intercepter.ResubmitInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @Prject: lottery
 * @Package: cn.lottery.security.annotation
 * @Description: TODO
 * @Date: 2018-10-20
 */
@Configuration
public class ApiSecurityConfig extends WebMvcConfigurerAdapter {

    @Autowired
    private ApiSecurityService apiSecurityService;
    @Autowired
    private RedisUtil redisUtil;

    /**
     * 拦截请求
     *
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new ApiSecurityInterceptor(apiSecurityService, redisUtil))
                .addPathPatterns("/**")
                .excludePathPatterns("/api/sun/member/out")
                .excludePathPatterns("/api/web/**")
                .excludePathPatterns("/api/backend/**")
                .excludePathPatterns("/api/sun/member/login")
                .excludePathPatterns("/api/sun/common/qiniuyun/img/upload");
        registry.addInterceptor(new ResubmitInterceptor(redisUtil))
                .addPathPatterns("/api/**");
    }

    /**
     * 跨域配置
     *
     * @param registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                // TODO 这里跨域最好配置域名 simazilin
                .allowedOrigins("*")
                .maxAge(3600)
                .allowCredentials(true)
                .allowedMethods("GET", "POST", "OPTIONS");
    }
}
