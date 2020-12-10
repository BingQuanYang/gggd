package com.smart.order.config;

import com.smart.lock.aop.DistributedLockAop;
import org.redisson.Redisson;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LockConfig {
    @Bean
    @ConditionalOnBean(Redisson.class)
    public DistributedLockAop distributedLockAop() {
        return new DistributedLockAop();
    }

}
