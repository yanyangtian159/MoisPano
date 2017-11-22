package com.deepai.moispano.data.exception;

/**
 * @author ZhaoZaigang
 * @Description 包装与服务器交互的所有异常
 * @date 2017/6/19  18:41
 */

public class ApiException extends RuntimeException {

    public int code;
    public String message;

    public ApiException(Throwable throwable, int code) {
        super(throwable);
        this.code = code;
    }
}
