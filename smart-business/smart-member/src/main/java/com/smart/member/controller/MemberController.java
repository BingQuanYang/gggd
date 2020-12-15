package com.smart.member.controller;

import com.smart.commons.result.ResponseResult;
import com.smart.member.dto.MemberDto;
import com.smart.member.feign.AuthService;
import com.smart.member.service.MemberService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/member")
public class MemberController {
    @Resource
    MemberService memberService;
    @Resource
    AuthService authService;

    /**
     * 手机号校验
     *
     * @param phone
     * @return
     */
    @GetMapping("/code")
    public String getVerifyCode(String phone) {
        String verifyCode = memberService.getVerifyCode(phone);
        return verifyCode;
    }

    /**
     * @param username
     * @param password
     * @return
     */
    @PostMapping("/login")
    public ResponseResult<String> login(String username, String password) {
        ResponseResult<String> result = authService.login(username, password);
        return result;
    }
    
    /**
     * @param username
     * @return
     */
    @GetMapping("/info")
    public ResponseResult<MemberDto> getUserByUsername(String username) {
        return memberService.findMemeberByName(username);
    }

}

