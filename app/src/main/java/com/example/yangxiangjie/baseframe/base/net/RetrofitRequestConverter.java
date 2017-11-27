package com.example.yangxiangjie.baseframe.base.net;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Converter;

/**
 * Created by yangxiangjie on 2017/10/25.
 */

public class RetrofitRequestConverter<T> implements Converter<T, RequestBody> {


    private static final MediaType MEDIA_TYPE = MediaType.parse("application/json; charset=UTF-8");
    private final Gson gson;
    private final TypeAdapter<T> adapter;

    /**
     * 构造器
     */

    public RetrofitRequestConverter(Gson gson, TypeAdapter<T> adapter) {
        this.gson = gson;
        this.adapter = adapter;
    }

    @Override
    public RequestBody convert(T value) throws IOException {
        return null;
    }


//    @Override
//    public RequestBody convert(T value) throws IOException {

//        Log.e("数据", "转换前：" + value.toString());
//        Log.e("数据", "转化后：" + Base64_Utils.encryptBASE64(value.toString()));
//        return RequestBody.create(MEDIA_TYPE,
//                Base64_Utils.encryptBASE64(value.toString()).substring(0,Base64_Utils.encryptBASE64(value.toString()).length()-3));
//    }
}
