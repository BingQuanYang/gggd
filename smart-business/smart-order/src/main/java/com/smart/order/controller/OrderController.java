package com.smart.order.controller;

import com.smart.commons.result.ResponseResult;
import com.smart.order.service.OrderService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Resource
    OrderService orderService;

    @PostMapping("/create")
    public ResponseResult<String> createOrder() throws Exception {
        return ResponseResult.success(orderService.createOrder());
    }
}
