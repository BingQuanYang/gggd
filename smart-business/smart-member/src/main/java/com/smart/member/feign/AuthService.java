package com.smart.member.feign;

import com.smart.commons.result.ResponseResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

// http://xxxx:8818/login
@FeignClient(value = "smart-auth")
public interface AuthService {

    @PostMapping("/login")
    ResponseResult<String> login(@RequestParam("username") String username, @RequestParam("password") String password);

}
