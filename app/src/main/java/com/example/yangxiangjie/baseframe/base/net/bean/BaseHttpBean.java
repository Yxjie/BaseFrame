package com.example.yangxiangjie.baseframe.base.net.bean;

/**
 * Created by yangxiangjie on 2017/10/30.
 * 网络基本的请求库
 */

public class BaseHttpBean<T> {

    private String code;
    private String message;
    private T data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
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
