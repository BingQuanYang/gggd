package com.smart.auth.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.smart.commons.result.ResponseResult;
import com.smart.commons.utils.ResponseUtils;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.smart.commons.result.ResultCodeEnum.AUTH_ERROR;

/**
 * jwt  字符串
 */
@Component
public class LoginFailHandler implements AuthenticationFailureHandler {
    @Resource
    ObjectMapper objectMapper;
    
    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        ResponseUtils.responseToJson(httpServletResponse, objectMapper, ResponseResult.error(AUTH_ERROR));
    }
}
