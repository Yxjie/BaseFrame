package com.example.yangxiangjie.baseframe.base.net.bean;

/**
 * Created by yangxiangjie on 2017/10/30.
 * 数据回来数据结构
 */

public class BaseHttpResponse<T> {

    private int code;
    private String message;
    private T data;

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
}
