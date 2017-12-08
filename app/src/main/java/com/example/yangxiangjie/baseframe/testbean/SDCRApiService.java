package com.example.yangxiangjie.baseframe.testbean;


import com.example.yangxiangjie.baseframe.base.net.bean.BaseHttpResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Headers;

/**
 * Created by yangxiangjie on 2017/12/4.
 */

public interface SDCRApiService {

    @Headers("Cache-Control: public, max-age=100")//设置缓存 缓存时间为100s
    @GET("web/product/tags")
    Observable<BaseHttpResponse<Tags>> getTags();
}
