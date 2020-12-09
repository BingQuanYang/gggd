package com.smart.commons.result;

import lombok.Data;

/**
 * 返回结果封装类
 */
@Data
public class ResponseResult<T> {

    /**
     * 返回码
     */
    private int status;

    /**
     * 返回说明
     */
    private String msg;

    /**
     * 提示
     */
    private String tips;

    /**
     * 返回数据
     */
    private T data;


    public ResponseResult() {
    }

    public ResponseResult(T data) {
        this();
        this.data = data;
    }

    public ResponseResult(ResultCodeEnum code, T info) {
        this.status = code.status;
        this.msg = code.msg;
        this.tips = code.tips;
        this.data = info;
    }

    public ResponseResult(ResultCodeEnum code) {
        this.status = code.status;
        this.msg = code.msg;
        this.tips = code.tips;
    }

    /**
     * @param data
     * @param <T>
     * @return
     */
    public static <T> ResponseResult<T> success(T data) {
        return success(ResultCodeEnum.SUCCESS, data);
    }

    /**
     * @param codeEnum
     * @param data
     * @param <T>
     * @return
     */
    public static <T> ResponseResult<T> success(ResultCodeEnum codeEnum, T data) {
        return new ResponseResult<>(codeEnum, data);
    }

    public static <T> ResponseResult<T> error() {
        return new ResponseResult<T>(ResultCodeEnum.ERROR);
    }


    public static <T> ResponseResult<T> error(ResultCodeEnum codeEnum) {
        return error(codeEnum, null);
    }

    public static <T> ResponseResult<T> error(ResultCodeEnum codeEnum, T data) {
        return new ResponseResult<>(codeEnum, data);
    }

    public static <T> ResponseResult<T> error(int retCode, String retMsg) {
        ResponseResult<T> responseResult = new ResponseResult<>();
        responseResult.setStatus(retCode);
        responseResult.setMsg(retMsg);
        return responseResult;
    }
}
