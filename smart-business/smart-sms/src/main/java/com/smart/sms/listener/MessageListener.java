package com.smart.sms.listener;

import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

@Component
@RocketMQMessageListener(consumerGroup = "sms-consumer-group", topic = "member-sms-topic")
public class MessageListener implements RocketMQListener<String> {
    /**
     * 60
     *
     * @param s
     */
    @Override
    public void onMessage(String s) {


    }
}
