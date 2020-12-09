package com.smart.commons.handler;

import com.smart.commons.exception.BizException;
import com.smart.commons.result.ResponseResult;
import com.smart.commons.result.ResultCodeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 处理自定义的业务异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(value = BizException.class)
    public ResponseResult<Object> bizExceptionHandler(BizException e) {
        return ResponseResult.error(ResultCodeEnum.SYSTEM_ERROR);
    }

    /**
     * 未知异常错误
     *
     * @param e
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    public ResponseResult<Object> exceptionHandler(Exception e) {
        log.error(e.getMessage());
        return ResponseResult.error(ResultCodeEnum.ERROR);
    }

    /**
     * 处理参数验证的错误
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseResult<Object> bindException(MethodArgumentNotValidException ex) {
        BindingResult bindingResult = ex.getBindingResult();
        // 获取所有的错误信息
        List<ObjectError> allErrors = bindingResult.getAllErrors();
        // 输出
        allErrors.forEach(e -> log.error(e.getDefaultMessage()));
        return ResponseResult.error(ResultCodeEnum.PARAMS_IS_INVALID, allErrors.get(0).getDefaultMessage());
    }
}