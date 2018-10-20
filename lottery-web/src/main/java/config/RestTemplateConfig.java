package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @Prject: bqmart-sunshine
 * @Package: cn.bqmart.config
 * @Description: TODO
 * @author: wuyujia
 * @Date: 2017/11/27 17:53
 */
@Configuration
public class RestTemplateConfig {

    // 启动的时候要注意，由于我们在controller中注入了RestTemplate，所以启动的时候需要实例化该类的一个实例
//    @Autowired
//    private RestTemplateBuilder builder;

    // 使用RestTemplateBuilder来实例化RestTemplate对象，spring默认已经注入了RestTemplateBuilder实例
//    @Bean
//    public RestTemplate restTemplate() {
//        return builder.build();
//    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
