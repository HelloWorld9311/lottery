package com.lottery.common.config.pay;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @北京联合倍全电子商务有限公司
 * @author: simazilin  @Email: fgihxq@163.com
 * @Date: 2018-01-03 15:33
 * @Project:
 * @Description:
 */
@Component
@ConfigurationProperties(prefix = "conf.yg")
public class YgConf {
    /**
     * 微信支付配置
     */
    private String wechatAppId;
    private String wechatPaykey;
    private String wechatPartner;
    private String wechatNotifyUrl;

    /**
     * 支付宝新版支付配置
     */
    private String aliAppId;
    private String aliAppRsaPrivateKey;
    private String aliPubKey;
    private String aliPayNotifyUrl;

    public String getWechatAppId() {
        return wechatAppId;
    }

    public void setWechatAppId(String wechatAppId) {
        this.wechatAppId = wechatAppId;
    }

    public String getWechatPaykey() {
        return wechatPaykey;
    }

    public void setWechatPaykey(String wechatPaykey) {
        this.wechatPaykey = wechatPaykey;
    }

    public String getWechatPartner() {
        return wechatPartner;
    }

    public void setWechatPartner(String wechatPartner) {
        this.wechatPartner = wechatPartner;
    }

    public String getWechatNotifyUrl() {
        return wechatNotifyUrl;
    }

    public void setWechatNotifyUrl(String wechatNotifyUrl) {
        this.wechatNotifyUrl = wechatNotifyUrl;
    }

    public String getAliAppId() {
        return aliAppId;
    }

    public void setAliAppId(String aliAppId) {
        this.aliAppId = aliAppId;
    }

    public String getAliAppRsaPrivateKey() {
        return aliAppRsaPrivateKey;
    }

    public void setAliAppRsaPrivateKey(String aliAppRsaPrivateKey) {
        this.aliAppRsaPrivateKey = aliAppRsaPrivateKey;
    }

    public String getAliPubKey() {
        return aliPubKey;
    }

    public void setAliPubKey(String aliPubKey) {
        this.aliPubKey = aliPubKey;
    }

    public String getAliPayNotifyUrl() {
        return aliPayNotifyUrl;
    }

    public void setAliPayNotifyUrl(String aliPayNotifyUrl) {
        this.aliPayNotifyUrl = aliPayNotifyUrl;
    }
}
