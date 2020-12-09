package com.smart.order.service;

import com.smart.commons.exception.BizException;

public interface OrderService {
    String createOrder() throws BizException;
}
