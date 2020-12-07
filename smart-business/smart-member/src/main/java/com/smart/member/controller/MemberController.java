package com.smart.member.controller;

import com.smart.member.service.MemberService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/memeber")
public class MemberController {
    @Resource
    MemberService memberService;

    /**
     * 手机号校验
     *
     * @param phone
     * @return
     */
    public String getVerifyCode(String phone) {
        String verifyCode = memberService.getVerifyCode(phone);
        return verifyCode;
    }

    /**
     * @param phone
     * @param code
     * @return
     */
    public String login(String phone, String code) {

        return "";
    }
}

