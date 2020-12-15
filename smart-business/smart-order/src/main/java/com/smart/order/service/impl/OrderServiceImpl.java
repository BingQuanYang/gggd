package com.smart.order.service.impl;

import com.smart.commons.exception.BizException;
import com.smart.commons.result.ResultCodeEnum;
import com.smart.db.mapper.StoreMapper;
import com.smart.lock.annoation.DistributedLock;
import com.smart.order.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {
    @Resource
    StoreMapper storeMapper;
    private int num = 100;

    @Override
    @DistributedLock(prefix = "lock", suffix = "order", leaseTime = 100)
    public String createOrder() throws BizException {
        try {
            if (num > 0){
                num -= 1;
                log.info(num + "");
            }
        } catch (Exception e) {
            throw new BizException(ResultCodeEnum.SYSTEM_ERROR);
        }
        return "success";
    }
}
