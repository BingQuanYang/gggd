package com.smart.sms.listener;

import com.smart.sms.service.SmsMessageService;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
@RocketMQMessageListener(consumerGroup = "sms-consumer-group", topic = "member-sms-topic")
public class MessageListener implements RocketMQListener<String> {
    @Resource
    SmsMessageService smsMessageService;

    /**
     * @param phone
     */
    @Override
    public void onMessage(String phone) {
        smsMessageService.sendMsgByPhone(phone);
    }
}
