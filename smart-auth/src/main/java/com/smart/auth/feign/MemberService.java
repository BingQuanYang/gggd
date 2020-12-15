package com.smart.auth.feign;

import com.smart.commons.dto.MemberDto;
import com.smart.commons.result.ResponseResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "smart-member", path = "/member")
public interface MemberService {
    @GetMapping("/info")
    ResponseResult<MemberDto> getUserByUsername(@RequestParam("username") String username);
}
