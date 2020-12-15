package com.smart.auth.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.smart.auth.jwt.JwtUtils;
import com.smart.commons.result.ResponseResult;
import com.smart.commons.utils.ResponseUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 功能进行拆分
 * jwt 字符串
 */
@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {
    @Resource
    JwtUtils jwtUtils;
    @Resource
    ObjectMapper objectMapper;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        User user = (User) authentication.getPrincipal();
        Map<String, Object> map = new HashMap<>();
        map.put("username", user.getUsername());
        String token = jwtUtils.createToken(map);
        ResponseUtils.responseToJson(httpServletResponse, objectMapper, ResponseResult.success(token));
    }
}
