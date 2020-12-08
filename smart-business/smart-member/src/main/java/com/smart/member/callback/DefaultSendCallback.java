package com.smart.member.callback;

import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;

public class DefaultSendCallback implements SendCallback {
    @Override
    public void onSuccess(SendResult sendResult) {

    }

    @Override
    public void onException(Throwable throwable) {

    }
}
