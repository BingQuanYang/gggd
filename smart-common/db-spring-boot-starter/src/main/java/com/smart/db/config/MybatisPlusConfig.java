package com.smart.db.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * 通配符
 */
@Configuration
@MapperScan("com.smart.**.mapper")
public class MybatisPlusConfig {
    @Bean
    @Primary
    public PaginationInterceptor interceptor() {
        PaginationInterceptor interceptor = new PaginationInterceptor();
        interceptor.setLimit(50L);
        // 超过最大页数进行智能转化
        interceptor.setOverflow(true);
        return interceptor;
    }
}
