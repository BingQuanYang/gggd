package com.smart.lock.utils;

import org.redisson.Redisson;
import org.redisson.RedissonRedLock;
import org.redisson.api.RLock;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class RedisLockUtils {
    @Resource
    Redisson redisson;

    public RLock getLock(String key) {
        RLock lock = redisson.getLock(key);
        return new RedissonRedLock(lock);
    }



    public RLock getLock(String... keys) {
        RLock[] redissonRedLocks = new RedissonRedLock[keys.length];
        for (int i = 0; i < keys.length; i++) {
            String key = keys[i];
            redissonRedLocks[i] = redisson.getLock(key);
        }
        return new RedissonRedLock(redissonRedLocks);
    }

}
