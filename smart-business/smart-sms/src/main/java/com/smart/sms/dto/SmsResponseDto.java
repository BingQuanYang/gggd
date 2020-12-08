package com.smart.sms.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * {"RequestId":"A7AE3000-17A7-450F-ADBA-08075F16822E",
 * "Message":"OK","BizId":"884311207344181058^0","Code":"OK"}
 */
@Data
public class SmsResponseDto implements Serializable {
    @JsonProperty("RequestId")
    private String requestId;
    @JsonProperty("Message")
    private String message;
    @JsonProperty("BizId")
    private String bizId;
    @JsonProperty("Code")
    private String code;
    public static final String SMS_RESPONSE_CODE_OK = "OK";
}
