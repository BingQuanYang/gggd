package com.smart.sms.service.impl;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.smart.sms.config.AliSmsProperties;
import com.smart.sms.service.SmsMessageService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;

@Service
public class SmsMessageServiceImpl implements SmsMessageService {
    @Resource
    AliSmsProperties smsProperties;
    @Resource
    IAcsClient client;
    HashMap<String, String> hashMap = new HashMap<>();

    @Override
    public void sendMsgByPhone(String phone) {
        String code = "123456";
        try {
            CommonRequest commonRequest = getCommonRequest(phone, code);
            CommonResponse commonResponse = client.getCommonResponse(commonRequest);
            if (commonResponse.getData() != null) {
                hashMap.put(phone, code);
            }
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }


    private CommonRequest getCommonRequest(String phone, String code) {
        CommonRequest request = new CommonRequest();
        request.setSysMethod(MethodType.POST);
        request.setSysDomain(smsProperties.getDomain());
        request.setSysVersion(smsProperties.getVersion());
        request.setSysAction(smsProperties.getAction());
        request.putQueryParameter("RegionId", smsProperties.getRegionId());
        request.putQueryParameter("SignName", smsProperties.getSignName());
        request.putQueryParameter("TemplateCode", smsProperties.getTemplateCode());
        request.putQueryParameter("PhoneNumbers", phone);
        request.putQueryParameter("TemplateParam", String.format("{\"code\":\"+{}+\"}", code));
        return request;
    }
}
