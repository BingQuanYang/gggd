package com.smart.sms.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties("ali.sms")
public class AliSmsProperties {
    /**
     * 后台获取
     */
    private String accessKeyId;
    /**
     * 后台获取
     */
    private String accessKeySecret;
    /**
     * 固定值
     */
    private String regionId;
    /**
     * 固定值
     */
    private String domain;
    /**
     * 版本号 固定值
     */
    private String version;
    /**
     * 签名 根据创建的签名来设置
     */
    private String signName;
    /**
     * 在后台添加模板时阿里云后台生产
     */
    private String templateCode;

    private String action;


}
