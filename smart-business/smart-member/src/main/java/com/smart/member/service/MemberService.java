package com.smart.member.service;

import com.smart.commons.result.ResponseResult;
import com.smart.member.dto.MemberDto;

public interface MemberService {
    String getVerifyCode(String phone);

    String login(String phone, String code);

    ResponseResult<MemberDto> findMemeberByName(String username);
}
