package com.smart.order;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 网关
 * 分支
 * 配置中心
 * 认证
 */
@SpringBootApplication(scanBasePackages = {"com.smart"})
public class OrderApp {
    public static void main(String[] args) {
        SpringApplication.run(OrderApp.class, args);
    }
}
