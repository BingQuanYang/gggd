package com.smart.lock.service.impl;

import com.smart.lock.service.LockRedisService;
import com.smart.lock.utils.RedisLockUtils;
import org.redisson.api.RLock;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;



@Service
public class LockRedisServiceImpl implements LockRedisService {
    @Resource
    RedisLockUtils redisLockUtils;

    @Override
    public boolean tryLock(String key) {
        RLock lock = redisLockUtils.getLock(key);
        try {
            return lock.tryLock(-1, -1, null);
        } catch (InterruptedException e) {
            return false;
        }
    }

    @Override
    public boolean tryLock(String... keys) {
        RLock lock = redisLockUtils.getLock(keys);
        try {
            return lock.tryLock();
        } catch (Exception e) {
            return false;
        }
    }


    @Override
    public boolean tryLock(long waitTime, long leaseTime, TimeUnit unit, String... key) {
        RLock lock = redisLockUtils.getLock(key);
        try {
            return lock.tryLock(waitTime, leaseTime, unit);
        } catch (InterruptedException e) {
            return false;
        }
    }

    @Override
    public boolean tryLock(String key, long waitTime, long leaseTime, TimeUnit unit) {
        RLock lock = redisLockUtils.getLock(key);
        try {
            return lock.tryLock(waitTime, leaseTime, unit);
        } catch (InterruptedException e) {
            return false;
        }
    }

    @Override
    public RLock lock(String key) {
        RLock lock = redisLockUtils.getLock(key);
        lock.lock();
        return lock;
    }

    @Override
    public void unlock(String key) {
        RLock lock = redisLockUtils.getLock(key);
        lock.unlock();
    }


    @Override
    public void unlock(RLock rLock) {
        rLock.unlock();
    }

}
