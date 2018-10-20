package config;

import com.lottery.common.config.Conf;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;


/**
 * @北京联合倍全电子商务有限公司
 * @author: simazilin  @Email: fgihxq@163.com
 * @Date: 2017-11-10 15:25
 * @Project: 阳光物业V1.0
 * @Description:
 */
@Configuration
public class RedisTemplateConfig {
    public static final Logger logger = LoggerFactory.getLogger(RedisTemplateConfig.class);

    @Autowired
    private Conf conf;
    /**
     * 配置文件中设置了password，那么就必须在配置类中手工注入JedisConnectionFactory中，否则会在启动过程中报NOAUTH Authentication required.;：
     * @param redisProperties
     * @return
     */
    @Bean
    public JedisConnectionFactory jedisConnectionFactory(RedisProperties redisProperties) {
        //测试环境没有redis密码，这里不要添加
        String env="test";
        JedisConnectionFactory factory = new JedisConnectionFactory();
        if(!conf.getEnv().equals(env)){
            factory.setPassword(redisProperties.getPassword());
            factory.setHostName(redisProperties.getHost());
            factory.setPort(redisProperties.getPort());
        }
        logger.debug("env-------"+conf.getEnv());
        return factory;
    }

    @Bean
    public RedisTemplate redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate redisTemplate = new RedisTemplate();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        return redisTemplate;
    }
}
