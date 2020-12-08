package com.smart.sms.service.impl;

import cn.hutool.core.util.RandomUtil;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.smart.sms.config.AliSmsProperties;
import com.smart.sms.dto.SmsResponseDto;
import com.smart.sms.service.SmsMessageService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;


@Service
public class SmsMessageServiceImpl implements SmsMessageService {
    @Resource
    AliSmsProperties smsProperties;
    @Resource
    IAcsClient client;
    @Resource
    ObjectMapper objectMapper;


    @Override
    public void sendMsgByPhone(String phone) {
        String code = RandomUtil.randomNumbers(6);
        try {
            CommonRequest commonRequest = getCommonRequest(phone, code);
            CommonResponse commonResponse = client.getCommonResponse(commonRequest);
            String data = commonResponse.getData();
            // 转话成json数据
//            objectMapper.writeValueAsString()
            /**
             *
             */
            SmsResponseDto smsResponseDto = objectMapper.readValue(data, SmsResponseDto.class);
            if (smsResponseDto != null && SmsResponseDto.SMS_RESPONSE_CODE_OK.equals(smsResponseDto.getCode())) {
                //讲验证码存入redis中
            }
        } catch (ClientException | IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param phone
     * @param code
     * @return
     */
    private CommonRequest getCommonRequest(String phone, String code) {
        CommonRequest request = new CommonRequest();
        request.setSysMethod(MethodType.POST);
        request.setSysDomain(smsProperties.getDomain());
        request.setSysVersion(smsProperties.getVersion());
        request.setSysAction(smsProperties.getAction());
        request.putQueryParameter(AliSmsProperties.REGION_ID, smsProperties.getRegionId());
        request.putQueryParameter(AliSmsProperties.SIGN_NAME, smsProperties.getSignName());
        request.putQueryParameter(AliSmsProperties.TEMPLATE_CODE, smsProperties.getTemplateCode());
        request.putQueryParameter(AliSmsProperties.PHONE_NUMBERS, phone);
        request.putQueryParameter(AliSmsProperties.TEMPLATE_CODE, String.format("{\"code\":\"%s\"}", code));
        return request;
    }
}
