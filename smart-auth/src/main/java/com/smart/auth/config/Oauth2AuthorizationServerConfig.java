package com.smart.auth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.builders.JdbcClientDetailsServiceBuilder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenStore;

import javax.annotation.Resource;
import javax.sql.DataSource;

@Configuration
//  表示开启oauth2认证服务
@EnableAuthorizationServer
public class Oauth2AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
    /**
     * 客服端配置信息
     *
     * @param clients
     * @throws Exception
     */


    @Resource
    PasswordEncoder passwordEncoder;

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        //  客服端的配置信息

//        clients.inMemory()
//                // app   web  admin     腾讯 app web应用   小程序  应用程序的名称   公司的信息   appId
//                .withClient("app")
//                .and()
//                .withClient("admin")
//                .and().withClient("web")
//                // 使用密码方式 授权码方式
//                .authorizedGrantTypes("authorization_code", "password")
//                .secret("12313132131")
//                //权限的配置
//                .scopes("all")
//                // 重定向url地址
//                .redirectUris("http://xxxx:8080/auth/callback")
//                //单位是秒
//                .accessTokenValiditySeconds((int) Duration.ofDays(2).getSeconds())
//                .refreshTokenValiditySeconds((int) TimeUnit.DAYS.toSeconds(3));
//        //   采用数据库模式  扩展性
        clients.withClientDetails(clientDetailsService);

    }

    @Resource
    DataSource dataSource;
    @Resource
    ClientDetailsService clientDetailsService;


    @Bean
    public ClientDetailsService clientDetailsService() throws Exception {
        return new JdbcClientDetailsServiceBuilder()
                .dataSource(dataSource)
                .passwordEncoder(passwordEncoder).build();
    }

    /**
     * 授权类型选择不同的Service
     *
     * @param endpoints
     * @throws Exception
     */

    @Resource
    AuthenticationManager authenticationManager;
    @Resource
    AuthorizationCodeServices authorizationCodeServices;

    @Resource
    TokenStore tokenStore;
    @Resource
    TokenEnhancer tokenEnhancer;

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints
                //授权模式使用授权码配置
                .authorizationCodeServices(authorizationCodeServices)
                // 1.token相关的配置信息
                .tokenStore(tokenStore)
                // 支持刷新
                .reuseRefreshTokens(true)
                // 2. 对jwt的内容增加
                .tokenEnhancer(tokenEnhancer)        ;


        //授权码模式设置 密码模式专用
//                .authenticationManager(authenticationManager).;
        //授权码模式设置
    }

    /**
     * 内部端点
     *
     * @param security
     * @throws Exception
     */
//    @Override
//    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
//
//    }
}
