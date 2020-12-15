package com.smart.auth.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Data
@ConfigurationProperties("jwt")
public class JwtProperties {
    /**
     * 加密的秘钥
     */
    private String secret;
    /**
     * 过期时间  单位是秒
     */
    private int expire;
}
