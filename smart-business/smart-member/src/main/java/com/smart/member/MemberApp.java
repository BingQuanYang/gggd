package com.smart.member;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 文件服务
 * 权限 认证(登录)  操作权限  资源权限-- 菜单权限 --  界面元素 审核  批准   url权限
 * 需要用户ID必须要认证
 * 设计权限系统
 * spring boot + shiro
 * spring cloud security oath2认证
 */
@EnableDiscoveryClient
@EnableFeignClients
@SpringBootApplication(scanBasePackages = {"com.smart"})
public class MemberApp {
    public static void main(String[] args) {
        SpringApplication.run(MemberApp.class, args);
    }
}
