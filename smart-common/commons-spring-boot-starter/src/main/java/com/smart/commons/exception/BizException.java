package com.smart.commons.exception;

import com.smart.commons.result.ResultCodeEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class BizException extends Exception {
    /**
     * 错误码
     */
    private String status;
    /**
     * 错误信息
     */
    private String msg;

    public BizException(String status, String msg) {
        super(msg);
        this.status = status;
    }

    public BizException(ResultCodeEnum resultCodeEnum) {
        super(resultCodeEnum.getMsg());
        this.status = status;
    }
}
