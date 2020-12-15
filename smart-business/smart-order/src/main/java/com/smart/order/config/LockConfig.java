package com.smart.order.config;

import com.smart.lock.aop.DistributedLockAop;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LockConfig {
    @Bean
    public DistributedLockAop distributedLockAop() {
        return new DistributedLockAop();
    }

}
