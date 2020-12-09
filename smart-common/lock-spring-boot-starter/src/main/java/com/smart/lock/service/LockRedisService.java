package com.smart.lock.service;


import org.redisson.api.RLock;

import java.util.concurrent.TimeUnit;

public interface LockRedisService {
    /**
     * 获取锁
     */
    boolean tryLock(String key);

    boolean tryLock(String... key);

    boolean tryLock(long waitTime, long leaseTime, TimeUnit unit, String... key);

    /**
     * 获取锁
     *
     * @param key       锁的名称
     * @param waitTime  等待超时时间
     * @param leaseTime 自动释放锁的时间
     * @param unit      时间单位
     * @return
     */
    boolean tryLock(String key, long waitTime, long leaseTime, TimeUnit unit);

    /**
     * 加锁
     */
    RLock lock(String key);

    void unlock(RLock rLock);

    void unlock(String key);

/**
 * 释放锁
 */

}
