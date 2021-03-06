package com.example.yangxiangjie.baseframe.base.net.interceptor;

import android.util.Log;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by yangxiangjie on 2017/10/25.
 * 向请求头里添加公共参数
 */

public class HttpHeadInterceptor implements Interceptor {

    private Map<String, String> mHeaderParamsMap = new HashMap<>();

    public HttpHeadInterceptor() {
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Log.d("HttpHeadInterceptor", "add common params");
        Request oldRequest = chain.request();
        // 新的请求
        Request.Builder requestBuilder = oldRequest.newBuilder();
        requestBuilder.method(oldRequest.method(), oldRequest.body());
        //添加公共参数,添加到header中
        if (mHeaderParamsMap.size() > 0) {
            for (Map.Entry<String, String> params : mHeaderParamsMap.entrySet()) {
                requestBuilder.header(params.getKey(), params.getValue());
            }
        }

        Request newRequest = requestBuilder.build();

        return chain.proceed(newRequest);
    }

    public static class Builder {
        HttpHeadInterceptor mHttpHeadInterceptor;

        public Builder() {
            mHttpHeadInterceptor = new HttpHeadInterceptor();
        }

        public Builder addHeaderParams(String key, String value) {
            mHttpHeadInterceptor.mHeaderParamsMap.put(key, value);
            return this;
        }

        public Builder addHeaderParams(String key, int value) {
            return addHeaderParams(key, String.valueOf(value));
        }

        public Builder addHeaderParams(String key, float value) {
            return addHeaderParams(key, String.valueOf(value));
        }

        public Builder addHeaderParams(String key, long value) {
            return addHeaderParams(key, String.valueOf(value));
        }

        public Builder addHeaderParams(String key, double value) {
            return addHeaderParams(key, String.valueOf(value));
        }


        public HttpHeadInterceptor build() {
            return mHttpHeadInterceptor;
        }
    }
}
