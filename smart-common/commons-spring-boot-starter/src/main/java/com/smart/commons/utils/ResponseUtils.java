package com.smart.commons.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.smart.commons.result.ResponseResult;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ResponseUtils {
    public static void responseToJson(HttpServletResponse response, ObjectMapper objectMapper, ResponseResult<Object> result) throws IOException {
        response.setContentType("application/json; charset=utf-8");
        response.getWriter().print(objectMapper.writeValueAsString(result));
    }
}
