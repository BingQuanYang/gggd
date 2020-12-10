package com.smart.order.service.impl;

import com.smart.commons.exception.BizException;
import com.smart.commons.result.ResultCodeEnum;
import com.smart.db.entity.Store;
import com.smart.db.mapper.StoreMapper;
import com.smart.order.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {
    @Resource
    StoreMapper storeMapper;

    @Override
//    @DistributedLock(prefix = "lock", suffix = "order")
    public String createOrder() throws BizException {
        try {
            Store store = storeMapper.selectById(1);
            store.setQuantity(store.getQuantity() - 1);
            log.info(store.getQuantity() + "");
            storeMapper.updateById(store);
        } catch (Exception e) {
            throw new BizException(ResultCodeEnum.SYSTEM_ERROR);
        }
        return "success";
    }
}
