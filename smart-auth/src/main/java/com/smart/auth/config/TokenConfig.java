package com.smart.auth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

import java.security.KeyPair;
import java.util.Collections;

/**
 * 所有tokenStore配置信息
 * JwtTokenStore()
 * RedisTokenStore()
 *
 *  熟练使用
 *
 */
@Configuration
public class TokenConfig {
    /**
     * 内置加密   RAS   非对称加密 加密  "HS256"  秘钥
     *
     * @param jwtAccessTokenConverter
     * @return
     */
    @Bean
    public TokenStore tokenStore(JwtAccessTokenConverter jwtAccessTokenConverter) {
        return new JwtTokenStore(jwtAccessTokenConverter);
    }

    /**
     * jwt
     * payload
     * JwtAccessTokenConverter
     *
     * @return
     */
    @Bean
    @Primary
    public JwtAccessTokenConverter accessTokenConverter(KeyPair keyPair) {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        //jks 非对称加密  公钥 私钥
        converter.setKeyPair(keyPair);
        //  对称加密设置秘钥
//        converter.setSigningKey("123456");
        return converter;
    }

    /**
     * xxx.jks
     * keytool -genkey -alias test（别名）
     * -keypass 123123（私钥密码）
     * -keyalg RSA（算法）
     * -sigalg sha256withrsa（算法小类）
     * -keysize 1024（密钥长度）
     * -validity 365（有效期）
     * -keystore d:/test.jks（生成路径）
     * -storepass 123123（主密码）
     * keytool -genkey -alias smart  -keypass 123456   -keyalg RSA  -keystore smart.jks  -storepass 123456
     *
     * @return
     */
    @Bean
    public KeyPair keyPair() {
        Resource resource = new ClassPathResource("classpath:smart.jks");
        KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(resource, "123456".toCharArray());
        //-alias的名字  -keypass 123456
        KeyPair keyPair = keyStoreKeyFactory.getKeyPair("smart", "123456".toCharArray());
        return keyPair;
    }

    /**
     * jwt内容增加
     */
    @Bean
    public TokenEnhancer tokenEnhancer(JwtAccessTokenConverter accessTokenConverter) {
        TokenEnhancerChain tokenEnhancer = new TokenEnhancerChain();
        tokenEnhancer.setTokenEnhancers(Collections.singletonList(accessTokenConverter));
        return tokenEnhancer;
    }

}
