package com.smart.member.service.impl;

import com.smart.member.callback.DefaultSendCallback;
import com.smart.member.service.MemberService;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 采用异步方式
 */
@Service
public class MemberServiceImpl implements MemberService {
    @Resource
    RocketMQTemplate mqTemplate;

    /**
     * 接受到手机号
     * 生成验证码  6位 保存  60秒自动删除  redis 过期时间
     * 调用第三方的短信服务
     *
     * @param phone
     * @return
     */
    @Override
    public String getVerifyCode(String phone) {
        mqTemplate.asyncSend("member-sms-topic", phone, new DefaultSendCallback());
        return "success";
    }
}
