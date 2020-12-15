package com.smart.auth.config;

import com.smart.auth.handler.LoginSuccessHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.Resource;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Resource
    PasswordEncoder passwordEncoder;
    @Resource
    UserDetailsService userDetailsService;

    @Resource
    LoginSuccessHandler loginSuccessHandler;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder);
        /**
         * 1密码加密
         * 2.从数据库中认证
         */
    }

    /**
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        /*
         * 1.登录配置
         */
        http.formLogin()
                .loginPage("/login")
                .successHandler(loginSuccessHandler)
                .permitAll();
        /*
         * 2.登出配置
         */

        http.logout().logoutUrl("/logout")
                .logoutSuccessHandler((request, response, auth) -> {
                })
                .permitAll();
        /*
         * 授权过滤配置
         */

        http.antMatcher("/index")
                .antMatcher("/login")
                .antMatcher("/register")
                .antMatcher("/product/**")
                .anonymous()
                .and()
                //其他链接必须通过认证之后才能方法
                .antMatcher("/**")
                .authorizeRequests()
                .and()
                // 禁用缓存
                .headers().cacheControl().disable()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .csrf().disable();
        http.cors().disable();

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

}

