package com.lottery.common.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @北京联合倍全电子商务有限公司
 * @author: simazilin  @Email: fgihxq@163.com
 * @Date: 2017-11-10 14:35
 * @Project: 阳光物业V1.0
 * @Description:
 */
@Component
@ConfigurationProperties(prefix = "conf")
public class Conf {

    private String env;
    private String backendUrl;

    public String getEnv() {
        return env;
    }

    public void setEnv(String env) {
        this.env = env;
    }

    public String getBackendUrl() {
        return backendUrl;
    }

    public void setBackendUrl(String backendUrl) {
        this.backendUrl = backendUrl;
    }

}
