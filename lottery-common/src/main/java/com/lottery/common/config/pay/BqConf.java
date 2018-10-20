package com.lottery.common.config.pay;

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
@ConfigurationProperties(prefix = "conf.bq")
public class BqConf {
    /**
     * 微信支付配置
     */
    private String iosWechatAppId;
    private String androidWechatAppId;
    private String miniProWechatAppId;
    private String iosWechatPaykey;
    private String androidWechatPaykey;
    private String miniProWechatPayKey;
    private String iosWechatPartner;
    private String androidWechatPartner;
    private String miniProWechatPartner;
    private String wechatNotifyUrl;

    //支付宝支付配置
    /** 合作伙伴身份（PID）*/
    private String aliPartner;
    /** 支付宝公钥 */
    private String aliPubKey;
    /** 商户自己生成的私钥 */
    private String aliAppRsaPrivateKey;
    private String aliPayNotifyUrl;

    public String getIosWechatAppId() {
        return iosWechatAppId;
    }

    public void setIosWechatAppId(String iosWechatAppId) {
        this.iosWechatAppId = iosWechatAppId;
    }

    public String getAndroidWechatAppId() {
        return androidWechatAppId;
    }

    public void setAndroidWechatAppId(String androidWechatAppId) {
        this.androidWechatAppId = androidWechatAppId;
    }

    public String getMiniProWechatAppId() {
        return miniProWechatAppId;
    }

    public void setMiniProWechatAppId(String miniProWechatAppId) {
        this.miniProWechatAppId = miniProWechatAppId;
    }

    public String getIosWechatPaykey() {
        return iosWechatPaykey;
    }

    public void setIosWechatPaykey(String iosWechatPaykey) {
        this.iosWechatPaykey = iosWechatPaykey;
    }

    public String getAndroidWechatPaykey() {
        return androidWechatPaykey;
    }

    public void setAndroidWechatPaykey(String androidWechatPaykey) {
        this.androidWechatPaykey = androidWechatPaykey;
    }

    public String getMiniProWechatPayKey() {
        return miniProWechatPayKey;
    }

    public void setMiniProWechatPayKey(String miniProWechatPayKey) {
        this.miniProWechatPayKey = miniProWechatPayKey;
    }

    public String getIosWechatPartner() {
        return iosWechatPartner;
    }

    public void setIosWechatPartner(String iosWechatPartner) {
        this.iosWechatPartner = iosWechatPartner;
    }

    public String getAndroidWechatPartner() {
        return androidWechatPartner;
    }

    public void setAndroidWechatPartner(String androidWechatPartner) {
        this.androidWechatPartner = androidWechatPartner;
    }

    public String getMiniProWechatPartner() {
        return miniProWechatPartner;
    }

    public void setMiniProWechatPartner(String miniProWechatPartner) {
        this.miniProWechatPartner = miniProWechatPartner;
    }

    public String getWechatNotifyUrl() {
        return wechatNotifyUrl;
    }

    public void setWechatNotifyUrl(String wechatNotifyUrl) {
        this.wechatNotifyUrl = wechatNotifyUrl;
    }

    public String getAliPartner() {
        return aliPartner;
    }

    public void setAliPartner(String aliPartner) {
        this.aliPartner = aliPartner;
    }

    public String getAliPubKey() {
        return aliPubKey;
    }

    public void setAliPubKey(String aliPubKey) {
        this.aliPubKey = aliPubKey;
    }

    public String getAliAppRsaPrivateKey() {
        return aliAppRsaPrivateKey;
    }

    public void setAliAppRsaPrivateKey(String aliAppRsaPrivateKey) {
        this.aliAppRsaPrivateKey = aliAppRsaPrivateKey;
    }

    public String getAliPayNotifyUrl() {
        return aliPayNotifyUrl;
    }

    public void setAliPayNotifyUrl(String aliPayNotifyUrl) {
        this.aliPayNotifyUrl = aliPayNotifyUrl;
    }
}
