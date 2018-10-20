package config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import web.intercepter.LogInterceptor;

/**
 * 功能 :
 *
 * @author : Bruce(刘正航) 下午3:51 2018/3/30
 */
@Configuration
public class InterceptorConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LogInterceptor());
        super.addInterceptors(registry);
    }
}
