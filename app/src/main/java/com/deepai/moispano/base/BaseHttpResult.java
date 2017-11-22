package com.deepai.moispano.base;

/**
 * @author ZhaoZaigang
 * @Description 抽取的一个基类的bean, 直接在泛型中传data就行
 * @date 2017/10/25  17:06
 */

public class BaseHttpResult <T> {
    private int code; // 状态码
    private String message; // 信息
    private T data; // 数据

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "BaseHttpResult{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}