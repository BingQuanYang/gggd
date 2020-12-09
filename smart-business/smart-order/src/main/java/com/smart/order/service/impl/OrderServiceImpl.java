package com.smart.order.service.impl;

import com.smart.commons.exception.BizException;
import com.smart.commons.result.ResultCodeEnum;
import com.smart.db.entity.Store;
import com.smart.db.mapper.StoreMapper;
import com.smart.lock.service.LockRedisService;
import com.smart.order.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.redisson.Redisson;
import org.redisson.RedissonRedLock;
import org.redisson.api.RLock;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {
    @Resource
    LockRedisService lockRedisService;
    @Resource
    StoreMapper storeMapper;
    @Resource
    Redisson redisson;
    private static final String LOCK_ORDER_KEY = "lock:order";

    @Override
    public String createOrder() throws BizException {
        log.info("start:===============+>");
        RLock lock = redisson.getLock(LOCK_ORDER_KEY);
        RedissonRedLock redissonRedLock = new RedissonRedLock(lock);
        boolean b = redissonRedLock.tryLock();
        log.info(b + "获取到锁");
        if (b) {
            try {
                redissonRedLock.lock();
                Store store = storeMapper.selectById(1);
                store.setQuantity(store.getQuantity() - 1);
                log.info(store.getQuantity() + "");
                storeMapper.updateById(store);
            } catch (Exception e) {
                throw new BizException(ResultCodeEnum.SYSTEM_ERROR);
            } finally {
                redissonRedLock.unlock();
                log.info("释放锁");
            }
            return "success";
        } else {
            throw new BizException(ResultCodeEnum.SYSTEM_ERROR);
        }
    }
}
